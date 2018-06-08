
# Tips about Regular Expression
## 1. The syntax and conventions table

|Meta character| Description | Examples  |
|--|--|--|--|
|.  | Normally matches any character except a newline. ||
| \d | a digit: 0-9 ||
| \D |a non-digit;||
| \s | Matches a whitespace character, ||
| \S | Matches anything BUT a whitespace.||
| \w | Matches an alphanumeric character, including "_"||
| \| | The choice (also known as alternation or set union) operator matches either the expression before or the expression after the operator. For example,  `abc|def`  matches "abc" or "def".|
| [..] | A set of possible character matches.|
| () |Groups a series of pattern elements to a single element.  When you match a pattern within parentheses, you can use any of `$1`, `$2`, … later to refer to the previously matched pattern.|

## 2. Quantification
|Meta character| Description | Examples  |
|--|--|--|--|
|?| indicates _zero or one_ occurrences of the preceding element ||
| * | indicates _zero or more_ occurrences of the preceding element ||
| + |indicates _one or more_ occurrences of the preceding element||
| {n} | The preceding item is matched exactly  _n_  times ||
| {min,} | The preceding item is matched _min_ or more times||
| {min,max}| The preceding item is matched at least _min_ times, but not more than _max_ times.||

## 3. Nick name
|Meta character| Description | Examples  |
|--|--|--|--|
|?p\<xxx>| give the matched characters a nick name| (?P<time>[\d\s\-\.:]{0,25})|

## 4. Special characters
|Meta character| Description | Examples  |
|--|--|--|--|
|\|| Boolean "or"|`gray|grey` can match "gray" or "grey".|
|()| grouping|`gray|grey` and `gr(a|e)y` are equivalent patterns which both describe the set of "gray" or "grey".|

Reference  [Regular expression](https://en.wikipedia.org/wiki/Regular_expression "Regular expression")
