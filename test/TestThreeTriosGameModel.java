import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.model.ThreeTriosGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * Test class for ThreeTriosGameModel.
 */
public class TestThreeTriosGameModel {
  private ThreeTriosCard card;
  private ThreeTriosCard maxAttackCard;
  private ThreeTriosCard minAttackCard;
  private ThreeTriosCard filledCard;
  private Cell hole;
  private CardCell emptyCardCell;
  private CardCell filledCardCell;
  private CardCell red1;
  private CardCell blue1;
  private Player redPlayer;
  private Player bluePlayer;
  private ThreeTriosGameModel gameModel;
  private File nonExistentFile;
  private List<List<Cell>> grid1;
  private List<List<Cell>> grid2;
  private List<List<Cell>> grid3;
  private List<Card> deck5;
  private List<Card> deck10;

  private List<Card> deck26;

  private List<Card> deck50;
  private List<Card> deckSame;
  private ThreeTriosCard croc;
  private ThreeTriosCard eagle;
  private ThreeTriosCard fox;

  @Before
  public void setUp() {
    card = new ThreeTriosCard("Warrior", 5, 5, 7, 6);
    maxAttackCard = new ThreeTriosCard("MaxCard", 10, 10, 10, 10);
    minAttackCard = new ThreeTriosCard("MinCard", 1, 1, 1, 1);
    filledCard = new ThreeTriosCard("Filled", 6, 2, 1, 3);
    eagle = new ThreeTriosCard("Eagle", 6, 10, 7, 3);
    fox = new ThreeTriosCard("Fox", 8, 3, 10, 5);
    croc = new ThreeTriosCard("Crocodile", 3, 8, 5, 7);
    hole = new Hole();
    emptyCardCell = new CardCell();
    filledCardCell = new CardCell();
    red1 = new CardCell();
    blue1 = new CardCell();


    eagle.setColor(CardColor.RED);
    red1.setCard(eagle);
    fox.setColor(CardColor.BLUE);
    blue1.setCard(fox);
    croc.setColor(CardColor.BLUE);
    filledCard.setColor(CardColor.RED);
    filledCardCell.setCard(filledCard);

    redPlayer = new HumanPlayer();
    bluePlayer = new HumanPlayer();
    gameModel = new ThreeTriosGameModel(new Random(100));

    /*
    // Uncomment if you are using Windows. Comment if you are using Mac //
    // -----------------------------------------------------------------//
    deck5 = CardReader.getDeckFromConfig("src/cs3500/threetrios/exampleFiles/DeckOfCard(5).txt");
    deck10 = CardReader.getDeckFromConfig("src/cs3500/threetrios/exampleFiles" + "/DeckOfCard(10)" +
            ".txt");
    deck26 = CardReader.getDeckFromConfig("src/cs3500/threetrios/exampleFiles/DeckOfCard(26).txt");
    deck50 = CardReader.getDeckFromConfig("src/cs3500/threetrios/exampleFiles/DeckOfCard(50).txt");

    grid1 = GridReader.getGridFromConfig("src/cs3500/threetrios/exampleFiles/GridEx(1).txt");
    grid2 = GridReader.getGridFromConfig("src/cs3500/threetrios/exampleFiles/GridEx(2).txt");
    grid3 = GridReader.getGridFromConfig("src/cs3500/threetrios/exampleFiles/GridEx(3).txt");
    // -----------------------------------------------------------------//
*/

    // Uncomment if you are using Mac. Comment if you are using windows //
    // -----------------------------------------------------------------//
    deck5 = CardReader.getDeckFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/DeckOfCard(5).txt");
    deck10 = CardReader.getDeckFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles" +
                    "/DeckOfCard(10).txt");
    deck26 = CardReader.getDeckFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/DeckOfCard(26).txt");
    deck50 = CardReader.getDeckFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/DeckOfCard(50).txt");
    deckSame = CardReader.getDeckFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/DeckOfCardSame(50).txt");

