
package getstudent;

import java.util.Scanner;
import pckgdatabase.StudentDBUtil;
import pckgmodel.Student;

public class Main {

    public static void main(String[] args) {
        StudentDBUtil studentDBUtil = new StudentDBUtil();
        Scanner kb = new Scanner(System.in);
    Student student = new Student();
        System.out.println("Enter your choice:");
        System.out.println("1. Insert data");
        System.out.println("2. Fetch data");
        System.out.println("3. Fetch by id ");
        System.out.println("4. Update");
          System.out.println("5. Delete data");
        int choice;
        int id;
        String name;
        int input;
        String course;
        int sem;
       
        choice = kb.nextInt();
             switch(choice){
            case 1:
                     
                System.out.println("Enter id, name, course, sem");
             id = kb.nextInt();
             name = kb.next();
             course = kb.next();
             sem = kb.nextInt();
             student.setId(id);
             student.setName(name);
             student.setCourse(course);
             student.setSem(sem);
            
                if(studentDBUtil.createStudent(student)){
                    System.out.println("student created");
                }
               
                break;
            case 2:
              
                studentDBUtil.fetchdata();
                
                break;
            case 3:
                  
                System.out.println("Enter ID to fetch data: ");
                id=kb.nextInt();
                studentDBUtil.fetchbyid(id);
                break;
            case 4:
                System.out.println("Enter id:");
                id=kb.nextInt();
                System.out.println("Enter name:");
                name=kb.next();
                System.out.println("Enter course:");
                course = kb.next();
                System.out.println("Enter sem");
                sem = kb.nextInt();
                
                student.setId(id);
                student.setName(name);
                student.setCourse(course);
                student.setSem(sem);
                
                if(studentDBUtil.updatedata(student)){
                    System.out.println("Student table updated successfully");
                }
                break;
            case 5:
                System.out.println("Enter id to delete record:");
                id = kb.nextInt();
                student.setId(id);
              if (studentDBUtil.deletedata(id))
              {
                  System.out.println("Successfully data deleted....");
              }
                           
                break;
            default:
                System.out.println("Enter the correct choice");
                break;
        }
    }
}

  
