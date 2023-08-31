package blockchain;
import java.util.ArrayList;

public class BlockChain {
    private ArrayList<Block> chain;

    // Create a constructor with an initial block
    public BlockChain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        // Generate first block with ID 1 and initial hash 0
        return new Block(1, "0");
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock() {

        // Collecting the information and adding a new block
        Block lastBlock = getLastBlock();
        int newId = lastBlock.getId() + 1;
        String previousHash = lastBlock.getHash();
        Block newBlock = new Block(newId, previousHash);
        chain.add(newBlock);
    }

    public boolean validateChain() {
        // Check the chain size and compare hashes of blocks together
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    public void printBlockChain(int index){

            System.out.println("Block:");
            System.out.println("Id: " + chain.get(index).getId());
            System.out.println("Timestamp: " + chain.get(index).getTimeStamp());
            System.out.println("Hash of the previous block:");
            System.out.println(chain.get(index).getPreviousHash());
            System.out.println("Hash of the block:");
            System.out.println(chain.get(index).getHash());
            System.out.println();
    }

}
