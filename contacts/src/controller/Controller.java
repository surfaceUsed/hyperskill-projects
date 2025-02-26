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
import manager.ContactManager;
import manager.StateManager;
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

    private void add() {
        Contact newContact = ContactCreator.createContact();
        if (newContact != null) {
            ContactManager.setCurrentContact(newContact);
            updateRegistry();
            return;
        }
        StateManager.setCurrentState(State.MENU);
    }

    private void list() {
        this.lister.listRecords();
        if (StateManager.getCurrentState() == State.UPDATE) {
            updateRegistry();
        }
    }

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

    private void updateRegistry() {
        this.updater.updateRegistry();
    }

    private void exit() {
        this.isExit = true;
        ConsoleView.close();
        this.registry.closeRegistry();
    }
}
