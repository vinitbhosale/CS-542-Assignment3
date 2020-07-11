
# CSX42: Assignment 3
**Name:Vint Surendra Bhosale** 

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Doutput1="output1.txt" -Doutput2="output2.txt" -Doutput3="output3.txt" -Derror="error.txt" -Ddebug="debugValue"
```
Note: Arguments accept the absolute path of the files.


## Description:
1. Assumption
- Format of line in input file should be as given format:
    <B_NUMBER>:<FIRST_NAME>,<LAST_NAME>,<GPA>,<MAJOR>,[<SKILL>,[<SKILL>, ...]]
- After (:) valuse are separated by coma(,) No spaces after and before coma(,)
- Bnumber should be 4 digit positive integer.
- Max number of skill that will be listed in the input file will be 10.
- Lines in input file can have repeated bnumbers.
- Format of line in modify file should be as given format:
    <REPLICA_ID>,<B_NUMBER>,<ORIG_VALUE>:<NEW_VALUE>
- Before (:) valuse are separated by coma(,) No spaces after and before coma(,)
- Modification file will not have any request to change the GPA.
- Order of input arguments to run command is input.txt, modify.txt, output1.txt,
  output2.txt, output3.txt, debug value.

2. Data structures:
- String are used to append the results that need to display.
- Set are used to store skills.
- List is used to store trees.

3. External Materials:
- Used BST algorithms reference:
    https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/

4. Code working:
- Input file is read line by line in the InputDataProcessor and values are split
  on (:) and are stored in respective variable.
- All values are passed to StudentRecord to store the values of student and object
  of that student is created.
- Created student obj is passed to TreeHelper to create node by sending student
  record to Node class to create Node and is return to clone that node for other
  tress.
- After cloning node observers of those nodes are registered and then send to Tree
  to insert node in tree to create tree using BST algorithm.
- After inserting node and creating tree Modify file is read line by line in
  InputDataProcessor and values are split at (:) and are stored in respective
  variables.
- Modified information is passed to TreeHelper to search the node based on 
  bNumber and update the node information.
- After updating node notify its observers to update their nodes.
- After all insert and update all trees are stored in Results and are printed
  on StdOut and in respective output file.

5. Note:
- I have used 1 slack day from 5 slack days.  



## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 07/11/2020


