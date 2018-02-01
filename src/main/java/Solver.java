import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JianZhang on 1/28/18.
 */
public class Solver {
    private boolean isSolvable;
    private int moveSteps;
    //    private List<BoardMovement> solutionOptionList;
    private List<Board> solutionList;

    private MinPQ<BoardMovement> minBMPQ;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        moveSteps = 0;
        minBMPQ = new MinPQ<>(new Comparator<BoardMovement>() {
            @Override
            public int compare(BoardMovement o1, BoardMovement o2) {
                if (o1.priority - o2.priority == 0)
                    return (o1.priority - o1.moves) - (o2.priority - o2.moves);
                else
                    return o1.priority - o2.priority;
            }
        });
        findSolutions(initial);

    }       // find a solution to the initial board (using the A* algorithm)

    private void findSolutions(Board initial) {
        List<BoardMovement> solutionOptionList = new ArrayList<>();
        BoardMovement initialMovement = new BoardMovement(initial, 0);
        minBMPQ.insert(initialMovement);
        BoardMovement minBM = null;

        while (!minBMPQ.isEmpty()) {
            minBM = minBMPQ.delMin();
            Board minBoard = minBM.board;
//            System.out.println("minBM");
//            System.out.println("minBM informati\r\n moves: " + minBM.moves + " priority: " + minBM.priority + " " + minBM.board);

            int minMove = minBM.moves;
//            bug: It's hard to remove correct the useless board, because the condition is always changed
//            Previous pointer is the good way.

//          bug: if we remove the element from the solutionOptionList with comparing minMove, that's would be wrong
//            we should check if whose neighbors contain the minBoard
//            while (!solutionOptionList.isEmpty() && minMove <= solutionOptionList.get(solutionOptionList.size() - 1).moves) {
//                solutionOptionList.remove(solutionOptionList.size() - 1);
//            }
//
//            if (!solutionOptionList.isEmpty() && minMove <= solutionOptionList.get(solutionOptionList.size() - 1).moves) {
//                for (int i = 0; i < solutionOptionList.size(); i++) {
//                    for (Board tmpBd : solutionOptionList.get(i).board.neighbors()) {
//                        if (tmpBd.equals(minBoard) && minMove == solutionOptionList.get(i).moves) {
//                            i++;
//                            while (i < solutionOptionList.size()) {
//                                removedSolutionList.add(solutionOptionList.get(i));
//                                solutionOptionList.remove(i);
//                            }
//                            break;
//                        }
//
//                    }
//                }
//            }
            solutionOptionList.add(minBM);
            if (minBoard.isGoal()) {
                isSolvable = true;
                break;
            }
            for (Board neighbor : minBoard.neighbors()) {
                boolean usedFlag = false;
//                bug2: 此处的插入操作会导致 之前删去的board,会在此时追加回来,所以需要再处理下,将从solutionList中抹掉的board 提出
                for (BoardMovement solution : solutionOptionList) {
                    if (solution.board.equals(neighbor)) {
                        usedFlag = true;
                        break;
                    }
                }
                BoardMovement newBM = new BoardMovement(neighbor, minMove + 1);
                newBM.previous = minBM;
                if (!usedFlag) minBMPQ.insert(newBM);
            }


//            System.out.println("minBMPQ contents");
//            for (BoardMovement bm : minBMPQ) {
//                System.out.println("bm moves: " + bm.moves + " priority: " + bm.priority + " " + bm.board);
//
//            }

        }

//        System.out.println("solution start");
//        for (int i = 0; i < solutionOptionList.size(); i++) {
//            BoardMovement bm = solutionOptionList.get(i);
//            System.out.println("bm moves: " + bm.moves + " priority: " + bm.priority);
//
//        }
        solutionList = new ArrayList<>();
        if (!isSolvable) {
            moveSteps = -1;
        } else {
            BoardMovement lastBoard = solutionOptionList.get(solutionOptionList.size() - 1);
            moveSteps = lastBoard.moves;

            while (lastBoard != null) {
                solutionList.add(0, lastBoard.board);
                lastBoard = lastBoard.previous;
            }
        }

//        int tmp = moveSteps;
//        while (tmp > 0) {
//            BoardMovement tmpBM = null;
//            for (BoardMovement bm : solutionOptionList) {
//                if (bm.moves == tmp) {
//                    tmpBM = bm;
//                    break;
//                }
//            }
//            int tmpIndex = 0;
//            while (tmpIndex < solutionOptionList.size()) {
//                if (solutionOptionList.get(tmpIndex).moves == tmp - 1) {
//                    boolean equalFlg = false;
//                    for (Board b : solutionOptionList.get(tmpIndex).board.neighbors()) {
//                        if (b.equals(tmpBM.board)) {
//                            equalFlg = true;
//                            break;
//                        }
//                    }
//                    if (!equalFlg) {
//                        solutionOptionList.remove(tmpIndex);
//                        continue;
//                    }
//                }
//                tmpIndex++;
//
//            }
//            tmp--;
//        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }      // is the initial board solvable?

    public int moves() {

        return moveSteps;
    }// min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        Iterable<Board> result = new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return solutionList.iterator();
//                return solutionOptionList.iterator();
            }
        };
        return result;
    } // sequence of boards in a shortest solution; null if unsolvable

    private class BoardMovement {
        private int priority;
        private Board board;
        private int moves;
        private BoardMovement previous;

        public BoardMovement(Board board, int moves) {
            priority = board.manhattan() + moves;
            this.board = board;
            this.moves = moves;
        }

        public Board getBoard() {
            return board;
        }
    }

    public static void main(String[] args) {
        String abc = "def";
        String def = "def";
        System.out.print(abc.equals(def));
        return;
    }// solve a slider puzzle (given below)
}
