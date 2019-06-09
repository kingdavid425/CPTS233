import java.util.List;
import java.util.Random;

public class MA7_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisjointSet<Integer> without_compression = new DisjointSet<>();
		DisjointSet<Integer> with_compression = new DisjointSet<>();
		with_compression.use_path_compression = true;
		
		disjointTest();
		
		//uncomment for benchmark. Don't have to pass for MA7.
		System.out.println("With compression: " + disjointSetBenchmark(with_compression, 50000) + "ms.");
		System.out.println("Without compression: " + disjointSetBenchmark(without_compression, 50000) + "ms.");
	}
	
	public static long disjointSetBenchmark(DisjointSet<Integer> data, int loop_count)
	{
		Random rng = new Random();
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < loop_count; i++)
		{
			int left = rng.nextInt(10000);
			int right = rng.nextInt(10000);
			data.union_with(left, right);
		}

		for (int i = 0; i < loop_count * 2; i++)
		{
			int left = rng.nextInt(10000);
			data.find(left);
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	//basic test function for disjoint sets
	public static void disjointTest()
	{
		DisjointSet<Character> items = new DisjointSet<>();
		
		//add a bunch of starter sets
		for (char i = 'a'; i < 'k'; i++)
		{
			//"find" operation will insert the item into the set if it doesn't exist
			items.find(i);
		}

		//now, perform some unions
		items.union_with('a', 'b');
		items.union_with('c', 'd');
		items.union_with('e', 'f');
		items.union_with('g', 'h');

		//basic tests
		System.out.println("a in same set as b (expected True): " + ((items.find('a')) == (items.find('b'))) );
		System.out.println("c in same set as a (expected False): " + ((items.find('a')) == (items.find('c'))) );

		//do some more unions
		items.union_with('b', 'd');

		//recheck
		System.out.println("c in same set as a (expected True): " + ((items.find('a')) == (items.find('c'))) );

		//get all roots
		List<Character> roots = items.getRoots();
		System.out.println("Set roots (expected i, a, e, g, j - order not important): ");
		for (char ch : roots)
		{
			System.out.print(ch + " ");
		}
		System.out.println();
	}

}
