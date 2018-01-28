import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JianZhang on 1/28/18.
 */
public class Solver {
    private MinPQ<Board> minPQ;
    private boolean isSolvable;
    private int moveSteps;
    private List<Board> solutionList;

    public Solver(Board initial) {
        moveSteps = 0;
        minPQ = new MinPQ<>(new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                return o1.manhattan() - o2.manhattan();
            }
        });

    }       // find a solution to the initial board (using the A* algorithm)

    private void findSolutions(Board initial) {
        solutionList = new ArrayList<>();
        minPQ.insert(initial);
        while (!minPQ.isEmpty()) {
            Board minBoard = minPQ.delMin();
            solutionList.add(minBoard);
            if (minBoard.isGoal()) {
                isSolvable = true;
                break;
            }

            for (Board neighbor : minBoard.neighbors()) {
                boolean usedFlag = false;
                for (Board solution : solutionList) {
                    if (solution.equals(neighbor)) {
                        usedFlag = true;
                        break;
                    }
                }
                if (!usedFlag) minPQ.insert(neighbor);
            }
            moveSteps++;
        }
        if (!isSolvable) solutionList = new ArrayList<>();
        minPQ = null;
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
            }
        };
        return result;
    } // sequence of boards in a shortest solution; null if unsolvable

    public static void main(String[] args) {
        return;
    }// solve a slider puzzle (given below)
}
