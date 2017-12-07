import java.security.MessageDigest;

public class Node
{
	private byte[] data;
	private byte[] hash;
	private Node left;
	private Node right;
	private boolean isLeaf;
    
	// creates a branch node
	public Node (Node left, Node right){
		this.left = left;
		this.right = right;
		data = null;
		isLeaf = false;
		createHash();
		
	}
	
	// creates a leaf node
	public Node (byte[] data){
		left = null;
		right = null;
		this.data = data;
		isLeaf = true;
		createHash();
	}
	
	public byte[] getData(){
		return data;
	}
	
	public Node left(){
		return left;
		
	}
	
	public Node right(){
		return right;
	}
	
	public byte[] getHash(){
		return hash;
		
	}
	
	public void createHash(){
		try{
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		if(isLeaf){
			md.update(data);
			hash = md.digest();
		}
		else{
			byte[] leftHash = left.getHash();
			byte[] rightHash = right.getHash();
			md.update(leftHash);
			md.update(rightHash);
			hash = md.digest();
		}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public boolean isLeaf(){
	    return isLeaf;
	
	   }
   
}