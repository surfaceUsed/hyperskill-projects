package service;

import contact.Contact;
import java.util.List;

public interface Service {

    void addContact(Contact contact);

    void removeContact(Contact contact);

    int countContacts();

    List<String> listContactNamesAsList();

    Contact getContactById(int contactId);

    Contact getContactByName(String name);

    List<Contact> getContacts();

    void closeRegistry();
}
