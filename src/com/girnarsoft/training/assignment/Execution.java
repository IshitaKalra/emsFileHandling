package com.girnarsoft.training.assignment;

import java.util.*;

/**
 * function calls according to the option selected by user
 * function call of the class according to the role of the user
 * CEO being given highest authorities followed by HR.
 */
import java.io.*;

public class Execution {
	public static HashMap<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
	public static final Scanner SCANNER = new Scanner(System.in);
	private static int SELF_ID;
	private static String STRING_SELF_ID;
	private static String STRING_EMPLOYEE_ID;
	private static int EMPLOYEE_ID;
	private static int selfRole;
	private static EmployeeService employeeService;
	private static ManagerService managerService;
	private static HRService hrService;
	private static CEOService ceoService;
	private static int newId = 100;

	public static void main(String[] args) {
		int userInput = 0;
		Employee ceo = new Employee(newId, "Amit", 3, 0, "pass01", null);
		employeeMap.put(newId, ceo);
		newId = newId + 1;
		Employee hr1 = new Employee(newId, "Rekha", 2, ceo.id, "pass02", new ArrayList<Integer>());
		ManagerService managerService = new ManagerService();

		employeeMap.put(newId, hr1);
		managerService.addEmployee(hr1.id, ceo.id, employeeMap, ceo.reportingEmployees);

		newId++;

		do {
			readWriteToFile(1); // reading from file copying data into hashMap
			readWriteToFile(0);
			System.out.println(Constants.Input.WELCOME_TO_GIRNARSOFT);
			System.out.println(Constants.Input.MENU);
			String input = SCANNER.nextLine();
			try {
				while (!isInteger(input) || Integer.parseInt(input) > 7 || Integer.parseInt(input) < 1) {
					System.out.println(Constants.Input.INPUT);
					input = SCANNER.nextLine();
				}

				userInput = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println(Constants.Input.INPUT);
			}
			;
			switch (userInput) {
			case 1:
				printAllEmployees();
				break;
			case 2:
				takeInputAdd();
				readWriteToFile(0);
				break;
			case 3:
				takeInputRemove();
				readWriteToFile(0);
				break;
			case 4:
				takeAllDetails();
				readWriteToFile(0);
				break;
			case 5:
				takePromoteDetails();
				readWriteToFile(0);
				break;
			case 6:
				listReportingEmployees();
				break;
			default:
				break;

			}
		} while (userInput != 7);
		SCANNER.close();
	}
	/**
	 * 
	 *
	 * @param flag
	 * 
	 * flag=0 -> to write to file
	 * writing to file
	 * storing details of every employee in the form of string
	 * seperating each attribute by delimeter "@	
	 * 
	 * flag=1 ->to read from file
	 * Copying from file to HashMap<Employee>
	 * On each and every execution Hashmap will be updated from File
	 */

