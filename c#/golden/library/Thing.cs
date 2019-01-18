using System;
using System.Collections.Generic;
using System.Linq;

namespace library
{
    public class Thing
    {
        public int Get() {
            return 41;
        }

        public Boolean areAnagrams(string a, string b) {

            if(a.Length != b.Length) return false;

            var aChars = new List<char>(a.ToCharArray());
            var bChars = new LinkedList<char>(b.ToCharArray());

            for(var i = 0; i < aChars.Count; i++) {
                var bNode = bChars.Find(aChars[i]);
                if(bNode != null) 
                {
                    bChars.Remove(bNode);
                }
                else
                {
                    return false; 
                }
            }

            return bChars.Count == 0; 
        }
    }


    public class BST {

        class Node {
            public Node left {get; set;}
            public Node right {get; set;}
            public int value {get; set;}

            public Node(int value) {
                this.value = value;
            }
        }

        Node root {get; set;}

        public BST() {}

        public BST(int rootValue) {
            this.root = new Node(rootValue);
        }

        public void Add(int value) {
            
            if(this.root == null)
                this.root = new Node(value);
            
            else {
                AddNode(root, value);
            }
        
        }

        private void AddNode(Node root, int value) {
            
            if(value > root.value && root.right == null)
            {
                root.right = new Node(value);
            }

            else if(value < root.value && root.left == null)
            {
                root.left = new Node(value);
            }

            else 
            {
                if(value > root.right.value)
                {
                    AddNode(root.right, value);
                }
                else
                {
                    AddNode(root.left, value);
                }
            }
        }

        public void PrintTree() {
            Console.WriteLine($"{this.root.value},{this.root.left.value},{this.root.right.value}");
        }
    }
}
