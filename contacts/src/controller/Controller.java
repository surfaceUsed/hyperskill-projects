package controller;

import config.ConfigurationManager;
import contact.Contact;
import contact.ContactCreator;
import enums.State;
import handler.ListHandler;
import handler.SearchHandler;
import handler.UpdateHandler;
import service.ContactRegistry;
import service.Service;
import view.ConsoleView;

public class Controller {

    private final Service registry;
    private final ListHandler lister;
    private final SearchHandler searcher;
    private final UpdateHandler updater;

    private boolean isExit = false;

    public Controller() {
        this.registry = new ContactRegistry();
        this.lister = new ListHandler(this.registry);
        this.searcher = new SearchHandler(this.registry);
        this.updater = new UpdateHandler(this.registry);
    }

    public void start() {

        ConsoleView.printPhonebookPath(ConfigurationManager.getInstance().getPath());

        while (!this.isExit) {

            switch (StateManager.getCurrentState()) {

                case MENU -> menu();

                case ADD -> add();

                case LIST -> list();

                case SEARCH -> search();

                case COUNT -> count();

                case EXIT -> exit();

                default -> {
                    ConsoleView.invalidInput();
                    StateManager.setCurrentState(State.MENU);
                }
            }
        }
    }

    private void menu() {
        StateManager.setCurrentState(State.getState(ConsoleView.chooseMainMenuAction()));
    }

    /**
     *
     * If a newContact object is successfully created, then we set the current record to that contact, and call the
     * "updateRegistry()", to add the current record.
     *
     * If newContact is null, go back to main menu.
     */
    private void add() {
        Contact newContact = ContactCreator.createContact();
        if (newContact != null) {
            ContactManager.setCurrentContact(newContact);
            updateRegistry();
            return;
        }
        StateManager.setCurrentState(State.MENU);
    }

    /**
     *
     * Invokes the list handler.
     *
     * If the application state, after the "listRecords()" method is finished, is "UPDATE", then the contact set as
     * current record by the ContactManager class is set for modification (edit/delete). The "updateRegistry()" method
     * is called to perform the modification.
     */
    private void list() {
        this.lister.listRecords();
        if (StateManager.getCurrentState() == State.UPDATE) {
            updateRegistry();
        }
    }

    /**
     *
     * Invokes the search handler.
     *
     * If the application state, after the "searchRecords()" method is finished, is "UPDATE", then the contact set as
     * current record by the ContactManager class is set for modification (edit/delete). The "updateRegistry()" method
     * is called to perform the modification.
     */
    private void search() {
        this.searcher.searchRecords();
        if (StateManager.getCurrentState() == State.UPDATE) {
            updateRegistry();
        }
    }

    private void count() {
        ConsoleView.countNumberOfRecords(this.registry.countContacts());
        StateManager.setCurrentState(State.MENU);
    }

    /**
     *
     * Modifies registry (add, edit or delete).
     */
    private void updateRegistry() {
        this.updater.updateRegistry();
    }

    private void exit() {
        this.isExit = true;
        ConsoleView.close();
        this.registry.closeRegistry();
    }
}
