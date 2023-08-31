package blockchain;
import java.util.Date;

public class Block {
    private final long timeStamp;
    private final int id;
    private final String hash;
    private final String previousHash;

    public Block(int id, String previousHash) {
        this.id = id;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    public String calculateHash() {
        return StringUtil.applySha256(
                id +
                        Long.toString(timeStamp) +
                        previousHash);
    }
    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
