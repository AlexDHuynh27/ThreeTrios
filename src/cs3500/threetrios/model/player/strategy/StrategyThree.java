// StrategyThree.java
package cs3500.threetrios.model.player.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

/**
 * A Strategy class for implementing strategyThree please see below for more information on how the
 * strategy works.
 */
public class StrategyThree implements ThreeTriosStrategy {
  /**
   * Determines the best move by selecting the play that minimizes the risk of the placed card
   * being flipped by the opponent in future turns. It calculates a "vulnerability" score for
   * each possible move, representing how susceptible the card is to being flipped based on the
   * opponent's potential moves. If multiple moves have the same minimal vulnerability,
   * tie-breaker rules are applied: choose the move with the uppermost-leftmost coordinate, and
   * if still tied, select the card with the smallest index in the hand. If no legal moves are
   * available, it selects the first legal move found.
   *
   * @param model the current read-only game model
   * @param player the AI Player
   * @return a HandGridCoord
   */
  @Override
    public HandGridCoord chooseMove(ThreeTriosModel model, Player player) {
      List<Card> myHand = player.getHand();
      CardColor opponentColor = player.getColor() == CardColor.RED ? CardColor.BLUE : CardColor.RED;
      List<Card> opponentHand = model.getHand(opponentColor);
      List<List<Cell>> grid = model.getGrid();

      int minVulnerability = Integer.MAX_VALUE;
      List<int[]> bestMoves = new ArrayList<>();

      for (int cardIdx = 0; cardIdx < myHand.size(); cardIdx++) {
        Card myCard = myHand.get(cardIdx);

        for (int row = 0; row < grid.size(); row++) {
          for (int col = 0; col < grid.get(row).size(); col++) {
            if (model.isLegalPlay(row, col)) {
              int vulnerability = calculateVulnerability(model, myCard, row, col, opponentHand);

              if (vulnerability < minVulnerability) {
                minVulnerability = vulnerability;
                bestMoves.clear();
                bestMoves.add(new int[]{cardIdx, row, col});
              } else if (vulnerability == minVulnerability) {
                bestMoves.add(new int[]{cardIdx, row, col});
              }
            }
          }
        }
      }

      if (bestMoves.isEmpty()) {
        int[] defaultMove = StrategyOne.findFirstLegalMove(model);
        return new HandGridCoord(0, defaultMove[0], defaultMove[1]);
      } else {
        int[] selectedMove = StrategyOne.selectBestMove(bestMoves);
        return new HandGridCoord(selectedMove[0], selectedMove[1], selectedMove[2]);
      }
    }

    private int calculateVulnerability(ReadOnlyThreeTriosModel model, Card myCard,
                                       int row, int col, List<Card> opponentHand) {
      int vulnerability = 0;
      List<List<Cell>> grid = model.getGrid();

      for (Direction dir : Direction.values()) {
        int adjRow = row;
        int adjCol = col;
        switch (dir) {
          case NORTH:
            adjRow = row - 1;
            break;
          case SOUTH:
            adjRow = row + 1;
            break;
          case EAST:
            adjCol = col + 1;
            break;
          case WEST:
            adjCol = col - 1;
            break;
          default:
            throw new IllegalArgumentException("Invalid direction: " + dir);
        }

        if (adjRow >= 0 && adjRow < grid.size() && adjCol >= 0 && adjCol < grid.get(adjRow).size()) {
          Cell adjCell = grid.get(adjRow).get(adjCol);
          if (adjCell instanceof CardCell && adjCell.isEmpty()) {
            for (Card oppCard : opponentHand) {
              if (canOpponentFlipOurCard(myCard, oppCard, dir)) {
                vulnerability++;
                break;
              }
            }
          }
        }
      }
      return vulnerability;
    }

    private boolean canOpponentFlipOurCard(Card ourCard, Card oppCard, Direction dir) {
      Direction oppAttackDir = null;
      Direction ourAttackDir = null;
      switch (dir) {
        case NORTH:
          oppAttackDir = Direction.SOUTH;
          ourAttackDir = Direction.NORTH;
          break;
        case SOUTH:
          oppAttackDir = Direction.NORTH;
          ourAttackDir = Direction.SOUTH;
          break;
        case EAST:
          oppAttackDir = Direction.WEST;
          ourAttackDir = Direction.EAST;
          break;
        case WEST:
          oppAttackDir = Direction.EAST;
          ourAttackDir = Direction.WEST;
          break;
        default:
          throw new IllegalArgumentException("Invalid direction: " + dir);
      }

      int oppAttackValue = oppCard.getAttack(oppAttackDir);
      int ourDefenseValue = ourCard.getAttack(ourAttackDir);

      return oppAttackValue > ourDefenseValue;
    }
  }