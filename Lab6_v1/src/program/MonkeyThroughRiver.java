package program;

import adt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 负责进行程序的main函数所在的类.
 * 
 * @author junbaba
 *
 */
public class MonkeyThroughRiver {

  public static StringBuilder loggings = new StringBuilder();
  public static Logger log = Logger.getLogger("MonkeyThroughRiver");
  public static FileHandler fh = null;
  static {
    try {
      fh = new FileHandler("src\\logging\\MonkeyThroughRiver.txt");
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert fh != null;
    fh.setFormatter(new SimpleFormatter());
    log.addHandler(fh);
  }

  public static List<Ladder> ladders = new ArrayList<>();
  public static int MV = 0;
  public static int t = 0;
  public static int k = 0;
  public static int N = 0;
  public static int h = 20;
  public static int strategynum = 0;
  public static Integer finishsum = 0;
  public static List<Integer> arriveorder = Collections.synchronizedList(new ArrayList<>());

  public static void main(String[] args) {
    List<String> logs = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    System.out.println("Input N:");
    N = sc.nextInt();
    System.out.println("Input MV:");
    MV = sc.nextInt();
    System.out.println("Input t:");
    t = sc.nextInt();
    System.out.println("Input k:");
    k = sc.nextInt();
    System.out.println("Input n:");
    int n = sc.nextInt();
    System.out.println("Choose Strategy:");
    strategynum = sc.nextInt();
    long start = System.currentTimeMillis();
    MonkeyGenetraor genetator = new MonkeyGenetraor();
    for (int i = 0; i < n; i++) {
      ladders.add(new Ladder(h));
    }
    genetator.generatemonkey();
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      synchronized (finishsum) {
        if (finishsum == N) {
          break;
        }
      }
    }
    long end = System.currentTimeMillis();
    long delta = (end - start) / 1000;
    double Th = (double) N / delta;
    loggings.append("N = " + N + "\n");
    loggings.append("MV = " + MV + "\n");
    loggings.append("t = " + t + "\n");
    loggings.append("k = " + k + "\n");
    loggings.append("n = " + n + "\n");
    loggings.append("h = " + h + "\n");
    loggings.append("吞吐率： " + Th + "\n");
    double justice = 0;
    for (int i = 0; i < arriveorder.size(); i++) {
      for (int j = i; j < arriveorder.size(); j++) {
        if (arriveorder.get(i) > arriveorder.get(j)) {
          justice -= 1;
        } else {
          justice += 1;
        }
      }
    }
    justice = justice / ((N * (N - 1)) / 2);
    loggings.append("公平性： " + justice + "\n");
    new MyFrame(loggings);
    fh.close();
    System.out.println("Do you need information?");
    String choice = sc.next();
    if (!choice.equals("yes")) {
      sc.close();
      return;
    } else {
      try {
        File file = new File("src\\logging\\MonkeyThroughRiver.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line1, line2;
        while ((line1 = br.readLine()) != null && (line2 = br.readLine()) != null) {
          logs.add(line1 + "\n" + line2);
        }
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      while (true) {
        System.out.println("1.Monkey.");
        System.out.println("2.Ladder.");
        System.out.println("3.Filtration by time interval.");
        System.out.println("0.Quit.");
        System.out.println("Input your choice:");
        int choicenum = sc.nextInt();
        String matcher;
        switch (choicenum) {
          case 0:
            sc.close();
            System.exit(0);
            break;

          case 1:
            System.out.println("Input the monkey number:");
            int mknum = sc.nextInt();
            matcher = "monkey:" + mknum + ",";
            for (String line : logs) {
              if (line.indexOf(matcher) != -1) {
                System.out.println(line);
              }
            }
            break;
          case 2:
            System.out.println("Input the ladder number:");
            int ldnum = sc.nextInt();
            matcher = ldnum + " ladder";
            for (String line : logs) {
              if (line.indexOf(matcher) != -1) {
                System.out.println(line);
              }
            }
            break;
          case 3:
            System.out.println("Starting time:");
            String stime = sc.next();
            System.out.println("Ending time:");
            String etime = sc.next();
            int button = 0;
            for (String line : logs) {
              if (line.indexOf(stime) != -1) {
                button = 1;
              }
              if (button == 1) {
                System.out.println(line);
              }
              if (line.indexOf(etime) != -1) {
                button = 0;
              }
            }
            break;
          default:
            System.out.println("Input wrong!");
            break;
        }
      }
    }
  }

}


/**
 * 辅助进行GUI表示的类.
 * 
 * @author junbaba
 *
 */
class MyFrame extends JFrame {

  private final StringBuilder str;

  private JScrollPane jsp;

  public MyFrame(StringBuilder str) {
    this.str = str;
    this.setBounds(100, 100, 500, 500);
    this.setDefaultCloseOperation(1);
    JTextArea jt = new JTextArea();
    jt.append(str.toString());
    jsp = new JScrollPane(jt);
    this.add(jsp);
    this.setVisible(true);
  }


}
