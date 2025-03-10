import java.util.Scanner;

class LabActivity2EmployeeInformationSystemPart2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // 1. Prompt the user for inputs.
        System.out.println("Enter your first name: ");
        String Fname = s.nextLine();

        System.out.println("Enter your last name: ");
        String Lname = s.nextLine();

        System.out.println("Enter your age: ");
        int age = s.nextInt();

        System.out.println("Enter hours worked in a day: ");
        double hwork = s.nextDouble();

        System.out.println("Enter hourly wage: ");
        double hwage = s.nextDouble();

        // 2. Compute the required details.
        // Full name in uppercase.
        String fullName = Lname + ", " + Fname;

        // Years to retirement using Math.abs()
        int yearsToRetirement = Math.abs(65 - age);

        // Daily salary rounded to the nearest whole number.
        double dailySalaryDouble = hwork * hwage;
        int Daily_Salary = (int) Math.round(dailySalaryDouble);

        // Weekly wage: daily salary * 5 (days)
        int Wsalary = Daily_Salary * 5;

        // Monthly wage: weekly salary * 4 (weeks)
        int Msalary = Wsalary * 4;

        // Gross yearly wage: monthly wage * 12 (months)
        int Ysalary = Msalary * 12;

        // Net yearly wage: gross yearly wage minus 32% deduction and Php 1,500.00 for
        // benefits.
        double Net_Ysalary = Ysalary - (0.32 * Ysalary) - 1500;

        // 3. Display the computed details.
        System.out.println("\nEmployee Information");
        System.out.println("---------------------");
        System.out.printf("Full Name:          %s%n", fullName);
        System.out.printf("Age:                %d years old%n", age);
        System.out.printf("Years to Retirement:%d years%n", yearsToRetirement);
        System.out.printf("Daily Salary:       Php %,.2f%n", Daily_Salary);
        System.out.printf("Weekly Salary:      Php %,.2f%n", Wsalary);
        System.out.printf("Monthly Salary:     Php %,.2f%n", Msalary);
        System.out.printf("Gross Yearly Salary:Php %,.2f%n", Ysalary);
        System.out.printf("Net Yearly Salary:  Php %,.2f%n", Net_Ysalary);

    }
}