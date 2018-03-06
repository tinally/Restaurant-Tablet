package kitchen;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
public class Request {
    private static Writer data = new Writer();
    public static void addToRequests(String ingredient) {
        try {
            data.writeToFile(ingredient);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}

    class Writer {
        private String path = "/kitchen/requests.txt";

        public Writer(){}
        public void writeToFile(String textLine) throws IOException {
            FileWriter write = new FileWriter(path);
            PrintWriter printLine = new PrintWriter(write);
            printLine.printf("%s" + "%n", textLine);
            printLine.close();
        }


    }


