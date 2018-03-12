package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * RequestEmailWriterService keeps track of requests.txt.
 */
public class RequestEmailWriterService extends Service {
  private LoggingOutputService loggingOutputService;

  @ServiceConstructor
  public RequestEmailWriterService(LoggingOutputService loggingOutputService) {
    this.loggingOutputService = loggingOutputService;
    try {
      writeToFile("Ingredients to be reordered: \r\n", "requests.txt", false);
    } catch (IOException ignored) {

    }
  }

  /**
   * Write a text file called requests.txt that shows the ingredients
   * to be reordered.
   *
   * @param ingredient ingredient to be written on the text file.
   */
  public void write(Ingredient ingredient) {
    StringBuilder string = new StringBuilder();
    string.append(ingredient.getName());
    string.append(": ");
    string.append(ingredient.getReorderAmount());
    try {
      writeToFile(string.toString(), "requests.txt", true);
      loggingOutputService.printString(string.toString());
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Given a text and a path of the file to be written, create a file
   * on the path and add the given text to it.
   *
   * @param text   text to be written on the text file.
   * @param path   the path in which the file is stored
   * @param append boolean to indicate whether you want to append to the file or overwrite it.
   */
  private void writeToFile(String text, String path, boolean append) throws IOException {
    FileWriter write = new FileWriter(path, append);
    PrintWriter printLine = new PrintWriter(write);
    printLine.printf("%s" + "%n", text);
    printLine.close();
  }

}
