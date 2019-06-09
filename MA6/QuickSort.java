
public class QuickSort<T extends Comparable<T>> extends Sorter<T> 
{
	
	public Indexed<T> sort(Indexed<T> data) 
   {				
		sortHelper(data, 0, data.getSize() - 1);
      	
		return data;
	}	
	
	//MA6 TODO: implement recursive solution
	private void sortHelper(Indexed<T> data, int start_bound, int end_bound)
	{
      if(start_bound < end_bound) 
      {
         int pi = partition(data, start_bound, end_bound);
         
         sortHelper(data, start_bound, pi - 1);
         sortHelper(data, pi + 1, end_bound);
      }
	}
   
   private int partition(Indexed<T> data, int start_bound, int end_bound) 
   {
      //int middle_bound = end_bound / 2;
      int middle_bound = end_bound >>> 1;
      
      T partition, temp;
      
      //save the first, middle and last value in the Index and find the medium value to use as partition
      T a = data.getElementAt(start_bound);
      T b = data.getElementAt(middle_bound);
      T c = data.getElementAt(end_bound);
      
      //finds median of a,b, and c
      if((a.compareTo(b) <= 0 && a.compareTo(c) >= 0) || (a.compareTo(b) >= 0 && a.compareTo(c) <= 0)) 
      {
         partition = a;
         data.removeElementAt(start_bound);  
      } 
      else if((b.compareTo(a) <= 0 && b.compareTo(c) >= 0) || (b.compareTo(a) >= 0 && b.compareTo(c) <= 0)) 
      {
         partition = b;
         data.removeElementAt(middle_bound);
      } 
      else 
      {
         partition = c;
         data.removeElementAt(end_bound);
      }
          
      int i = -1;
      
      //sorts values around the partition
      for(int j = 0; j < end_bound; j++) 
      {         
         if(data.getElementAt(j).compareTo(partition) <= 0) 
         {
            i++;
            temp = data.getElementAt(i);
            data.setElementAt(data.getElementAt(j), i);
            data.setElementAt(temp, j);
         }
      }
      
      i++;
      
      //moves partition to correct spot in Index
      if(i < data.getSize()) 
      {
         temp = data.getElementAt(i);
         data.setElementAt(partition, i);
         data.addElementAt(temp, i + 1);
      }
      
      return i;
   }
}