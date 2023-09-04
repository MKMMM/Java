# Blockchain Implementation

For the initial stage of the project, the goal is to create a basic blockchain system. Each block in the blockchain has the following properties:

- **Id**: A unique identifier. The first block has an id of 1.
- **Timestamp**: Represents the creation time of the block in milliseconds since 1 January 1970.
- **Hash of the Previous Block**: A cryptographic hash reference to the preceding block.
- **Hash of the Block**: A cryptographic hash of the current block's content.

Below are some example blocks to illustrate the format:

```java
Block:
Id: 1
Timestamp: 1539810682545
Hash of the previous block: 
0
Hash of the block: 
796f0a5106c0e114cef3ee14b5d040ecf331dbf1281cef5a7b43976f5715160d

Block:
Id: 2
Timestamp: 1539810682557
Hash of the previous block: 
796f0a5106c0e114cef3ee14b5d040ecf331dbf1281cef5a7b43976f5715160d
Hash of the block: 
717242af079ccb7dd44c3f016936a81cf8ab2d4c1901243f30cbb7daa2060a0d

Block:
Id: 3
Timestamp: 1539810682558
Hash of the previous block: 
717242af079ccb7dd44c3f016936a81cf8ab2d4c1901243f30cbb7daa2060a0d
Hash of the block: 
28a2269bb34abd01dee9cea03400345bc9ea7322d73d3263221a47c6d970404f
```
