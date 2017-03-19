### App2

This application is based on 2d trees. I use this data structure to store the crime records and implement several algorithms to manipulate the tree. 

- Find Points In Range: After the 2d tree is constructed, you can find the records in an input range. A list of 0 or more crimes is returned. These crimes occurred within the rectangular range specified by the four parameters. The pair (x1, y1) is the left bottom of the rectangle. The pair (x2, y2) is the top right of the rectangle. Result points will be written into a kml file and displayed in Google Map.
- Nearest Neighbor: After the 2d tree is constructed, you can find the nearest record with an input point. The distance in feet to the nearest node is returned.
- Different kinds of traversal printing the tree records: inorder, preorder, level order, post order.

The input crime records is a comma delimited text file containing crime records from the Pittburgh area. For each crime record, the file contains (X, Y) coordinate pairs in the state plane coordinate system. These (X, Y) coordinates are useful for calculating the distance between points (using the Pythagorean theorem). Each record also contains latitude and longitude coordinates. These coordinates are useful for displaying locations in GIS tools such as Google Earth.