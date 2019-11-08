package monkey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import main.Direction;

/**
 * Generate monkeys.
 *
 */
public class MonkeyGenerator {
  private final int numMonkey;
  private final int generateSpeed;
  private final int maxMoveSpeed;
  private int currentMonkey = 0;
  private List<Monkey> monkeys = Collections.synchronizedList(new ArrayList<>());

  private static Random RANDOM = new Random();

  /**
   * init.
   * 
   * @param numMonkey        number of monkeys.
   * @param generateSpeed velocity of generation.
   * @param maxMoveSpeed  the possible largest speed.
   */
  public MonkeyGenerator(int numMonkey, int generateSpeed, int maxMoveSpeed) {
    super();
    this.numMonkey = numMonkey;
    this.generateSpeed = generateSpeed;
    this.maxMoveSpeed = maxMoveSpeed;
  }

  private boolean checkRep() {
    if (currentMonkey != monkeys.size()) {
      System.err.println("The currentMonkey is " + currentMonkey + " , while the monkey.size is "
          + monkeys.size() + " .");
      return false;
    }
    return true;
  }

  /**
   * Generate a batch of monkeys according to generate speed.
   */
  public List<Monkey> generateMonkeysPerSec(int time) {
    List<Monkey> newMonkeys = Collections.synchronizedList(new LinkedList<>());
    if (currentMonkey == numMonkey) {
      return newMonkeys;
    }
    if (currentMonkey + generateSpeed > numMonkey) {
      for (int i = 0; i < numMonkey - currentMonkey; i++) {
        newMonkeys.add(generateOneMonkey(time));
      }
    } else {
      for (int i = 0; i < generateSpeed; i++) {
        newMonkeys.add(generateOneMonkey(time));
      }
    }
    return newMonkeys;
  }

  /**
   * Generate a new monkey.
   */
  private Monkey generateOneMonkey(int time) {
    Monkey monkey = new Monkey(currentMonkey, getRandomDirection(),
        getRandomVelocity(maxMoveSpeed), time);
    monkeys.add(monkey);
    currentMonkey++;
    checkRep();
    return monkey;
  }

  public static int getRandomVelocity(int maxMoveVelocity) {
    return RANDOM.nextInt(maxMoveVelocity) + 1;
  }

  /**
   * get a random direction.
   * 
   * @return L->R or R->L
   */
  public static String getRandomDirection() {
    int dir = RANDOM.nextInt(2);
    if (dir == 1) {
      return Direction.LR;
    } else {
      return Direction.RL;
    }
  }
}
