import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JianZhang on 1/28/18.
 */
public class Board {
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private final int dimension;
    private int[][] blocks = null;
    private int manhattanNumber;
    private List<Board> neighbourList = null;

    public Board(int[][] blocks) {
        if (blocks == null || blocks.length == 0 || blocks.length != blocks[0].length)
            throw new IllegalArgumentException();
        dimension = blocks.length;
        this.blocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                this.blocks[i][j] = blocks[i][j];
        this.countManhattan();
    }        // construct a board from an n-by-n array of blocks


    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dimension;
    }        // board dimension n

    public int hamming() {
        int hammingResult = 0;
        int index = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != index) {
                    hammingResult++;
                }
                index++;
            }
        }
        // remove the number of blank cell
        return hammingResult - 1;
    }             // number of blocks out of place

    private void countManhattan() {
        int manhattanResult = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // judge if the cell is blank
                if (blocks[i][j] != 0) {
                    int goalRow = (blocks[i][j] - 1) / this.dimension;
                    int goalCol = (blocks[i][j] - 1) % this.dimension;
                    manhattanResult += Math.abs(goalRow - i) + Math.abs(goalCol - j);
                }
            }
        }
        this.manhattanNumber = manhattanResult;

    }

    public int manhattan() {
        return this.manhattanNumber;
    }               // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return manhattan() == 0;
    }             // is this board the goal board?

    public Board twin() {
//        bug: doesn't consider the blank cell
//        int temp1 = blocks[0][this.dimension() - 1];
//        blocks[0][this.dimension() - 1] = blocks[this.dimension() - 1][0];
//        blocks[this.dimension() - 1][0] = temp1;
//        Board twin = new Board(blocks);
//        blocks[this.dimension() - 1][0] = blocks[0][this.dimension() - 1];
//        blocks[0][this.dimension() - 1] = temp1;
        int firstIndex = 0;
        while (firstIndex < dimension * dimension) {
            if (blocks[firstIndex / dimension][firstIndex % dimension] != 0) {
                break;
            }
            firstIndex++;
        }
        int secondIndex = firstIndex + 1;
        while (secondIndex < dimension * dimension) {
            if (blocks[secondIndex / dimension][secondIndex % dimension] != 0) {
                break;
            }
            secondIndex++;
        }
        int temp = blocks[firstIndex / dimension][firstIndex % dimension];
        blocks[firstIndex / dimension][firstIndex % dimension] = blocks[secondIndex / dimension][secondIndex % dimension];
        blocks[secondIndex / dimension][secondIndex % dimension] = temp;
        Board twin = new Board(blocks);

        blocks[secondIndex / dimension][secondIndex % dimension] = blocks[firstIndex / dimension][firstIndex % dimension];
        blocks[firstIndex / dimension][firstIndex % dimension] = temp;
        return twin;
    }

    public boolean equals(Object y) {
//        bug: confused to write code
        if (this == y) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension != that.dimension)
            return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++)
                if (blocks[i][j] != that.blocks[i][j]) return false;
        }
        return true;
    }  // does this board equal y?

    private List<Board> setNeighbors() {
        List<Board> list = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    if (i > 0) {
                        blocks[i][j] = blocks[i - 1][j];
                        blocks[i - 1][j] = 0;
                        list.add(new Board(blocks));
                        blocks[i - 1][j] = blocks[i][j];
                        blocks[i][j] = 0;
                    }
                    if (j > 0) {
                        blocks[i][j] = blocks[i][j - 1];
                        blocks[i][j - 1] = 0;
                        list.add(new Board(blocks));
                        blocks[i][j - 1] = blocks[i][j];
                        blocks[i][j] = 0;
                    }
                    if (i < dimension() - 1) {
                        blocks[i][j] = blocks[i + 1][j];
                        blocks[i + 1][j] = 0;
                        list.add(new Board(blocks));
                        blocks[i + 1][j] = blocks[i][j];
                        blocks[i][j] = 0;
                    }
                    if (j < dimension() - 1) {
                        blocks[i][j] = blocks[i][j + 1];
                        blocks[i][j + 1] = 0;
                        list.add(new Board(blocks));
                        blocks[i][j + 1] = blocks[i][j];
                        blocks[i][j] = 0;
                    }

                    break;
                }
            }
        }

        return list;
    }

    public Iterable<Board> neighbors() {
        Iterable<Board> result = new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                if (neighbourList == null) {
                    neighbourList = setNeighbors();
                }
                return neighbourList.iterator();
            }
        };
        return result;
    }   // all neighboring boards


    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.dimension());
        result.append("\r\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                result.append(" ");
                result.append(this.blocks[i][j]);
            }
            result.append("\r\n");
        }

        return result.toString();
    }         // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
//        Board board = new Board(null);
//        Iterable<Board> iterable = board.neighbors();
//        for (Board b : iterable) {
//        }
    } // unit tests (not graded)
}
