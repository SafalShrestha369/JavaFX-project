
package pckgmodel;

import java.sql.Date;

public class Employee {
    Integer Eid, Salary;
    String Ename, Eaddress, Faculty;
    String Joinedyear;
    private String Username, Password;



    public Employee(int Eid, int Salary, String Ename, String Eaddress, String Faculty, String Joinedyear, String Username, String Password) {
        this.Eid = Eid;
        this.Salary = Salary;
        this.Ename = Ename;
        this.Eaddress = Eaddress;
        this.Faculty = Faculty;
        this.Joinedyear = Joinedyear;
        this.Username = Username;
        this.Password = Password;
    }

    public Employee() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    

    public Integer getEid() {
        return Eid;
    }

    public void setEid(Integer Eid) {
        this.Eid = Eid;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer Salary) {
        this.Salary = Salary;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String Ename) {
        this.Ename = Ename;
    }

    public String getEaddress() {
        return Eaddress;
    }

    public void setEaddress(String Eaddress) {
        this.Eaddress = Eaddress;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public String getJoinedyear() {
        return Joinedyear;
    }

    public void setJoinedyear(String Joinedyear) {
        this.Joinedyear = Joinedyear;
    }
    
}
