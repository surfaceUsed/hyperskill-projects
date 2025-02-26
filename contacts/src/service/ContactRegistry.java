package service;

import config.ConfigurationManager;
import config.PhoneBook;
import contact.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactRegistry implements Service {

    private final PhoneBook phonebook;
    private final List<Contact> contactList;

    public ContactRegistry() {
        this.phonebook = ConfigurationManager.getInstance().getPhonebook();
        this.contactList = this.phonebook.getContacts();
    }

    @Override
    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }

    @Override
    public void removeContact(Contact contact) {
        this.contactList.remove(contact);
    }

    @Override
    public int countContacts() {
        return this.contactList.size();
    }

    @Override
    public List<String> listContactNamesAsList() {
        List<String> list = new ArrayList<>();
        int counter = 0;
        for (Contact contact : this.contactList) {
            counter++;
            String contactInfo = counter + ". " + contact.getFullName();
            list.add(contactInfo);
        }
        return list;
    }

    @Override
    public Contact getContactById(int contactId) {
        return this.contactList.get(contactId - 1);
    }

    @Override
    public Contact getContactByName(String name) {
        for (Contact contact : this.contactList) {
            if (contact.getFullName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getContacts() {
        return this.contactList;
    }

    @Override
    public void closeRegistry() {
        this.phonebook.savePhonebook();
    }
}