package core.thread.threadpool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. ..): ");
        String diectory = in.nextLine();
        System.out.println("Enter keyword (e.g. volatile) :");
        String keyword = in.nextLine();

        ExecutorService pool = Executors.newCachedThreadPool();
        MatchCounter matchCounter = new MatchCounter(new File(diectory), keyword, pool);
        Future<Integer> result = pool.submit(matchCounter);
        try {
            System.out.println(result.get() + " matching files.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();

        pool.shutdown();
        System.out.println("largest pool size=" + largestPoolSize);
    }
}

class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private ExecutorService pool;// thread pool
    private int count = 0;


    public MatchCounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;

    }

    @Override
    public Integer call() throws Exception {
        count = 0;

        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter matchCounter = new MatchCounter(file, keyword, pool);
                    Future<Integer> result = pool.submit(matchCounter);
                    results.add(result);
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }
            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {

        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                        break;
                    }
                    return found;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}

class NumMatrix {

    SegmentTree segmentTree;
    int x;// bug: forget
    int col;// bug: forget

    public NumMatrix(int[][] matrix) {
        segmentTree = new SegmentTree(matrix);
    }

    public void update(int row, int col, int val) {
        segmentTree.update(row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return segmentTree.sumRegion(row1, col1, row2, col2);
    }
}

class SegmentTree {

    Node root;
    int[] bk;
    int x;

    public SegmentTree(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            root = new Node(0, 0, 0);
        } else {
            x = matrix.length;
            root = setupTree(matrix);
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRange(root, row1 * x + col1, row2 * x + col2);
    }

    public int sumRange(Node r, int s, int e) {
        if (r == null || s < r.start || e > r.end || e < s) { // bug: forget to do
            return 0;
        }

        if (r.start >= s && r.end <= e) {
            return r.sum;
        }

        int mid = (r.start + r.end) >>> 1;
        return sumRange(r.left, s, Math.min(e, mid)) // bug : 范围不对
                + sumRange(r.right, Math.max(s, mid + 1), e);
    }

    public void update(int row, int col, int val) {
        int newIndex = row * x + col;
        updateNode(root, newIndex, val);
    }


    private void updateNode(Node r, int index, int val) {
        if (r == null) {
            return;
        }
        if (r.start == r.end && r.start == index) {
            r.sum = val;
            return;
        }

        int mid = (r.start + r.end) >>> 1;
        if (index <= mid) {
            updateNode(r.left, index, val);
        } else {
            updateNode(r.right, index, val);
        }
        r.sum = r.left.sum + r.right.sum;
    }

    private Node setupTree(int[][] matrix) {//bug return type
        int n = matrix.length;
        int m = matrix[0].length;
        bk = new int[n * m]; // bug: 应该将其放置到 class内部的成员变量
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bk[index++] = matrix[i][j];
            }
        }

        return setupNode(bk, 0, m * n - 1);
    }

    private Node setupNode(int[] bk, int s, int e) {
        if (s == e) {
            return new Node(s, e, bk[s]);
        }
        if (s > e) {
            return null;
        }

        int m = (s + e) >>> 1;
        Node node = new Node(s, e);
        node.left = setupNode(bk, s, m);
        node.right = setupNode(bk, m + 1, e);
        node.sum = node.left.sum + node.right.sum;
        return node;
    }
}

class Node {
    int index; // bug: 不能用index，只能start和end
    int start;
    int end;
    int sum;
    Node left;
    Node right;

    public Node(int start, int end, int sum) {
        this.sum = sum;
        this.end = end;
        this.start = start;
    }

    public Node(int start, int end) {
        this.end = end;
        this.start = start;
    }
}

/**
 * 1. 明白是使用segmentTree， 但是忘记了怎么用了
 * 2. 这样的话，就是需要 将二维数组，打平为一维数组
 * <p>
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */