package edu.wmich.cs1120.LA7;

public class Course implements ICourse {

	private String courseDept;
	private int courseNumber;
	private int capacity;
	private LinkedList<String> students;
	
	/**
	 * Constructor that sets each private variable to the input variables
	 * 
	 * @param courseDept course dept of this course
	 * @param courseNumber course number of this course
	 * @param capacity initial capacity of this course
	 */
	public Course(String courseDept, int courseNumber, int capacity) {
		this.courseDept = courseDept;
		this.courseNumber = courseNumber;
		this.capacity = capacity;
		this.students = new LinkedList<String>();
	}

	/**
	 * Checks if the class is full by checking if there is any capacity left.
	 */
	@Override
	public boolean isFull() {
		if (capacity == 0)
			return true;
		else
			return false;
	}

	/**
	 * Adds the input name to the list of students in the course
	 * @param name student name to be added to list of students in class
	 */
	@Override
	public void addStudent(String name) {
		students.add(name);
	}

	/**
	 * Checks if the course is empty. If not, prints each student in the student list. Else, prints that the class is empty.
	 */
	@Override
	public void printClassList() {
		if(students.size() != 0) {
			for (int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i));
			}
		}
		else {
			System.out.println("Class is empty.");
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
