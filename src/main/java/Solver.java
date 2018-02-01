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
//    private MinPQ

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

            int minMove = minBM.moves;
//            bug: It's hard to remove correct the useless board, because the condition is always changed
//            Previous pointer is the good way.

//          bug: if we remove the element from the solutionOptionList with comparing minMove, that's would be wrong
//            we should check if whose neighbors contain the minBoard
            solutionOptionList.add(minBM);
            if (minBoard.isGoal()) {
                isSolvable = true;
                break;
            }
            for (Board neighbor : minBoard.neighbors()) {
                boolean usedFlag = false;
//                bug2: 此处的插入操作会导致 之前删去的board,会在此时追加回来,所以需要再处理下,将从solutionList中抹掉的board 提出
                Iterator<BoardMovement> bmIT = minBMPQ.iterator();
//                bug3: 在考虑是否将neighbour放入队列的问题的时候,其实可以根据priority来考虑,比它小的不可能放进队伍?
//                bug3: continuous 检测到equal的调用次数过多! 需要进行必要的调整,如何调整呢
//                估计只能使用sort这个策略,即每次都是按照拍好序列的方式来处理
//                while (bmIT.hasNext()) {
//                    BoardMovement tmBM = bmIT.next();
//                    if (tmBM.board.equals(neighbor)) {
//                        usedFlag = true;
//                        break;
//                    }
//                }
                for (BoardMovement solution : solutionOptionList) {
                    if (solution.board.equals(neighbor)) {
                        usedFlag = true;
                        break;
                    }
                }
                if (!usedFlag) {
                    BoardMovement newBM = new BoardMovement(neighbor, minMove + 1);
                    newBM.previous = minBM;
                    minBMPQ.insert(newBM);
                }
            }

        }
        solutionList = new ArrayList<>();
        if (!isSolvable) {
            moveSteps = -1;
        } else {
            moveSteps = minBM.moves;

            while (minBM != null) {
                solutionList.add(0, minBM.board);
                minBM = minBM.previous;
            }
        }
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
