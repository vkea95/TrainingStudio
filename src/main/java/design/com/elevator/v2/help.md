# 电梯系统设计

## 参考链接
 * 面试流程分析: http://www.hiringlibrary.com/object-oriented-design-interview-for-parking-lot-or-elevator/
 * 另一个链接:https://www.careercup.com/question?id=1808669

## Tips

### 要熟知OO设计原则: The SOLID Principals of Object Oriented Design ?
 * S: Single Responsibility Principle | Single responsibility to each Object
    * http://www.oodesign.com/single-responsibility-principle.html
    * https://www.javacodegeeks.com/2011/11/solid-single-responsibility-principle.html
 * O: Open/Closed Principal  | On production level Objects are ready for extension for not for modification
    * http://www.oodesign.com/open-close-principle.html
    * https://en.wikipedia.org/wiki/Open/closed_principle
 * L: Liskov Substitution Principal | Base Class and Derived class follow ‘IS A’ principal
    * http://stackoverflow.com/questions/56860/what-is-the-liskov-substitution-principle
    * http://www.oodesign.com/liskov-s-substitution-principle.html
 * I: Interface segregation principle | If an implementation don’t require then don’t implement it. 接口隔离
    * http://efectivejava.blogspot.in/2013/09/interface-segregation-principleisp-java.html
 * D: Dependency Inversion principle | Reduce the dependency In composition of objects.
    * http://stackoverflow.com/questions/62539/what-is-the-dependency-inversion-principle-and-why-is-it-important

## Example

### Time limit & discussion
Interviewer and Applicants spent 1 hour on this discussion, During this discussion – applicant took the major ownership. He asked various questions before actually writing the Object Oriented Design of ‘elevator system’. He shown examples and thought process that where and how such SOLID principles would be applied to design the Object Oriented Design of ‘elevator system’,

Here is one snapshot of the code which applicant written, and that discussed during the whole 60 Minutes of face to face Object Oriented Design Interview .

### Make sure reqirements

    * How many floors : 10 floors – 1st floor lobby / 2nd floor parking garage
    * How entry/exit works : Enter to the building /exit building – happens from 1st or 2nd
    * Number of Elevators : Three elevators – no limitations 1 to 10 , people are sitting from 1 to 10
    * Design Approach
    * Certain requirement we can talk in between / main thing is to design the system
    * Assumption: Lets say we are in the floor-2 / we would like to call the elevator;

### Git source:https://github.com/joeblau/sample-elevator-control-system/tree/master/src
## Intelij Idea 设置
 * Ref:http://www.cnblogs.com/ShaYeBlog/p/4540915.html