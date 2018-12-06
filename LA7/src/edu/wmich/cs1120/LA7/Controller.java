package edu.wmich.cs1120.LA7;

import java.io.BufferedReader;
import java.io.IOException;

public class Controller implements IController{

	private PriorityQueue<Request> requestQueue;
	private LinkedList<Course> courses;
	private BufferedReader fileIn;
	private BufferedReader fileIn1;
	
	/**
	 * Constructor that sets each private variable to the input variables
	 * 
	 * @param requestQueue priority queue to be filled with requests
	 * @param courses linked list to be filled with courses
	 * @param fileIn course text file to be read for each course
	 * @param fileIn1 request text file to be read for each request
	 */
	public Controller(PriorityQueue<Request> requestQueue, LinkedList<Course> courses,
			BufferedReader fileIn, BufferedReader fileIn1) {
		this.requestQueue = requestQueue;
		this.courses = courses;
		this.fileIn = fileIn;
		this.fileIn1 = fileIn1;
	}
	
	/**
	 * Reads each line in the course file then creates a new course obj with the dept, course number, capacity found from the line in the file. Adds that new course to the linked list 
	 * and loops until the end of the file then closes the reader.
	 */
	@Override
	public void readCourseFile() {
		try {
			  String currentLine;
			  
			  while ((currentLine = fileIn.readLine()) != null) {
				  System.out.println(currentLine);
				  String[] line = currentLine.split(",");
				  Course c = new Course(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				  courses.add(c);
			  }
			  fileIn.close();
			  System.out.print("\n");
			  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads each line in the request file then creates a new request obj with the student name, student dept, student level,
	 * course dept, and course number found from the line in the file. Adds that new request to the priority queue and loops until the end of the file then closes the reader.
	 */
	@Override
	public void readRequestFile() {
		try {
			  String currentLine;
			  
			  while ((currentLine = fileIn1.readLine()) != null) {
				  String[] line = currentLine.split(",");
				  
				  String studentName = line[0];
				  String studentDept = line[2];
				  String studentLevel = line[1];
				  String courseDept = line[3];
				  int courseNumber = Integer.parseInt(line[4]);
				  
				  double[][] GPA_Array = new double[2][4];
				  
				  //row 0 = GPA ; row 1 = Credit
				  for(int i = 0; i < 4; i++) {
					  int x = 5 + 2*(i);
					  int y = 6 + 2*(i);
					  GPA_Array[0][i] = Double.parseDouble(line[x]);
					  GPA_Array[1][i] = Double.parseDouble(line[y]);
				  }  
				  
				  Request r = new Request(studentName, studentDept, studentLevel, courseDept, courseNumber, GPA_Array);
				  addRequest(r);
			  }
			  fileIn1.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enqueues the input request into the priority queue
	 * @param req request to be enqueued
	 */
	@Override
	public void addRequest(Request req) {
		requestQueue.enqueue(req);
	}

	/**
	 * Prints the queue before processing. Dequeues each request, prints it then checks if the class is full. If not, prints that the students has been registered, otherwise, the student 
	 * cannot be registered. Loops until the queue is empty then reprints the now empty queue.
	 */
	@Override
	public void processRequests() {
		//print queue before processing
		System.out.println("<<<<<<<<<<<< Beginning of Queue Contents >>>>>>>>>>>>>>>>>");
		requestQueue.Qprint();
		System.out.println("<<<<<<<<<<<< End of Queue Contents >>>>>>>>>>>>>>>>>");
		
		//loops until queue is empty
		System.out.print("\n");
		while(!requestQueue.isEmpty()) {
			Request currentReq = requestQueue.dequeue();
			Course targetCourse = getCourse(currentReq.getCourseDept(), currentReq.getCourseNumber());
			
			System.out.println(currentReq.toString());
			
			//if course is not full,
			if(!targetCourse.isFull()) {
				// reduce class cap and add student to class list 
				targetCourse.setCapacity(targetCourse.getCapacity() - 1);
				targetCourse.addStudent(currentReq.getStudentName());
				//print successful registration
				System.out.println(currentReq.getStudentName() + " has successfully registered for "
						+ currentReq.getCourseDept() + " " + currentReq.getCourseNumber());
		}
			//else course is full, print that student cannot register
			else {
				System.out.println(currentReq.getStudentName() + " cannot register for "
						+ currentReq.getCourseDept() + " " + currentReq.getCourseNumber());
			}
		}
		System.out.print("\n");
		
		//print queue after processing
		System.out.println("<<<<<<<<<<<< Beginning of Queue Contents >>>>>>>>>>>>>>>>>");
		requestQueue.Qprint();
		System.out.println("<<<<<<<<<<<< End of Queue Contents >>>>>>>>>>>>>>>>>");
	}

	/**
	 * Uses the input course dept and course number to return the matching course obj. If the course is not found, returns null.
	 * @param courseDept desired course dept
	 * @param courseNumber desired course number
	 */
	@Override
	public Course getCourse(String courseDept, int courseNumber) {
		//initialized to null
		Course cour = null;
		
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getCourseDept().equals(courseDept) && 
					courses.get(i).getCourseNumber() == courseNumber) {
				cour = courses.get(i);
			}
		}
		
		return cour;
	}

	/**
	 * Prints the course dept and course number then prints each student in the class. If there are no students, it prints that the class is empty.
	 */
	@Override
	public void printClassList() {
		
		for(int i = 0; i < courses.size(); i++) {
			//prints specific course dept and number
			System.out.println("\nClasslist for " + courses.get(i).getCourseDept() + " " 
					+ courses.get(i).getCourseNumber());
			//prints class list of specific course
			courses.get(i).printClassList();
		}
		
	}

}
