package cci.com.ch03.p01;

/**
 * Created by JianZhang on 1/8/18.
 */
public class FixedMultiStack {

    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }


    public void push(int stackNum, int value) throws Exception {
        if (isFull(stackNum)) throw new Exception();
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum) throws Exception {
        if (isEmpty(stackNum)) throw new Exception();
        int topIndex = indexOfTop(stackNum);
        int resultNum = values[topIndex];
        values[topIndex] = 0;
        sizes[stackNum]--;//shrink
        return resultNum;
    }

    public int peek(int stackNum) throws Exception {
        if (isEmpty(stackNum)) throw new Exception();
        return values[indexOfTop(stackNum)];
    }

    private boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    private boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        return sizes[stackNum] + offset - 1;//此处-1,貌似是因为,实际处理中,是先index+1,再进行赋值
    }
}
