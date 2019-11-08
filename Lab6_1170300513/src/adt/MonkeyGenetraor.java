package adt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  /**
   * 读文件生成猴子的方法.
   * 
   * @param filename 文件名.
   * @throws IOException IO异常.
   */
  public void readfile(String filename) throws IOException {

    Map<Integer, List<Monkey>> timetable = new HashMap<>();
    String pathname = "src\\monkeys\\" + filename;
    File file = new File(pathname);
    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
    BufferedReader br = new BufferedReader(reader);
    String format1 = "n=\\d+";
    String format2 = "h=\\d+";
    String format3 = "monkey=<([\\w->]+,?)+>";
    String line;
    int max = 0;
    while ((line = br.readLine()) != null) {
      Matcher m1 = Pattern.compile(format1).matcher(line);
      Matcher m2 = Pattern.compile(format2).matcher(line);
      Matcher m3 = Pattern.compile(format3).matcher(line);
      while (m1.find()) {
        String[] words = m1.group().split("n=");
        MonkeyThroughRiver.n = Integer.valueOf(words[1]);
      }
      while (m2.find()) {
        String[] words = m2.group().split("h=");
        MonkeyThroughRiver.h = Integer.valueOf(words[1]);
      }
      while (m3.find()) {
        MonkeyThroughRiver.N++;
        String[] words = m3.group().split("monkey=<|,");
        int i = Integer.valueOf(words[1]);
        int id = Integer.valueOf(words[2]);
        String direction = words[3];
        String[] couples = words[4].split(">");
        int velocity = Integer.valueOf(couples[0]);
        if (velocity > MonkeyThroughRiver.MV) {
          MonkeyThroughRiver.MV = velocity;
        }
        Monkey monkey = new Monkey(id, direction, velocity);
        if (timetable.get(i) == null) {
          timetable.put(i, new ArrayList<>());
        }
        timetable.get(i).add(monkey);
        if (i > max) {
          max = i;
        }
      }
    }
    br.close();
    for (int i = 0; i < MonkeyThroughRiver.n; i++) {
      MonkeyThroughRiver.ladders.add(new Ladder(MonkeyThroughRiver.h));
    }
    int lasti = -1;
    for (int i = 0; i <= max; i++) {
      if (timetable.get(i) != null) {
        if (lasti != -1 && MonkeyThroughRiver.t == 0) {
          MonkeyThroughRiver.t = i - lasti;
        }
        if (lasti == -1) {
          lasti = i;
        }
        if (MonkeyThroughRiver.k == 0) {
          MonkeyThroughRiver.k = timetable.get(i).size();
        }
        for (Monkey monkey : timetable.get(i)) {
          monkey.start();
        }
      }
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
