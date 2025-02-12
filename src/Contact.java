import java.util.Objects;

public class Contact {

    // This class will represent an individual contact

    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public Contact() {
        this.name = "No name";
        this.phoneNumber = "No phone number";
        this.email = "No email";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null || name.trim().isEmpty()) ? "No name" : name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            this.phoneNumber = "No phone number";
        } else if (phoneNumber.matches("\\d{10,15}")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Phone number must contain only digits and be between 10-15 characters.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            this.email = "No email";
        } else if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ").append(name)
                .append("\nPhone Number: ").append(phoneNumber)
                .append("\nEmail: ").append(email);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return Objects.equals(name, contact.name) &&
                Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(email, contact.email);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email);
    }
}