    grid1 = GridReader.getGridFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/GridEx(1).txt");
    grid2 = GridReader.getGridFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/GridEx(2).txt");
    grid3 = GridReader.getGridFromConfig(
            "Assignment5/src/cs3500/threetrios/exampleFiles/GridEx(3).txt");
    // -----------------------------------------------------------------//
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
   * Test battleCell on Hole, which should return false for all Cell Types and Directions.
   */
  @Test
  public void testHoleBattleCellIsFalse() {
    assertFalse(hole.battleCell(hole, Direction.NORTH));
    assertFalse(hole.battleCell(hole, Direction.SOUTH));
    assertFalse(hole.battleCell(hole, Direction.EAST));
    assertFalse(hole.battleCell(hole, Direction.WEST));

    assertFalse(hole.battleCell(new CardCell(), Direction.NORTH));
    assertFalse(hole.battleCell(new CardCell(), Direction.SOUTH));
    assertFalse(hole.battleCell(new CardCell(), Direction.EAST));
    assertFalse(hole.battleCell(new CardCell(), Direction.WEST));

  }

  /**
   * Test getCard on Hole, which should return null.
   */
  @Test
  public void testHoleGetCardIsNull() {
    assertNull(hole.getCard());
  }

  /**
   * Test toString for an empty CardCell, should return "_".
   */
  @Test
  public void testEmptyCardCellToString() {
    assertEquals("_", emptyCardCell.toString());
  }

