package blockchain;
public class Runner {
    private final int numOfBlocks;

    public Runner(int numOfBlocks) {
        this.numOfBlocks = numOfBlocks;
    }

    public void StartChain() {

        // Initialize the blockchain with a specified number of blocks
        BlockChain myBlockchain = new BlockChain();

        for (int i = 0; i < numOfBlocks; i++) {
            myBlockchain.addBlock();
            myBlockchain.printBlockChain(i);
        }

    }


}
