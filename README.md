# familytree

### build
```
gradle jar
```

### run
```
java -jar build/libs/familytree.jar
```

### usage
Implements the problem statement presented her 
https://paper.dropbox.com/doc/Backend-Coding-Challenge-2-zlm3DReYJVNEXuufLsFme, with 2 exceptioms
1. Removed add relationship command, as there are fixed of relationships in a family tree. The program here supports only son and daugher relationship, because they are one few set of instructions with which we can create any family tree.
2. Add gender (M for male and F for female) along with name in add person command.

### Example

Following are the input commands from command line
```
family-tree add person x F
family-tree connect y as son of x
family-tree connect z as son of x
family-tree connect a as son of y
family-tree count sons of x
family-tree count all sons of x
```

gives following output
```
adding person x
adding person y
adding person z
adding person a
1
2
```
