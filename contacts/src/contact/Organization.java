package contact;

import view.ConsoleView;
import java.util.ArrayList;
import java.util.List;

class Organization extends Contact {

    private String address;

    Organization() {
        super();
    }

    void setAddress(String address) {
        this.address = address;
    }

    public void updateContact(String fieldName, String updateValue) {

        boolean isUpdated = true;

        Fields field = Fields.getField(fieldName);

        switch (field) {

            case NAME:
                super.setName(updateValue);
                break;

            case ADDRESS:
                setAddress(updateValue);
                break;

            case NUMBER:
                super.setNumber(updateValue);
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
    public String getFullName() {
        return super.getName();
    }

    @Override
    public List<String> getEditableFields() {
        return Fields.getFieldsAsStringList();
    }

    @Override
    public String getContactInfo() {
        return getFullName() + " " +  this.address + " " + super.getNumber().replaceAll(" ", "");
    }

    @Override
    public String toString() {
        return String.format("""
                Organization name: %s
                Address: %s
                Number: %s
                Time created: %s
                Time last edit: %s
                """,
                super.getName(), this.address, super.getNumber(), super.getContactCreated(),
                super.getContactUpdated());
    }

    private enum Fields {

        NAME("name"),
        ADDRESS("address"),
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
