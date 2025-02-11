import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;
    public ContactManager() {
        contacts = new ArrayList<Contact>();
    }
    public boolean addContact(Contact contact) {
        for (Contact c : contacts) {
            if (c.equals(contact)) {
                System.out.println("Contact already exists and will not be added.");
                return false;
            }
        }
        contacts.add(contact);
        return true;
    }

    public boolean deleteContact(String identifier) {
        return contacts.removeIf(contact ->
                contact.getName().equalsIgnoreCase(identifier) ||
                contact.getPhoneNumber().equalsIgnoreCase(identifier) ||
                contact.getEmail().equalsIgnoreCase(identifier)
                );
    }
    public Contact searchContact(String identifier) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(identifier) ||
                    contact.getPhoneNumber().equals(identifier) ||
                    contact.getEmail().equalsIgnoreCase(identifier)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("\n\nCONTACTS:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void loadContactsFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No existing contacts found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String phone = (parts.length > 1 && !parts[1].trim().isEmpty()) ? parts[1].trim() : null;
                String email = (parts.length > 2 && !parts[2].trim().isEmpty()) ? parts[2].trim() : null;

                try {
                    addContact(new Contact(name, phone, email));
                } catch (IllegalArgumentException e) {
                }
            }
            System.out.println("Contacts loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error reading contacts file: " + e.getMessage());
        }
    }


    public void saveContactsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," +
                        (contact.getPhoneNumber() == null ? "" : contact.getPhoneNumber()) + "," +
                        (contact.getEmail() == null ? "" : contact.getEmail()));
                writer.newLine();
            }
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

}
