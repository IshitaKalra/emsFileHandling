package com.girnarsoft.training.assignment;

public class Constants {
	public static interface ErrorMessages{
		public static final String WRONG_PASSWORD = "Wrong Password!!";
		public static final String NO_ONE_REPORTING = "No one reporting\n";
		public static final String INVALID_INPUT = "Invalid Input Format!! \n";
		public static final String INVALID_NAME = "Invalid Name Format!! \nPlease Enter Again";
		public static final String INVALID_OPERATION= "Invalid Operation!!\n";
	}
	public static interface Input{
		public static final String WELCOME_TO_GIRNARSOFT = "WELCOME TO GIRNARSOFT!!!";
		public static final String MENU = "\nPress 1 to find details of all employees\n" + 
									"Press 2 to add an existing employee in your team\n" + 
									"press 3 to remove an employee\"\n" + 
									"press 4 to add a new employee\"\n" + 
									"press 5 to promote an employee\"\n" + 
									"press 6 to find the list of reporting employees\"\n" + 
									"press 7 to exit\n" ;
		public static final String INPUT = "Please enter in correct format and within range";
		public static final String CORRECT_FORMAT= "Please enter name in correct format";
		public static final String NAME_INPUT = "Enter the name of the employe";
		public static final String ROLE_INPUT = "Enter its Role in numerical format : \n0.Employee \n1.Manager \n2.Hr";
		public static final String HEAD_INPUT = "Enter the id of the head";
		public static final String PASSWORD_INPUT = "Enter the Password of the employe";
		public static final String ID_INPUT = "Enter the id of the employee";
	
	}

}

