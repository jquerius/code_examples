import java.util.*;

public class PracticeProblems {

  public static void main(String[] args) {
    
    // Find the most frequest integer of an array 
    int[] arr = {0, 1, 2, 3, 3, 4, 5, 6, 6, 6, -1, -1, -1, -1};
    System.out.println("\n\nTEST CASE - Most frequent int: ");
    System.out.println(mostFrequentInt(arr));

    // Find pairs in an integer array whose sum is equal to 10
    System.out.println("\n\nTEST CASE - Pairs of Sum");
    int[] arr2 = {0, 10, 0, 33, 10, 10, 4, 6, 11, 32, 67};
    findPairsOfSum(arr2, 100);

    // Determine whether two arrays are the same, just rotated 
    System.out.println("\n\nTEST CASE - Rotated Arrays");
    int[] arrNormal = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int[] arrRotated = { 6, 7, 8, 9, 1, 2, 3, 4, 5 };
    findRotatedArray(arrNormal, arrRotated);
    int[] arrBad = { 1, 2, 3, 4, 5, 6, 7, 8, 10 };
    findRotatedArray(arrBad, arrRotated);

    // Write fibbonacci sequence recursively and iteratively 
    System.out.println("\n\nTEST CASE - Fibbonacci Sequence (Recursive)");
    fibbonacciR(0, 1);

    System.out.println("\n\nTEST CASE - Fibbonacci Sequence (Iterative)");
    fibbonacciR(0, 1);

    // Use dynamic programming to find the first X prime numbers
    System.out.println("\n\nTEST CASE - Find Prime Number with DP");
    Prime p = new Prime(); 
    p.findPrimeNumbers(10);

    System.out.println("\n\nTEST CASE - Find Prime Number with DP (Top-Down)");
    Prime t = new PrimeTopDown();
    t.findPrimeNumbers(10);

    System.out.println("\n\nTEST CASE - Print a binary function from an int!");
    printBinary(37);

    System.out.println("\n\nTEST CASE - Build ParseInt from scratch!");
    System.out.println(parseInt("37"));

    System.out.println("\n\nTEST CASE - Find the square root!");
    System.out.println(squareRoot(64));
    System.out.println(squareRoot(7));


    System.out.println("\n\nTEST CASE - Check if string is palindrome!");
    System.out.println(isPalindrome("racecaar") ? "String is a palindrome" : "Nope");

    System.out.println("\n\nTEST CASE - Justify string!");
    System.out.println(justifyString("Hello. I love you", 20));
  }

  /* 
    Given a single-line text String and a maximum width value, 
    write the function 'String justify(String text, int maxWidth)' 
    that formats the input text using full-justification, i.e., 
    extra spaces on each line are equally distributed between the words; 
    the first word on each line is flushed left and the last word 
    on each line is flushed right. 
  */
  public static String justifyString(String s, int maxWidth) {
    String result = "";
    String[] words = s.split("\\s+");

    int charWordCount = 0;
    for(String word : words) {
      charWordCount += word.length();
    }

    // Create a list of entries for all the words and spaces that will be written  
    List<String> wordsFinal = new LinkedList<>();
    for(int i = 0; i < words.length; i++) {
        wordsFinal.add(words[i]);
        if(i < words.length - 1) {
          wordsFinal.add(" ");
        }
    }
    
    // How many space nodes are there? 
    int additionalSpaces = maxWidth - charWordCount;
    int spaceNodes = words.length - 1;
    
    // If there are less spaces than nodes, add one space to random nodes (no same node twice)
    int index = 1;
    for(int i = 1; i < wordsFinal.size(); i += 2) {
      wordsFinal.set(i, wordsFinal.get(i) + " ");
      additionalSpaces--;
    }

    for(String word : wordsFinal) {
      result += word;
    }

    return result;
  }

  public static boolean isPalindrome(String s) {
    for(int i = 0; i < s.length(); i++) {
      char a = s.charAt(i);
      char b = s.charAt(s.length() - i - 1);
      if(a != b) return false;
    }

    return true;
  }

  public static boolean isSquare(int n, int input, int currAnswer) {
    return (n * n == input ||
      (n * n < input && n > currAnswer));
  }

  public static int squareRoot(int input) {
    int answer = 0;

    // Binary search solution 
    int start = 0;
    int end = input; 
    
    while(end - start > 0) {
      int mid = end - start / 2;
      if(mid * mid == input) 
        return mid;
      else if(mid * mid > answer && mid * mid < input) {
        answer = mid;
      }
      end = mid - 1;
    }

    return answer;
  }

