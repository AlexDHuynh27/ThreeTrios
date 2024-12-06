package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.Cell;

/**
 * Interface representing a battle rule in ThreeTrios.
 */
public interface BattleRule {

    /**
     * Determines if Card B should be flipped based on Card A and the specified direction.
     *
     * @param cardA     The attacking card (Card A).
     * @param cellB     The target cell containing Card B.
     * @param direction The direction from Card A to Card B.
     * @return true if Card B should be flipped, false otherwise.
     */
    boolean shouldFlip(Card cardA, Cell cellB, Direction direction);
}