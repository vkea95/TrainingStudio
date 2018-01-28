import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JianZhang on 1/28/18.
 */
public class Board {
    private final int dimension;
    private int[][] blocks = null;

    public Board(int[][] blocks) {
        if (blocks == null || blocks.length == 0 || blocks.length != blocks[0].length)
            throw new IllegalArgumentException();
        dimension = blocks.length;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                this.blocks[i][j] = blocks[i][j];
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

    public int manhattan() {
        int manhattanResult = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // judge if the cell is blank
                if (blocks[i][j] != 0) {
                    int goalRow = blocks[i][j] / this.dimension;
                    int goalCol = blocks[i][j] % this.dimension;
                    manhattanResult += Math.abs(goalRow - i) + Math.abs(goalCol - j);
                }
            }
        }

        return manhattanResult;
    }               // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return hamming() == 0 && manhattan() == 0;
    }             // is this board the goal board?

    public Board twin() {
        int temp1 = blocks[0][this.dimension() - 1];
        blocks[0][this.dimension() - 1] = blocks[this.dimension() - 1][0];
        blocks[this.dimension() - 1][0] = temp1;
        Board twin = new Board(blocks);
        blocks[this.dimension() - 1][0] = blocks[0][this.dimension() - 1];
        blocks[0][this.dimension() - 1] = temp1;
        return twin;
    }

    public boolean equals(Object y) {
        Board that = (Board) y;
        int[][] thatBlocks = that.blocks;
        if (dimension != that.dimension()) return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != thatBlocks[i][j]) return false;
            }
        }
        return true;
    }  // does this board equal y?

    public Iterable<Board> neighbors() {
//        List<Board> list = new ArrayList<>();
//
        Iterable<Board> result = new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
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
                        }
                    }
                }
                return list.iterator();
            }
        };
        return result;
    }   // all neighboring boards


    public int[][] getBlocks() {
        int[][] cloneBlocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                cloneBlocks[i][j] = blocks[i][j];
            }
        }
        return cloneBlocks;
    }

    public String toString() {
        String result = new String();
        result += this.dimension() + "\r\n";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result += " " + this.blocks[i][j] + " ";
            }
            result += "\r\n";
        }

        return result;
    }         // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
        Board board = new Board(null);
        Iterable<Board> iterable = board.neighbors();
        for (Board b : iterable) {

        }
    }// unit tests (not graded)
}
