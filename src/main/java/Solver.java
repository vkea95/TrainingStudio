import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JianZhang on 1/28/18.
 */

//            bug: It's hard to remove correct the useless board, because the condition is always changed
//            Previous pointer is the good way.

//                bug2: 此处的插入操作会导致 之前删去的board,会在此时追加回来,所以需要再处理下,将从solutionList中抹掉的board 提出
//          bug: if we remove the element from the solutionOptionList with comparing minMove, that's would be wrong
//            we should check if whose neighbors contain the minBoard
//                bug3: 在考虑是否将neighbour放入队列的问题的时候,其实可以根据priority来考虑,比它小的不可能放进队伍?
//                bug3: continuous 检测到equal的调用次数过多! 需要进行必要的调整,如何调整呢
//                Bug4: 后来用了发现在匹配neighbour的时候,会产生n*m的时间复杂度,现在的想法就是,所有的board都放入一个heap中排序,
//                  然后再进行2分查找,希望可以提供速度,而又不增加额外的空间,同时呢这个原来想定的solutionList这个方案,
//                  因为previous指针的存在,可以考虑废弃了
//                估计只能使用sort这个策略,即每次都是按照拍好序列的方式来处理
//          bug5: isSolvable的判断方法就是,要同时run 两个board,固定步长交换,根据现出来的那个board来判断initial是否work!!!
public class Solver {
    private static final int LOCK_STEPS = 500;
    private boolean isSolvable;
    private int moveSteps;
    private List<Board> solutionList;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        moveSteps = 0;
        mainDeal(initial);

    }       // find a solution to the initial board (using the A* algorithm)

    private void mainDeal(Board initial) {
        MinPQ<BoardMovement> minBMPQ = new MinPQ<>(new Comparator<BoardMovement>() {
            @Override
            public int compare(BoardMovement o1, BoardMovement o2) {
                if (o1.priority - o2.priority == 0)
                    return (o1.priority - o1.moves) - (o2.priority - o2.moves);
                else
                    return o1.priority - o2.priority;
            }
        });

        MinPQ<BoardMovement> minTwinBMPQ = new MinPQ<>(new Comparator<BoardMovement>() {
            @Override
            public int compare(BoardMovement o1, BoardMovement o2) {
                if (o1.priority - o2.priority == 0)
                    return (o1.priority - o1.moves) - (o2.priority - o2.moves);
                else
                    return o1.priority - o2.priority;
            }
        });
        BoardMovement initialMovement = new BoardMovement(initial, 0);
        minBMPQ.insert(initialMovement);
        BoardMovement twinMovement = new BoardMovement(initial.twin(), 0);
        minTwinBMPQ.insert(twinMovement);
        while (true) {
            BoardMovement minBM = findSolutions(minBMPQ, 0);
            if (minBM.board.isGoal()) {
                solutionList = new ArrayList<>();
                moveSteps = minBM.moves;

                isSolvable = true;
                while (minBM != null) {
                    solutionList.add(0, minBM.board);
                    minBM = minBM.previous;
                }
                break;
            }
            minBM = findSolutions(minTwinBMPQ, 0);
            if (minBM.board.isGoal()) {
                isSolvable = false;
                moveSteps = -1;
                break;
            }
        }
    }

    private BoardMovement findSolutions(MinPQ<BoardMovement> minBMPQ, int lockSteps) {
        BoardMovement minBM = null;

        while (!minBMPQ.isEmpty() && lockSteps < LOCK_STEPS) {
            minBM = minBMPQ.delMin();
            Board minBoard = minBM.board;

            int minMove = minBM.moves;
            if (minBoard.isGoal()) {
                break;
            }
            if (minBoard.twin().isGoal()) {
                break;
            }
            for (Board neighbor : minBoard.neighbors()) {
//                bug6:    - equals() compares a board to a board that is not a neighbor of a neighbor

//                if (neighbor.equals(minBoard)) { -->wrong
                if (minBM.previous != null && neighbor.equals(minBM.previous.board)) {
                    continue;
                }

                BoardMovement newBM = new BoardMovement(neighbor, minMove + 1);
                newBM.previous = minBM;
                minBMPQ.insert(newBM);
            }
            lockSteps++;
        }

        return minBM;
    }

    public boolean isSolvable() {
        return isSolvable;
    }      // is the initial board solvable?

    public int moves() {

        return moveSteps;
    } // min number of moves to solve initial board; -1 if unsolvable


    public Iterable<Board> solution() {
        if (solutionList == null) return null;
        Iterable<Board> result = new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {

                return solutionList.iterator();
            }
        };
        return result;
    } // sequence of boards in a shortest solution; null if unsolvable


    private class BoardMovement {
        private final int priority;
        private final Board board;
        private final int moves;
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

    private static boolean findNumber(int[] array, int start, int end, int goal) {
        while (start <= end) {
            int median = start + (end - start) / 2;
            if (array[median] == goal) {
                return true;
            } else if (array[median] > goal) {
                end = median - 1;
            } else {
                start = median + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        String abc = "def";
//        String def = "def";
//        System.out.print(abc.equals(def));
        int[] array = new int[0];
        System.out.print("The result is : " + Solver.findNumber(array, 0, -1, 1) + "\n");
        array = new int[1];
        array[0] = 1;
        System.out.print("The result is : " + Solver.findNumber(array, 0, array.length - 1, 1) + "\n");
        array = new int[4];
        array[0] = -10;
        array[1] = -2;
        array[2] = 0;
        array[3] = 1;
        System.out.print("The result is : " + Solver.findNumber(array, 0, array.length - 1, -2) + "\n");
        return;
    } // solve a slider puzzle (given below)
}
