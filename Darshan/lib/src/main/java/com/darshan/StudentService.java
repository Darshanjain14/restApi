package com.darshan;
//import com.darshan.*;
import java.util.*;
public class StudentService {
	
	private ArrayList<Student> ll = new ArrayList<>();
	
	public void addStudent(Student s)
	{
		ll.add(s);
	}
	public ArrayList<Student> getStudents()
	{
		return this.ll;
	}
	public Student getStudent(int rollNumber)
	{
		Student s = null;
		for(int i=0;i<ll.size();i++)
		{
			Student temp = ll.get(i);
			if(temp.getRollNumber()==rollNumber)
			{
				s=temp;
			}
		}
		return s;
	}
	public void editStudent(int rollNumber,Student n)
	{
		for(int i=0;i<ll.size();i++)
		{
			Student temp =ll.get(i);
			if(temp.getRollNumber()==rollNumber)
			{
				ll.remove(temp);
				ll.add(n);
				break;
			}
		}
		
	}
	public void deleteStudent(int rollNumber)
	{
		for(int i=0;i<ll.size();i++)
		{
			Student temp = ll.get(i);
			if(temp.getRollNumber()==rollNumber)
			{
				ll.remove(temp);
				break;
			}
		}
	}
	

	
}
