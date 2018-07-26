package com.girnarsoft.training.assignment;

import java.util.*;
/**
 * 
 * @author gspl
 * some functionalities which are similar to that of employee are not overridden
 *
 */


public class ManagerService extends EmployeeService{
	

	/**
	 * 
	 * @param selfId
	 * @param employeeId
	 * @param employeeMap
	 * checking if the employee to be removed is reporting to the manager
	 * Manager can only remove employees under him
	 */
	public void removeEmployee(int employeeId,int selfId, HashMap<Integer,Employee> employeeMap)
	{
		Employee employee=employeeMap.get(employeeId);
		int head=employee.getHead();
		Employee headEmployee=employeeMap.get(head);
		if(employee.role==0 && selfId==headEmployee.id)
		{
			employeeMap.remove(employeeId);
			headEmployee.getReportingEmployees().remove(employeeId);
		}
		else
		{
			System.out.println(Constants.ErrorMessages.INVALID_OPERATION);
		}

	}
	
	
	/**
	 * 
	 * @param id
	 * @param selfId
	 * @param employeeMap
	 * @param employees
	 * Addition of an employee in team by Manager
	 * Can only add employees in his team.
	 */
	public void addEmployee(int id,int selfId, HashMap<Integer,Employee> employeeMap ,ArrayList<Integer> employees)
	{
		Employee manager=employeeMap.get(selfId);
		Employee employee=employeeMap.get(id);
		employee.setHead(selfId);
		if(employees != null)
		{
			if(!manager.getReportingEmployees().contains(id))
			manager.getReportingEmployees().add(id);
		}
		else
		{
			ArrayList<Integer> reportingList=new ArrayList<Integer>();
			reportingList.add(id);
			manager.reportingEmployees=reportingList;
		}

	}

}
