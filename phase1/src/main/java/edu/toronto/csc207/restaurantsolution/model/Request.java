package edu.toronto.csc207.restaurantsolution.model;

import edu.toronto.csc207.restaurantsolution.data.Ingredient;

import java.io.*;
import java.util.ArrayList;

/**
 * Request keeps track of requests.txt.
 */
public class Request {

    /**
     * Write the list of ingredients to be reordered on requests.txt file.
     * This file is stored in the kitchen package
     *
     * @param ingredients ingredients to be written on the text file.
     */
    public static void write(ArrayList<Ingredient> ingredients) {
        StringBuilder string = new StringBuilder("Ingredients to be reordered: \r\n");

        for (Ingredient ingredient : ingredients) {
            string.append(ingredient.getName());
            string.append(": ");
            string.append(ingredient.getReorderAmount());
            string.append("\r\n");
        }
        try {
            writeToFile(string.toString(), "requests.txt");
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
    private static void writeToFile(String text, String path) throws IOException {
        FileWriter write = new FileWriter(path);
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", text);
        printLine.close();
    }

}
