
public abstract class Queue<T> extends Collection<T> {
	public abstract T getFirst();
	public abstract void enqueue(T item);
	public abstract T dequeue();
}
