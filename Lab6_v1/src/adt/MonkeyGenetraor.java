package adt;

import java.util.Random;

import program.MonkeyThroughRiver;

/**
 * 猴子生成器，用于猴子的生成.
 * 
 * @author junbaba
 *
 */
public class MonkeyGenetraor {

  private int sum = 0;
  Random random = new Random();

  /**
   * 随机生成猴子的方法.
   */
  public void generatemonkey() {
    int k = MonkeyThroughRiver.k;
    int t = MonkeyThroughRiver.t;
    int N = MonkeyThroughRiver.N;
    int left = 0;
    String direction = "R->L";
    while (N - sum >= k) {
      for (int i = 0; i < k; i++) {
        if (random.nextInt(2) == 0) {
          direction = "R->L";
        } else {
          direction = "L->R";
        }
        int velocity = random.nextInt(MonkeyThroughRiver.MV) + 1;
        sum += 1;
        Monkey monkey = new Monkey(sum, direction, velocity);
        monkey.start();
      }
      try {
        Thread.sleep(t * 1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    left = N - sum;
    for (int i = 0; i < left; i++) {
      if (random.nextInt(2) == 0) {
        direction = "R->L";
      } else {
        direction = "L->R";
      }
      int velocity = random.nextInt(MonkeyThroughRiver.MV) + 1;
      sum += 1;
      Monkey monkey = new Monkey(sum, direction, velocity);
      monkey.start();
    }
  }
}
