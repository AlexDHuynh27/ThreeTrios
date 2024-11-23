package cs3500.threetrios.model.player.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;


/**
 * A Strategy class for implementing strategyTwo please see below for more information on how the
 * strategy works.
 */
public class StrategyTwo implements ThreeTriosStrategy {
  /**
   * Determines the best move by placing a card in a corner position, selecting the card that is
   * hardest for the opponent to flip. It evaluates all legal corner positions and calculates a
   * "hardness" score for each card based on the attack values exposed when placed in that corner.
   * If multiple moves have the same maximum hardness, tie-breaker rules are applied: choose the
   * move with the uppermost-leftmost coordinate, and if still tied, select the card with the
   * smallest index in the hand. If no legal corner moves are available, it selects the first
   * legal move found.
   *
   * @param model the current game model
   * @param player the AI player
   * @return an array of integers representing the chosen move: [card index in hand, row, column]
   */
  @Override
  public HandGridCoord chooseMove(ThreeTriosModel model, Player player) {
    List<int[]> possibleMoves = new ArrayList<>();
    List<Card> hand = player.getHand();
    List<List<Cell>> grid = model.getGrid();
    List<int[]> corners = getCorners(grid);

    for (int[] corner : corners) {
      int row = corner[0];
      int col = corner[1];
      if (model.isLegalPlay(row, col)) {
        for (int cardIdx = 0; cardIdx < hand.size(); cardIdx++) {
          Card card = hand.get(cardIdx);
          int hardness = cardHardnessInCorner(card, row, col, grid);
          possibleMoves.add(new int[]{cardIdx, row, col, hardness});
        }
      }
    }

    if (possibleMoves.isEmpty()) {
      int[] defaultMove = StrategyOne.findFirstLegalMove(model);
      return new HandGridCoord(0, defaultMove[0], defaultMove[1]);
    } else {
      int maxHardness = -1;
      List<int[]> bestMovesByHardness = new ArrayList<>();
      for (int[] move : possibleMoves) {
        int hardness = move[3];
        if (hardness > maxHardness) {
          maxHardness = hardness;
          bestMovesByHardness.clear();
          bestMovesByHardness.add(move);
        } else if (hardness == maxHardness) {
          bestMovesByHardness.add(move);
        }
      }
      int[] selectedMove = StrategyOne.selectBestMove(bestMovesByHardness);
      return new HandGridCoord(selectedMove[0], selectedMove[1], selectedMove[2]);
    }
  }

  private List<int[]> getCorners(List<List<Cell>> grid) {
    List<int[]> corners = new ArrayList<>();
    int numRows = grid.size();
    int numCols = grid.get(0).size();

    if (isCardCell(grid, 0, 0)) {
      corners.add(new int[]{0, 0});
    }
    if (isCardCell(grid, 0, numCols - 1)) {
      corners.add(new int[]{0, numCols - 1});
    }
    if (isCardCell(grid, numRows - 1, 0)) {
      corners.add(new int[]{numRows - 1, 0});
    }
    if (isCardCell(grid, numRows - 1, numCols - 1)) {
      corners.add(new int[]{numRows - 1, numCols - 1});
    }
    return corners;
  }

  private boolean isCardCell(List<List<Cell>> grid, int row, int col) {
    Cell cell = grid.get(row).get(col);
    return cell instanceof CardCell;
  }

  private int cardHardnessInCorner(Card card, int row, int col, List<List<Cell>> grid) {
    int numRows = grid.size();
    int numCols = grid.get(0).size();
    int hardness = 0;

    if (row == 0 || row == numRows - 1) {
      hardness += card.getAttack(row == 0 ? Direction.SOUTH : Direction.NORTH);
    }
    if (col == 0 || col == numCols - 1) {
      hardness += card.getAttack(col == 0 ? Direction.EAST : Direction.WEST);
    }

    return hardness;
  }
}