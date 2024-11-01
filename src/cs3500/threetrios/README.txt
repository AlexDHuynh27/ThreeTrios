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

