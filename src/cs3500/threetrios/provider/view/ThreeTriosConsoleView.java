package cs3500.threetrios.provider.view;

import java.io.IOException;
import java.util.List;

import cs3500.threetrios.provider.model.Model;
import cs3500.threetrios.provider.model.ReadOnlyModel;

/**
 * Text-based view to visualize the state of the game.
 */
public class ThreeTriosConsoleView implements ConsoleView {
  private final ReadOnlyModel model;
  private final Appendable output;

  /**
   * Constructor for the view.
   *
   * @param model  contains all the information in the game state.
   * @param output is where all the text will be written to.
   */
  public ThreeTriosConsoleView(Model model, Appendable output) {
    if (output == null) {
      throw new IllegalArgumentException("Appendable must not be null");
    }
    this.model = model;
    this.output = output;
  }

  @Override
  public String toString() {
    StringBuilder text = new StringBuilder();

    String currentPlayer = "Player: " + model.toMove().toString() + "\n";
    String boardContent = retrieveBoard();
    String handContent = retrieveHand();

    text.append(currentPlayer).append(boardContent).append(handContent);
    return text.toString();
  }

  /**
   * Gets the state of the game board in string format.
   * 'B'/'R' represents a player card, ' ' represents a hole, '_' represents an empty cell.
   *
   * @return a String representing all the cells on the board in String format.
   */
  private String retrieveBoard() {
    StringBuilder boardContent = new StringBuilder();
    for (List<Object> row : model.getAllCells()) {
      for (Object cell : row) {
        boardContent.append(cell.toString());
      }
      boardContent.append("\n");
    }
    return boardContent.toString();
  }

  /**
   * Gets the state of the current player's hand in string format.
   * Lists every card in their hand including each unique name and attack values respectively.
   *
   * @return a String representing all the cards in the current player's hand.
   */
  private String retrieveHand() {
    StringBuilder handTextBuilder = new StringBuilder();
    handTextBuilder.append("Hand:\n");
    for (Object card : model.getCurrentHand()) {
      handTextBuilder.append(card.toString()).append("\n");
    }
    return handTextBuilder.toString();
  }

  @Override
  public void render() throws IOException {
    output.append(toString());
  }
}
