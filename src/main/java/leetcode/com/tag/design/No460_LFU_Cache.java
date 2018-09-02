package leetcode.com.tag.design;

import java.util.HashMap;
import java.util.Map;

public class No460_LFU_Cache {

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);

        lfuCache.put(2,2);
        lfuCache.put(1,1);
        lfuCache.get(1);
        lfuCache.put(3,3);
        lfuCache.get(2);
        lfuCache.get(3);


    }




}
