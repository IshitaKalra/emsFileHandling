package com.girnarsoft.training.assignment;

import java.util.*;
/**
 * 
 * @author gspl
 * Basic functionalities of an employee are implemented
 */

public class EmployeeService {

	//
	/**
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param employee
	 * change the password by verifying old password
	 */
	public void changePassword(String oldPassword,String newPassword,Employee employee) {
		employee.setPassword(oldPassword ,newPassword);
	}

	/**
	 * 
	 * @param promoteId
	 * @param selfId
	 * @param employeeMap
	 * Employee can not promote anyone
	 */
	public void promoteEmployee(int promoteId,int selfId,HashMap<Integer,Employee> employeeMap) {
		System.out.println(Constants.ErrorMessages.INVALID_OPERATION);
	}

	
	/**
	 * 
	 * @param id
	 * @param selfId
	 * @param employeeMap
	 * @param employees
	 * Employee cannot add anyone in his team
	 */
	public void addEmployee(int id,int selfId, HashMap<Integer,Employee> employeeMap,ArrayList<Integer> employees)
	{
		System.out.println(Constants.ErrorMessages.INVALID_OPERATION);

	}

	/**
	 * 
	 * @param selfId
	 * @param employeeId
	 * @param employeeMap
	 * Employee cannot delete any other employee
	 */
	public void removeEmployee(int selfId,int employeeId, HashMap<Integer,Employee> employeeMap)
	{
		System.out.println(Constants.ErrorMessages.INVALID_OPERATION);
	}

}
