package main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import monkey.Monkey;

/**
 * calculate the throughput rate and equality.
 *
 */
public class Calculator {
  private Set<Monkey> monkeys = new HashSet<>();
  private final int sumTime;

  /**
   * get monkeys.
   */
  public Calculator(List<Monkey> monkeys, int sumTime) {
    for (Monkey monkey : monkeys) {
      this.monkeys.add(monkey);
    }
    this.sumTime = sumTime;
  }

  public double throughputRate() {
    return monkeys.size() * 1.0 / sumTime;
  }

  /**
   * calculate equality.
   * 
   * @return - equality.
   */
  public double equality() {
    int sum = 0;
    int combination = 0;

    for (Monkey monkey1 : monkeys) {
      for (Monkey monkey2 : monkeys) {

        if (monkey1.equals(monkey2)) {
          continue; // 跳过自身
        }

        combination++; // 一种组合

        int judge = (monkey1.getBirthTime() - monkey2.getBirthTime()) * (monkey1.getBirthTime()
            + monkey1.getAge() - monkey2.getBirthTime() - monkey2.getAge());

        if (judge >= 0) {
          sum++;
        } else {
          sum--;
        }
      }
    }
    return sum * 1.0 / combination;
  }
}
