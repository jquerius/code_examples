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

            for(var i = 0; i < aChars.Length; i++) {
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
}
