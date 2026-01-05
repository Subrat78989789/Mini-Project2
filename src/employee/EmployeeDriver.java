package employee;

import java.util.Scanner;

public class EmployeeDriver {
//Tesr changes
    public static void main(String[] args) {
    	try {
        Scanner sc = new Scanner(System.in);
        LoginService login = new LoginService();
        EmployeeService service = new EmployeeService();

        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();

        if (!login.login(u, p)) {
            System.out.println("Invalid Login");
            return;//return in main terminates the program
        }

        int choice;
        do {
            System.out.println("\n1.Add Employee\n2.Display All\n3.Search by ID");
            System.out.println("4.Update Salary\n5.Delete Employee");
            System.out.println("6.Display Sorted\n7.Display Departments\n8.Exit");

            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Salary: ");
                        double sal = sc.nextDouble();
                        System.out.print("Department: ");
                        String dept = sc.next();
                        service.addEmployee(new Employee(id, name, sal, dept));
                        break;

                    case 2:
                        service.displayAll();
                        break;

                    case 3:
                        System.out.println(service.searchEmployee(sc.nextInt()));
                        break;

                    case 4:
                        service.updateSalary(sc.nextInt(), sc.nextDouble());
                        break;

                    case 5:
                        service.deleteEmployee(sc.nextInt());
                        break;

                    case 6:
                        service.displaySorted();
                        break;

                    case 7:
                        service.displayDepartments();
                        break;
                }
            } catch (EmployeeException e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 8);
    	}
    	catch (Exception e) {
			System.out.println(e.getMessage());//handling IO exception
		}
    }
}
