package cs3500.threetrios.view;


import cs3500.threetrios.model.card.ThreeTriosCard;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class CardComponent extends Path2D.Double {
  ThreeTriosCard card;
  Boolean selected;

  public CardComponent(ThreeTriosCard card, boolean selected) {
    this.card = card;
    this.selected = selected;


  }

}
