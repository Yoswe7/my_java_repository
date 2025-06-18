import java.util.ArrayList;
import java.util.Scanner;

public class MyMidtermLabExam {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);

        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> urgencies = new ArrayList<>();
        ArrayList<String> statuses = new ArrayList<>();
        int ticketcount = 0;
        int choice;

        do {
            System.out.println("\n=== IT Ticket Processing System ===");
            System.out.println("1. Add Ticket");
            System.out.println("2. Update Ticket Status");
            System.out.println("3. Show All Tickets");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    ticketcount = addTicket(s, descriptions, urgencies, statuses, ticketcount);
                    break;
                case 2:
                    updateTicketStatus(s, descriptions, urgencies, statuses, ticketcount);
                    break;
                case 3:
                    showTickets(descriptions, urgencies, statuses, ticketcount);
                    break;
                case 4:
                    generateReport(statuses, ticketcount);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid, Please try again");
            }
        } while (choice != 5);

        System.out.println("Exiting program.");
    }

    public static int addTicket(Scanner s, ArrayList<String> descriptions, ArrayList<String> urgencies, ArrayList<String> statuses, int ticketcount) {
        if (ticketcount >= 5) {
            System.out.println("Ticket limit reached.");
            return ticketcount;
        }

        System.out.print("Enter issue description: ");
        String issue = s.nextLine();
        descriptions.add(issue);

        System.out.print("Enter urgency level (Low, Medium, High): ");
        String urgency = s.nextLine();
        urgencies.add(urgency);

        statuses.add("Pending");

        ticketcount++;
        System.out.println("Ticket added successfully with status 'Pending'.");
        return ticketcount;
    }

    public static void updateTicketStatus(Scanner s, ArrayList<String> descriptions, ArrayList<String> urgencies, ArrayList<String> statuses, int ticketcount) {
        if (ticketcount == 0) {
            System.out.println("There are no tickets to update");
            return;
        }

        System.out.println("\nList of Tickets:");
        for (int i = 0; i < ticketcount; i++) {
            System.out.println(i + ". " + descriptions.get(i) + " - " + statuses.get(i));
        }

        System.out.print("Enter ticket number to update (0 to " + (ticketcount - 1) + "): ");
        int ticketNumber = s.nextInt();
        s.nextLine();

        if (ticketNumber < 0 || ticketNumber >= ticketcount) {
            System.out.println("Invalid ticket number.");
            return;
        }

        String currentStatus = statuses.get(ticketNumber);
        if (currentStatus.equals("Resolved")) {
            System.out.println("Ticket is already resolved. It cannot be updated.");
            return;
        }

        System.out.print("Enter new status (1 for 'In Progress', 2 for 'Resolved'): ");
        int statusChoice = s.nextInt();
        s.nextLine();

        if (statusChoice == 1) {
            statuses.set(ticketNumber, "In Progress");
            System.out.println("Ticket status updated to 'In Progress'.");
        } else if (statusChoice == 2) {
            statuses.set(ticketNumber, "Resolved");
            System.out.println("Ticket status updated to 'Resolved'.");
        } else {
            System.out.println("Invalid choice. Status not updated.");
        }
    }

    public static void showTickets(ArrayList<String> descriptions, ArrayList<String> urgencies, ArrayList<String> statuses, int ticketcount) {
        if (ticketcount == 0) {
            System.out.println("No tickets to display.");
            return;
        }

        System.out.println("\n=== All Tickets ===");
        for (int i = 0; i < ticketcount; i++) {
            System.out.println("Ticket #" + i);
            System.out.println("Issue: " + descriptions.get(i));
            System.out.println("Urgency: " + urgencies.get(i));
            System.out.println("Status: " + statuses.get(i));
            System.out.println("---------------------");
        }
    }

    public static void generateReport(ArrayList<String> statuses, int ticketcount) {
        int pendingOrInProgress = 0;
        int resolved = 0;

        for (int i = 0; i < ticketcount; i++) {
            String status = statuses.get(i);
            if (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("In Progress")) {
                pendingOrInProgress++;
            } else if (status.equalsIgnoreCase("Resolved")) {
                resolved++;
            }
        }

        System.out.println("\n=== Ticket Report ===");
        System.out.println("Total Tickets: " + ticketcount);
        System.out.println("Pending/In Progress: " + pendingOrInProgress);
        System.out.println("Resolved: " + resolved);
    }
}
