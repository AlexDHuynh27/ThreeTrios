import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestThreeTriosGameModel {
  private ThreeTriosCard card;
  private ThreeTriosCard maxAttackCard;
  private ThreeTriosCard minAttackCard;
  private ThreeTriosCard filledCard;
  private Cell hole;
  private CardCell emptyCardCell;
  private CardCell filledCardCell;
  private Player redPlayer;
  private Player bluePlayer;
  private ThreeTriosGameModel gameModel;

  @Before
  public void setUp() {
      card = new ThreeTriosCard("Warrior", 5, 5, 7, 6);
      maxAttackCard = new ThreeTriosCard("MaxCard", 10, 10, 10, 10);
      minAttackCard = new ThreeTriosCard("MinCard", 1, 1, 1, 1);
      filledCard = new ThreeTriosCard("Filled", 6, 2, 1, 3);
      hole = new Hole();
      emptyCardCell = new CardCell();
      filledCardCell = new CardCell();

      filledCard.setColor(CardColor.RED);
      filledCardCell.setCard(filledCard);

      redPlayer = new HumanPlayer();
      bluePlayer = new HumanPlayer();
      gameModel = new ThreeTriosGameModel();
    }

  /**
   * Test for retrieving the card name.
   */
  @Test
  public void testGetName() {
    assertEquals("Warrior", card.getName());
    assertEquals("MaxCard", maxAttackCard.getName());
    assertEquals("MinCard", minAttackCard.getName());
  }

  /**
   * Test for retrieving the North attack value.
   */
  @Test
  public void testGetNorthAttack() {
    assertEquals(5, card.getAttack(Direction.NORTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.NORTH));
    assertEquals(1, minAttackCard.getAttack(Direction.NORTH));
  }

  /**
   * Test for retrieving the South attack value.
   */
  @Test
  public void testGetSouthAttack() {
    assertEquals(5, card.getAttack(Direction.SOUTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.SOUTH));
    assertEquals(1, minAttackCard.getAttack(Direction.SOUTH));
  }

  /**
   * Test for retrieving the East attack value.
   */
  @Test
  public void testGetEastAttack() {
    assertEquals(7, card.getAttack(Direction.EAST));
    assertEquals(10, maxAttackCard.getAttack(Direction.EAST));
    assertEquals(1, minAttackCard.getAttack(Direction.EAST));
  }

  /**
   * Test for retrieving the West attack value.
   */
  @Test
  public void testGetWestAttack() {
    assertEquals(6, card.getAttack(Direction.WEST));
    assertEquals(10, maxAttackCard.getAttack(Direction.WEST));
    assertEquals(1, minAttackCard.getAttack(Direction.WEST));
  }

  /**
   * Test setting color to RED.
   */
  @Test
  public void testSetColorToRed() {
    card.setColor(CardColor.RED);
    assertEquals(CardColor.RED, card.getColor());
  }

  /**
   * Test setting color to BLUE.
   */
  @Test
  public void testSetColorToBlue() {
    card.setColor(CardColor.BLUE);
    assertEquals(CardColor.BLUE, card.getColor());
  }

  /**
   * Test setting color again after it's already been set.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetColorAlreadySet() {
    card.setColor(CardColor.RED);
    card.setColor(CardColor.BLUE);
  }

  /**
   * Test flipping color from RED to BLUE.
   */
  @Test
  public void testFlipFromRedToBlue() {
    card.setColor(CardColor.RED);
    card.flip();
    assertEquals(CardColor.BLUE, card.getColor());
  }

  /**
   * Test flipping color from BLUE to RED.
   */
  @Test
  public void testFlipFromBlueToRed() {
    card.setColor(CardColor.BLUE);
    card.flip();
    assertEquals(CardColor.RED, card.getColor());
  }

  /**
   * Test flipping color without setting an initial color.
   */
  @Test(expected = IllegalStateException.class)
  public void testFlipWithoutSettingColor() {
    card.flip();
  }

  /**
   * Test colorString method when color is RED.
   */
  @Test
  public void testColorStringRed() {
    card.setColor(CardColor.RED);
    assertEquals("R", card.colorString());
  }

  /**
   * Test colorString method when color is BLUE.
   */
  @Test
  public void testColorStringBlue() {
    card.setColor(CardColor.BLUE);
    assertEquals("B", card.colorString());
  }

  /**
   * Test colorString method without setting an initial color.
   */
  @Test(expected = IllegalStateException.class)
  public void testColorStringWithoutSettingColor() {
    card.colorString();
  }

  /**
   * Test toString method to verify the representation of card data.
   */
  @Test
  public void testToStringRepresentation() {
    assertEquals("Warrior 5 5 7 6", card.toString());
    assertEquals("MaxCard A A A A", maxAttackCard.toString());
    assertEquals("MinCard 1 1 1 1", minAttackCard.toString());
  }

  /**
   * Test setting an invalid negative attack value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAttackValues() {
    new ThreeTriosCard("NegativeCard", -1, 5, 7, 6);
  }

  /**
   * Test setting an invalid attack value greater than 10.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExcessiveAttackValues() {
    new ThreeTriosCard("ExcessiveCard", 11, 5, 7, 6);
  }

  /**
   * Test minimum attack values to ensure they are within bounds.
   */
  @Test
  public void testMinimumAttackValues() {
    assertEquals(1, minAttackCard.getAttack(Direction.NORTH));
    assertEquals(1, minAttackCard.getAttack(Direction.SOUTH));
    assertEquals(1, minAttackCard.getAttack(Direction.EAST));
    assertEquals(1, minAttackCard.getAttack(Direction.WEST));
  }

  /**
   * Test maximum attack values to ensure they are within bounds.
   */
  @Test
  public void testMaximumAttackValues() {
    assertEquals(10, maxAttackCard.getAttack(Direction.NORTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.SOUTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.EAST));
    assertEquals(10, maxAttackCard.getAttack(Direction.WEST));
  }

  /**
   * Test Hole's toString method to verify it returns a space.
   */
  @Test
  public void testHoleToString() {
    assertEquals(" ", hole.toString());
  }

  /**
   * Test Hole's isEmpty method to verify it always returns false.
   */
  @Test
  public void testHoleIsAlwaysNotEmpty() {
    assertFalse(hole.isEmpty());
  }

  /**
   * Test flipCell on Hole, which should throw an exception.
   */
  @Test(expected = IllegalCallerException.class)
  public void testHoleFlipCellThrowsException() {
    hole.flipCell();
  }

  /**
   * Test setCard on Hole, which should throw an exception.
   */
  @Test(expected = IllegalCallerException.class)
  public void testHoleSetCardThrowsException() {
    hole.setCard(card);
  }

  /**
   * Test toString for an empty CardCell, should return "_".
   */
  @Test
  public void testEmptyCardCellToString() {
    assertEquals("_", emptyCardCell.toString());
  }

  /**
   * Test toString for a filled CardCell with a red card, should return "R".
   */
  @Test
  public void testFilledCardCellToString() {
    assertEquals("R", filledCardCell.toString());
  }

  /**
   * Test isEmpty for both empty and filled CardCell.
   */
  @Test
  public void testCardCellIsEmpty() {
    assertTrue(emptyCardCell.isEmpty());
    assertFalse(filledCardCell.isEmpty());
  }

  /**
   * Test setting a card in an empty CardCell.
   */
  @Test
  public void testSetCardInEmptyCell() {
    minAttackCard.setColor(CardColor.BLUE);
    emptyCardCell.setCard(minAttackCard);
    assertEquals("B", emptyCardCell.toString());
  }

  /**
   * Test setting a card in a filled CardCell, should throw an exception.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetCardInFilledCellThrowsException() {
    filledCardCell.setCard(minAttackCard);
  }

  /**
   * Test flipping an empty CardCell, which should throw an exception.
   */
  @Test(expected = IllegalStateException.class)
  public void testFlipEmptyCardCellThrowsException() {
    emptyCardCell.flipCell();
  }

  /**
   * Test flipping a filled CardCell with a red card, should turn it blue.
   */
  @Test
  public void testFlipCardCellWithRedCard() {
    filledCardCell.flipCell();
    assertEquals("B", filledCardCell.toString());
  }

  /**
   * Test flipping a CardCell containing maxAttackCard set to blue, should turn it red.
   */
  @Test
  public void testFlipCardCellWithMaxAttackCard() {
    emptyCardCell.setCard(maxAttackCard);
    maxAttackCard.setColor(CardColor.BLUE);
    emptyCardCell.flipCell();
    assertEquals("R", emptyCardCell.toString());
  }

  /**
   * Test setting a null card in CardCell, should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetNullCardInCardCell() {
    emptyCardCell.setCard(null);
  }

  /**
   * Test flipping a card multiple times to verify color toggles each time.
   */
  @Test
  public void testRepeatedFlipping() {
    filledCardCell.flipCell();  // RED -> BLUE
    assertEquals("B", filledCardCell.toString());

    filledCardCell.flipCell();  // BLUE -> RED
    assertEquals("R", filledCardCell.toString());
  }

  /**
   * Tests for adding and retrieving cards in the HumanPlayer's hand.
   */
  @Test
  public void testAddToHandAndGetHand() {
    redPlayer.addToHand(card);
    redPlayer.addToHand(maxAttackCard);
    List<ThreeTriosCard> hand = redPlayer.getHand();

    assertEquals(2, hand.size());
    assertEquals("Warrior", hand.get(0).getName());
    assertEquals("MaxCard", hand.get(1).getName());
  }

  /**
   * Tests playFromHand for both successful removal and boundary conditions.
   */
  @Test
  public void testPlayFromHand() {
    redPlayer.addToHand(card);
    redPlayer.addToHand(maxAttackCard);

    assertEquals("Warrior", redPlayer.playFromHand(0).getName());
    assertEquals("MaxCard", redPlayer.getHand().get(0).getName());

    assertEquals(1, redPlayer.getCurrentHandSize());
    assertEquals("MaxCard", redPlayer.playFromHand(0).getName());
    assertEquals(0, redPlayer.getCurrentHandSize());
  }
}