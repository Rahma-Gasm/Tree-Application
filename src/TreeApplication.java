
import java.util.Scanner;

public class TreeApplication {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner input = new Scanner(System.in);
        int choice, numbers = 0;
        do {
            showMenu();// display menu that user will select from it
            choice = input.nextInt();
            if (choice == 1) {//this selection allows to user to construct tree
                System.out.print("Enter number to construct tree: ");
                numbers = input.nextInt();
                tree.insert(numbers);
                System.out.println("");
                System.out.println("The number is inserted successfully!");
                System.out.println("");

            } else if (choice == 2) {//this selection allows to user to search about value
                System.out.print("What value do you want to search for: ");
                int value = input.nextInt();
                if (tree.search(value)) {
                    System.out.println("");
                    System.out.println(value + " was found in the tree.");
                } else {
                    System.out.println("");
                    System.out.println(value + " was NOT found in the tree.");
                }
                System.out.println();

            } else if (choice == 3) {//this selection allows to user to delete value from tree
                System.out.print("What value do you want to delete: ");
                int value = input.nextInt();
                // First, check to see if value is actually in the tree.
                // IF it is in the tree, delete it.
                if (tree.search(value)) {
                    tree.delete(value);
                    System.out.println("");
                    System.out.println(value + " was successfully deleted from the tree.");
                } else {// ELSE, print message stating that no delete is needed (since value is not in the tree)
                    System.out.println("");
                    System.out.println(value + " is NOT in the tree (no delete needed).");
                }
                System.out.println();

            } else if (choice == 4) {//this selection will delete value that user entered by user
                if (tree.isEmpty()) {
                    System.out.println("");
                    System.out.println("Error: cannot perform sum (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println("");
                    System.out.println("The sum of all nodes is " + tree.sum() + ".");
                    System.out.println();
                }
            } else if (choice == 5) {// this selection exist from program
                System.out.println("");
                System.out.println(" >    Goodbye!");
            } else {// this selection excute if user enter choice doesn't exist
                System.out.println("");
                System.out.println(" >   Wrong selection. Try again.");
                System.out.println("");
            }

        } while (choice != 5);
    }

    public static void showMenu() {
        System.out.println("--------------------------------");
        System.out.println("--------    BST Menu    --------");
        System.out.println("--------------------------------");
        System.out.println("  1. Construct Tree             ");
        System.out.println("  2. Search for a node          ");
        System.out.println("  3. Delete a node              ");
        System.out.println("  4. Compute total sum          ");
        System.out.println("  5. Exit          ");
        System.out.println("--------------------------------");
        System.out.println("");
        System.out.print(" > Please enter your choice: ");
    }

}
