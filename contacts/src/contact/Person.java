package contact;

import util.ValidationConstants;
import util.InputValidator;
import view.ConsoleView;
import java.util.ArrayList;
import java.util.List;

class Person extends Contact {

    private String surname;
    private String birth;
    private String gender;

    Person() {
        super();
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setGender(String gender) {
        if (InputValidator.isValidGender(gender)) {
            this.gender = gender;
        } else {
            this.gender = ValidationConstants.INVALID_INPUT;
            ConsoleView.invalidGender();
        }
    }

    void setBirth(String birth) {
        if (InputValidator.isValidBirthDate(birth)) {
            this.birth = birth;
        } else {
            this.birth = ValidationConstants.INVALID_INPUT;
            ConsoleView.invalidBirthDate();
        }
    }

    @Override
    public void updateContact(String fieldName, String updateValue) {

        boolean isUpdated = true;
        Fields field = Fields.getField(fieldName);

        switch (field) {

            case NAME:
                super.setName(updateValue);
                break;

            case SURNAME:
                setSurname(updateValue);
                break;

            case GENDER:
                setGender(updateValue);
                break;

            case NUMBER:
                super.setNumber(updateValue);
                break;

            case BIRTH_DATE:
                setBirth(updateValue);
                break;

            default:
                ConsoleView.invalidFieldValue(fieldName);
                isUpdated = false;
                break;
        }

        if (isUpdated) {
            super.updateNewChange();
        }
    }

    @Override
    public List<String> getEditableFields() {
        return Fields.getFieldsAsStringList();
    }


    @Override
    public String getFullName() {
        return super.getName() + " " + this.surname;
    }

    @Override
    public String getContactInfo() {
        return getFullName() + " " + super.getNumber().replaceAll(" ", "");
    }

    @Override
    public String toString() {
        return String.format("""
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                Number: %s
                Time created: %s
                Time last edit: %s
                """,
                super.getName(), this.surname, this.birth, this.gender, super.getNumber(),
                super.getContactCreated(), super.getContactUpdated());
    }

    enum Fields {

        NAME("name"),
        SURNAME("surname"),
        BIRTH_DATE("birth date"),
        GENDER("gender"),
        NUMBER("number"),
        INVALID_FIELD("invalid field");

        private final String fieldName;

        Fields(String fieldName) {
            this.fieldName = fieldName;
        }

        private String getFieldName() {
            return this.fieldName;
        }

        private static Fields getField(String fieldName) {
            for (Fields field : Fields.values()) {
                if (field.getFieldName().equals(fieldName)) {
                    return field;
                }
            }
            return INVALID_FIELD;
        }

        private static List<String> getFieldsAsStringList() {
            List<String> fieldList = new ArrayList<>();
            for (Fields field : Fields.values()) {
                if (field != Fields.INVALID_FIELD) {
                    fieldList.add(field.getFieldName().toLowerCase());
                }
            }
            return fieldList;
        }
    }
}