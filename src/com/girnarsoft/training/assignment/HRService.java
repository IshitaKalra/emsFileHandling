package com.girnarsoft.training.assignment;

import java.util.*;
/**
 * 
 * @author gspl
 * Some extra functionalities than that of manager are implemented 
 */

public class HRService extends ManagerService{
	
	
	/**
	 * 
	 * @param promoteId
	 * @param selfId
	 * @param employeeMap
	 * Hr can promote employees to manager only
	 */
	public void promoteEmployee(int promoteId,int selfId,HashMap<Integer,Employee> employeeMap) {
		Employee promoteEmployee=employeeMap.get(promoteId);
		int promoteRole=promoteEmployee.role;
		if(promoteRole < 1)
			promoteEmployee.role = promoteEmployee.role + 1;
		else
			System.out.println(Constants.ErrorMessages.INVALID_OPERATION);
	}
	
	/**
	 * 
	 * @param selfId
	 * @param employeeId
	 * @param employeeMap
	 * Hr can remove employees and managers
	 */
	public void removeEmployee(int employeeId,int selfId, HashMap<Integer,Employee> employeeMap)
	{
		Employee employee=employeeMap.get(employeeId);
		int head=employee.getHead();
		Employee headEmployee=employeeMap.get(head);
		if(employee.role <= 1)
		{
			employeeMap.remove(employeeId);
			if(employee.getReportingEmployees()!= null)
			{
				if(headEmployee.getReportingEmployees()!=null)
					headEmployee.getReportingEmployees().addAll(employee.getReportingEmployees());
				else
				{
					headEmployee.reportingEmployees = employee.getReportingEmployees();
				}
			}
		}
		else
		{
			System.out.println(Constants.ErrorMessages.INVALID_OPERATION);
		}

	}
	

}
