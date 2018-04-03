package AVL;

public class AVLTreeDemo {

	 public static void main( String [] args ){
		 AVLTree avl = new AVLTree();

		 System.out.println("------Performing 1st set of operation i.e. Adding to the tree-------");

		 avl.add(30);
		 avl.add(80);
		 avl.add(50);
		 avl.add(40);
		 avl.add(20);
		 avl.add(60);
		 avl.add(70);
		 avl.add(10);
		 avl.add(90);
		 avl.add(95);

		 System.out.println("------------------X----------X--------------");
	     System.out.println("Traversing in the tree: ");
         avl.display();
         System.out.println("\n------------------X----------X--------------");
         System.out.println("------Performing 2nd set of operation i.e. Adding to the tree-------");

         avl.add( 15 );
		 avl.add( 85 );

		 System.out.println("------------------X----------X--------------");
	     System.out.println("Traversing in the tree: ");
         avl.display();
		 System.out.println("");

         avl.delete(70);

         System.out.println("------------------X----------X--------------");
         System.out.println("Traversing in the tree: ");
         avl.display();
         System.out.println("");
		 }
}
