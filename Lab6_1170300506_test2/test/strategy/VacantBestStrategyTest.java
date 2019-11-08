package strategy;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import ladder.Ladder;
import main.Direction;
import monkey.Monkey;
import monkey.MonkeyGenerator;

public class VacantBestStrategyTest {

  @Test
  public void test() {
    List<Ladder> ladders = new LinkedList<>();
    for (int i = 1; i < 10; i += 2) {
      Ladder ladder = new Ladder(i, 20);
      ladder.getMonkey(new Monkey(i, Direction.RL, MonkeyGenerator.getRandomVelocity(5), i));
      ladders.add(ladder);
    }
    System.out.println();

    VacantBestStrategy strategy = new VacantBestStrategy(ladders);

    Ladder first = strategy.getLadder(Direction.LR);
    System.out.println(first);

    strategy.returnLadder(first);
    System.out.println(strategy.getLadder(Direction.LR));
  }
}
