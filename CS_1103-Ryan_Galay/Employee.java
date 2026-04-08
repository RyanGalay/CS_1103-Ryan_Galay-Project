
/**
 * Write a description of class Employee here.
 *
 * Ryan Galay
 * Apr 7 2026
 */
public class Employee
{
    // initialize variables
    int employee_id;
    String full_name;
    String title;
    double salary;
    String permissions;
    
    // constructor
    public Employee(int employee_id, String full_name, String title, double salary, String permissions)
    {
        this.employee_id = employee_id;
        this.full_name = full_name;
        this.title = title;
        this.salary = salary;
        this.permissions = permissions;
    }
    
    // getter methods
    
    public int getEmployeeId() {
        return employee_id;
    }

    public String getFullName() {
        return full_name;
    }

    public String getTitle() {
        return title;
    }

    public double getSalary() {
        return salary;
    }

    public String getPermissions() {
        return permissions;
    }

    // setter methods

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}