  /**
   * Test toString for a filled CardCell with a red card, should return "R" and should return "B"
   * after being flipped.
   */
  @Test
  public void testFilledCardCellToString() {
    assertEquals("R", filledCardCell.toString());

    filledCardCell.flipCell();

    assertEquals("B", filledCardCell.toString());
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
   * Test setting a card in an empty CardCell for Color BLUE.
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
   * Test getting a Card with getCard(), should return null if empty CardCell.
   */
  @Test
  public void testGetCard() {
    assertEquals(filledCard, filledCardCell.getCard());

    assertNull(emptyCardCell.getCard());

    maxAttackCard.setColor(CardColor.BLUE);
    emptyCardCell.setCard(maxAttackCard);
    assertEquals(maxAttackCard, emptyCardCell.getCard());
  }


  /**
   * Test battleCell for empty Cell in all directions, should be false.
   */
  @Test
  public void testBattleCellWithEmptyCell() {
    assertFalse(filledCardCell.battleCell(emptyCardCell, Direction.NORTH));
    assertFalse(filledCardCell.battleCell(emptyCardCell, Direction.SOUTH));
    assertFalse(filledCardCell.battleCell(emptyCardCell, Direction.EAST));
    assertFalse(filledCardCell.battleCell(emptyCardCell, Direction.WEST));
  }

  /**
   * Test battleCell for stronger cell in all directions, should be false.
   */
  @Test
  public void testBattleCellWithStrongerCell() {
    CardCell strongerCell = new CardCell();
    maxAttackCard.setColor(CardColor.RED);
    strongerCell.setCard(maxAttackCard);

    assertFalse(filledCardCell.battleCell(strongerCell, Direction.NORTH));
    assertFalse(filledCardCell.battleCell(strongerCell, Direction.SOUTH));
    assertFalse(filledCardCell.battleCell(strongerCell, Direction.EAST));
    assertFalse(filledCardCell.battleCell(strongerCell, Direction.WEST));
  }

  /**
   * Test battleCell for weaker cell in all directions, should be true, but if the same number,
   * should be false.
   */
  @Test
  public void testBattleCellWithWeakerCell() {
    CardCell weakerCell = new CardCell();
    minAttackCard.setColor(CardColor.BLUE);
    weakerCell.setCard(minAttackCard);

    assertTrue(filledCardCell.battleCell(weakerCell, Direction.NORTH));
    assertTrue(filledCardCell.battleCell(weakerCell, Direction.SOUTH));
    // False (same number) -> have to win if attacking
    assertFalse(filledCardCell.battleCell(weakerCell, Direction.EAST));
    assertTrue(filledCardCell.battleCell(weakerCell, Direction.WEST));
  }

  /**
   * Tests for adding and getting cards in the HumanPlayer's hand.
   */
  @Test
  public void testAddToHandAndGetHand() {
    redPlayer.setColor(CardColor.RED);
    redPlayer.addToHand(card);
    redPlayer.addToHand(maxAttackCard);
    List<Card> hand = redPlayer.getHand();

    assertEquals(2, hand.size());
    assertEquals("Warrior", hand.get(0).getName());
    assertEquals("MaxCard", hand.get(1).getName());
  }

  /**
   * Tests when the addToHand method for the HumanPlayer class when the color of the Player
   * hasn't been set.
   */
  @Test
  public void testAddToHandColorNotSet() {
    assertThrows("Adding to hand when card" + "color has already been set",
            IllegalStateException.class, () -> redPlayer.addToHand(card));
  }

  /**
   * Tests that the CardColor for the card gets changed when addToHand is called.
   */
  @Test
  public void testAddToHandChangesColor() {
    redPlayer.setColor(CardColor.RED);
    redPlayer.addToHand(card);
    assertEquals("R", card.colorString());
  }

  /**
   * Tests playFromHand for removal of card and getCurrentHandSize to make sure hand is updated.
   */
  @Test
  public void testPlayFromHandAndCurrentHandSize() {
    redPlayer.setColor(CardColor.RED);
    redPlayer.addToHand(card);
    redPlayer.addToHand(maxAttackCard);

    assertEquals("Warrior", redPlayer.playFromHand(0).getName());
    assertEquals("MaxCard", redPlayer.getHand().get(0).getName());

    assertEquals(1, redPlayer.getCurrentHandSize());
    assertEquals("MaxCard", redPlayer.playFromHand(0).getName());
    assertEquals(0, redPlayer.getCurrentHandSize());
  }

  /**
   * Test setColor and getColor for HumanPlayer in case of both colors.
   */
  @Test
  public void testSetColorSuccessfully() {
    redPlayer.setColor(CardColor.RED);
    bluePlayer.setColor(CardColor.BLUE);

    assertEquals(CardColor.RED, redPlayer.getColor());
    assertEquals(CardColor.BLUE, bluePlayer.getColor());
  }

  /**
   * Tests setting color twice should throw an exception (different color).
   */
  @Test(expected = IllegalStateException.class)
  public void testSetColorThrowsExceptionIfAlreadySet() {
    redPlayer.setColor(CardColor.RED);
    redPlayer.setColor(CardColor.BLUE);
  }

  /**
   * Tests setting color twice should throw an exception (same color).
   */
  @Test(expected = IllegalStateException.class)
  public void testSetColorThrowsExceptionIfAlreadySet2() {
    redPlayer.setColor(CardColor.RED);
    redPlayer.setColor(CardColor.RED);
  }

  /**
   * Tests getColor before setting color should throw an exception.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetColorThrowsExceptionIfColorNotSet() {
    redPlayer.getColor();
  }

  /**
   * Tests starting the game with an insufficient deck, expecting an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInsufficientDeck() {
    gameModel.startGame(grid3, deck5, redPlayer, bluePlayer);
  }

  /**
   * Tests starting game when game has already been started.
   */
  @Test
  public void testStartGameGameStarted() {
    gameModel.startGame(grid3, deck50, redPlayer, bluePlayer);
    assertThrows(IllegalStateException.class, () -> gameModel.startGame(grid3, deck50, redPlayer,
            bluePlayer));
  }

  /**
   * Tests starting game with null fields, should throw an exception.
   */
  @Test
  public void testStartGameNullFields() {
    assertThrows("Null Grid", IllegalArgumentException.class, () -> gameModel.startGame(null,
            deck26, redPlayer, bluePlayer));
    assertThrows("Null Deck", IllegalArgumentException.class, () -> gameModel.startGame(grid3,
            null, redPlayer, bluePlayer));
    assertThrows("Null RedPlayer", IllegalArgumentException.class, () -> gameModel
            .startGame(grid3, deck26, null, bluePlayer));
    assertThrows("Null BluePlayer", IllegalArgumentException.class, () -> gameModel
            .startGame(grid3, deck26, redPlayer, null));
    grid1.add(null);
    deck26.add(null);

    assertThrows("Null in Grid", IllegalArgumentException.class, () -> gameModel.startGame(grid1,
            deck50, redPlayer, bluePlayer));

    assertThrows("Null in Deck", IllegalArgumentException.class, () -> gameModel.startGame(grid2,
            deck26, redPlayer, bluePlayer));

  }

  /**
   * Tests playing a card to the grid, validating successful placement.
   */
  @Test
  public void testPlayToGrid() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 0, 0);
    assertFalse(grid1.get(0).get(0).isEmpty());
  }

  /**
   * Tests playing a card to an occupied cell, expecting an exception.
   */
  @Test(expected = IllegalStateException.class)
  public void testPlayToOccupiedCell() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 0, 0);
    gameModel.playToGrid(0, 0, 0);
  }

  /**
   * Tests that initiating battle does not result in flipped cards.
   */
  @Test
  public void testBattleWithFlip() {
    gameModel.startGame(grid2, deck50, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 1, 2);
    gameModel.battle();
    assertEquals("R", grid2.get(1).get(2).toString());
  }

  /**
   * Tests playthrough of game with battle.
   */
  @Test
  public void testFullGame() {
    gameModel.startGame(grid2, deck50, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 4);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 4);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 4);
    gameModel.battle();
    gameModel.playToGrid(0, 3, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 3, 4);
    gameModel.battle();
    gameModel.playToGrid(0, 4, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 4, 4);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 3);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 3);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 3);
    gameModel.battle();
    gameModel.playToGrid(0, 3, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 3, 3);
    gameModel.battle();
    gameModel.playToGrid(0, 4, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 4, 3);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 3, 2);
    assertTrue(gameModel.gameOver());
    assertEquals(redPlayer, gameModel.getWinner());
  }

  /**
   * Tests calling gameOver when game has not started, should throw an exception.
   */
  @Test(expected = IllegalStateException.class)
  public void testGameOverThrowsExceptionIfGameNotStarted() {
    gameModel.gameOver();
  }

  /**
   * Tests getWinner and gameOver for incomplete game.
   */
  @Test
  public void testGetWinnerThrowsExceptionIfGameNotOver() {
    gameModel.startGame(grid3, deck50, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 0, 0);

    assertThrows(IllegalStateException.class, () -> gameModel.getWinner());
    assertFalse(gameModel.gameOver());
  }

  /**
   * Test for getHand before and after playToGrid ensuring that playToGrid actually takes the
   * card out of hand.
   */
  @Test
  public void testGetHandAndPlayToGrid() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals("[Eagle 6 A 7 3, Tiger 4 8 3 7, Bear A 6 2 8, Gorilla A 5 6 4, Wolf 5 7 4 9]",
            gameModel.getHand(CardColor.RED).toString());

    gameModel.playToGrid(0, 0, 0);

    assertEquals("[Tiger 4 8 3 7, Bear A 6 2 8, Gorilla A 5 6 4, Wolf 5 7 4 9]",
            gameModel.getHand(CardColor.RED).toString());
  }

  /**
   * Tests getCurrentPlayer before and after first turn.
   */
  @Test
  public void testGetCurrentPlayerColor() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals(CardColor.RED, gameModel.getCurrentPlayerColor());

    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();

    assertEquals(CardColor.BLUE, gameModel.getCurrentPlayerColor());
  }

  /**
   * Tests getCurrentPlayer after multiple turns.
   */
  @Test
  public void testGetCurrentPlayerColorMultipleTurns() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals(CardColor.RED, gameModel.getCurrentPlayerColor());

    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();

    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();

    assertEquals(CardColor.RED, gameModel.getCurrentPlayerColor());

    gameModel.playToGrid(0, 2, 2);
    gameModel.battle();

    assertEquals(CardColor.BLUE, gameModel.getCurrentPlayerColor());
  }

  /**
   * Test for getGrid() method of GameModel in inital state, after 1 turn, after multiple turns.
   */
  @Test
  public void testGetGrid() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals("[[_, _, _], [_, _, _], [_, _, _]]", gameModel.getGrid().toString());

    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();

    assertEquals("[[R, _, _], [_, _, _], [_, _, _]]", gameModel.getGrid().toString());

    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();

    gameModel.playToGrid(0, 0, 2);
    gameModel.battle();

    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();

    gameModel.playToGrid(0, 1, 2);
    gameModel.battle();

    assertEquals("[[R, _, R], [_, R, R], [B, _, _]]", gameModel.getGrid().toString());
  }

  /**
   * Test playToGrid exceptions through isLegalPlay's exception handling.
   */
  @Test
  public void testIsLegalPlayExceptions() {
    assertThrows("Cannot play to grid: game hasn't started or is already over",
            IllegalStateException.class, () -> gameModel.playToGrid(0, 0, 0));

    gameModel.startGame(grid2, deck26, redPlayer, bluePlayer);

    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(0, -1, 0));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(0, 0, -1));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(0, 7, 0));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(0, 0, 7));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(-1, 0, 0));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(15, 0, 0));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalArgumentException.class, () -> gameModel.playToGrid(15, 0, 0));
    assertThrows("Cannot play to the specified cell: illegal move.",
            IllegalStateException.class, () -> gameModel.playToGrid(0, 0, 2));

    gameModel.playToGrid(0, 0, 0);

    assertThrows("CardCell to play on is not empty", IllegalStateException.class, () ->
            gameModel.playToGrid(0, 0, 0));
    assertThrows("Already played to grid this turn", IllegalStateException.class, () ->
            gameModel.playToGrid(0, 0, 1));
  }

  /**
   * Test initial score after starting the game should be 1/2 of deck.
   */
  @Test
  public void testGetPlayerScoreInitial() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals(5, gameModel.getPlayerScore(redPlayer));
    assertEquals(5, gameModel.getPlayerScore(bluePlayer));
  }

  /**
   * Test score after 0, 1, 2 turns of placing cards and a battle occurring.
   */
  @Test
  public void testGetPlayerScoreAfterBattle() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    // R _ _
    // _ _ _
    // _ _ _
    assertEquals(5, gameModel.getPlayerScore(redPlayer));
    assertEquals(5, gameModel.getPlayerScore(bluePlayer));
    gameModel.playToGrid(0, 1, 0);
    gameModel.battle();
    // R _ _
    // B _ _
    // _ _ _
    assertEquals(5, gameModel.getPlayerScore(redPlayer));
    assertEquals(5, gameModel.getPlayerScore(bluePlayer));
    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    // R _ _
    // R _ _
    // R _ _
    assertEquals(6, gameModel.getPlayerScore(redPlayer));
    assertEquals(4, gameModel.getPlayerScore(bluePlayer));
  }

  /**
   * Test score after full game of placing cards.
   */
  @Test
  public void testGetPlayerFullGame() {
    // Test score after multiple turns
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 2);
    gameModel.battle();
    gameModel.playToGrid(1, 1, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 1);
    gameModel.battle();
    assertEquals(7, gameModel.getPlayerScore(redPlayer));
    assertEquals(3, gameModel.getPlayerScore(bluePlayer));
  }

  /**
   * Test getGridSize() method for non-empty grid state and throwing exception before game start.
   */
  @Test
  public void testGetGridSize() {
    assertThrows("Game has not started.", IllegalStateException.class, () ->
            gameModel.getGridSize());
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals(9, gameModel.getGridSize());
  }

  /**
   * Test getGridSize() method for grid state with connected cardCells.
   */
  @Test
  public void testGetGridSize2() {
    gameModel.startGame(grid2, deck26, redPlayer, bluePlayer);
    assertEquals(22, gameModel.getGridSize());
  }

  /**
   * Test getGridSize() method for grid state with non-connected cardCells.
   */
  @Test
  public void testGetGridSize3() {
    gameModel.startGame(grid3, deck50, redPlayer, bluePlayer);
    assertEquals(31, gameModel.getGridSize());
  }

  /**
   * Test getCell to ensure the contents are properly retrieved and throws exceptions when needed.
   */
  @Test
  public void testGetCell() {
    assertThrows("Game has not started.", IllegalStateException.class, () ->
            gameModel.getCellAt(0,0));
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertEquals(new CardCell(), gameModel.getCellAt(0,0));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCellAt(0,-1));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCellAt(-1,0));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCellAt(3,0));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCellAt(0,3));

    gameModel.playToGrid(0, 0, 0);
    assertEquals(red1, gameModel.getCellAt(0,0));
    assertEquals(new CardCell(), gameModel.getCellAt(0,1));

    gameModel.battle();
    gameModel.playToGrid(0, 0, 1);
    assertNotEquals(new CardCell(), gameModel.getCellAt(0,1));
    assertEquals(blue1, gameModel.getCellAt(0,1));
  }

  /**
   * Test for getting CardOwnerColor at different coordinates and making sure exceptions are thrown.
   */
  @Test
  public void testCardOwnerColorAt() {
    assertThrows("Game has not started.", IllegalStateException.class, () ->
            gameModel.getCardOwnerColorAt(0,0));
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    assertNull(gameModel.getCardOwnerColorAt(0,0));

    assertEquals(new CardCell(), gameModel.getCellAt(0,0));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCardOwnerColorAt(0,-1));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCardOwnerColorAt(-1,0));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCardOwnerColorAt(3,0));
    assertThrows("Row or column out of bounds", IllegalArgumentException.class, () ->
            gameModel.getCardOwnerColorAt(0,3));

    gameModel.playToGrid(0, 0, 0);
    assertEquals(CardColor.RED, gameModel.getCardOwnerColorAt(0,0));
    assertNull(gameModel.getCardOwnerColorAt(0,1));

    gameModel.battle();
    gameModel.playToGrid(0, 0, 1);
    assertNotNull(gameModel.getCardOwnerColorAt(0,1));
    assertEquals(CardColor.BLUE, gameModel.getCardOwnerColorAt(0,1));
  }

  /**
   * Test howManyFlips() for 0, 1, and Combo Flips.
   */
  @Test
  public void testHowManyFlip() {
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);

    assertEquals(0, gameModel.howManyFlips(eagle,2, 1));

    gameModel.playToGrid(0, 2, 1);
    gameModel.battle();

    assertEquals(1, gameModel.howManyFlips(fox,2, 0));

    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();

    assertEquals(3, gameModel.howManyFlips(croc,0, 1));
  }

  /**
   * Test strategy 2. AI player should place fox at bottom-left because it has the strongest
   * combined attack.
   */
  @Test
  public void testStrategy2Corner() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deck10, redPlayer, bot1);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    System.out.println(gameModel.getHand(CardColor.BLUE));
    System.out.println(gameModel.getGrid());
    int [] a = bot1.strategy2(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    System.out.println(gameModel.getHand(CardColor.BLUE));
    System.out.println(gameModel.getGrid());
    assertEquals( 0, a[0]);
    assertEquals( 2, a[1]);
    assertEquals( 0, a[2]);
  }

  /**
   * Test strategy 2 with no corners available. AI player should place at 0,1 because
   * it is the top-left most.
   */
  @Test
  public void testStrategy2Corner0() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deck10, redPlayer, bot1);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 1);
    gameModel.battle();
    System.out.println(gameModel.getHand(CardColor.BLUE));
    System.out.println(gameModel.getGrid());
    int [] a = bot1.strategy2(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    System.out.println(gameModel.getHand(CardColor.BLUE));
    System.out.println(gameModel.getGrid());
    assertEquals( 0, a[0]);
    assertEquals( 0, a[1]);
    assertEquals( 1, a[2]);
  }

  /**
   * Test strategy 1 for 1 Flip. AI player should place Falcon at row:0, col:1 because it will flip
   * the Red card that was placed there.
   */
  @Test
  public void testStrategy1With1Flip() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deck10, redPlayer, bot1);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    int[] a = bot1.strategy1(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    assertEquals(1, a[0]);
    assertEquals(0, a[1]);
    assertEquals(1, a[2]);
  }

  /**
   * Test strategy 1. AI player should place at row:0, col:1 because it flips 3 cards in 1 turn.
   */
  @Test
  public void testStrategy1With3Flips() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deck10, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 2, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    gameModel.playToGrid(0, 1, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    System.out.println(gameModel.getGrid());
    System.out.println(gameModel.getHand(CardColor.BLUE));
    int[] a = bot1.strategy1(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();

    System.out.println(gameModel.getGrid());
    System.out.println(gameModel.getHand(CardColor.BLUE));

    assertEquals(0, a[0]);
    assertEquals(0, a[1]);
    assertEquals(1, a[2]);
  }

  /**
   * Test strategy 1 with 0 flips. Since all cards are same, it should place at 0,0.
   */
  @Test
  public void testStrategy1With0Flips() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deckSame, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    int[] a = bot1.strategy1(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    assertEquals(0, a[0]);
    assertEquals(0, a[1]);
    assertEquals(0, a[2]);
  }

  /**
   * Test strategy 2 with 0 flips. Since all cards are same, it should place at 0,0.
   */
  @Test
  public void testStrategy2With0Flips() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deckSame, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    int[] a = bot1.strategy2(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    assertEquals(0, a[0]);
    assertEquals(0, a[1]);
    assertEquals(0, a[2]);
  }

  /**
   * Test strategy 2 with 0 flips. Since all cards are same, it should place at 0,0.
   */
  @Test
  public void testStrategy3With0Flips() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deckSame, redPlayer, bluePlayer);
    gameModel.playToGrid(0, 1, 1);
    gameModel.battle();
    int[] a = bot1.strategy2(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    assertEquals(0, a[0]);
    assertEquals(0, a[1]);
    assertEquals(0, a[2]);
  }

  /**
   * Test strategy 3 with no corners available. AI player should place at 1,0 because
   * it is the top-left most.
   */
  @Test
  public void testStrategy3() {
    AIPlayer bot1 = new AIPlayer();
    gameModel.startGame(grid1, deck10, redPlayer, bot1);
    gameModel.playToGrid(0, 0, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 0);
    gameModel.battle();
    gameModel.playToGrid(0, 0, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 2);
    gameModel.battle();
    gameModel.playToGrid(0, 2, 1);
    gameModel.battle();
    System.out.println(gameModel.getHand(CardColor.BLUE));
    System.out.println(gameModel.getGrid());
    int [] a = bot1.strategy3(gameModel);
    gameModel.playToGrid(a[0],a[1],a[2]);
    gameModel.battle();
    System.out.println(gameModel.getHand(CardColor.BLUE));
    System.out.println(gameModel.getGrid());
    assertEquals( 2, a[0]);
    assertEquals( 1, a[1]);
    assertEquals( 0, a[2]);
  }
}