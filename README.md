CustomJunit3
============


In Junits the execution order of test cases being run by Junit runner depends on the list being returned by JVM 
at the runtime . This list is returned using java reflection mechanism using Class.getDeclaredMethods() . 


Earlier list being returned is as same order as the test cases were written till JDK 1.6 ,
But now in Java 7 oracle has stream lined the same and follows the specs which says Class.getDeclaredMethods() 
will return an un-order list of methods . 


Ideally each test case should be an independent unit and should not dependent on any other test cases.


But while bad practice followed while designing the Unit tests many of the Junits tests are made 
dependent on each other .
But with thousands of test cases it becomes practically no feasible to understand and modify .



This Project maintains ordering in of tests in Junit 3
