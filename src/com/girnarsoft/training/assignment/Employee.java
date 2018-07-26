package com.girnarsoft.training.assignment;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @author gspl
 *
 */

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String password;	
	protected int id;
	protected String name;
	protected int role;								
	protected int head;
	//protected static ArrayList<Employee> employees=new ArrayList<Employee>();
	
	public ArrayList<Integer> reportingEmployees=new ArrayList<Integer>();
	public Employee(int id,String name, int role, int head, String password, ArrayList<Integer> reportingEmployees) {
		this.id = id;
		this.name=name;
		this.role = role;
		this.head = head;
		this.password= password;
		this.reportingEmployees = reportingEmployees;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setPassword(String oldPassword,String newPassword)
	{
		if(this.password==oldPassword)
			this.password=newPassword;
	}
	public void setPassword(int permit,String password)
	{
		if(permit==100)
		{
			this.password=password;
		}
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getRole() {
		switch(role) {
		case 0:
			return "Employee";
		case 1:
			return "Manager";
		case 2:
			return "HR";
		default:
			return "CEO" ;
		}
	}
	public int getHead() {
		return head;
	}
	public ArrayList<Integer> getReportingEmployees() {
		return reportingEmployees;
	}
	public void setReportingEmployees(ArrayList<Integer> reportingEmployees) {
		this.reportingEmployees = reportingEmployees;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public String toString() {
		//return "Employee Id:" + id + "\nName: " + name + "\nRole: " + this.getRole() + "\nHead " + this.getHead() + "\nPassword " + this.password ;
		return id+":";
	}

}
