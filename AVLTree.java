package AVL;

public class AVLTree {

	 AVLNode root = null;

	 public void display( ){
		 display( root );
	 }

	 public void add( int x ){
		 root = add( x, root );
	 }
	 public void delete( int x ){
		 root = delete( x, root, root );
	 }

	//Add function for a the tree
	 private AVLNode add( int x, AVLNode nptr ){
		 if( nptr == null ) {
			 return new AVLNode(x, null, null);
		 }

		 if( x < nptr.data ){
			 nptr.left = add( x, nptr.left );
			 if( height( nptr.left ) - height( nptr.right ) == 2 )
				 if( x < nptr.left.data )
					 nptr = rotateLeft( nptr );
				 else
					 nptr = doubleLeft( nptr );
			 }

		 else if( x > nptr.data ){
			 nptr.right = add( x, nptr.right );
			 if( height( nptr.right ) - height( nptr.left ) == 2 )
				 if( x > nptr.right.data)
					 nptr = rotateRight( nptr );
				 else
					 nptr = doubleRight( nptr );
		 }

		 nptr.height = Math.max( height( nptr.left ), height( nptr.right )) + 1;
		 return nptr;
	 }

	 //Delete function for the tree
	 private AVLNode delete( int x, AVLNode nptr, AVLNode nptr_parent){

	 	//If the key isn't found it will return a null value
	 	if (nptr == null){
		 	System.out.println("Key not Found");
		 	return null;
		 }
		 if (nptr != null) {
			 if (x == nptr.data) {
				 System.out.println("Key is found for deletion.");
				 System.out.println("nptr.data : " + nptr.data);
				 System.out.println("nptr_parent.data : " + nptr_parent.data);

				 //If the one that we want to delete has no children
				 if (nptr.left == null && nptr.right == null) {
					 if (nptr == root) {
						 root = null;
						 nptr_parent = null;
					 }
					 if (nptr_parent.left == nptr){
					 	nptr_parent.left = null;
					 } else if (nptr_parent.right == nptr){
					 	nptr_parent.right = null;
					 }
					 return null;
				 }

				 //If the one we want to delete has only one child
				 if ((nptr.left != null && nptr.right == null) || (nptr.left == null && nptr.right != null)) {

					 if (nptr == root) {
						 if (nptr.right != null) {
							 root = nptr.right;
						 } else {
							 root = nptr.left;
						 }
						 return null;
					 }
					 if (nptr_parent.left == nptr) {
						 if (nptr.right != null) {
							 nptr_parent.left = nptr.right;
							 nptr.right = null;
						 } else {
							 nptr_parent.left = nptr.left;
							 nptr.left = null;
						 }
						 nptr_parent.left = rotate(nptr_parent.left);
						 return nptr_parent.left;
					 } else if (nptr_parent.right == nptr) {

						 if (nptr.right != null) {
							 nptr_parent.right = nptr.right;
							 nptr.right = null;
						 } else {
							 nptr_parent.right = nptr.left;
							 nptr.left = null;
						 }
						 nptr_parent.right = rotate(nptr_parent.right);
						 return nptr_parent.right;
					 }
				 }

				 //If the node we want to delete has two children
				 if(nptr.left != null && nptr.right != null){
				 	AVLNode nptr_successor = successor(nptr);
				 	AVLNode nptr_successor_parent = root;
				 	while (nptr_successor_parent.right != nptr_successor && nptr_successor_parent.left != nptr_successor) {
				 		if(nptr_successor.data > nptr_successor_parent.data){
				 			nptr_successor_parent = nptr_successor_parent.right;
						} else {
							nptr_successor_parent = nptr_successor_parent.left;
						}
					}
					delete(nptr_successor.data, nptr_successor,nptr_successor_parent);

				 	nptr_successor.height = nptr.height;
					 if (nptr == root) {
					 	nptr_successor.right = root.right;
					 	nptr_successor.left = root.left;
					 	root.left = null;
					 	root.right = null;
					 	root = nptr_successor;
					 }
					 else {
					 	if(nptr.right != nptr_successor){
							nptr_successor.right = nptr.right;
						} else {
					 		nptr_successor.right = null;
						}
					 	nptr_successor.left = nptr.left;
					 	nptr.left = null;
					 	nptr.right = null;
					 	nptr = nptr_successor;
					 }

					 nptr_successor = rotate(nptr_successor);

					 return nptr_successor;


				 }

				 }
			 }

		 	//This is the recursive call for the delete function
			 if (x < nptr.data) {
				 if (height(nptr.left) > height(nptr.right)){
					 nptr.height = Math.max( height( nptr.left ), height( nptr.right ));
				 }
				 nptr.left = delete(x, nptr.left, nptr);
				 nptr = rotate(nptr);
			 } else if (x > nptr.data) {
				 if (height(nptr.left) < height(nptr.right)){
					 nptr.height = Math.max( height( nptr.left ), height( nptr.right ));
				 }
				 nptr.right = delete(x, nptr.right, nptr);
				 nptr = rotate(nptr);
			 }



		 return nptr;
	 }

	 //I made a seperate rotate function for deletion because it was a bit different from the add a node function
	 private AVLNode rotate(AVLNode nptr){

		 if (height(nptr.right) - height(nptr.left) == 2 || height(nptr.left) - height(nptr.right) == 2) {
			 if (height(nptr.right) > height(nptr.left)) {
				 if (nptr.right.right != null) {

					 nptr = rotateRight(nptr);
				 } else {

					 nptr = doubleRight(nptr);
				 }
			 } else {
				 if (nptr.left.left != null) {
					 nptr = rotateLeft(nptr);
				 } else {

					 nptr = doubleLeft(nptr);
				 }

			 }
		 }
		 return nptr;
	 }

	 private void display( AVLNode t ){
         if( t != null ){
        	 display( t.left );
        	 System.out.print( t.data + " ");
        	 display( t.right );
         }
     }

	 private int height( AVLNode t ){  // return height of node t, or -1, if null.
         if( t == null )
        	 return -1;
         else return t.height;
     }

	 private AVLNode rotateRight( AVLNode node1 ){
		 AVLNode node2 = node1.right;
		 node1.right = node2.left;
		 node2.left = node1;
		 node1.height = Math.max(height(node1.left), height(node1.right))+1;
		 node2.height = Math.max(height(node2.right), height(node1))+1;
		 return node2;
	 }

	 private AVLNode rotateLeft( AVLNode node2 ){
		 AVLNode node1 = node2.left;
		 node2.left = node1.right;
		 node1.right = node2;
		 node2.height = Math.max(height(node2.left), height(node2.right))+1;
		 node1.height = Math.max(height(node1.left), height(node2))+1;
		 return node1;
	 }
	 //Left-Right
	 private AVLNode doubleLeft( AVLNode node3 ){
		 node3.left = rotateRight( node3.left );
		 return rotateLeft( node3 );
	 }
	 //Right-Left
	 private AVLNode doubleRight( AVLNode node1 ){
		 node1.right = rotateLeft( node1.right );
		 return rotateRight( node1 );
	 }

	//This function finds the closest node larger than the one entered
	public AVLNode successor(AVLNode x) {
		AVLNode nptr_parent = x.right;
		AVLNode nptr = nptr_parent;
		while (nptr.left != null) {
			nptr_parent = nptr;
			nptr = nptr.left;
		}
		nptr_parent = null;
		return nptr;
	}
}
