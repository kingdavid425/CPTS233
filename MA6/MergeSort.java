
public class MergeSort<T extends Comparable<T>> extends Sorter<T> {
	
	//MA6 TODO: IMPLEMENT
	public Indexed<T> sort(Indexed<T> data) 
   {	
      sort(data, 0, data.getSize() - 1);
          
		return data;
	}
   
   public void sort(Indexed<T> data, int left, int right)
   {
      if(left < right)
      {
         int middle = (left + right) / 2;
         
         sort(data, left, middle);
         sort(data, middle + 1, right);
         
         merge(data, left, middle, right);
      }
   }
   
   public void merge(Indexed<T> data, int left, int middle, int right) 
   {
      //getElementAt(int index)
      //setElementAt(T item, int index)
      //Array dataArray = (Array) data;
      
      Indexed<T> data1, data2;
      
      int arraySize1 = middle - left + 1;
      int arraySize2 = right - middle;
      
      data1 = new Array<>(arraySize1);
      data2 = new Array<>(arraySize2);
      
      for(int i = 0; i < arraySize1; ++i)
      {
         data1.setElementAt(data.getElementAt(left + i), i);
      }
      
      for(int j = 0; j < arraySize2; ++j)
      {
         data2.setElementAt(data.getElementAt(middle + 1 + j), j);
      }
      
      int i = 0, j = 0, k = left;
      
      while(i < arraySize1 && j < arraySize2) 
      {
         if(data1.getElementAt(i).compareTo(data2.getElementAt(j)) <= 0)
         {
            data.setElementAt(data1.getElementAt(i), k);
            i++;   
         }
         else 
         {
            data.setElementAt(data2.getElementAt(j), k);
            j++;
         }
         k++;
      }
      
      while(i < arraySize1)
      {
         data.setElementAt(data1.getElementAt(i), k);
         i++;
         k++;
      }
      
      while(j < arraySize2)
      {
         data.setElementAt(data2.getElementAt(j), k);
         j++;
         k++;
      }
   }
}
