package strategy;

import ladder.Ladder;

/**
 * choose the a strategy to get a ladder.
 *
 */
public interface ChooseLadderStrategy {

  /**
   * get a ladder for monkey.
   * 
   * @return a accessible ladder.
   */
  public Ladder getLadder(String direction);

  /**
   * return the ladder to the collection.
   * 
   * @param ladder - ladder has been changed.
   * @return true only if success.
   */
  public boolean returnLadder(Ladder ladder);

}
