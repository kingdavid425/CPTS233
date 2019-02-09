import java.util.Vector;

public class MA2_main
{
    public static void main(String args[])
    {
        BucketHashTable<String, String> ht = new BucketHashTable<>(new SimpleStringHasher());
        MA2_main.hashTableTest(ht);
    }
    
    public static void hashTableTest(BucketHashTable<String, String> ht)
    {
      ht.addElement("I", "Love");
    	ht.addElement("CptS", "233");
    	ht.addElement("And", "I"); 
    	ht.addElement("especially", "love"); 
    	ht.addElement("Hashtables", "!"); 
    	    
    	//test inputs
    	//buckets is different than _items in HashTableBase, so we are using getBuckets() this time.
    	Vector<Vector<HashItem<String, String>>> buckets = ht.getBuckets();   
    	
    	// MA2 TODO
    	//Write your own test cases, and show that if they are correct or not. Print out any statement you'd like/need to.
    	//Does your test cases eventually test the resize function? If not, try to.
      if(buckets.elementAt(7).elementAt(0).getKey() != "I") {
         System.out.println("Key failure: I");
      } else System.out.println("Key success: I");
      
      if(buckets.elementAt(4).elementAt(0).getKey() != "CptS") {
         System.out.println("Key failure: CptS");
      } else System.out.println("Key success: CptS");
      
      if(buckets.elementAt(0).elementAt(0).getKey() != "And") {
         System.out.println("Key failure: And");
      } else System.out.println("Key success: And");    

      if(buckets.elementAt(0).elementAt(1).getKey() != "especially") {
         System.out.println("Key failure: especially");
      } else System.out.println("Key success: especially");
      
      if(buckets.elementAt(0).elementAt(2).getKey() != "Hashtables") {
         System.out.println("Key failure: Hashtables");
      } else System.out.println("Key success: Hashtables");
    
    	//remove test
      ht.removeElement("I");
    	ht.removeElement("CptS");
    	ht.removeElement("And");
      ht.removeElement("especially");
      ht.removeElement("Hashtables");
      
      if(buckets.elementAt(7).elementAt(0).isEmpty()) {
         System.out.println("Remove success: I");
      } else System.out.println("Remove failure: I");
      
      if(buckets.elementAt(4).elementAt(0).isEmpty()) {
         System.out.println("Remove success: CptS");
      } else System.out.println("Remove failure: CptS");
      
      if(buckets.elementAt(0).elementAt(0).isEmpty()) {
         System.out.println("Remove success: And");
      } else System.out.println("Remove failure: And");
      
      if(buckets.elementAt(0).elementAt(1).isEmpty()) {
         System.out.println("Remove success: especially");
      } else System.out.println("Remove failure: especially"); 
      
      if(buckets.elementAt(0).elementAt(2).isEmpty()) {
         System.out.println("Remove success: Hashtables");
      } else System.out.println("Remove failure: Hashtables");    
    
      ht.addElement("I", "Lovely");
    	ht.addElement("CptS", "233");
    	ht.addElement("And", "I"); 
    	ht.addElement("especially", "love"); 
    	ht.addElement("Hashtables", "!");
      
      if(buckets.elementAt(7).elementAt(0).getValue() != "Lovely") {
         System.out.println("Key failure: I");
      } else System.out.println("Key success: I");
      
      if(buckets.elementAt(4).elementAt(0).getKey() != "CptS") {
         System.out.println("Key failure: CptS");
      } else System.out.println("Key success: CptS");
      
      if(buckets.elementAt(0).elementAt(0).getKey() != "And") {
         System.out.println("Key failure: And");
      } else System.out.println("Key success: And");    

      if(buckets.elementAt(0).elementAt(1).getKey() != "especially") {
         System.out.println("Key failure: especially");
      } else System.out.println("Key success: especially");
      
      if(buckets.elementAt(0).elementAt(2).getKey() != "Hashtables") {
         System.out.println("Key failure: Hashtables");
      } else System.out.println("Key success: Hashtables");  
    }
}