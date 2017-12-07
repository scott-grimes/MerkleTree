import java.util.*;
public class MerkleTree
{
    // constructs a MerkleTree given a list of byte arrays where
    // each array of bytes is the data to be stored
    
    ArrayList<Node[]> tree;
    Node root;
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    int size;
    
    public MerkleTree (byte[][] data){
        tree = new ArrayList<Node[]>();
        size = data.length;
        int leafsSize = ((data.length+1)/2)*2;
        
        Node[] row = new Node[leafsSize];
        
        // add leaf nodes into our bottom row
        for(int i = 0;i<data.length;i++){
            
            row[i] = new Node(data[i]);
                
        }
        
        if(data.length%2==1){
            row[row.length-1] = new Node(data[data.length-1]);
        }
        
        tree.add(row);
        Node[] newRow;
        do{
            newRow = buildRow(tree.get(tree.size()-1));
            tree.add(newRow);
        }while(newRow.length>1);
        root = newRow[0];
        
    }
    
    // 
    public Node[] buildRow(Node[] prior){
        
        int priorLength = prior.length; // size of the previous row
        int size = (prior.length+1)/2;  //size of the current row
        
        Node[] row = new Node[size];
        
        int c_i = 0;
        int p_i = 0;
        
        do{
            row[c_i] = new Node( prior[p_i],prior[p_i+1] );
            c_i++;
            p_i+=2;
        }while(p_i<priorLength);
        
        // if there was an odd number, the last node is doubled up
        if(priorLength%2==1){
            
            row[row.length-1] = new Node(prior[prior.length-1],prior[prior.length-1]);
        }
        
        return row;
    }
    
       // returns data at node x, and the hashes of all branches leading to the node
    public String verifyData(int x){
        if(x>=size) return null;
        String ans = "";
        int depth = 0; 
        Node current = root;
        
        ans+= b2h(current.getHash())+"\n";// the root hash
        // the low and high leaf x-values of each branch at the current depth
        int low = 0;
        int high = size-1;
        while(!current.isLeaf()){
            ans+= b2h(current.left().getHash())+" , "+b2h(current.right().getHash())+"\n";
            int mid = low + (high - low)/2;
            
            if(x<=mid){
                high = mid;
                current = current.left();
            }
            else{
                low = mid+1;
                current = current.right();
            }
            
        }
        
        ans+= b2h(current.getData());
        return ans;
        
    }
    
    //only returns the data at leaf node x
    public String getData(int x){
        if(x>=size) return null;
        String ans = "";
        int depth = 0; 
        Node current = root;
        
        
        // the low and high leaf x-values of each branch at the current depth
        int low = 0;
        int high = size-1;
        while(!current.isLeaf()){
            
            int mid = low + (high - low)/2;
            
            if(x<=mid){
                high = mid;
                current = current.left();
            }
            else{
                low = mid+1;
                current = current.right();
            }
            
        }
        
        ans+= b2h(current.getData());
        return ans;
        
    }
    
    
    
    public static String b2h(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
    return new String(hexChars);
}

    
    
    
}