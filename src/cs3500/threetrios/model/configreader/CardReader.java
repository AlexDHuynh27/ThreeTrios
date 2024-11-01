package cs3500.threetrios.model.configreader;

import cs3500.threetrios.model.card.ThreeTriosCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that can read configuration files and produce a deck of cards.
 */
public class CardReader {

  private static boolean uniqueDeck(List<ThreeTriosCard> deck) {
    List<String> names = new ArrayList<>();
    for (int i = 0; i < deck.size(); i++) {
      if (names.contains(deck.get(i).getName())) {
        return false;
      }
      names.add(deck.get(i).getName());
    }
    return true;
  }

  /**
   * Reads a configuration file with a specified format and produces a List of ThreeTriosCards
   * from it. Each line in the configuration file must consist of 5 parts seperated by a single
   * space, the five parts together represents a card. The first part represents the
   * name of a card. The four other parts represent the attack values of the North, South,
   * East, and Wests sides respectively.
   * @param filename The name and path(if necessary) of the configuration file to read.
   * @return A List of ThreeTriosCards based on the given deck configuration file.
   * @throws IllegalArgumentException If the configuration file doesn't match the specified format.
   * @throws IllegalArgumentException if the card in the deck don't have unique names.
   */
  public static List<ThreeTriosCard> getDeckFromConfig(String filename) {
    List<ThreeTriosCard> deck = new ArrayList<>();
    try {
      FileReader fr = new FileReader(filename);
      StringBuilder fileContent = new StringBuilder();

      // Reads every char from the file and adds it to a StringBuilder
      int nextChar;
      while ((nextChar = fr.read()) != -1) {
        fileContent.append((char) nextChar);
      }

      // Strips the StringBuilder by new line into an Array
      String[] lines = fileContent.toString().split("\n");
      String[] attributes;

      // Segments the parts of each line and adds a new card into deck
      for (String line : lines) {
        attributes = line.split("\\s");
        if (attributes.length != 5) {
          throw new IllegalArgumentException("Invalid card format");
        }
        deck.add(new ThreeTriosCard(attributes[0], Integer.parseInt(attributes[1]),
                Integer.parseInt(attributes[2]),
                Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4])));
      }

      if (!uniqueDeck(deck)) {
        throw new IllegalArgumentException("Cards must have unique names");
      } else {
        return deck;
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File Not Found");
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid card format");
    }
  }
}
