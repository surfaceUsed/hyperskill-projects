package handler;

import contact.Contact;
import enums.State;
import service.Service;
import manager.ContactManager;
import manager.StateManager;
import util.InputValidator;
import view.ConsoleView;
import java.util.List;

public class ListHandler {

    private final Service registry;

    public ListHandler(Service registry) {
        this.registry = registry;
    }

    public void listRecords() {
        int numberOfRecords = this.registry.countContacts();
        if (numberOfRecords > 0) {
            chooseContact(this.registry.listContactNamesAsList());
        } else {
            ConsoleView.emptyPhoneBook();
            StateManager.setCurrentState(State.MENU);
        }
    }

    private void chooseContact(List<String> contactRecords) {
        String input = ConsoleView.chooseListMenuAction(contactRecords);
        int id;
        if (InputValidator.isNumber(input) && (id = Integer.parseInt(input)) <= contactRecords.size() && id > 0) {
            Contact contact = this.registry.getContactById(id);
            System.out.println(contact);
            ContactManager.setCurrentContact(contact);
            StateManager.setCurrentState(State.UPDATE);
        } else {
            State state = State.getState(input);

            if (state == State.BACK) {
                StateManager.setCurrentState(State.MENU);
            } else {
                ConsoleView.invalidInput();
                StateManager.setCurrentState(State.LIST);
            }
        }
    }
}