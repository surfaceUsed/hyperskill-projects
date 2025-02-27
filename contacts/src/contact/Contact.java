package contact;

import util.ValidationConstants;
import util.InputValidator;
import view.ConsoleView;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime contactCreated;

    private String name;
    private String number;
    private LocalDateTime contactUpdated;

    Contact() {
        this.contactCreated = LocalDateTime.now();
        this.contactUpdated = LocalDateTime.now();
    }

    public String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    void setNumber(String number) {
        if (InputValidator.isValidPhoneNumber(number)) {
            this.number = number;
        } else {
            this.number = ValidationConstants.INVALID_NUMBER;
            ConsoleView.invalidPhoneNumber();
        }
    }

    void updateNewChange() {
        this.contactUpdated = LocalDateTime.now();
    }

    String getContactCreated() {
        return formatTime(this.contactCreated);
    }

    String getContactUpdated() {
        return formatTime(this.contactUpdated);
    }

    private String formatTime(LocalDateTime time) {
        String europeanDateFormat = ValidationConstants.DATE_TIME_FORMAT;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(europeanDateFormat);
        return (time.format(formatter));
    }

    public abstract String getFullName();

    /**
     *
     * Returns the person/organization name + phone number as a string.
     *
     * This method is used for query searching and matching.
     */
    public abstract String getContactInfo();

    /**
     *
     * Used for updating a specific contact field with a new value.
     */
    public abstract void updateContact(String fieldName, String updateValue);

    /**
     *
     * Returns a list of the contact-fields that are editable.
     */
    public abstract List<String> getEditableFields();
}