package monkey;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * test.
 *
 */
public class MonkeyGeneratorTest {

  @Test
  public void test() {
    MonkeyGenerator monkeyGenerator = new MonkeyGenerator(20, 3, 5);

    List<Monkey> monkeys = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      monkeys.addAll(monkeyGenerator.generateMonkeysPerSec(1));
    }

    for (Monkey monkey : monkeys) {
      System.out.println(monkey.properties());
    }
  }
}
