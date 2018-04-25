package adapter;

public interface EditThread {
	public void Edit(int ops, String [] input, boolean isSynchronized);
	//String [] input is there so it can hold the name u want to change along with its original name as well as optionset names etc.

}
