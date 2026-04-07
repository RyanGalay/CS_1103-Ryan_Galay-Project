
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
    
    public Employee(int employee_id, String full_name, String title, double salary, String permissions)
    {
        this.employee_id = employee_id;
        this.full_name = full_name;
        this.title = title;
        this.salary = salary;
        this.permissions = permissions;
    }
}