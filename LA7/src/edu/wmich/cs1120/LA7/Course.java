package edu.wmich.cs1120.LA7;

public class Course implements ICourse {

	private String courseDept;
	private int courseNumber;
	
	private int capacity;
	private LinkedList<String> students;
	
	//constructor
	public Course(String courseDept, int courseNumber, int capacity) {
		this.courseDept = courseDept;
		this.courseNumber = courseNumber;
		this.capacity = capacity;
		this.students = new LinkedList<String>();
	}

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
	
	//Getters and Setters
	public String getCourseDept() {
		return this.courseDept;
	}
	
	public int getCourseNumber() {
		return this.courseNumber;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	//don't need setter for this?
	public LinkedList<String> getStudents() {
		return this.students;
	}
	
	public void setCourseDept(String courseDept) {
		this.courseDept = courseDept;
	}
	
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
