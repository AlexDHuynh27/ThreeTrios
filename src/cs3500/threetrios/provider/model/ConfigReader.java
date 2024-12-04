package cs3500.threetrios.provider.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import cs3500.threetrios.provider.model.Card;

/**
 * An object that provides utility methods to read configurations
 * for other game objects.
 */
public interface ConfigReader {
  /**
   * Gets the list of cards defined in the file `cardPath`.
   * Guaranteed to return a (possibly empty) list of cards.
   *
   * @param cardPath the path to the configuration file
   * @return a list of cards defined in the file `cardPath`
   * @throws FileNotFoundException if no such file exists at the
   *                               specified path
   * @throws IOException           if the attack values are not integers or if any of
   *                               the values for the cards are incomplete (e.g. less
   *                               than 4 numbers specified for attack values)
   */
  public List<Card> getCards(String cardPath) throws IOException, FileNotFoundException;

  /**
   * Gets the number of rows to be used in the game.
   * Guaranteed to return a (possibly negative or zero) integer.
   *
   * @param gridPath the path to the configuration file
   * @return the number of rows to be used
   * @throws FileNotFoundException if no such file exists at the
   *                               specified path
   * @throws IOException           if the first token in the file is not an integer
   */
  public int getNumRows(String gridPath) throws IOException, FileNotFoundException;

  /**
   * Gets the number of columns to be used in the game.
   * Guaranteed to return a (possibly negative or zero) integer.
   *
   * @param gridPath the path to the configuration file
   * @return the number of columns to be used
   * @throws FileNotFoundException if no such file exists at the
   *                               specified path
   * @throws IOException           if the second token in the file is not an integer
   */
  public int getNumCols(String gridPath) throws IOException, FileNotFoundException;

  /**
   * Gets the holes to be used in the game.
   * Guaranteed to return a (possibly empty) list of {integer, integer}.
   *
   * @param gridPath the path to the configuration file
   * @return the holes to be used
   * @throws FileNotFoundException if no such file exists at the
   *                               specified path
   * @throws IOException           if any of the following hold:
   *                               - invalid number of rows
   *                               - invalid number of columns
   *                               - file does not have number of rows specified
   *                               - file does not have number of columns specified
   */
  public List<List<Integer>> getHoles(String gridPath) throws IOException, FileNotFoundException;
}
