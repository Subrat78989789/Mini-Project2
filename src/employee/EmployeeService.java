package employee;

import java.io.*;
import java.util.*;



public class EmployeeService {
	

    private Map<Integer, Employee> employees = new HashMap<>();
    private final String FILE_NAME = "employees.txt";
 private final File file=new File(FILE_NAME);
 public EmployeeService()throws IOException {
	 file.createNewFile();//It throws exception therefore it should be declared inside constructor or method
     loadFromFile();
 }
   
   
    

   

    // Add Employee
    public void addEmployee(Employee emp) throws EmployeeException,IOException {
        if (employees.containsKey(emp.getId()))
            throw new EmployeeException("Employee ID already exists");

        if (emp.getSalary() <= 0)
            throw new EmployeeException("Salary must be positive");

        if (emp.getDepartment().isEmpty())
            throw new EmployeeException("Department cannot be empty");

        employees.put(emp.getId(), emp);
        saveToFile();
    }

    // Display All
    public void displayAll() {
        employees.values().forEach(System.out::println);
    }

    // Search by ID
    public Employee searchEmployee(int id) throws EmployeeException {
        if (!employees.containsKey(id))
            throw new EmployeeException("Employee not found");
        return employees.get(id);
    }

    // Update Salary
    public void updateSalary(int id, double salary) throws EmployeeException,IOException {
        if (salary <= 0)
            throw new EmployeeException("Salary must be positive");

        Employee emp = searchEmployee(id);
        emp.setSalary(salary);
        saveToFile();
    }

    // Delete Employee
    public void deleteEmployee(int id) throws EmployeeException,IOException{
        if (employees.remove(id) == null)//remove method returns object
            throw new EmployeeException("Employee not found");
        saveToFile();
    }

    // Sorted Employees
    public void displaySorted() {
        employees.values().stream()
                .sorted(Comparator.comparing(Employee::getName))//according to name ascending order sorting is performed
                .forEach(System.out::println);
    }

    // Display Departments
    public void displayDepartments() {
        Set<String> depts = new HashSet<>();
        employees.values().forEach(e -> depts.add(e.getDepartment()));
        depts.forEach(System.out::println);
    }

    // File Save
    private void saveToFile()throws IOException {
    	
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // File Load
    private void loadFromFile() {
     //   File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (HashMap<Integer, Employee>) ois.readObject();
        } catch (Exception e) {
            System.out.println("File read error");
        }
    }
}
