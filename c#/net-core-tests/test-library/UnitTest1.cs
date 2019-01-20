using System;
using Xunit;
using library;

namespace test_library
{
    public class UnitTest1
    {
        [Fact]
        public void TestAnagram() {
            Assert.True(new AnagramEval().areAnagrams("cinema", "iceman"));
        }
        
        [Fact]
        public void TestBinarySearchTree() {
            var tree = new BinarySearchTree(10);
            tree.Add(5);
            tree.Add(11);
            tree.PrintTree();
            Assert.True(tree != null);
        }
    }
}
