package cs3500.threetrios.model.player.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

/**
 * A Strategy class for implementing strategyOne please see below for more information on how the
 * strategy works.
 */
public class StrategyOne implements ThreeTriosStrategy {
  /**
   * Determines the best move by selecting the play that flips the maximum number of opponent's
   * cards in a single turn. It evaluates all possible plays with each card in the hand on all
   * legal positions of the grid, calculating the number of flips for each move. If multiple
   * moves result in the same maximum flips, tie-breaker rules are applied: choose the move with
   * the uppermost-leftmost coordinate, and if still tied, select the card with the smallest
   * index in the hand. If no legal moves are available, it selects the first legal move found.
   *
   * @param model the current read-only game model
   * @param player the AI player
   * @return a HandGridCoord
   */
  @Override
  public HandGridCoord chooseMove(ThreeTriosModel model, Player player) {
    int maxFlips = -1;
    List<int[]> bestMoves = new ArrayList<>();
    List<Card> hand = player.getHand();
    List<List<Cell>> grid = model.getGrid();

    for (int cardIdx = 0; cardIdx < hand.size(); cardIdx++) {
      Card card = hand.get(cardIdx);

      for (int row = 0; row < grid.size(); row++) {
        List<Cell> gridRow = grid.get(row);
        for (int col = 0; col < gridRow.size(); col++) {
          if (model.isLegalPlay(row, col)) {
            int flips = model.howManyFlips(card, row, col);

            if (flips > maxFlips) {
              maxFlips = flips;
              bestMoves.clear();
              bestMoves.add(new int[]{cardIdx, row, col});
            } else if (flips == maxFlips) {
              bestMoves.add(new int[]{cardIdx, row, col});
            }
          }
        }
      }
    }

    if (bestMoves.isEmpty()) {
      int[] defaultMove = findFirstLegalMove(model);
      return new HandGridCoord(0, defaultMove[0], defaultMove[1]);
    } else {
      int[] selectedMove = selectBestMove(bestMoves);
      return new HandGridCoord(selectedMove[0], selectedMove[1], selectedMove[2]);
    }
  }

  // protected method as it is used for implementation of other strategies
  protected static int[] findFirstLegalMove(ReadOnlyThreeTriosModel model) {
    List<List<Cell>> grid = model.getGrid();
    for (int row = 0; row < grid.size(); row++) {
      for (int col = 0; col < grid.get(row).size(); col++) {
        if (model.isLegalPlay(row, col)) {
          return new int[]{row, col};
        }
      }
    }
    return new int[]{-1, -1};
  }

  // protected method as it is used for implementation of other strategies
  protected static int[] selectBestMove(List<int[]> moves) {
    int[] bestMove = moves.get(0);
    for (int[] move : moves) {
      if (move[1] < bestMove[1] || (move[1] == bestMove[1] && move[2] < bestMove[2])
              || (move[1] == bestMove[1] && move[2] == bestMove[2] && move[0] < bestMove[0])) {
        bestMove = move;
      }
    }
    return bestMove;
  }
}