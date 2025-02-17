import config.ConfigurationManager;
import handler.SearchHandler;
import handler.SearchHandlerDispatcher;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        ConfigurationManager.loadInputFiles(
                "data/phone_book.txt",
                "data/search_names.txt");
        printResults();

    }

    private static void printResults() {
        List<SearchHandler> handlers = SearchHandlerDispatcher.getHandlers();
        for (SearchHandler handler : handlers) {
            handler.printResult();
            System.out.println();
        }
    }
}