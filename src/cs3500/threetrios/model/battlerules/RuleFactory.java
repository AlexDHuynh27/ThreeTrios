package cs3500.threetrios.model.battlerules;

import java.util.Arrays;
import java.util.List;

/**
 * Factory class to create BattleRule instances based on command-line arguments.
 */
public class RuleFactory {

  /**
   * Parses command-line arguments and returns a CompositeBattleRule containing the selected rules.
   *
   * @param args Command-line arguments specifying which rules to activate.
   * @return A CompositeBattleRule containing the active rules.
   */
  public static CompositeBattleRule getBattleRules(String[] args) {
    CompositeBattleRule compositeRule = new CompositeBattleRule();

    List<String> argList = Arrays.asList(args);

    // Level 1 Rules
    if (argList.contains("--reverse")) {
      compositeRule.addRule(new ReverseRule());
    }
    if (argList.contains("--fallenAce")) {
      compositeRule.addRule(new FallenAceRule());
    }

    // Level 2 Rules
    boolean hasSame = argList.contains("--same");
    boolean hasPlus = argList.contains("--plus");

    if (hasSame && hasPlus) {
      throw new IllegalArgumentException("Cannot apply both Same and Plus rules simultaneously.");
    }

    if (hasSame) {
      compositeRule.addRule(new SameRule());
    }
    if (hasPlus) {
      compositeRule.addRule(new PlusRule());
    }

    return compositeRule;
  }
}