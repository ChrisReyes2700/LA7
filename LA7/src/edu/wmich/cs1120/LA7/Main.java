package edu.wmich.cs1120.LA7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Project takes a request file and puts them into a queue based on a priority system
 * Will then process the request and register students into a linkedlist for the courses specified in the course input file
 * Students may or may not get registered for a course depending on the size of the course and the priority given to the student
 * Prints out status of students registration, the request queue, and the class list of registered students
 * 
 * @author Austin W, Chris R
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		PriorityQueue<Request> requestQueue = new PriorityQueue<Request>();
		LinkedList<Course> courses = new LinkedList<Course>();
		BufferedReader fileIn = new BufferedReader(new FileReader("course.txt"));
		//change to request later
		BufferedReader fileIn1 = new BufferedReader(new FileReader("request.txt"));
		IController control = new Controller(requestQueue, courses, fileIn, fileIn1);
		control.readCourseFile();
		control.readRequestFile();
		control.processRequests();
		control.printClassList();
				
	}


}
