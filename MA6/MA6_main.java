import java.util.Random;

public class MA6_main {

	public static void main(String[] args) {
		
		//generate large list of random number
		Random randomGenerator = new Random();
		Vector<Integer> numbers_base = new Vector<>(3000);
		
		//for testing purpose, I suggest you start with fewer elements to sort.
		for (int i = 0; i < 3000; i ++)
		{
			numbers_base.addElement(randomGenerator.nextInt(10000));
		}
		
		//instantiate sorters
		QuickSort<Integer> quick = new QuickSort<>();
		MergeSort<Integer> merge = new MergeSort<>();
		
		//MA6 TODO: extra credits: radix sort
		//RadixSort<Integer> radix = new RadixSort<>();
		
		//run benchmarks
		runBenchmark("Quick sort", quick, numbers_base);
		runBenchmark("Merge sort", merge, numbers_base);
	}
	
	public static long indexedBenchmark(Sorter<Integer> sorter, Indexed<Integer> data)
	{
		//start time
		long startTime = System.currentTimeMillis();
		
		sorter.sort(data);
		
		long endTime = System.currentTimeMillis();
		
		return endTime - startTime;
	}
	
	public static long runBenchmark(String sort_name, Sorter<Integer> sorter, Vector<Integer> data)
	{
		//copy values into temp array first
		Vector<Integer> temp = new Vector<>(data.getSize());
		for(int i = 0; i < data.getSize(); i ++)
			temp.addElement(data.getElementAt(i));
		
		System.out.println("Using " + sort_name + " to sort " + data.getSize() + " elements...");
		for (int i = 0; i < data.getSize(); i ++)
		{
			System.out.print(temp.getElementAt(i).toString() + ",");
		}
		System.out.println();
		System.out.println("-----------------------------");
		
		long result = indexedBenchmark(sorter, temp);
		System.out.println("Result: " + result + "ms");
		for (int i = 0; i < data.getSize(); i ++)
		{
			System.out.print(temp.getElementAt(i).toString() + ",");
		}
		System.out.println();
		System.out.println("==============================");
		return result;
	}

}
