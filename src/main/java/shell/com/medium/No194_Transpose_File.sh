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

awk '
    {
        for(i=1; i<=NF; i++)
        {
#             print line[i]
             print $i
            if(line[i] == "")
            {
                line[i] = $i
            }
            else
            {
                line[i] = line[i]" "$i
            }
        }
    }
    END{
         for(i=1; i<=NF; i++)
         {
             print line[i]
         }
         print "hii"
       }
    ' file.txt