  public static int parseInt(String s) {
    int result = 0;
    for(int i = 0; i < s.length(); i++) {
      int c = ((int) s.charAt(i)) - 48; // 48 is the offset to 0 in ascii values 
      int digit = (s.length() - i - 1) * 10;
      if(digit == 0) digit = 1;
      result += c * digit;
    }
    return result;
  }

  public static void printBinary(int n) {
    String result = "";
    while(n > 0) {
      result = (n & 1) + result;
      n >>= 1;
    }
    System.out.println(result);
  }

  public static class Prime {

    public boolean isPrime(int n) {
      if(n <= 1) return false;
      for(int i = 2; i < n; i++) {
        if(n % i == 0 && i < n) {
          return false;
        }
      }
      return true;
    }
  
    public void findPrimeNumbers(int n) {
      int prime = 0;
      for(int i = 0; i < n; i++) {
        while(!isPrime(prime)) {
          prime++;
        }
        System.out.print(prime + ",");
        prime++;
      }
    }
  }

  public static class PrimeTopDown extends Prime {
    
    public PrimeTopDown() {
      super();
    }

    int[] map = null;
    
    public PrimeTopDown(int n) {
      map = new int[n];
      findPrimeNumbersTopDown(n);
    }
    
    public int findPrimeNumbersTopDown(int n) {
      // No 0th prime number
      if(n == 0) return -1; 
      if(n == 1) return 2;
      
      // If no prime has been mapped and n isn't 0, find it 
      if(map[n] == 0 && n > 0) {
        int prevPrime = findPrimeNumbers(n - 1);
        int prime = prevPrime + 1; 
        while(!isPrime(prime)) {
          prime++;
        }
        map[n] = prime;
        return prime;
      }
    }
  }

  public static void fibbonacciR(int a, int b) {
    if(b > 144) { 
      System.out.println("");
      return;
    }
    System.out.print(a + b + ",");
    fibbonacciR(b, a + b);
  }

  public static void fibbonacciW(int a, int b) {
    while(b < 144) {
      int f = a + b;
      a = b;
      b = a + b;
      System.out.print(f + ","); 
    }
    System.out.println("");
  }

  public static void findRotatedArray(int[] arr1, int[] arr2) {
    // Find start of first array 
    int startingNum = arr1[0];
    // Find start of second array 
    int matchingIndex = Integer.MAX_VALUE;
    for(int i = 0; i < arr2.length; i++) {
      if(arr2[i] == startingNum) {
        matchingIndex = i;
      }
    }

    if(matchingIndex == Integer.MAX_VALUE) 
      System.out.println("No rotated array found.");
    else {
      // Iterate the first array and find matching elements for second 
      for(int i = 0; i < arr1.length; i++) {
        int secondIndex = (matchingIndex) % arr1.length;
        if(arr1[i] == arr2[secondIndex]) {
          matchingIndex++;
        } else {
          System.out.println("Arrays are not rotated.");
          return;
        }
      }

      System.out.println("Arrays are rotated.");
      return;
    }

  }

  public static void findPairsOfSum(int[] arr, int sum) {
    
    int[] m = new int[sum + 1];
    for(int i = 0; i < arr.length; i++) {
      int first = arr[i];
      // Keep going if we encounter an int that is sum or larger 
      if(first > sum) continue;
      // Record frequency of numbers in array
      m[first]++;
    }

    for(int i = 0; i < sum; i++) {
      // Find a pair such that their sum is equal to the sum 
      if(m[i] > 0 && m[sum - i] > 0) {
        // If duplicates exist, take note of that and print them 
        int minPairs = m[i] < m[sum - i] ? m[i] : m[sum - i];
        for(int j = 0; j < minPairs; j++) {
          System.out.println("Pair: (" + i + "," + (sum - i) + ")");
          m[i]--;
          m[sum]--;
        }    
      }
    }
  }


  public static int mostFrequentInt(int[] arr) {
    Map<Integer, Integer> freq = new HashMap<>();
    Map.Entry<Integer, Integer> largest = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
    
    for(int i = 0; i < arr.length; i++) {
      int num = arr[i];
      Integer numberFrequency = freq.get(num);
      
      if(numberFrequency == null) {
        numberFrequency = 1;
        freq.put(num, numberFrequency);
      } else {
        freq.put(num, ++numberFrequency);
      }

      if(numberFrequency > largest.getValue()) {
        largest = new AbstractMap.SimpleEntry<Integer, Integer>(num, numberFrequency);
      }
    }
    return largest.getKey();
  }


}