package ctc.com.viii.ch07.p05;

import java.util.*;

public class OnlineReaderSystem {
    private Library library;
    private UserManager userManager;
    private Display display;

    private Book activeBook;
    private User activeUser;

    public OnlineReaderSystem() {
        userManager = new UserManager();
        library = new Library();
        display = new Display();
    }

    public Library getLibrary() {
        return library;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public Display getDisplay() {
        return display;
    }

    public Book getActiveBook() {
        return activeBook;
    }

    public void setActiveBook(Book book) {
        display.displayBook(book);
        activeBook = book;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User user) {
        activeUser = user;
        display.displayUser(user);
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> list = accounts.get(i);
            for (int j = 1; j < list.size(); j++) {
                if (map.containsKey(list.get(j))) {
                    uf.union(map.get(list.get(j)), i);
                } else {
                    map.put(list.get(j), i);
                }
            }
        }
        Map<Integer, Set<String>> map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = uf.find(i);//bug
            if (map2.containsKey(p)) {//bug
                map2.get(p).addAll(accounts.get(i).subList(1, accounts.get(i).size()));
            } else {
                map2.put(p, new HashSet<>(accounts.get(i).subList(1, accounts.get(i).size())));
            }
        }

        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> e : map2.entrySet()) {
            List<String> r = new ArrayList<>();
            r.add(accounts.get(e.getKey()).get(0));
            List<String> mails = new ArrayList<>(e.getValue());
            Collections.sort(mails);
            r.addAll(mails);
            list.add(r);
        }

        return list;
    }


}

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public void union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i != j) {
            parent[i] = j;
        }
    }
}