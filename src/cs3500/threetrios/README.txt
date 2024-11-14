    The Following Code enforces the rules of the game, Three Trios, allows access to immutable copies
of data related to the game as well functionality to play the games.

    To use this code it is assumed that you know how classes work and how to call methods,
as well as read comments. It is also assumed that you know how to set up a class that actually
runs and are familiar with the rules of Three Trios, though additional information about the rules
and concepts can be found below. Information on how to run the game is also found below

Quick Start Code:
    List<ThreeTriosCard> deck = CardReader.getDeckFromConfig(
    "src/cs3500/threetrios/exampleFiles/Hello.txt");
    List<List<Cell>> grid = GridReader.getGridFromConfig(
        "src/cs3500/threetrios/exampleFiles/gridconfig1.txt");
    ThreeTriosGameModel newGame = new ThreeTriosGameModel();
    newGame.startGame(grid, deck, new HumanPlayer(), new HumanPlayer());
    newGame.playToGrid(1, 2, 2);
    newGame.battle();
    new ThreeTriosGameView(newGame, System.out).render();
    newGame.isGameOver()
    newGame.getWinner()

To see the example in go to the TestMain file in the cs3500.threetrios package.


Quick Start Explanation:
    Use one of the valid decks and grids in the cs3500.threetrios.exampleFiles package
    or create your own based on the specifications listed in the CardReader and GridReader.
    Decks for a game must have n+1 cards where n = the number of CardCell's in the game Grid.

    To convert a configuration file to a deck:
    List<ThreeTriosCard> deck = CardReader.getDeckFromConfig(
        "path of config");

    To convert a configuration file to a grid:
    List<List<Cell>> grid = GridReader.getGridFromConfig(
            "path of config");

    "path of config" changes from system to system. For example for the DeckOfCard(5).txt file:
    For Windows: "src/cs3500/threetrios/exampleFiles/DeckOfCard(5).txt"
    For Mac: "Assignment5/src/cs3500/threetrios/exampleFiles/DeckOfCard(5).txt"

    To traverse through turns use the method playToGrid(int idx, int row, int col) and battle().

        idx - idx0 of the card in the players hand you want to play.
        row - row on the grid to play to.
        col - column on the grid to play to.

    Once the battle() method is called the turn will automatically change to the other
    player's turn.

    To check if the game is over use the method isGameOver(). And once the
    game is over to obtain the Player that is the winner use the getWinner() method.

    To view the current state of the game as well as the current players hand use the line.
    "new ThreeTriosGameView(newGame, System.out).render();".


Key Components:

The Model - Contains the logic and rules of the ThreeTrios game. Contains the playing phase and
the battling phase, setting up the grid and player hands, and switching who's turn it is. The model
also determines if the game is over and who is the winner.

    Grid - A List<List<Cells>> that represents the playing board grid of the Three Trios game.

    Deck - The List<ThreeTriosCard> that is used to play the game.

    RedPlayer - This is the player that goes first and is associated with the CardColor Red.

    BluePlayer - This is the player that goes second and is associated with teh CardColor Blue.

    CurrentPlayer - The player that is currently taking their turn.

    Turn - A turn consists of a Placing Phase and a Battle Phase. The turn switches to the other
    player once those two phases are done.

    Placing - Maintained by the playToGrid() method in ThreeTriosModel. The placing phase consists
    of the current player playing a card

    Battle Phase - Maintained by the battle() method in ThreeTriosModel. The battle phase
    first occurs after placing a card and compares the just placed card to adjacent cells on the
    grid in all Directions. If a battle is won against a card that is the opposite color that card
    changes color and then initiates a battle. The turn is then switch when all battles are over.

    Game Over - Maintained by the isGameOver() method.
    When all Cells are not empty.

    Game Winner - Maintained by the getWinner() method.
    The Player who has the most Cards on the Grid that match their CardColor

Cards - The cards that are used to play the game.

    Direction - North, South, East, and West. Directions that the card has and the card
    has an associated attack value for each direction.

    CardColor - Red or Blue. Represents the two sides of the game.


