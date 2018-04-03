package AVL;

public class AVLNode {
	int   data;   // Data in the node         
	AVLNode  left;     // Left child         
	AVLNode  right;    // Right child         
	int      height;   // Height   
		
    AVLNode( int data, AVLNode left, AVLNode right ) {
    	this.data     = data;
    	this.left     = left;
    	this.right    = right;
    	height   = 0;
    }
}
