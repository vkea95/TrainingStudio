package jian.concurrent.chapter03;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "DOD");

    public static void main(String[] args) {
        List<Integer> l = new LinkedList<>();
        int[] r = l.stream().mapToInt(i -> i).toArray();


        List<String> result = search("SH", "BJ");
        result.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {
        final List<String> result = new ArrayList<>();

        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f, original, dest)).collect(toList());
        //分别启动这几个线程
        tasks.forEach(Thread::start);
        tasks.forEach(f -> {
            try {
                f.join();
            } catch (InterruptedException e) {

            }
        });
// map中的::用法要注意啊
//        jdk8中使用了::的用法。就是把方法当做参数传到stream内部，
// 使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的[方法引用],[方法引用]的格式是：
//        感觉好像：：前面是类或是接口的名字，而不是object的名字
//        既然Lambda表达式x->System.out.println(x)相当于匿名函数（接受一个String类型的参数，无返回值），
// 那么对于其使用者forEach而言，传给它一个匿名函数还是有名字的函数其实没有区别，只要这个函数满足forEach的参数规约即可。
// 而且很多时候有名字的函数反而可读性更好并且更利于代码重用。如此说来，Java 8 引入方法引用（Method Reference）也就顺理成章了。
// 上面的代码用方法引用可以写成：
        tasks.stream().map(FightQueryTask::get).forEach(f -> result.addAll(f));
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String origin, String dest) {
        return new FightQueryTask(fight, origin, dest);
    }
}