Cells - A cell that is a part of the Grid.

    CardCell - A Cell that the players can place a card on if the CardCell is empty.

    Hole - A Cell that no player can play on.


ConfigReaders - Classes that read configuration files and then produce an output of some
sort

    CardReader - Reads in configuration files and produces a Deck

    GridReader - Reads in configuration files and produces a Gri


Players - Represents a Player playing the game

    Hand - The List<ThreeTriosCard> that represents the cards the Player can play to the Grid.
    The hand is drawn for each player at the beginning of the game.

    CardColor - Red or Blue. Represents the two sides of the game.

View - A way to display a textual view of the game, that represents the current game state. The
current player, the current player's hand as well as the current Grid are all displayed.


Source Organization:

All key components and subcomponents are seperated into corresponding packages
within the cs3500 package, except the CardColor subcomponent which is contained with Cards.

The Model - cs3500.model
Cards - cs3500.model.cards
Cells - cs3500.model.cell
ConfigReaders - cs3500.model.configreader
Player - cs3500.model.player
View - cs3500.view




Changes for part 2:
	⁃	Updated Battle method by separating the processing of battling to a helper method making
	the battle method easier to use
	⁃	Added ReadOnly interface with methods used only for observing the model
	⁃	Added a mutable copy of Grid method making so that instead of returning an immutable list
	    copy of the grid, we were able to have a mutable copy that we could use later on for
	    simulating battle.
	⁃	Added methods in order to figure out how many potential flips a turn could allow for by
	    running simulated battles in mutable copies of the grid. This also helped when thinking
	    about what the AI bot would need in order to implement the given strategies.
	⁃	Added a method that returns the total gridSize or the total amount of CardCell (playable
	    cells) in the grid making it easier for GUI implementation.
	⁃	Added a method that returns the contents of a cell at the given (row, column). Gave us easy
	    access to certain cells contents, before we were just checking if it was a cardCell and if
	    it was the same color which was making the methods too long.
	⁃	Added a method that gave the specific color of a cell. Made it easier to distinguish what
	    colors are where.
	⁃	Added a isLegalPlay method that helped to make the playToGrid much smaller and easier to
	    work with. Also helped separate the legality with the actual functionality of the method,
	    (is it legal and what to do were combined before).
	⁃	Added a method to keep track of player score by counting number of cards in deck and in
	    hand for given player. Made it easier to tell who is winning.
	⁃	Already had a method for if game was over, so no change there.
	⁃	Already had a method for playing to grid, however, changed it as mentioned above making it
	    easier to work with and read.
	⁃	Already had a way to run simulated tests of the grid with the configuration files at hand,
	    so no big change there.
	⁃	Updated the test class for whatever new methods we created to ensure proper functionality.







Strategy 1 Impl:
- We iterate over each card in the AI player's hand and every legal position on the grid,
 calculating the number of opponent's cards that would be flipped if that card were played at that
 position. It keeps track of all moves that result in the maximum number of flips, storing them in
 the bestMoves list. If multiple moves have the same maximum flips, tie-breaker rules are applied
 via the selectBestMove method to choose the optimal move. If no legal moves are available, it
 defaults to selecting the first legal move found which would be the top-most left-most position
 with cardIndex 0.


Strategy 2 Impl:
 - We first identify all corner cells and check for legal plays in those positions. For each legal
 corner and each card in the player's hand, it calculates a "hardness" score based on the sum of
 the card's attack values on the exposed sides when placed in that corner. The strategy selects the
 move with the highest hardness score and if there are multiple such moves, it applies tie-breaker
 rules to choose the optimal one.

Strategy 3 Impl (Extra Credit):
- We evaluate each card in the AI player's hand and every legal position on the grid, calculating a
  "vulnerability" score for each potential move using the calculateVulnerability method which tells
  us how susceptible the placed card would be to being flipped by the opponent's possible moves.
  The strategy selects the moves with the lowest vulnerability scores, aiming to choose the safest
  option. If there are multiple moves with the same minimal vulnerability, it applies tie-breaker
  rules using selectBestMove to determine the optimal move. If no legal moves are found, it
  defaults to selecting the first available legal move.