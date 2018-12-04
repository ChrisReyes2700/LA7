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
				  String[] line = currentLine.split(",");
				  Course c = new Course();
				  
				  c.setCourseDept(line[0]);
				  c.setCourseNumber(Integer.parseInt(line[1]));
				  c.setCapacity(Integer.parseInt(line[2]));
				  
				  courses.add(c);
			  }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readRequestFile() {
		try {
			  String currentLine;
			  
			  while ((currentLine = fileIn.readLine()) != null) {
				  String[] line = currentLine.split(",");
				  
				  String studentName = line[0];
				  String studentDept = line[2];
				  String studentLevel = line[1];
				  String courseDept = line[3];
				  int courseNumber = Integer.parseInt(line[4]);
				  
				  double[][] GPA_Array = new double[4][4];
				  //row 0 = GPA ; row 1 = Credit
				  for(int i = 0; i < 5; i++) {
					  int x = 5 + 2*(i);
					  int y = 6 + 2*(i);
					  GPA_Array[0][i] = Double.parseDouble(line[x]);
					  GPA_Array[1][i] = Double.parseDouble(line[y]);
				  }  
				  
				  Request r = new Request(studentName, studentDept, studentLevel, courseDept, courseNumber, GPA_Array);
				  requestQueue.enqueue(r);
			  }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRequest(Request req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processRequests() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course getCourse(String courseDept, int courseNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printClassList() {
		// TODO Auto-generated method stub
		
	}

}
