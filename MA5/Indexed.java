
public abstract class Indexed<T> extends Collection<T> {

	public abstract T getElementAt(int index);
	public abstract void setElementAt(T item, int index);
	public abstract void addElementAt(T item, int index);
	public abstract void removeElementAt(int index);
}
