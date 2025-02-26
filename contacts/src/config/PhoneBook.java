package config;

import contact.Contact;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private final File phonebookFile;
    private final List<Contact> contactList = new ArrayList<>();

    private PhoneBook(File file) {
        this.phonebookFile = file;
    }

    protected static PhoneBook loadPhonebook(File path) {
        try {

            return  (path.length() > 0) ?
                    (PhoneBook) Deserialize.deserialize(path) :
                    new PhoneBook(path);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to deserialize file '" + path + "': " + e.getMessage());
        }
    }

    public List<Contact> getContacts() {
        return this.contactList;
    }

    public void savePhonebook() {
        try {
            Serialize.serialize(this.phonebookFile, this);
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }
}
