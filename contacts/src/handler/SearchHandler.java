package handler;

import enums.State;
import service.Service;
import controller.ContactManager;
import controller.StateManager;
import util.InputValidator;
import view.ConsoleView;
import util.PatternMatcher;

import java.util.List;

public class SearchHandler {

    private final Service registry;

    public SearchHandler(Service registry) {
        this.registry = registry;
    }

    public void searchRecords() {
        int numberOfRecords = this.registry.countContacts();
        if (numberOfRecords > 0) {
            String query = ConsoleView.enterQuery();
            List<String> matches = PatternMatcher.getSearchMatches(this.registry.getContacts(), query);
            if (!matches.isEmpty()) {
                chooseContact(matches);
            } else {
                ConsoleView.noQueryMatch(query);
                StateManager.setCurrentState(State.MENU);
            }
        } else {
            ConsoleView.emptyPhoneBook();
            StateManager.setCurrentState(State.MENU);
        }
    }

    private void chooseContact(List<String> matches) {
        String option = ConsoleView.chooseSearchMenuActions(matches);
        int id;
        if (InputValidator.isNumber(option) && (id = Integer.parseInt(option)) <= matches.size() && id > 0) {
            String contactName = matches.get(id - 1);
            ContactManager.setCurrentContact(this.registry.getContactByName(contactName));
            StateManager.setCurrentState(State.UPDATE);
        } else {
            State state = State.getState(option);
            switch (state) {
                case BACK -> {
                    StateManager.setCurrentState(State.MENU);
                    ContactManager.setCurrentContact(null);
                }
                case AGAIN -> StateManager.setCurrentState(State.SEARCH);
                default -> {
                    ConsoleView.invalidInput();
                    chooseContact(matches);
                }
            }
        }
    }
}
