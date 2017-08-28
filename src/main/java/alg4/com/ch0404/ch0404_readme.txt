the shortest path
Applications: Map, Network, Task Schedule, 套汇

1. 最短路径的性质
1.1 路径是有向的
1.2 权重不一定等于距离
1.3 并不是所有的顶点都是可达的
1.4 负权重会是问题更复杂
1.5 最短路径一般都是简单的(忽略零权重边)
1.6 最短路径不一定是唯一的
最短路径树:SPT
2.
加权有向图的数据结构: DirectedEdge, EdgeWeightedDigraph
2.1 最短路径的API:class SP
constructor(含起点), distTo, hasPathTo, pathTo
2.2 测试用例
2.3 最短路径的数据结构
2.3.1 和DFS,BFS,Prim一样使用数组edgeTo[],edgeTo[v]表示连接v和它的父节点的边(该最短路径上的最后一条边)
2.3.2 到达起点的距离:distTo[], distTo[v]表示从s到v的已知最短路径的长度
2.4 边的松弛(relaxation):新顶点被加入到SP之后,到非SP顶点w的距离,可能会因为v的加入而变化,那么distTo[w],edgeTo[w]都会变化
2.5 顶点的松弛:即循环所有sp的顶点,将非SP的顶点的边,松弛
3. SP算法的理论基础
4. Dijkstra算法
欧几里得图中的路径算法,可通过小改动dijkstra 大幅提高运算速度
5. 无环加权有向图中的SP算法--->比Dijkstra更快
性质:
A.线性时间内
B.能够处理负权重的问题
C.能够解决相关的问题,如找出最长的路径
---->基于无环有向图的拓扑排序算法的拓展,顶点的松弛+拓扑排序结合起来,就是解决无环加权有向图中的最短路径问题的算法
命题:按照拓扑顺序放松顶点,就能在E+V成正比的时间内解决无环加权有向图的单点最短路径问题
AcyclicSP
5.1 解决最长路径问题:clone一个原图,将所有边的权重取反,然后寻找SP,最后再将路径的权重变为正值即可
