# MerkleTree

A small implementation of a Merkle Tree in Java, used to demonstrate a zero-knowledge proof.

Alice wants to prove to Bob that she knows P(x), where P is the x'th fibbonacci number.

Example
 x  : 0, 1, 2, 3, 4, 5, 6...
P(x): 0, 1, 1, 2, 3, 5, 8... 

Alice creates a Merkle Tree, where each leaf "x" contains H(P(x)), where H(x) is the SHA-256 hash.

Each node in the tree contains H(child_1 + child_2), the top node of the tree contains the root hash. 

The Merkle Tree can be used in two different ways, one interactive, and the other non-interactive.

Interactive
Alice creates a Merkle Tree for all x values between 0 and 1000.
Alice gives bob the root hash of the Merkle Tree.
Bob picks N random x values to check.
Alice provides bob with each leaf, and the hashes of the branches to each leaf, for each of the N solutions
Bob can verify that each of the hashes is correct, and that the solutions for each x value are correct. 
Bob can be confident that Alice really does know P(x), but he is not able to determine P(x) himself from the information he obtained

Non-Interactive
Alice creates a Merkle Tree for all x values between 0 and 1000.
Alice uses the root hash as the seed for a random number generator.
The random number generator chooses N random x values to check.
Alices publishes the root hash, and the hashes and branches for each leaf for all N solutions.
Anyone can verify that Alice really does know P(x), but he is not able to determine P(x) himself from the information he obtained

Tiny Test Case:
Proving Fibbonacci Numbers from 0-7;

Alice Genrates the following Tree:

           R
      N          N
  N     N     N      N
N   N  N  N  N  N  N  N
0   1  1  2  3  5  8  11

Then Publishes the Root of the Tree (in hexdecimal):
'Root: F002E9A28193F05D9CD71CAA00F7F073A443925D9B22D57A77FB2E870AF6411C'

Bob requests P(4) and P(7) Then Alice Publishes:

```P(4)
BB71C64CBA861C4973826B4215463173DF0907CE63A48C3E585D4C73A2224B3C , EA6EB12B99245ED37A93866053B5BB1823B10C099ADDD74CEBF19A88B0D6230D
1A5E2E2E2268CAE10384DE073C885484EC4544659EC5B5448BEAA124E2D15EFF , 02B96777AF0DC4948C1113CB00079305FA1FDECBB60F0B094609D3BCE664D00D
88185D128D9922E0E6BCD32B07B6C7F20F27968EAB447A1D8D1CDF250F79F7D3 , 221F8AF2372A95064F2EF7D7712216A9AB46E7EF98482FD237E106F83EAA7569
00000003
P(7)
BB71C64CBA861C4973826B4215463173DF0907CE63A48C3E585D4C73A2224B3C , EA6EB12B99245ED37A93866053B5BB1823B10C099ADDD74CEBF19A88B0D6230D
1A5E2E2E2268CAE10384DE073C885484EC4544659EC5B5448BEAA124E2D15EFF , 02B96777AF0DC4948C1113CB00079305FA1FDECBB60F0B094609D3BCE664D00D
17EB70034B5B71092521D184C5E7B069D47DE657E51AEF2BE11A00C115036943 , 8092FE01B9CE31A49CE380FC2FE1EE6B6B7D5C15C5F1ECB6AD98C60C4273A435```

Bob Verifies that P(4) is valid, starting from the leaf node, using H()=SHA-256()
P(4) is 3
H(3) is 221F8AF2372A95064F2EF7D7712216A9AB46E7EF98482FD237E106F83EAA7569 
This matches the right leaf at the bottom of the Tree. Then Bob verifies the tree is intact and untampered with by hashing the braches together up the tree.
H(88185D128D9922E0E6BCD32B07B6C7F20F27968EAB447A1D8D1CDF250F79F7D3 and 221F8AF2372A95064F2EF7D7712216A9AB46E7EF98482FD237E106F83EAA7569) gives us 02B96777AF0DC4948C1113CB00079305FA1FDECBB60F0B094609D3BCE664D00D. 
H(1A5E2E2E2268CAE10384DE073C885484EC4544659EC5B5448BEAA124E2D15EFF and 02B96777AF0DC4948C1113CB00079305FA1FDECBB60F0B094609D3BCE664D00D) gives us BB71C64CBA861C4973826B4215463173DF0907CE63A48C3E585D4C73A2224B3C.
H(BB71C64CBA861C4973826B4215463173DF0907CE63A48C3E585D4C73A2224B3C , EA6EB12B99245ED37A93866053B5BB1823B10C099ADDD74CEBF19A88B0D6230D) gives us the root F002E9A28193F05D9CD71CAA00F7F073A443925D9B22D57A77FB2E870AF6411C.

Bob Verifies that P(7) is valid, starting from the leaf node, using H()=SHA-256()
P(7) is 13
H(13) is 8092FE01B9CE31A49CE380FC2FE1EE6B6B7D5C15C5F1ECB6AD98C60C4273A435 
This matches the right leaf at the bottom of the Tree. Then Bob verifies the tree is intact and untampered with by hashing the braches together up the tree.
H(17EB70034B5B71092521D184C5E7B069D47DE657E51AEF2BE11A00C115036943 and 8092FE01B9CE31A49CE380FC2FE1EE6B6B7D5C15C5F1ECB6AD98C60C4273A435') gives us 02B96777AF0DC4948C1113CB00079305FA1FDECBB60F0B094609D3BCE664D00D. 
H(1A5E2E2E2268CAE10384DE073C885484EC4544659EC5B5448BEAA124E2D15EFF and 02B96777AF0DC4948C1113CB00079305FA1FDECBB60F0B094609D3BCE664D00D) gives us EA6EB12B99245ED37A93866053B5BB1823B10C099ADDD74CEBF19A88B0D6230D.
H(BB71C64CBA861C4973826B4215463173DF0907CE63A48C3E585D4C73A2224B3C , EA6EB12B99245ED37A93866053B5BB1823B10C099ADDD74CEBF19A88B0D6230D) gives us the root F002E9A28193F05D9CD71CAA00F7F073A443925D9B22D57A77FB2E870AF6411C.







