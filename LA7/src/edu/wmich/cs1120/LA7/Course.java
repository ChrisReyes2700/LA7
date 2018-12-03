package edu.wmich.cs1120.LA7;

public class Course implements ICourse {

	private String courseDept;	//dunno if this is needed?
	private int courseNumber;	//dunno if this is needed?
	
	private int capacity;
	private LinkedList<String> students;

	@Override
	public boolean isFull() {
		if (students.size() < capacity)
			return false;
		else
			return true;
	}

	@Override
	public void addStudent(String name) {
		students.add(name);
	}

	@Override
	public void printClassList() {
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
	}
}
