import java.util.Comparator;
import java.util.Scanner;

public class ContactManagerApp {
    private static final String FILE_PATH = "contacts.txt";

    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        manager.loadContactsFromFile(FILE_PATH);
        manager.getContacts().sort(Comparator.comparing(Contact::getName));

        Scanner sc = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(manager, sc);
                    break;
                case 2:
                    deleteContact(manager, sc);
                    break;
                case 3:
                    editContact(manager, sc);
                    break;
                case 4:
                    searchContact(manager, sc);
                    break;
                case 5:
                    manager.displayAllContacts();
                    break;
                case 6:
                    System.out.println("Saving contacts and exiting...");
                    manager.saveContactsToFile(FILE_PATH);
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n\t\t\tCONTACT MANAGER\n");
        System.out.println("1. Add Contact");
        System.out.println("2. Delete Contact");
        System.out.println("3. Edit Contact");
        System.out.println("4. Search Contact");
        System.out.println("5. Display All Contacts");
        System.out.println("6. Exit");
    }

    private static void addContact(ContactManager manager, Scanner sc) {
        System.out.print("Enter contact name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        try {
            manager.addContact(new Contact(name, phone, email));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteContact(ContactManager manager, Scanner sc) {
        System.out.print("Enter Name, Phone, or Email to delete: ");
        String identifier = sc.nextLine();

        if (manager.deleteContact(identifier)) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("No matching contact found!");
        }
    }

    private static void editContact(ContactManager manager, Scanner sc) {
        System.out.print("Enter Name, Phone, or Email to edit: ");
        String identifier = sc.nextLine();

        Contact contact = manager.searchContact(identifier);
        if (contact == null) {
            System.out.println("No matching contact found!");
            return;
        }

        System.out.print("Enter new name (or press Enter to keep the same): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            contact.setName(newName);
        }

        System.out.print("Enter new phone number (or press Enter to keep the same): ");
        String newPhone = sc.nextLine();
        if (!newPhone.isEmpty()) {
            try {
                contact.setPhoneNumber(newPhone);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid phone number: " + e.getMessage());
            }
        }

        System.out.print("Enter new email (or press Enter to keep the same): ");
        String newEmail = sc.nextLine();
        if (!newEmail.isEmpty()) {
            contact.setEmail(newEmail);
        }

        System.out.println("Contact updated successfully!");
    }

    private static void searchContact(ContactManager manager, Scanner sc) {
        System.out.print("Enter Name, Phone, or Email to search: ");
        String identifier = sc.nextLine();

        Contact contact = manager.searchContact(identifier);
        if (contact != null) {
            System.out.println("Contact found: " + contact);
        } else {
            System.out.println("No matching contact found!");
        }
    }
}
