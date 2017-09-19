
package pckgmodel;

import java.sql.Date;

public class Student {
    Integer id;
    String name;
    String course;
    int sem;
    private String username, password, dob;
    private Date regdate;

    public Student(int id, String name, String course, int sem, String username, String password, String dob, Date regdate) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.sem = sem;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.regdate = regdate;
    }

    public Student() {
    }

   

    public String getDob() {
        return dob;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Date getRegdate() {
       // regdate = new Date();
        return regdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }
    
    
    
}
