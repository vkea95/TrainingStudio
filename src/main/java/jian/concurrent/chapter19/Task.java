package jian.concurrent.chapter19;

/*
https://colobu.com/2014/10/28/secrets-of-java-8-functional-interface/
函数式接口(Functional Interface)是Java 8对一类特殊类型的接口的称呼。
这类接口只定义了唯一的抽象方法的接口（除了隐含的Object对象的公共方法）， 因此最开始也就做SAM类型的接口（Single Abstract Method）。

为什么会单单从接口中定义出此类接口呢？ 原因是在Java Lambda的实现中，
开发组不想再为Lambda表达式单独定义一种特殊的Structural函数类型，称之为箭头类型（arrow type），
依然想采用Java既有的类型系统(class, interface, method等)， 原因是增加一个结构化的函数类型会增加函数类型的复杂性，
破坏既有的Java类型，并对成千上万的Java类库造成严重的影响。 权衡利弊， 因此最终还是利用SAM 接口作为 Lambda表达式的目标类型。

JDK中已有的一些接口本身就是函数式接口，如Runnable。 JDK 8中又增加了java.util.function包， 提供了常用的函数式接口。

函数式接口代表的一种契约， 一种对某个特定函数类型的契约。 在它出现的地方，实际期望一个符合契约要求的函数。
Lambda表达式不能脱离上下文而存在，它必须要有一个明确的目标类型，而这个目标类型就是某个函数式接口。

当然， Java 8发布快一年了， 你对以上的概念也应该有所了解了，这篇文章也不会介绍这些基础的东西， 而是想深入的探讨函数式接口的定义和应用。
 */
@FunctionalInterface
public interface Task<IN, OUT> {
    OUT get(IN input);
}
