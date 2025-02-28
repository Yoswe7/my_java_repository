import java.util.Scanner;
public class LabActivity1EmployeeInformationSystem {
    public static void main(String[] args) {
	
        Scanner s = new Scanner(System.in);
                
        System.out.println("Enter your first name: ");
        String Fname = s.nextLine();
        
                
        System.out.println("Enter your last name: ");
        String Lname = s.nextLine();
        
                
        System.out.println("Enter your Age: ");
        int Age = s.nextInt();
        
        
        System.out.println("Enter hours worked: ");
        float Hwork = s.nextFloat();
        
        
        System.out.println("Enter hourly wage: ");
        float Hwage = s.nextFloat();
        
        float Daily_Salary = (Hwork * Hwage);
        
        System.out.println("\nEmployee Information");
        
        System.out.println("----------------------");
        
        System.out.println("Full name   : " + Fname + " " + Lname);
        
        System.out.println("Age         : " + Age + " years old");
        
        
        System.out.printf("Daily Salary: PHP %.2f%n", Daily_Salary);
        
            }
        }

