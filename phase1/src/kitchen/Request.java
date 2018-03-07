package kitchen;
import java.io.*;
import java.util.ArrayList;

public class Request {
    public static void write(ArrayList<Ingredient> ingredients) {
        StringBuilder string = new StringBuilder("Ingredients to be reordered: \r\n");

        for (Ingredient ingredient : ingredients) {
            string.append(ingredient.getName()).append(": ").append(ingredient.getReorderAmount()).append("\r\n");
        }
        try {
            writeToFile(string.toString(), "src/kitchen/requests.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void writeToFile(String textLine, String path) throws IOException {
        FileWriter write = new FileWriter(path);
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", textLine);
        printLine.close();
    }

}
