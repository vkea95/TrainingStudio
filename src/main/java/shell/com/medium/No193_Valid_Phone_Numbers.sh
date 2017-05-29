#!/usr/bin/env bash
#Given a text file file.txt that contains list of phone numbers (one per line), write a one liner bash script to
# print all valid phone numbers.
#
#You may assume that a valid phone number must appear in one of the following two formats:
# (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)
#
#You may also assume each line in the text file must not contain leading or trailing white spaces.
#
#For example, assume that file.txt has the following content:
#
#987-123-4567
#123 456 7890
#(123) 456-7890
#Your script should output the following valid phone numbers:
#987-123-4567
#(123) 456-7890
grep -P '^(\d{3}-|\(\d{3}\) )\d{3}-\d{4}$' file.txt

sed -n -r '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/p' file.txt

awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' file.txt

grep -P '^(\d{3}-|\(\d{3} )\d{3}-d{4}$'


#*****************************************************************************************************
#ref: http://www.zybang.com/question/a974c538448c4669e525fc5d6ad9ac91.html
#字符/
#意义：对于字符,通常表示按字面意义,指出接着的字符为特殊字符,不作解释.
#例如：/b/匹配字符’b’,通过在b 前面加一个反斜杠,也就是/b/,则该字符变成特殊字符,表示
#匹配一个单词的分界线.
#或者：
#对于几个字符,通常说明是特殊的,指出紧接着的字符不是特殊的,而应该按字面解释.
#例如：*是一个特殊字符,匹配任意个字符(包括0个字符)；例如：/a*/意味匹配0个或多个a.为了匹配字面上的*,在a前面加一个反斜杠；例如：/a*/匹配’a*’.
#字符^
#意义：表示匹配的字符必须在最前边.
#例如：/^A/不匹配"an A,"中的’A’,但匹配"An A."中最前面的’A’.
#字符$
#意义：与^类似,匹配最末的字符.
#例如：/t$/不匹配"eater"中的’t’,但匹配"eat"中的’t’.
#字符*
#意义：匹配*前面的字符0次或n次.
#例如:/bo*/匹配"A ghost booooed"中的’boooo’或"A bird warbled"中的’b’,但不匹配"Agoat g
#runted"中的任何字符.
#字符+
#意义：匹配+号前面的字符1次或n次.等价于{1,}.
#例如：/a+/匹配"candy"中的’a’和"caaaaaaandy."中的所有’a’.
#字符?
#意义：匹配?前面的字符0次或1次.
#例如：/e?le?/匹配"angel"中的’el’和"angle."中的’le’.
#字符.
#意义：(小数点)匹配除换行符外的所有单个的字符.
#例如：/.n/匹配"nay, an apple is on the tree"中的’an’和’on’,但不匹配’nay’.
#字符(x)
#意义：匹配’x’并记录匹配的值.
#例如：/(foo)/匹配和记录"foo bar."中的’foo’.匹配子串能被结果数组中的素[1], ...,[n] 返
#回,或被RegExp对象的属性, ..., 返回.
#字符x│y
#意义：匹配’x’或者’y’.
#例如：/green│red/匹配"green apple"中的’green’和"red apple."中的’red’.
#字符{ n }
#意义：这里的n是一个正整数.匹配前面的n个字符.
#例如：/a{ 2 }/不匹配"candy,"中的’a’,但匹配"caandy," 中的所有’a’和"caaandy."中前面的两个’a’.
#字符{ n, }
#意义：这里的n是一个正整数.匹配至少n个前面的字符.
#例如：/a{ 2, }不匹配"candy"中的’a’,但匹配"caandy"中的所有’a’和"caaaaaaandy."中的所有’a’
#字符{ n,m }
#意义：这里的n和m都是正整数.匹配至少n个最多m个前面的字符.
#例如：/a{ 1,3 }/不匹配"cndy"中的任何字符,但匹配 "candy,"中的’a’,"caandy," 中的前面两个
#’a’和"caaaaaaandy"中前面的三个’a’,注意：即使"caaaaaaandy" 中有很多个’a’,但只匹配前面的三 个’a’即"aaa".
#字符[xyz]
#意义：一字符列表,匹配列出中的任一字符.你可以通过连字符-指出一个字符范围.
#例如：[abcd]跟[a-c]一样.它们匹配"brisket"中的’b’和"ache"中的’c’.
#字符[^xyz]
#意义：一字符补集,也就是说,它匹配除了列出的字符外的所有东西. 你可以使用连字符-指出一 字符范围.
#例如：[^abc]和[^a-c]等价,它们最早匹配"brisket"中的’r’和"chop."中的’h’.
#字符
#意义：匹配一个空格(不要与b混淆)
#字符b
#意义：匹配一个单词的分界线,比如一个空格(不要与混淆)
#例如：/bnw/匹配"noonday"中的’no’,/wyb/匹配"possibly yesterday."中的’ly’.
#字符B
#意义：匹配一个单词的非分界线
#例如：/wBn/匹配"noonday"中的’on’,/yBw/匹配"possibly yesterday."中的’ye’.
#字符cX
#意义：这里的X是一个控制字符.匹配一个字符串的控制字符.
#例如：/cM/匹配一个字符串中的control-M.
#字符d
#意义：匹配一个数字,等价于[0-9].
#例如：/d/或/[0-9]/匹配"B2 is the suite number."中的’2’.
#字符D
#意义：匹配任何的非数字,等价于[^0-9].
#例如：/D/或/[^0-9]/匹配"B2 is the suite number."中的’B’.
#字符f
#意义：匹配一个表单符
#字符n
#意义：匹配一个换行符
#字符r
#意义：匹配一个回车符
#字符s
#意义：匹配一个单个white空格符,包括空格,tab,form feed,换行符,等价于[ fnrtv].
#例如：/sw*/匹配"foo bar."中的’ bar’.
#字符S
#意义：匹配除white空格符以外的一个单个的字符,等价于[^ fnrtv].
#例如：/S/w*匹配"foo bar."中的’foo’.
#字符t
#意义：匹配一个制表符
#字符v
#意义：匹配一个顶头制表符
#字符w
#意义：匹配所有的数字和字母以及下划线,等价于[A-Za-z0-9_].
#例如：/w/匹配"apple,"中的’a’,".28,"中的’5’和"3D."中的’3’.
#字符W
#意义：匹配除数字、字母外及下划线外的其它字符,等价于[^A-Za-z0-9_].
#例如：/W/或者/[^$A-Za-z0-9_]/匹配"50%."中的’%’.
#字符n
#意义：这里的n是一个正整数.匹配一个正则表达式的最后一个子串的n的值(计数左圆括号).
#例如：/apple(,)sorange1/匹配"apple, orange, cherry, peach."中的’apple, orange’,下面有一个更加完整的例子.
#注意：如果左圆括号中的数字比n指定的数字还小,则n取下一行的八进制escape作为描述.
#字符ooctal和xhex
#意义：这里的ooctal是一个八进制的escape值,而xhex是一个十六进制的escape值,允许在一个正则表达式中嵌入ASCII码
#附:下表是元字符及其在正则表达式上下文中的行为的一个完整列表：
#字符 描述
#\
#将下一个字符标记为一个特殊字符、或一个原义字符、或一个后向引用、或一个八进制转义符.例如,'n' 匹配字符 "n".'\n' 匹配一个换行符.
# 序列 '\' 匹配 "" 而 "\(" 则匹配 "(".
#^
#匹配输入字符串的开始位置.如果设置了 RegExp 对象的 Multiline 属性,^ 也匹配 '\n' 或 '\r' 之后的位置.
#$
#匹配输入字符串的结束位置.如果设置了RegExp 对象的 Multiline 属性,$ 也匹配 '\n' 或 '\r' 之前的位置.
#*
#匹配前面的子表达式零次或多次.例如,zo* 能匹配 "z" 以及 "zoo". * 等价于{0,}.
#+ 匹配前面的子表达式一次或多次.例如,'zo+' 能匹配 "zo" 以及 "zoo",但不能匹配 "z".+ 等价于 {1,}.
#?
#匹配前面的子表达式零次或一次.例如,"do(es)?" 可以匹配 "do" 或 "does" 中的"do" .? 等价于 {0,1}.
#{n}
#n 是一个非负整数.匹配确定的 n 次.例如,'o{2}' 不能匹配 "Bob" 中的 'o',但是能匹配 "food" 中的两个 o.
#{n,}
#n 是一个非负整数.至少匹配n 次.例如,'o{2,}' 不能匹配 "Bob" 中的 'o',但能匹配 "foooood" 中的所有 o.'o{1,}' 等价于 'o+'.'o{0,}'
# 则等价于 'o*'.
#{n,m}
#m 和 n 均为非负整数,其中n