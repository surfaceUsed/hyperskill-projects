package contact;

import view.ConsoleView;

public abstract class ContactCreator {

    public static Contact createContact() {

        ContactType type = ContactType.getContactType(ConsoleView.enterContactType());

        if (type != ContactType.INVALID_CONTACT) {

            switch (type) {

                case PERSON:

                    Person person = new Person();

                    person.setName(ConsoleView.enterFirstName());

                    person.setSurname(ConsoleView.enterSurname());

                    person.setBirth(ConsoleView.enterBirthDate());

                    person.setGender(ConsoleView.enterGender());

                    person.setNumber(ConsoleView.enterPhoneNumber());

                    return person;

                case ORGANIZATION:

                    Organization org = new Organization();

                    org.setName(ConsoleView.enterOrgName());

                    org.setAddress(ConsoleView.enterAddress());

                    org.setNumber(ConsoleView.enterPhoneNumber());

                    return org;
            }
        }
        ConsoleView.invalidType();
        return null;
    }

    enum ContactType {

        PERSON("person"),
        ORGANIZATION("organization"),
        INVALID_CONTACT("invalid contact type");

        private final String type;

        ContactType(String type) {
            this.type = type;
        }

        private String getType() {
            return this.type;
        }

        static ContactType getContactType(String type) {
            for (ContactType contact : ContactType.values()) {
                if (contact.getType().equals(type)) {
                    return contact;
                }
            }
            return INVALID_CONTACT;
        }
    }
}
