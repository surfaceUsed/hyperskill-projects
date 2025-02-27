package controller;

import contact.Contact;

public class ContactManager {

    private ContactManager() {}

    private static Contact currentContact = null; // Default value.

    public static void setCurrentContact(Contact contact) {
        currentContact = contact;
    }

    public static Contact getCurrentContact() {
        return currentContact;
    }
}
