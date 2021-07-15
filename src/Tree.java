
public class Tree {

    private Node root;

    public Tree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    ////////////////////////////////////////////////////////////////////////////
    // method that insert values that comes from user
    public void insert(int data) {
        Node newNode = new Node(data);
        root = insert(root, newNode);
    }

    private Node insert(Node root, Node newNode) {
        // IF there is no tree, newNode will be the root, so just return it
        if (root == null) {
            return newNode;
        } // ELSE, we have a tree. Insert to the right or the left
        else {
            // Insert to the RIGHT of root
            if (newNode.getData() > root.getData()) {
                // Recursively insert into the right subtree
                // The result of insertion is an updated root of the right subtree
                Node temp = insert(root.getRight(), newNode);
                // Save this newly updated root of right subtree into p.right
                root.setRight(temp);
            } // Insert to the LEFT of root
            else {
                // Recursively insert into the left subtree
                // The result of insertion is an updated root of the left subtree
                Node temp = insert(root.getLeft(), newNode);
                // Save this newly updated root of left subtree into p.left
                root.setLeft(temp);
            }
        }
        // Return root of updated tree
        return root;
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(Node p, int data) {
        if (p == null) {
            return false;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (data == p.getData()) {
                return true;
            } else if (data < p.getData()) {
                return search(p.getLeft(), data);
            } else {
                return search(p.getRight(), data);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node p, int data) {
        Node node2delete, newnode2delete, node2save, parent;
        int saveValue;

        // Step 1: Find the p we want to delete
        node2delete = findNode(p, data);
        // If p is not found (does not exist in tree), we clearly cannot delete it.
        if (node2delete == null) {
            return p;
        }

        // Step 2: Find the parent of the p we want to delete
        parent = parent(p, node2delete);

        // Step 3: Perform Deletion based on three possibilities
        // **1** :  node2delete is a leaf p
        if (isLeaf(node2delete)) {
            // if parent is null, this means that node2delete is the ONLY p in the tree
            if (parent == null) {
                return null; // we return null as the updated root of the tree
            }
            // Delete p if it is a left child
            if (data < parent.getData()) {
                parent.setLeft(null);
            } // Delete p if it is a right child
            else {
                parent.setRight(null);
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **2** : node2delete has only a left child
        if (hasOnlyLeftChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some p
            // IF it is the left child of some p
            if (data < parent.getData()) {
                parent.setLeft(parent.getLeft().getLeft());
            } // ELSE it is the right child of some p
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **3** :  node2delete has only a right child
        if (hasOnlyRightChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some p
            // IF it is the left child of some p
            if (data < parent.getData()) {
                parent.setLeft(parent.getLeft().getRight());
            } // ELSE it is the right child of some p
            else {
                parent.setRight(parent.getRight().getRight());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **4** :  node2delete has TWO children.
        // Remember, we have two choices: the minVal from the right subtree (of the deleted p)
        // or the maxVal from the left subtree (of the deleted p)
        // We choose to find the minVal from the right subtree.
        newnode2delete = minNode(node2delete.getRight());
        // Now we need to temporarily save the data value(s) from this p
        saveValue = newnode2delete.getData();

        // Now, we recursively call our delete method to actually delete this p that we just copied the data from
        p = delete(p, saveValue);

        // Now, put the saved data (in saveValue) into the correct place (the original p we wanted to delete)
        node2delete.setData(saveValue);

        // Finally, return the root of the tree (in case the root got updated)
        return p;
    }

    ////////////////////////////////////////////////////////////////////////////
    //this method to find node that we want to delete
    public Node findNode(int data) {
        return findNode(root, data);
    }

    private Node findNode(Node root, int data) {
        if (root == null) {
            return null;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (data == root.getData()) {
                return root;
            } else if (data < root.getData()) {
                return findNode(root.getLeft(), data);
            } else {
                return findNode(root.getRight(), data);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public Node minNode(Node root) {
        if (root == null) {
            return null;
        } else {
            if (root.getLeft() == null) {
                return root;
            } else {
                return minNode(root.getLeft());
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public Node parent(Node p) {
        return parent(root, p);
    }

    private Node parent(Node root, Node p) {
        // Take care of NULL cases
        if (root == null || root == p) {
            return null; // because there is on parent
        }
        // If root is the actual parent of p p
        if (root.getLeft() == p || root.getRight() == p) {
            return root; // because root is the parent of p
        }
        // Look for p's parent in the left side of root
        if (p.getData() < root.getData()) {
            return parent(root.getLeft(), p);
        } // Look for p's parent in the right side of root
        else if (p.getData() > root.getData()) {
            return parent(root.getRight(), p);
        } // Take care of any other cases
        else {
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean isLeaf(Node node) {
        return (node.getLeft() == null && node.getRight() == null);
    }

    ////////////////////////////////////////////////////////////////////////////
    //method to check if the node has only left child
    public boolean hasOnlyLeftChild(Node node) {
        return (node.getLeft() != null && node.getRight() == null);
    }

    ////////////////////////////////////////////////////////////////////////////
    //method to check if the node has only right child
    public boolean hasOnlyRightChild(Node node) {
        return (node.getLeft() == null && node.getRight() != null);
    }

    ////////////////////////////////////////////////////////////////////////////
    //method to sum values that inserted by user
    public int sum() {
        return (sum(root));
    }

    private int sum(Node root) {
        if (root == null) {
            return (0);
        } else {
            return root.getData() + sum(root.getLeft()) + sum(root.getRight());
        }
    }

}
