using static library.Node;


namespace library {
    public interface IBinarySearchTree {

        /// <summary>
        /// Get the total depth of the tree.
        /// </summary>
        /// <param name="n"></param>
        /// <returns>The number of levels that are in the tree</returns>
        int GetTreeHeight();

        /// <summary>
        /// Level-order print the tree so that we can view its contents.
        /// </summary>
        void PrintTree();

        /// <summary>
        /// Add a node to the tree with a given value.
        /// </summary>
        /// <param name="value"></param>
        void Add(int value);

    
    }
}