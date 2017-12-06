import java.util.*;
public class MerkleTree
{
	int size;
    // constructs a MerkleTree given a list of byte arrays where
    // each array of bytes is the data to be stored
    
    ArrayList<Node[]> tree;
    
	public MerkleTree (byte[][] data){
		tree = new ArrayList<Node[]>();
		int size = ((data.length+1)/2)*2;
		
		Node[] row = new Node[size];
		
		// add leaf nodes into our bottom row
		for(int i = 0;i<data.length;i++){
			
			row[i] = new Node(data[i]);
				
		}
		
		// if there was an odd number, the last node is doubled up
		if(data.length%2==1){
			row[row.length-1] = new Node(data[data.length-1]);
		}
		
		tree.add(row);
		Node[] newRow;
		do{
			newRow = buildRow(tree.get(tree.size()-1));
		}while(newRow.length>1);
		
		
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
	
	// returns the node at the base of the tree in position x
	public Node getNode(int x){
		return tree.get(0)[x];
		
	}
	
	
}