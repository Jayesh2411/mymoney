package org.example;

import static org.example.service.RequestProcessor.processRequest;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("PLEASE SPECIFY THE PATH FOR TEST DATA; EG- $java -jar <path_to>/geektrust.jar <absolute_path_to_input_file>");
            return;
        }

        processRequest(args[0]);

    }


}