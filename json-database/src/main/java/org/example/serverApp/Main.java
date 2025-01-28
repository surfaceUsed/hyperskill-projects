package org.example.serverApp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Tacotaco");
        list.add("avo");
        list.add("Bravo");

        Comparator<String> comp = (text1, text2) -> text1.compareToIgnoreCase(text2);

        list.sort(comp);

        System.out.println(list);

        Stream<String> persons = Stream.of("John", "Demetra", "Cleopatra");



        /*try (ServerSocket serverSocket = new ServerSocket(5555)) {

            // Receives path to server directory from command line.
            Server server = new Server(serverSocket, Directory.loadDirectory(args[0]));

            server.start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

         */
    }
}
