package cs3500.threetrios;

import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.configreader.CardReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testMain {
  public static void main(String[] args) {
    List<ThreeTriosCard> deck = CardReader.getDeckFromConfig("src/cs3500/threetrios/Hello.txt");
    System.out.println(deck);
  }
}
