package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.io.*;
import java.util.ArrayList;

/**
 * RequestEmailWriterService keeps track of requests.txt.
 */
public class RequestEmailWriterService extends Service {

    @ServiceConstructor
    public RequestEmailWriterService() {
        try {
            writeToFile("Ingredients to be reordered: \r\n", "requests.txt", false);
        } catch (IOException ignored) {

        }
    }

    /**
     * Write an ingredient to be reordered on requests.txt file.
     * This file is stored in the kitchen package
     *
     * @param ingredient ingredient to be written on the text file.
     */
    public void write(Ingredient ingredient) {
        StringBuilder string = new StringBuilder();
        string.append(ingredient.getName());
        string.append(": ");
        string.append(ingredient.getReorderAmount());
        string.append("\r\n");
        try {
            writeToFile(string.toString(), "requests.txt", true);
            logger.info(string.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Given a text and a path of the file to be written, create a file
     * on the path and add the given text to it.
     *
     * @param text text to be written on the text file.
     * @param path the path in which the file is stored
     */
    private void writeToFile(String text, String path, boolean append) throws IOException {
        FileWriter write = new FileWriter(path, append);
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", text);
        printLine.close();
    }

}
