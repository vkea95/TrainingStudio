#!/usr/bin/env bash
#Given a text file file.txt, transpose its content.
#
#You may assume that each row has the same number of columns and each field is separated by the ' ' character.
#
#For example, if file.txt has the following content:
#
#name age
#alice 21
#ryan 30
#Output the following:
#
#name alice ryan
#age 21 30
# using awk for this purpose
#solution: https://discuss.leetcode.com/topic/10805/ac-solution-using-awk-and-statement-just-like-c/5
#explanation:
#END part will run after it dealt with every line.
#After dealing with all the lines, the s[] array is what we want.
#END part is to print the s[], this for loop will end when s[i] is empty.
#Because the lines may have different number of words, I cannot find the length of s[] easily
#***********************************************************
#NR - Number of Record - 当前处理的行是第几行（因为awk是流处理工具，一行一行处理的，所以NR在不停的自增1)
#FNR - File Number of Record - 当前处理的行是当前处理文件的第几行
#NF - Number of Fileds - 当前行有多少列数据（这个在每行都会根据设定的分割符重新计算，默认分割符是任意连续的多个空白符）
#
#(NR==FNR) 大致等效于 (ARGIND==1) , 一般用来判断是不是在处理第一个文件。
#但区别是： 前者是判断当前处理的总行数跟文件内行数相等，而这种情况一般都是在第一个文件上，而ARGIND==1是参数位置第1的文件时生效。——
# 但，如果前面的文件是空的，那么NR==FNR就生效于第一个非空文件，而ARGIND==1不会。这微妙的区别使它们可以应用于不同的需要。
#实际使用中还可以用文件名判断当前处理的是哪一个文件。
#上面几种使用的环境： 用awk连续处理多个文件时，比如 awk '命令序列' file1 file2 file* 这样的情况下，判断在处理哪个文件了。
#比如现在AWK处理到第五行。第一行没有进行操作，2,3,4,5行进行了操作，那么NR=5,FNR=4
# NR==FNR 表示从起始行到当前行，awk都进行了操作，比如修改，添加等等
#简单来说，NR就是当前读取多少行，FNR就是当前修改了多少行，FNR<=NR
#***********************************************************
#Blog ref:http://www.cnblogs.com/ggjucheng/archive/2013/01/13/2858470.html
#***********************************************************
#***********************************************************
#***********************************************************
#***********************************************************


awk '
{
    for (i = 1; i <= NF; i++) {
        if(NR == 1) {
            s[i] = $i;
        } else {
            s[i] = s[i] " " $i;
        }
    }
}
END {
    for (i = 1; s[i] != ""; i++) {
        print s[i];
    }
}' file.txt