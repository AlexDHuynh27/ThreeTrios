package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.cell.CardCell;

public class StratsClassTemp {
  private final List<ThreeTriosCard> hand;
  private final int strategyNumber;
  private CardColor color;

  public StratsClassTemp(int strategyNumber) {
    if (strategyNumber != 1 && strategyNumber != 2) {
      throw new IllegalArgumentException("Invalid strategy number. Must be 1 or 2.");
    }
    this.strategyNumber = strategyNumber;
    this.hand = new ArrayList<>();
  }

  public List<ThreeTriosCard> getHand() {
    return List.copyOf(hand);
  }

  public CardColor getColor() {
    if (this.color == null) {
      throw new IllegalStateException("Color is not set");
    }
    return this.color;
  }

  public void setColor(CardColor color) {
    if (this.color != null) {
      throw new IllegalStateException("Color is already set");
    }
    this.color = color;
  }

  public int[] strategy1(ReadOnlyThreeTriosModel model) {
    int maxFlips = -1;
    List<int[]> bestMoves = new ArrayList<>();
    List<ThreeTriosCard> hand = this.getHand();
    List<List<Cell>> grid = model.getGrid();

    for (int cardIdx = 0; cardIdx < hand.size(); cardIdx++) {
      ThreeTriosCard card = hand.get(cardIdx);

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
      return new int[]{0, defaultMove[0], defaultMove[1]};
    } else {
      // Apply tie-breaker
      return selectBestMove(bestMoves);
    }
  }

  private int[] findFirstLegalMove(ReadOnlyThreeTriosModel model) {
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

  private int[] selectBestMove(List<int[]> moves) {
    int[] bestMove = moves.get(0);
    for (int[] move : moves) {
      if (move[1] < bestMove[1] ||
              (move[1] == bestMove[1] && move[2] < bestMove[2]) ||
              (move[1] == bestMove[1] && move[2] == bestMove[2] && move[0] < bestMove[0])) {
        bestMove = move;
      }
    }
    return bestMove;
  }

  public int[] strategy2(ReadOnlyThreeTriosModel model) {
    List<int[]> possibleMoves = new ArrayList<>();
    List<ThreeTriosCard> hand = this.getHand();
    List<List<Cell>> grid = model.getGrid();
    List<int[]> corners = getCorners(grid);

    for (int[] corner : corners) {
      int row = corner[0];
      int col = corner[1];
      if (model.isLegalPlay(row, col)) {
        for (int cardIdx = 0; cardIdx < hand.size(); cardIdx++) {
          ThreeTriosCard card = hand.get(cardIdx);
          int hardness = cardHardnessInCorner(card, row, col, grid);
          possibleMoves.add(new int[]{cardIdx, row, col, hardness});
        }
      }
    }

    if (possibleMoves.isEmpty()) {
      // No valid corner moves; pick the upper-leftmost open position and card index 0
      int[] defaultMove = findFirstLegalMove(model);
      return new int[]{0, defaultMove[0], defaultMove[1]};
    } else {
      // Choose the card and corner with the highest hardness
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
      // Apply tie-breaker rules
      return selectBestMove(bestMovesByHardness);
    }
  }

  private List<int[]> getCorners(List<List<Cell>> grid) {
    List<int[]> corners = new ArrayList<>();
    int numRows = grid.size();
    int numCols = grid.get(0).size();

    if (isCardCell(grid, 0, 0)) corners.add(new int[]{0, 0});
    if (isCardCell(grid, 0, numCols - 1)) corners.add(new int[]{0, numCols - 1});
    if (isCardCell(grid, numRows - 1, 0)) corners.add(new int[]{numRows - 1, 0});
    if (isCardCell(grid, numRows - 1, numCols - 1)) corners.add(new int[]{numRows - 1, numCols - 1});

    return corners;
  }

  private boolean isCardCell(List<List<Cell>> grid, int row, int col) {
    Cell cell = grid.get(row).get(col);
    return cell instanceof CardCell;
  }

  private int cardHardnessInCorner(ThreeTriosCard card, int row, int col, List<List<Cell>> grid) {
    int numRows = grid.size();
    int numCols = grid.get(0).size();
    List<Direction> exposedDirections = new ArrayList<>();

    if (row == 0) exposedDirections.add(Direction.NORTH);
    if (row == numRows - 1) exposedDirections.add(Direction.SOUTH);
    if (col == 0) exposedDirections.add(Direction.WEST);
    if (col == numCols - 1) exposedDirections.add(Direction.EAST);

    int hardness = 0;
    for (Direction dir : exposedDirections) {
      hardness += card.getAttack(dir);
    }
    return hardness;
  }

  public int[] strategy3(ReadOnlyThreeTriosModel model) {
    List<ThreeTriosCard> myHand = this.getHand();
    CardColor opponentColor = this.getColor() == CardColor.RED ? CardColor.BLUE : CardColor.RED;
    List<ThreeTriosCard> opponentHand = model.getHand(opponentColor);
    List<List<Cell>> grid = model.getGrid();

    int minVulnerability = Integer.MAX_VALUE;
    List<int[]> bestMoves = new ArrayList<>();

    for (int cardIdx = 0; cardIdx < myHand.size(); cardIdx++) {
      ThreeTriosCard myCard = myHand.get(cardIdx);

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
      int[] defaultMove = findFirstLegalMove(model);
      return new int[]{0, defaultMove[0], defaultMove[1]};
    } else {
      return selectBestMove(bestMoves);
    }
  }

  private int calculateVulnerability(ReadOnlyThreeTriosModel model, ThreeTriosCard myCard,
                                     int row, int col, List<ThreeTriosCard> opponentHand) {
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
      }

      if (adjRow >= 0 && adjRow < grid.size() && adjCol >= 0 && adjCol < grid.get(adjRow).size()) {
        Cell adjCell = grid.get(adjRow).get(adjCol);
        if (adjCell instanceof CardCell && adjCell.isEmpty()) {
          // Check if opponent can play here
          for (ThreeTriosCard oppCard : opponentHand) {
            if (canOpponentFlipOurCard(myCard, oppCard, dir)) {
              vulnerability++;
              break; // No need to check other cards for this position
            }
          }
        }
      }
    }
    return vulnerability;
  }

  private boolean canOpponentFlipOurCard(ThreeTriosCard ourCard, ThreeTriosCard oppCard, Direction dir) {
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
    }

    int oppAttackValue = oppCard.getAttack(oppAttackDir);
    int ourDefenseValue = ourCard.getAttack(ourAttackDir);

    return oppAttackValue > ourDefenseValue;
  }

}