	public static void readWriteToFile(int flag) {

		try {

			if (flag == 0) {
				FileWriter fw = new FileWriter("myObjects.txt");
				for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
					int key = entry.getKey();
					fw.write(key + "@" + employeeMap.get(key).getName() + "@" + employeeMap.get(key).role + "@"
							+ employeeMap.get(key).getHead() + "@" + employeeMap.get(key).getPassword() + "@"
							+ employeeMap.get(key).getReportingEmployees() + "\n");
				}

				fw.close();
			}
			else {
				FileReader fileReader = new FileReader("myObjects.txt");
				String line = null;
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				BufferedReader bufferedReader2 = new BufferedReader(fileReader);
				String[] arrOfStr;

				 
				while ((line = bufferedReader.readLine()) != null) {

					// Converting String to array of Strings

					arrOfStr = line.split("@");
					int id = Integer.parseInt(arrOfStr[0]);
					String setName = arrOfStr[1];
					int role = Integer.parseInt(arrOfStr[2]);
					int setHead = Integer.parseInt(arrOfStr[3]);
					String setPassword = arrOfStr[4];
					String reporters = arrOfStr[5];
					ArrayList<Integer> employees;
					if (reporters.equals("[]")) {
						employees = new ArrayList<Integer>();
					} else {
						reporters = reporters.replaceAll("[\\[\\]]", "");
						reporters = reporters.trim();
						String[] reporterList = reporters.split(",");
						List<String> c = Arrays.asList(reporterList);
						ArrayList<String> ids = new ArrayList<>(c);
						int employee1;
						employees = new ArrayList<Integer>();
						if (!reporters.equals("null")) {
							for (int i = 0; i < ids.size(); i++) {
								employee1 = Integer.parseInt(ids.get(i).trim());
								employees.add(employee1);
							}
						}
					}
					Employee addEmployee = new Employee(id, setName, role, setHead, setPassword, employees);
					employeeMap.put(id, addEmployee);
					newId = addEmployee.id + 1;
				}

				int k;
				while ((k = bufferedReader2.read()) != -1) {
					System.out.println("new");
					System.out.print((char) k);
				}
				bufferedReader.close();
				bufferedReader2.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * function to check if string having alphabets and whitespaces
	 * @param name
	 * @return
	 */
	
	
	public static boolean isAlpha(String name) {
		return name.matches("[a-zA-Z][a-zA-Z ]*");
	}
	/**
	 * 
	 * @param input
	 * @return
	 */

	public static boolean isInteger(String input) {
		return (input.matches("^\\d+(\\.\\d+)?"));

	}
	/**
	 * Function to print all employees basic info
	 */
	public static void printAllEmployees() {
		for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
			Employee employee = entry.getValue();
			System.out.println(" Name : " + employee.getName());
			System.out.println(" Id : " + employee.getId());
			System.out.println(" Role : " + employee.getRole());
		}

	}
	/**
	 * 
	 * @param string
	 * @return
	 */

	public static String validId(String string) {
		while (!isInteger(string) || !employeeMap.containsKey(Integer.parseInt(string))) {
			System.out.println("Please Enter the correct Id");
			string = SCANNER.nextLine();
		}
		return string;
	}
	/**
	 * validating users id and password and then performing the required action
	 */

	public static void takeInputs() {
		System.out.println("Enter your id");
		STRING_SELF_ID = validId(SCANNER.nextLine());
		SELF_ID = Integer.parseInt(STRING_SELF_ID);
		Employee employee = employeeMap.get(SELF_ID);
		
		System.out.println("Enter your password");
		String selfPassword=SCANNER.nextLine();
		if(!selfPassword.equals(employee.getPassword()))
		{
			System.out.println("Wrong Password");
			return;
		}

		System.out.println("Enter the id of the employee");
		STRING_EMPLOYEE_ID = validId(SCANNER.nextLine());
		EMPLOYEE_ID = Integer.parseInt(STRING_EMPLOYEE_ID);

		
		selfRole = employee.role;

		switch (selfRole) {
		case 0:
			employeeService = new EmployeeService();
			break;
		case 1:
			managerService = new ManagerService();
			break;
		case 2:
			hrService = new HRService();
			break;
		case 3:
			ceoService = new CEOService();
			break;
		default:
			System.out.println("Invalid Input");
		}

	}
	/**
	 * Calling the function of appropriate class according to the role of the user
	 */

	public static void takeInputAdd() {
		takeInputs();
		switch (selfRole) {
		case 0:
			employeeService.addEmployee(EMPLOYEE_ID, SELF_ID, employeeMap, employeeMap.get(SELF_ID).reportingEmployees);
			break;
		case 1:
			managerService.addEmployee(EMPLOYEE_ID, SELF_ID, employeeMap, employeeMap.get(SELF_ID).reportingEmployees);
			break;
		case 2:
			hrService.addEmployee(EMPLOYEE_ID, SELF_ID, employeeMap, employeeMap.get(SELF_ID).reportingEmployees);
			break;
		case 3:
			ceoService.addEmployee(EMPLOYEE_ID, SELF_ID, employeeMap, employeeMap.get(SELF_ID).reportingEmployees);
			break;
		default:
			System.out.println("Invalid Input");
		}

	}
	/**
	 * function call to remove an employee based on your designation 
	 */

	
	public static void takeInputRemove() {
		takeInputs();

		switch (selfRole) {
		case 0:
			employeeService.removeEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		case 1:
			managerService.removeEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		case 2:
			hrService.removeEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		case 3:
			ceoService.removeEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		default:
			System.out.println("Invalid Input");
		}

	}
	/**
	 * Function to input details of new employee and adding it
	 */

	
	public static void takeAllDetails() {
		String name;
		int role;
		String inputRole;
		String headId;

		System.out.println(Constants.Input.NAME_INPUT);
		name = SCANNER.nextLine();
		while (!isAlpha(name)) {
			System.out.println(Constants.Input.CORRECT_FORMAT);
			name = SCANNER.nextLine();
		}

		System.out.println(Constants.Input.ROLE_INPUT);
		inputRole = SCANNER.nextLine();
		while (!isInteger(inputRole) || Integer.parseInt(inputRole) > 2 || Integer.parseInt(inputRole) < 0) {
			System.out.println(Constants.Input.ROLE_INPUT);
			inputRole = SCANNER.nextLine();
		}
		role = Integer.parseInt(inputRole);

		System.out.println(Constants.Input.HEAD_INPUT);
		headId = validId(SCANNER.nextLine());
		int head = Integer.parseInt(headId);
		ManagerService managerService = new ManagerService();
		newId++;
		System.out.println(Constants.Input.PASSWORD_INPUT);
		String password = SCANNER.nextLine();
		Employee newEmployee = new Employee(newId, name, role, head, password, null);
		employeeMap.put(newId, newEmployee);
		managerService.addEmployee(newId, head, employeeMap, employeeMap.get(head).reportingEmployees);

	}

	
	/**
	 * Input details through the method takeInput()
	 * Function call according to the designation of the employee
	 */

	public static void takePromoteDetails() {
		takeInputs();

		switch (selfRole) {
		case 0:
			employeeService.promoteEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		case 1:
			managerService.promoteEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		case 2:
			hrService.promoteEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		case 3:
			ceoService.promoteEmployee(EMPLOYEE_ID, SELF_ID, employeeMap);
			break;
		default:
			System.out.println(Constants.ErrorMessages.INVALID_INPUT);
		}
	}

	/**
	 * Lists all the reporting employees of the person
	 */
	public static void listReportingEmployees() {
		System.out.println("Enter your Id");
		String stringId;
		stringId = validId(SCANNER.nextLine());
		int selfId = Integer.parseInt(stringId);
		System.out.println("Enter your password");
		String password=SCANNER.nextLine();
		Employee employee = employeeMap.get(selfId);
		if(password.equals(employee.getPassword()))
		{
			if (employee.getReportingEmployees() != null) {
				ArrayList<Integer> employees = employee.getReportingEmployees();
				System.out.println("List of reporting employees of "+ employee.getName()+":");
				for (int i = 0; i < employees.size(); i++) {
					System.out.println(employees.get(i));
				}
			} else
				System.out.println(Constants.ErrorMessages.NO_ONE_REPORTING);
		}
		else
		{
			System.out.println(Constants.ErrorMessages.WRONG_PASSWORD);
		}
	}
}
