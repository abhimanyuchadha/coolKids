public class TestClass{

     public static void main(String []args){
        BloomFilter bf= new BloomFilter(40);// has to be adjusted as per the data size to get better accuracy 
        
        
        String inputSet[] =new String[] {"hello", "there", "how", "are", "you", "doing", "this", "is", "an",
            "implementation", "of", "bloomfilter", "hope", "it", "does", "not", "return", "false", "negative",
            "1", "2","3","4","5","6","7","8","9","10","11",
            
        };
        
        String testSet[] = new String[] {
          "mac", "windows", "operting", "system", "smarty", "pants", "master", "mutually","exclusive","from", "input", "set"  
        };
        
        addToBloomFilter(bf, inputSet);
        System.out.println("-----------------------test for false positives start-----------------------");
        testForFalsePositives(bf, testSet);
        System.out.println("-----------------------test for false positives end-----------------------");
        
        System.out.println("-----------------------test for false negative start-----------------------");
        testForFalseNegative(bf, inputSet);
        System.out.println("-----------------------test for false negative end-----------------------");
       // bf.printArray();
        
        
     }
     
     public static void addToBloomFilter(BloomFilter bf, String[] set){
         for(String input:set)
            bf.add(input);
     }
     
      public static void testForFalseNegative(BloomFilter bf, String[] set){
         for(String testCase:set)
           if(!bf.exists(testCase))
                System.out.println(testCase +"-->"+ bf.exists(testCase));
     }
     
     
      public static void testForFalsePositives(BloomFilter bf, String[] set){
         for(String testCase:set) {
             if(bf.exists(testCase))
                System.out.println(testCase +"-->"+ bf.exists(testCase));
         }
            
     }
     
}

class BloomFilter {
    
    int size;
    boolean bitArray[];
    
    public void printArray() {
        int counter=0;
        for(boolean x: bitArray) {
            
            if(x)
                System.out.println(counter + "-->"+x);
            counter++;
        }
    }
    
    public void add(String word) {
      //  System.out.println("hashone==>>"+hashOne(word)+" for word::"+word);
        bitArray[hashOne(word)]=true;
        bitArray[hashTwo(word)]=true;
        bitArray[hashThree(word)]=true;
    }
    
    public boolean exists(String word) {
        return bitArray[hashOne(word)] && bitArray[hashTwo(word)] && bitArray[hashThree(word)];
    }
    
    
    public BloomFilter(int size) {
        this.size=size;
        bitArray = new boolean[size];
    }
    
    public int hashOne(String input) {
        return Math.abs(input.hashCode()%size);
    }
    
    public int hashTwo(String input) {
        return Math.abs((input.hashCode()+2)*7%size);
    }
    
    public int hashThree(String input) {
        return Math.abs((input.hashCode()+3)*3%size);
    }
} 
