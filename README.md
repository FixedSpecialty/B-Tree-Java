****************
* Project BTree 
* Class CS321
* Date December 11
* Osama Natouf - Ryan Mitchell - Andre Murphy 
**************** 
## OVERVIEW:
1- This Project represents an implementation for BTree DataStructure

2- BTree.java 
is the class that represents all of the BTree and is responsive for inserting, searching, splitting and printing the TreeNodes stored in the BTree. This file contains most of our logic.

3- TreeNode.java 
represents a node in the BTree and contains all of the keys, children and the byte offset of the parent. The metadata for our TreeNode contains n, the number of keys stored, and if it was a leaf.

4- TreeObject.java
contains the frequency of the DNA string and the long value that represents the DNA string. The object class represents the objects that are stored in the TreeNodes.

5- DataConversion.java
is the file where we created the binary conversions for the DNA strings. The conversion goes both ways between binary and long. The only data going in is A(00) C(01) G(10) T(11). 

6- FileParse.java
is the file created in order to scan through the given .gbk file containing the DNA string although that is not all the files contained so we had to write a method to take in ONLY the DNA string sequences. We were required to use a sliding window method which moves a given "window" or range of DNA and moves one by one.

7- GeneBankCreateBTree.java 
This is the driver we created in order to actually create a BTree and write it to a binary file. We did so my going through multiple arugement checks to set our BTree parameters and then wrote the output of the parameters to a binary file.

8- GeneBankSearch.java
The search driver is reliant on the create driver since a parameter of the search driver takes in a binary BTree file.

9- Cache.java
The cache file decreases running time by storing temporary tree nodes that can be read for faster data access than the binary file. This is because it takes a long time to read something from the disk. 

## INCLUDED FILES:
*BTree.java Source File

*Cache.java Source File

*DataConversion.java Source File

*FileParse.java Source File

*TreeNode.java Source File

*TreeObject.java Source File

*GeneBankCreateBTree.java (Driver class)

*GeneBankSearch.java (Driver class)

*README- this File

## COMPILING AND RUNNING:
The format to create a BTree on the command line is the following:
```
java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length> [<cache size>] [<debug level>]
```
The degree is the degree to be used for the BTree. If the user specifies 0, then your
program should choose the optimum degree based on a disk block size of 4096 bytes
and the size of your BTree node on disk.
The sequence length is an integer that must be between 1 and 31 (inclusive). The
query file contains all the DNA strings of a specific sequence length that we want to
search for in the specified btree file. The strings are one per line and they all must
have the same length as the DNA sequences in the btree file. The DNA strings use a,
c, t, and g (either lower or upper case).

The debug level is an optional argument with a default value of zero. It must at least
support the following levels for GeneBankCreateBTree:
```0``` Any diagnostic messages, help and status messages must be be printed on standard
error stream.
```1``` The program writes a text file named dump, that has the following line format:
DNA string: frequency. The dump file contains DNA string (corresponding to
the key stored) and frequency in an inorder traversal.
 ```
java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>] [<debug level>]
```
The debug level for GeneBankSearch supports the following.
```0``` The output of the queries should be printed on the standard output stream.
    
## PROGRAM DESIGN AND IMPORTANT CONCEPTS:
In the beginning of the project, we had to decide how to create our nodes and objects. It could have been possible to create our nodes with Linked Lists although we found it easier later into the project to do it with arrays since you could directly call node.n, node.key[i], etc... Another big design that we decided on was our metadata and node design. We settled on having our metadata store a boolean to check if node is a leaf, int to represent the amount of keys, and a long to represent the location of the node. An important piece of information was that we are not storing any metadata for the tree, only metadata for the roots. We decided it was redundant as the root of the tree contains the data of the entire tree.
## TESTING:
Our testing started out with creating a class that instantiated a Btree, created a LinkedList to reprent the parsed file and then looped through the linked list, adding the sequenced into the btree. We then created a print method that would display all of the DNA strings and the frequencies of each string.

Run speeds when using a cache/ no cache when creating the BTree:

    No cache (in milliseconds) = 526401
    
    Cache of size 100 (in milliseconds) = 278939
        A 247462 millisecond improvement.
        
    Cache of size 500 (in milliseconds) = 253550
        A 272851 millisecond improvement.
        
All of these tests were ran for "test3.gbk" with a sequence length of 6 and the optimal degree. From these run times it is evident that the cache is a useful tool that can almost cut the runtime in half. Note: The 100 and the 500 size caches did not show that large of a difference because there was just over 100 nodes so the size 100 cache could almost hold all of the nodes.

Run speeds when using a cache/ no cache when using the the search driver:

    No cache (in milliseconds) = 41310
    
    Cache of size 100 (in milliseconds) = 40276
        A 1034 millisecond improvement.
        
    Cache of size 500 (in milliseconds) = 38414
        A 2896 millisecond improvement. 
        
## DISCUSSION:
## EXTRA CREDIT: N / A
 
