package alg4.com.ch0203;

/**
 * Write a program Sort2distinct.java that sorts an array that is known to contain just two distinct key values.
 * Created by JianZhang on 6/15/17.
 */
public class Ch020305 {
//两个元素的位置不清,大小关系不清,所以至少要扫描一次,或者说就存储两个不一样的值,然后计算个数,再重新赋值给数组, O(n)=2n
//参考三向快速排序法,因为只有2个不同的主键,所以循环扫描过一次后,数组就已经有序了,不需要额外的递归调用sort方法

    public void sort2DistinctValues(Comparable[] a){
        int lo=0,hi=a.length-1,lt=0,gt=hi,i=lt+1;
        Comparable v=a[0];
        while (i<=gt){
            int val = a[i].compareTo(v);
            if (val<0) exch(a,lt++,i++);
            else if(val>0) exch(a,i,gt--);
            else i++;
        }
    }


    private void exch(Comparable[] a, int lo, int hi){
        Comparable temp=a[lo];
        a[lo]=a[hi];
        a[hi]=temp;
    }

}
