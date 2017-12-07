

import java.nio.ByteBuffer;

public class Tester
{
   
    public static MerkleTree myTree;
    
    public Tester(){
        int[] a = fibbon(32);
        myTree = makeTree(a);
        
        System.out.println(myTree.getData(0));
        System.out.println(myTree.getData(1));
        System.out.println(myTree.getData(2));
        System.out.println(myTree.getData(14));
        System.out.println(myTree.getData(30));
    }
    
    public int[] fibbon(int num){
        if(num<2) return null;
        
        int[] ans = new int[num];
        ans[0] = 0;
        ans[1] = 1;
        int i = 2;
        while(i<num){
            ans[i] = ans[i-1]+ans[i-2];
            i++;
        }
        return ans;
    }
    
    public MerkleTree makeTree(int[] a){
        
        byte[][] inputs = new byte[a.length][];
        for(int i = 0;i<a.length;i++){
            
            byte[] temp = ByteBuffer.allocate(4).putInt(a[i]).array();
            inputs[i] = temp;
        }
        return new MerkleTree(inputs);
        
    }
    
    
}