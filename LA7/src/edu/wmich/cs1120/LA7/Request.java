package edu.wmich.cs1120.LA7;

public class Request implements Comparable<Request>{

	private String studentName;
	private String studentDept;
	private String studentLevel;
	private String courseDept;
	private int courseNumber;
	private double[][] GPA_Array;
	private double GPA;
	private int yearsToGraduate;
	private int sameDept = 0;	//1 = true 0 = false for comparing in priorityqueue
	
	// Constructor
	public Request(String studentName, String studentDept, String studentLevel, String courseDept, int courseNumber,
			double[][] GPA_Array) {
		this.studentName = studentName;
		this.studentDept = studentDept;
		this.studentLevel = studentLevel;
		this.courseDept = courseDept;
		this.courseNumber = courseNumber;
		this.GPA_Array = GPA_Array;
		GPA = GPA_Cal(GPA_Array);
		setYearsToGraduate(yearsFromGraduation(studentLevel));
		if (studentDept == courseDept) {
			sameDept = 1;
		}
	}
	
	@Override
	public int compareTo(Request req) {
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>compare departments
		
		//higher priority
		if(this.sameDept == 1 && req.sameDept == 0) {
			return 1;
		}
		//lower priority
		else if(this.sameDept == 0 && req.sameDept == 1) {
			return -1;
		}
		//same priority
		else {
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>compare year level
			
			//higher priority
			if(this.yearsToGraduate < req.yearsToGraduate) {
				return 1;
			}
			//lower priority
			else if(this.yearsToGraduate > req.yearsToGraduate) {
				return -1;
			}
			
			//same priority
			else {
				//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>compare GPA
				
				//higher priority
				if(this.GPA > req.GPA) {
					return 1;
				}
				//lower priority
				else if(this.GPA < req.GPA) {
					return -1;
				}
				
				//same priority
				else {
					return 0;
				}
			}
		}
	}

	// Returns number of years to graduation (0 for seniors, 1 for juniors etc.).
	// This is determined from the
	// student’s level – senior, junior, etc.
	public int yearsFromGraduation(String level) {
		switch (level) {
		case "Senior":
			return 0;
		case "Junior":
			return 1;
		case "Sophomore":
			return 2;
		case "Freshman":
			return 3;
		}
		return -1;
	}

	// Calculate the GPA for a particular student.
	private double GPA_Cal(double[][] GPA_Array) {
		double finalGPA = 0;
		
		//Total GPA = (Sum of GPA*Credit)/(Sum of Credit)?
		double sumGC= 0;
		double sumCredit = 0;
		
		for (int i = 0; i < GPA_Array[0].length; i++) {
			sumGC += GPA_Array[0][i]*GPA_Array[1][i];
			sumCredit += GPA_Array[1][i];
		}
		
		finalGPA = sumGC/sumCredit;
		return finalGPA;
	}
	
	// Getters for a student’s name and department, and the department and number of
	// a course
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentDept() {
		return studentDept;
	}

	public void setStudentDept(String studentDept) {
		this.studentDept = studentDept;
	}

	public String getStudentLevel() {
		return studentLevel;
	}

	public void setStudentLevel(String studentLevel) {
		this.studentLevel = studentLevel;
	}

	public String getCourseDept() {
		return courseDept;
	}

	public void setCourseDept(String courseDept) {
		this.courseDept = courseDept;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public double[][] getGPA_Array() {
		return GPA_Array;
	}

	public void setGPA_Array(double[][] gPA_Array) {
		GPA_Array = gPA_Array;
	}

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

	public int getYearsToGraduate() {
		return yearsToGraduate;
	}

	public void setYearsToGraduate(int yearsToGraduate) {
		this.yearsToGraduate = yearsToGraduate;
	}

	public int isSameDept() {
		return sameDept;
	}

	public void setSameDept(int sameDept) {
		this.sameDept = sameDept;
	}


}
