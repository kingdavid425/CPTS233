import java.util.Vector;

public class MA1_main
{
    public static void main(String args[])
    {        
        LinearHashTable<String, String> ht = new LinearHashTable<>(new SimpleStringHasher());
        MA1_main.hashTableTest(ht);
    }
    
    public static void hashTableTest(LinearHashTable<String, String> ht)
    {
    	ht.addElement("I", "Love");
    	ht.addElement("CptS", "233");
    	ht.addElement("And", "I");
    	ht.addElement("especially", "love");
    	ht.addElement("Hashtables", "!");
    
    	//test inputs
    	Vector<HashItem<String, String>> items = ht.getItems();
    	
    	if (items.elementAt(1).getKey() != "especially")
    	{
    		System.out.println("Key failure: especially");    		
    	}
    	else
    	{
    		System.out.println("Key success: especially");    		
    	}
    
    	if (items.elementAt(7).getKey() != "I")
    	{
    		System.out.println("Key failure: I");    		
    	}
    	else
    	{
    		System.out.println("Key success: I");    		
    	}
    
    	if (items.elementAt(4).getValue() != "233")
    	{
    		System.out.println("Value failure: 233");    		
    	}
    	else
    	{
    		System.out.println("Value success: 233");    		
    	}
    
    	if (items.elementAt(2).getKey() != "Hashtables")
    	{
    		System.out.println("Key failure: Hashtables");    		
    	}
    	else
    	{
    		System.out.println("Key success: Hashtables");    		
    	}
    
    	//remove test
    	ht.removeElement("especially");
    	ht.removeElement("And");
    	items = ht.getItems();
    	if (items.elementAt(1).isEmpty() == true && items.elementAt(1).getKey() == "especially")
    	{
    		System.out.println("Remove key \"especially\" success.");    		
    	}
    	else
    	{
    		System.out.println("Remove key \"especially\" failure.");    		
    	}
    	if (items.elementAt(0).isEmpty() == true && items.elementAt(0).getKey() == "And")
    	{
    		System.out.println("Remove key \"And\" success.");    		
    	}
    	else
    	{
    		System.out.println("Remove key \"And\" failure.");    		
    	}
    }
}