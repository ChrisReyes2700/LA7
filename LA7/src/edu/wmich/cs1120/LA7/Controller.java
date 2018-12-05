package edu.wmich.cs1120.LA7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Controller implements IController{

	private PriorityQueue<Request> requestQueue;
	private LinkedList<Course> courses;
	private BufferedReader fileIn;
	private BufferedReader fileIn1;
	
	public Controller(PriorityQueue<Request> requestQueue, LinkedList<Course> courses,
			BufferedReader fileIn, BufferedReader fileIn1) {
		this.requestQueue = requestQueue;
		this.courses = courses;
		this.fileIn = fileIn;
		this.fileIn1 = fileIn1;
	}
	
	
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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRequest(Request req) {
		requestQueue.enqueue(req);
	}

	@Override
	public void processRequests() {
		//print queue before processing
		System.out.println("<<<<<<<<<<<< Beginning of Queue Contents >>>>>>>>>>>>>>>>>");
		requestQueue.Qprint();
		
		//loops until queue is empty
		while(!requestQueue.isEmpty()) {
			Request currentReq = requestQueue.dequeue();
			Course targetCourse = getCourse(currentReq.getCourseDept(), currentReq.getCourseNumber());
			
			//if course is full, print that student cannot register
			if(targetCourse.isFull()) {
				System.out.println(currentReq.getStudentName() + " cannot register for "
						+ currentReq.getCourseDept() + " " + currentReq.getCourseNumber());
			}
			
			else {
				//else reduce class cap and add student to class list 
				targetCourse.setCapacity(targetCourse.getCapacity() - 1);
				targetCourse.addStudent(currentReq.getStudentName());
				//print successful registration
				System.out.println(currentReq.getStudentName() + " has successfully registered for "
						+ currentReq.getCourseDept() + " " + currentReq.getCourseNumber());
			}
		}
		
		//print queue after processing
		System.out.println("<<<<<<<<<<<< End of Queue Contents >>>>>>>>>>>>>>>>>");
		requestQueue.Qprint();
	}

	@Override
	public Course getCourse(String courseDept, int courseNumber) {
		//initialized to null
		Course cour = null;
		
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getCourseDept() == courseDept && 
					courses.get(i).getCourseNumber() == courseNumber) {
				cour = courses.get(i);
			}
		}
		
		return cour;
	}

	@Override
	public void printClassList() {
		
		for(int i = 0; i < courses.size(); i++) {
			System.out.println("\nClasslist for " + courses.get(i).getCourseDept() + " " 
					+ courses.get(i).getCourseNumber());
			
			if(!courses.get(i).getStudents().isEmpty()) {
				for(int j = 0; j < courses.get(i).getStudents().size(); j++) {
					System.out.println(courses.get(i).getStudents().get(j));
				}
			}
			
			else {
				System.out.println("Class is empty.");
			}
		}
		
	}

}
