
public class MA5_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MA5_main main = new MA5_main();
		main.heapTests();
	}
	
	public void heapTests()
	{
		Heap<Integer> pq = new Heap<>();
		pq.enqueue(10);
		pq.enqueue(9);
		pq.enqueue(8);
		pq.enqueue(7);
		pq.enqueue(6);
		pq.enqueue(5);
		pq.enqueue(4);
		pq.enqueue(3);
		pq.enqueue(2);
		pq.enqueue(1);

		while (pq.isEmpty() == false)
		{
			int top = pq.dequeue();
			System.out.println("Dequeue top: " + top);
		}
	}

}
