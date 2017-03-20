### App4

Topics: The Traveling Salesperson Problem (TSP), Minimum Spanning Trees (MST), Heaps and Graphs

#### Task1

This task implements an algorithm to find approxiamtion solution for Traveling Salesperson Problem in polynomianl time. 

The data file to be explored is the crime data used before with coordinates of the crimes.

The program will prompt the user for two offsets into the file. If the user enters 0 and 2 as input, the program will find an approximate TSP tour that visits the first three crimes on the file (0, 1 and 2). If the user enters 10 and 20 as input, your program will find an approximate TSP tour that visits 11 crime locations starting from the eleventh record (position 10) and proceeding through the twenty first record on file
(10,11,12,…20). The root of the minimum spanning tree will always be the first index. 

The output of the program will include a list of the crime records processed by your TSP approximation algorithm. It will provide a list of vertices showing a tour generated from the Approx-TSP-Tour algorithm shown above. It will also show the length of the tour. So, if the user enters 2 and then 10 as input, the solution will be a list of vertices numbered from 0 and ending at 8.

#### Task2

This task is to find an optimal tour using this brute force approach. Note that there are |V| ! permutations of the |V| vertices. Each of these is a different Hamiltonian cycle. But half of these are the same cycle travelled in a different direction.

#### Task3

This task is to generate a KML file that contains both tours (the first tour will be shown in blue and the second will be shown in red). This KML file, when loaded into Google Earth, will show both tours. 