namespace library
{
    /// <summary>
    /// This class helps us keep track of child nodes
    /// from the current node. The children can be 
    /// uninstantiated. If both are uninstantiated,
    /// this node is a leaf.
    /// </summary>  
    public class Node
    {
        public Node left { get; set; }
        public Node right { get; set; }
        public int value { get; set; }
        
        public Node(int value)
        {
            this.value = value;
        }
    }

}