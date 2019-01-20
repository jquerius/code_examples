using System;
using System.Collections.Generic;
using System.Linq;

namespace library
{
    public class AnagramEval
    {
        public int Get() {
            return 41;
        }

        public Boolean areAnagrams(string a, string b) {

            if(a.Length != b.Length) return false;
            var frequency = new Dictionary<char, int>();
            
            foreach(var c in a.ToCharArray()) {
                int f;
                frequency.TryGetValue(c, out f);
                frequency[c] = ++f;
            }

            foreach(var c in b.ToCharArray()) {
                int f;
                frequency.TryGetValue(c, out f);
                f++;
                if(f % 2 != 0) return false;
            }

            return true; 
        }
    }
}
