package cs3500.threetrios.model.configreader;

import com.sun.jdi.connect.Connector;
import cs3500.threetrios.model.card.ThreeTriosCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardReader {
  public static List<ThreeTriosCard> getDeckFromConfig(String filename) {
    List<ThreeTriosCard> deck = new ArrayList<>();
    try {
      FileReader fr = new FileReader(filename);
      StringBuilder fileContent = new StringBuilder();
      int nextChar;
      while ((nextChar = fr.read()) != -1) {
        fileContent.append((char) nextChar);
      }
      String[] lines = fileContent.toString().split("\n");
      String[] attributes;
      System.out.println(lines[0]);
      for (String line : lines) {
        attributes = line.split("\\s");
        if (attributes.length != 5 ) {
          throw new IllegalArgumentException("Invalid card format");
        }
        deck.add(new ThreeTriosCard(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]),
            Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4])));
      }
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File Not Found");
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid card format");
    }

    return deck;
  }
}
