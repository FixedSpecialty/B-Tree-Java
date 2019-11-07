# BTreesROA
B Trees is a search tree
 - all elements on left <= elements on right
 - good structure to store huge amounts of data
 - good at minimize disk i/o operations
 - A BTree node may have many keys and children
 (#OF KEYS = #OF CHILDREN-1)

A B Tree is a rooted tree
1. every node conatins
a. n[x]: =  #of keeps
b. leaf[x]: true if x is a leaf node
c. keys are stored in non-decreasing order
     ie, key1[x] <= key2[x] <=..........keyn(x)[x] 
2. Each internal node x has n[x] +1 children
      denoted as C1[x], C2[x],.......Cn[x]+1^[x]
3. If Ki is any key in the subtree rooted at Ci[x], then k1<=key1[x] <= k2<=key2[x]<=.......<= kn[x] <= keyn[x] <= kn[x]+1
4. All leaves have the same depth, which is the height of the tree
5. A minimum degree t(>=2) of a BTree specifies a lower and upper bounds on #of children each node can have
6. Every node except the root must have at least t children and at most 2t children
7. The root can have only 1 loops but at most 2t-1 keeps
