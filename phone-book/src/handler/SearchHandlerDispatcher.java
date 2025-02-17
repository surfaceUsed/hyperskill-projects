package handler;

import config.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

public class SearchHandlerDispatcher {

    private static final List<SearchHandler> HANDLERS = new ArrayList<>();
    private static final List<String> phoneBook = ConfigurationManager.getInstance().getPhoneBookList();
    private static final List<String> searchNames = ConfigurationManager.getInstance().getSearchNames();

   static {
        HANDLERS.add(new HandleLinearSearch(phoneBook, searchNames));
        HANDLERS.add(new HandleJumpSearch(phoneBook, searchNames));
        HANDLERS.add(new HandleBinarySearch(phoneBook, searchNames));
        HANDLERS.add(new HandleHashTableSearch(phoneBook, searchNames));
    }

    private SearchHandlerDispatcher() {}


    public static List<SearchHandler> getHandlers() {
        return HANDLERS;
    }
}
