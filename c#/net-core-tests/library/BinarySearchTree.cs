using System;
using System.Collections.Generic;
using System.Linq;

namespace library
{

    public class BinarySearchTree : IBinarySearchTree 
    {
        private int v;

        Node root { get; set; }

        public BinarySearchTree() { }

        /// <summary>
        /// This constructor will setup a BST with one
        /// root node, given the value passed in.!-- 
        /// </summary>
        /// <param name="rootValue"></param>
        public BinarySearchTree(int rootValue)
        {
            this.root = new Node(rootValue);
        }

        /// <summary>
        /// This adds one node to its proper place in the
        /// tree.
        /// </summary>
        /// <param name="value"></param>
        public void Add(int value)
        {

            if (this.root == null)
                this.root = new Node(value);

            else
            {
                AddNode(root, value);
            }

        }

        private void AddNode(Node root, int value)
        {

            // When the node doesn't have a right child
            // and the value is greater, this node gets a new right
            // child with the value. 
            if (value > root.value && root.right == null)
            {
                root.right = new Node(value);
            }

            // If no left child and lesser value, then the 
            // current node gets a new left child with the value.
            else if (value < root.value && root.left == null)
            {
                root.left = new Node(value);
            }

            else
            {
                // Traverse right subtree if value is greater than current 
                if (value > root.right.value)
                {
                    AddNode(root.right, value);
                }

                // Traverse left subtree if value is less than current 
                else
                {
                    AddNode(root.left, value);
                }
            }
        }

        
        public int GetTreeHeight() {
            return GetTreeHeight(this.root);
        }

        private int GetTreeHeight(Node n) 
        {
            // If no node passed in, no height 
            if(n == null) return 0;

            // If this is a leaf node, return 1
            else if(n.left == null && n.right == null) return 1;

            var leftDepth = GetTreeHeight(n.left);
            var rightDepth = GetTreeHeight(n.right);

            // Find largest depth and add this curret
            var height = rightDepth > leftDepth ? rightDepth : leftDepth;
            return height + 1;
        }

        public void PrintTree()
        {
            var height = GetTreeHeight(this.root);

            // Level-order print the current tree
            for(var i = 0; i < height; i++) {
                PrintTree(this.root, i);
                Console.Write("\n");

            }

        }

        private void PrintTree(Node n, int level) {
            // Nothing to print!
            if(n == null) return;
            // This is the lowest level, we should print it 
            if(level == 0)
                Console.Write(n.value + " ");
            // Keep going down to the lowest level we can,
            // where we will then print the data 
            else if(level > 0) {
                PrintTree(n.left, level - 1);
                PrintTree(n.right, level - 1);
            }
            
        }
    }



}