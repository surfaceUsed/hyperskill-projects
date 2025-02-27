package view;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public final class ConsoleView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleView() {}

    private static String writeInput() {
        return SCANNER.nextLine().trim();
    }

    public static void close() {
        SCANNER.close();
    }

    public static String chooseMainMenuAction() {
        System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
        return writeInput();
    }

    public static String chooseListMenuAction(List<String> contactRecords) {
        for (String contact : contactRecords) {
            System.out.println(contact);
        }
        System.out.print("[list] Enter action ([number], back): ");
        return writeInput();
    }

    public static String chooseSearchMenuActions(List<String> matches) {
        int counter = 0;
        for (String match : matches) {
            counter++;
            System.out.println(counter + ". " + match);
        }
        System.out.print("\n[search] Enter action ([number], back, again): ");
        return writeInput();
    }

    public static String chooseRecordModifyAction() {
        System.out.print("[record] Enter action (edit, delete, menu): ");
        return writeInput();
    }

    public static String enterQuery() {
        System.out.print("Enter search query (search for contact name or number): ");
        return writeInput();
    }

    public static String chooseFieldKey(List<String> fields) {
        StringBuilder sb = new StringBuilder("Select a field (");
        int length = fields.size();
        String lastField = fields.get(length -1);
        for (int i = 0; i < length - 1; i++) {
            sb.append(fields.get(i)).append(", ");
        }
        sb.append(lastField).append("): ");
        System.out.print(sb);
        return writeInput();
    }

    public static String enterNewFieldValue(String field) {
        System.out.print("Enter " + field + ": ");
        return writeInput();
    }

    public static void countNumberOfRecords(int numberOfRecords) {
        String record = (numberOfRecords == 1) ? "record" : "records";
        System.out.println("The Phone Book has " + numberOfRecords + " " + record + ".");
    }

    public static void noQueryMatch(String query) {
        System.out.println("\nThe query \"" + query + "\" produced no matching results.");
    }

    public static void emptyPhoneBook() {
        System.out.println("\nThe Phone Book is empty.");
    }

    public static void recordSaved() {
        System.out.println("saved!");
    }

    public static void printPhonebookPath(File path) {
        System.out.println("open " + path);
    }

    public static void invalidInput() {
        System.out.println("\nInvalid input, try again.");
    }

    public static void invalidType() {
        System.out.println("\nInvalid contact type!");
    }

    public static void invalidFieldValue(String fieldValue) {
        System.out.println("\n" + fieldValue + " is an invalid field.");
    }

    public static void invalidPhoneNumber() {
        System.out.println("Wrong number format!");
    }

    public static void invalidGender() {
        System.out.println("Bad Gender!");
    }

    public static void invalidBirthDate() {
        System.out.println("Bad birth date!");
    }

    public static String enterContactType() {
        System.out.print("Enter the type (person, organization): ");
        return writeInput();
    }

    public static String enterFirstName() {
        System.out.print("Enter first name: ");
        return writeInput();
    }

    public static String enterSurname() {
        System.out.print("Enter surname: ");
        return writeInput();
    }

    public static String enterBirthDate() {
        System.out.print("Enter birth date (dd.mm.yyyy): ");
        return writeInput();
    }

    public static String enterGender() {
        System.out.print("Enter gender (M, F): ");
        return writeInput();
    }

    public static String enterPhoneNumber() {
        System.out.print("Enter number: ");
        return writeInput();
    }

    public static String enterAddress() {
        System.out.print("Enter the address: ");
        return writeInput();
    }

    public static String enterOrgName() {
        System.out.print("Enter the organization name: ");
        return writeInput();
    }
}
