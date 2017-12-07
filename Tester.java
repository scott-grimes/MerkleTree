import java.nio.ByteBuffer;
public class Tester
{
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static MerkleTree myTree;
    
    public Tester(){
        int[] a = {1,3,3,7,8,8,9,9};
        myTree = makeTree(a);
        
        System.out.println(myTree.get(0));
    }
    
    public MerkleTree makeTree(int[] a){
        
        byte[][] inputs = new byte[a.length][];
        for(int i = 0;i<a.length;i++){
            ByteBuffer b = ByteBuffer.allocate(32);
            inputs[i] = b.putInt(a[i]).array();
        }
        return new MerkleTree(inputs);
        
    }
    
    
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
    return new String(hexChars);
}

    
}