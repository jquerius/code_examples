using System;
using Xunit;
using library;

namespace test_library
{
    public class UnitTest1
    {
        [Fact]
        public void TestAnagram() {
            Assert.True(new Thing().areAnagrams("cinema", "iceman"));
        }
        
    }
}
