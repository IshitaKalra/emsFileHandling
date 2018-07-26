package com.girnarsoft.training.assignment;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author gspl
 * Some extra functionalities are implemented of CEO
 */

public class CEOService extends HRService{

	
	/**
	 * 
	 * @param promoteId
	 * @param selfId
	 * @param employeeMap
	 * CEO can promote employees to manager and manager to Hr
	 */
	public void promoteEmployee(int promoteId, int selfId, HashMap<Integer, Employee> employeeMap) {
		Employee promoteEmployee=employeeMap.get(promoteId);
		int promoteRole=promoteEmployee.role;
		if(promoteRole <= 1)
			promoteEmployee.role=promoteRole+1;
	}
 

	/**
	 * 
	 * @param selfId
	 * @param employeeId
	 * @param employeeMap
	 * CEO can remove any employee irrespective of its role
	 */
	public void removeEmployee(int employeeId,int selfId, HashMap<Integer,Employee> employeeMap)
	{
		Employee employee=employeeMap.get(employeeId);
		int head=employee.getHead();
		Employee headEmployee=employeeMap.get(head);
		if(employee.role <= 2)
		{
			employeeMap.remove(employeeId);
			headEmployee.reportingEmployees.remove(employeeId);
			System.out.println("Id :" + employeeId + "name :"+ employee.name + "is deleted") ;
			if(employee.getReportingEmployees()!= null)
			{
				if(headEmployee.getReportingEmployees()!=null)
				{
						headEmployee.getReportingEmployees().addAll(employee.getReportingEmployees());
				}
				else
				{
					headEmployee.reportingEmployees = employee.getReportingEmployees();
				}
			}
		}
		else
		{
			System.out.println("Invalid Operation");
		}

	}
	
	/**
	 * 
	 * @param id
	 * @param selfId
	 * @param employeeMap
	 * @param employees
	 */
	
	public void addEmployee(int id,int selfId, HashMap<Integer,Employee> employeeMap,ArrayList<Integer> employees)
	{
		Employee manager=employeeMap.get(selfId);
		Employee employee=employeeMap.get(id);
		if(manager.getReportingEmployees() != null)
		{
			manager.getReportingEmployees().add(id);
			employee.head = selfId;
		}
		else
		{
			ArrayList<Integer> reporting_employees=new ArrayList<>();
			reporting_employees.add(id);
			manager.reportingEmployees=reporting_employees;
		}

	}
	
}
