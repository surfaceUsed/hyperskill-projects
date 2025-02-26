package handler;

import enums.State;
import service.Service;
import manager.ContactManager;
import manager.StateManager;
import view.ConsoleView;
import java.util.List;

public class UpdateHandler {

    private final Service registry;

    public UpdateHandler(Service registry) {
        this.registry = registry;
    }

    public void updateRegistry() {
        addOrUpdate();
    }

    public void addOrUpdate() {

        switch (StateManager.getCurrentState()) {

            case ADD -> addNewContact();

            case UPDATE -> update();
        }
        ContactManager.setCurrentContact(null);
    }

    private void addNewContact() {
        this.registry.addContact(ContactManager.getCurrentContact());
        StateManager.setCurrentState(State.MENU);
    }

    private void update() {
        State state = State.getState(ConsoleView.chooseRecordModifyAction());

        switch (state) {

            case EDIT -> edit();

            case DELETE -> delete();

            case MENU -> {
                StateManager.setCurrentState(State.MENU);
                ContactManager.setCurrentContact(null);
            }

            default -> {
                ConsoleView.invalidInput();
                update();
            }
        }
    }

    private void edit() {
        List<String> contactFields = ContactManager.getCurrentContact().getEditableFields();
        String fieldKey = ConsoleView.chooseFieldKey(contactFields);
        if (contactFields.contains(fieldKey)) {
            String updatedFieldValue = ConsoleView.enterNewFieldValue(fieldKey);
            ContactManager.getCurrentContact().updateContact(fieldKey, updatedFieldValue);
            ConsoleView.recordSaved();
            System.out.println(ContactManager.getCurrentContact());
        } else {
            ConsoleView.invalidFieldValue(fieldKey);
        }
        StateManager.setCurrentState(State.UPDATE);
        update();
    }

    private void delete() {
        this.registry.removeContact(ContactManager.getCurrentContact());
        StateManager.setCurrentState(State.LIST);
    }
}
