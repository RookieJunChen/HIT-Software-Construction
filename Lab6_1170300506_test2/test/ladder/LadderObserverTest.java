package ladder;

import org.junit.Test;
import main.Direction;

/**
 * test.
 *
 */
public class LadderObserverTest {

  @Test
  public void test() {
    LadderObserver ladderObserver = new LadderObserver(10, 20, 1, 5);
    for (int i = 0; i < 5; i++) {
      System.out.println(ladderObserver.getLadder(Direction.LR));
    }
    Ladder ladder = new Ladder(10, 20);
    ladderObserver.returnLadder(ladder);
    for (int i = 0; i < 7; i++) {
      System.out.println(ladderObserver.getLadder(Direction.LR));
    }
  }
}
