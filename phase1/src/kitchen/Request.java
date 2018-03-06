package kitchen;
import java.io.*;
import java.util.HashMap;

public class Request {
    private Writer data;
    private int lineNumber = 0;
    private HashMap<Ingredient, String > ingredients; //TODO: Update this for requests.

    public Request(){
        data = new Writer();
        try {
            data.writeToFile("Ingredients to be reordered:");
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void write(Ingredient ingredient) {
        String line = ingredient.getName() + ": " + ingredient.getReorderAmount();
        try {
            data.writeToFile(line);
            ingredients.put(ingredient, line);
            }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void remove(Ingredient ingredient){
        String line = ingredients.remove(ingredient);
        data.removeLine(line);
    }

}

    class Writer {
        private String path = "kitchen/requests.txt";

        public void writeToFile(String textLine) throws IOException {
            FileWriter write = new FileWriter(path);
            PrintWriter printLine = new PrintWriter(write);
            printLine.printf("%s" + "%n", textLine);
            printLine.close();
        }

        public void removeLine(String removeLine) {
            try {
                File currFile = new File(path);
                File tempFile = new File(currFile.getAbsolutePath() + ".tmp");
                BufferedReader lineReader = new BufferedReader(new FileReader(path));
                PrintWriter lineWriter = new PrintWriter(new FileWriter(tempFile));

                String line;

                while((line = lineReader.readLine()) != null) {
                    if (!line.trim().equals(removeLine)) {
                        lineWriter.println(line);
                    }
                }

                if (!currFile.delete()) {
                    System.out.println("Could not delete file");
                }

                if (!tempFile.renameTo(currFile)) {
                    System.out.println("Could not rename file");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}

