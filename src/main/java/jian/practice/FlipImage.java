package jian.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FlipImage {

    public static void main(String[] args) {
        FlipImage.LOOKUP_ARRAY[0] = 100;
//        Solution s = new Solution();
//        s.countNumbers(100);

//        Solution3 s3 = new Solution3();
//        System.out.println(s3.countNumbersWithUniqueDigits(2));


//        FlipImage.LOOKUP_ARRAY.foreach(n -> System.out.println(n));

    }

    private static final byte[] LOOKUP_ARRAY = INIT_LOOKUP_ARRAY();

    private static byte[] INIT_LOOKUP_ARRAY() {
// Some code here
        return new byte[256];
    }
}


class Solution implements Iterable<String> {
    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] arr = line.split(" ");
        Solution s = new Solution(Integer.valueOf(arr[0]), arr[1]);
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            arr = line.split(" ");
            if ("birth".equals(arr[0])) {
                s.birth(arr[1], arr[2]);
            } else if ("death".equals(arr[0])) {
                s.death(arr[1]);
            }
        }


        for (String s1 : s) {
            System.out.println(s1);

        }

    }

    Candidate king;
    Map<String, Candidate> map;
    int id;

    // bug: don't know how to write returnList for the class
    public Solution(int n, String name) {
        this.id = 0;
        map = new HashMap<>();
        king = new Candidate(this.id++, name);
        map.put(name, king);
    }

    public void birth(String father, String son) {
        Candidate candidate = new Candidate(this.id++, son);
        map.get(father).childList.add(candidate); //bug: forget: childList
        map.put(son, candidate);//bug: forget to add new person to the map
    }

    public void death(String name) {
        map.get(name).death();
    }

    public List<String> returnList() {
        List<String> list = new ArrayList<>();
        Deque<Candidate> dq = new ArrayDeque<>();
        // Candidate crt = king;
        for (int i = king.childList.size() - 1; i >= 0; i--) {
            dq.addLast(king.childList.get(i));
        }
        while (!dq.isEmpty()) {
            Candidate crt = dq.removeLast();
            if (crt.isLive) {
                list.add(crt.name);
            }
            for (int i = crt.childList.size() - 1; i >= 0; i--) {
                dq.addLast(crt.childList.get(i));//bug: king.childList => crt.childList
            }
        }
        return list;
    }

    public Iterator<String> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<String> {
        Deque<Candidate> dq;

        public Itr() {
            dq = new ArrayDeque<>();
            for (int i = king.childList.size() - 1; i >= 0; i--) {
                dq.addLast(king.childList.get(i));
            }
        }

        @Override
        public boolean hasNext() {
            while (!dq.isEmpty()) {
                Candidate crt = dq.peekLast();
                if (crt.isLive) {
                    return true;
                } else {
                    dq.removeLast();
                    for (int i = crt.childList.size() - 1; i >= 0; i--) {
                        dq.addLast(crt.childList.get(i));//bug: king.childList => crt.childList
                    }
                }
            }
            return false;
        }

        @Override
        public String next() {
            Candidate crt = dq.removeLast();
            for (int i = crt.childList.size() - 1; i >= 0; i--) {
                dq.addLast(crt.childList.get(i));//bug: king.childList => crt.childList
            }

            return crt.name;
        }
    }
}

class Candidate {
    public String name;
    public boolean isLive;
    public int birth;
    List<Candidate> childList;

    public Candidate(int birth, String name) {
        this.birth = birth;
        this.isLive = true;
        this.name = name;
        childList = new ArrayList<>();

    }

    public void death() {
        this.isLive = false;
    }

    public List<Candidate> getChildList() {//bug: forget return type
        return this.childList;
    }
}

class Solution3 {
    public String makeLargestSpecial(String S) {
        if (S.length() == 0) return S;
        int anchor = 0, bal = 0;
        List<String> mountains = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            bal += S.charAt(i) == '1' ? 1 : -1;
            if (bal == 0) {
                mountains.add("1" + makeLargestSpecial(S.substring(anchor + 1, i)) + "0");
                anchor = i + 1;
            }
        }
        Collections.sort(mountains, Collections.reverseOrder());
        StringBuilder ans = new StringBuilder();
        for (String mtn : mountains)
            ans.append(mtn);
        return ans.toString();
    }
}