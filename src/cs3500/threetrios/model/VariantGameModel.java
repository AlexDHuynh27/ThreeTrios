package cs3500.threetrios.model;

import cs3500.threetrios.model.battlerules.BattleRule;
import cs3500.threetrios.model.battlerules.CompositeBattleRule;
import cs3500.threetrios.model.battlerules.PlusRule;
import cs3500.threetrios.model.battlerules.SameRule;
import cs3500.threetrios.model.battlerules.SameRule.AdjacentCell;
import cs3500.threetrios.model.battlerules.RuleFactory;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Hole;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.view.ThreeTriosFeatures;

import java.util.ArrayList;
import java.util.List;

/**
 * VariantGameModel decorates an existing ThreeTriosModel by integrating variant battle rules.
 * It ensures that both regular and variant models coexist without modifying existing classes.
 */
public class VariantGameModel implements ThreeTriosModel {
  private final ThreeTriosModel baseModel;
  private final BattleRule battleRule;

  /**
   * Constructor for VariantGameModel.
   *
   * @param baseModel The base ThreeTriosModel to decorate.
   * @param args      Command-line arguments specifying variant rules.
   */
  public VariantGameModel(ThreeTriosModel baseModel, String[] args) {
    this.baseModel = baseModel;
    this.battleRule = RuleFactory.getBattleRules(args);
  }

  // List to track played card positions (row, col)
  private final List<PlayedCard> playedCards = new ArrayList<>();

  // Helper class to represent a played card's position
  private static class PlayedCard {
    int row;
    int col;

    PlayedCard(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  // Helper method to map Direction to row and column deltas
  private int getDeltaRow(Direction direction) {
    switch (direction) {
      case NORTH:
        return -1;
      case SOUTH:
        return 1;
      case EAST:
        return 0;
      case WEST:
        return 0;
      default:
        return 0;
    }
  }

  private int getDeltaCol(Direction direction) {
    switch (direction) {
      case NORTH:
        return 0;
      case SOUTH:
        return 0;
      case EAST:
        return 1;
      case WEST:
        return -1;
      default:
        return 0;
    }
  }

  // Delegated Methods

  @Override
  public void startGame(List<List<Cell>> grid, List<Card> deck, Player redPlayer, Player bluePlayer) {
    baseModel.startGame(grid, deck, redPlayer, bluePlayer);
  }

  @Override
  public void playToGrid(int curPlayerHandIdx, int row, int column) {
    // Record the played card's position
    playedCards.add(new PlayedCard(row, column));
    // Delegate to the base model
    baseModel.playToGrid(curPlayerHandIdx, row, column);
  }

  @Override
  public void battle() {
    // Retrieve the current grid
    List<List<Cell>> grid = baseModel.getGrid();

    // Iterate over all played cards
    for (PlayedCard playedCard : playedCards) {
      int row = playedCard.row;
      int col = playedCard.col;

      Cell attackingCell = baseModel.getCellAt(row, col);
      if (!(attackingCell instanceof CardCell) || attackingCell.isEmpty()) {
        continue; // Skip if no card is present
      }

      Card attackingCard = attackingCell.getCard();

      // Collect adjacent cells with their directions
      List<SameRule.AdjacentCell> sameAdjacentCells = new ArrayList<>();
      List<PlusRule.AdjacentCell> plusAdjacentCells = new ArrayList<>();

      for (Direction direction : Direction.values()) {
        int targetRow = row + getDeltaRow(direction);
        int targetCol = col + getDeltaCol(direction);

        if (isWithinGrid(targetRow, targetCol, grid)) {
          Cell targetCell = grid.get(targetRow).get(targetCol);
          if (!(targetCell instanceof Hole) && !targetCell.isEmpty()) {
            sameAdjacentCells.add(new SameRule.AdjacentCell(targetCell, direction));
            plusAdjacentCells.add(new PlusRule.AdjacentCell(targetCell, direction));
          }
        }
      }

      // Apply SameRule and PlusRule
      if (battleRule instanceof CompositeBattleRule) {
        CompositeBattleRule composite = (CompositeBattleRule) battleRule;
        List<BattleRule> rules = composite.getRules();

        for (BattleRule rule : rules) {
          if (rule instanceof SameRule) {
            SameRule sameRule = (SameRule) rule;
            List<Cell> cellsToFlip = sameRule.applySameRule(attackingCard, sameAdjacentCells);
            for (Cell cell : cellsToFlip) {
              cell.flipCell();
            }
          } else if (rule instanceof PlusRule) {
            PlusRule plusRule = (PlusRule) rule;
            List<Cell> cellsToFlip = plusRule.applyPlusRule(attackingCard, plusAdjacentCells);
            for (Cell cell : cellsToFlip) {
              cell.flipCell();
            }
          } else {
            // Apply other rules like ReverseRule and FallenAceRule
            // Since they don't require contextual information, apply directly
            for (Direction direction : Direction.values()) {
              int targetRow = row + getDeltaRow(direction);
              int targetCol = col + getDeltaCol(direction);

              if (isWithinGrid(targetRow, targetCol, grid)) {
                Cell targetCell = grid.get(targetRow).get(targetCol);
                if (!(targetCell instanceof Hole) && !targetCell.isEmpty()) {
                  boolean shouldFlip = rule.shouldFlip(attackingCard, targetCell, direction);
                  if (shouldFlip) {
                    targetCell.flipCell();
                  }
                }
              }
            }
          }
        }
      }

    }

    // Clear the played cards list after processing
    playedCards.clear();

    // Delegate to the base model to handle any additional battle logic
    baseModel.battle();
  }

  @Override
  public void addListener(ThreeTriosFeatures listener) {
    baseModel.addListener(listener);
  }

  @Override
  public void somethingChanged() {
    baseModel.somethingChanged();
  }

  @Override
  public boolean gameOver() {
    return baseModel.gameOver();
  }

  @Override
  public Player getWinner() {
    return baseModel.getWinner();
  }

  @Override
  public List<Card> getHand(CardColor color) {
    return baseModel.getHand(color);
  }

  @Override
  public CardColor getCurrentPlayerColor() {
    return baseModel.getCurrentPlayerColor();
  }

  @Override
  public List<List<Cell>> getGrid() {
    return baseModel.getGrid();
  }

  @Override
  public int getPlayerScore(Player player) {
    return baseModel.getPlayerScore(player);
  }

  @Override
  public Cell getCellAt(int row, int column) {
    return baseModel.getCellAt(row, column);
  }

  @Override
  public CardColor getCardOwnerColorAt(int row, int column) {
    return baseModel.getCardOwnerColorAt(row, column);
  }

  @Override
  public int getGridSize() {
    return baseModel.getGridSize();
  }

  @Override
  public boolean isLegalPlay(int row, int column) {
    return baseModel.isLegalPlay(row, column);
  }

  @Override
  public int howManyFlips(Card card, int row, int column) {
    return baseModel.howManyFlips(card, row, column);
  }

  // Helper Methods

  /**
   * Checks if the specified row and column are within the grid boundaries.
   *
   * @param row  The row index.
   * @param col  The column index.
   * @param grid The game grid.
   * @return true if within bounds, false otherwise.
   */
  private boolean isWithinGrid(int row, int col, List<List<Cell>> grid) {
    return row >= 0 && row < grid.size() && col >= 0 && col < grid.get(row).size();
  }
}