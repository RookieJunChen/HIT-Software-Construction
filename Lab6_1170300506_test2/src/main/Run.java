package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.Window;
import ladder.Ladder;
import ladder.LadderObserver;
import log.Recorder;
import monkey.Monkey;
import monkey.MonkeyGenerator;

/**
 * The launcher
 *
 */
public class Run implements Runnable {
  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private CountDownLatch localLatch = new CountDownLatch(1);

  private final Recorder recorder;
  private final int numMonkeys;
  private final int timeInterval;
  private int time = 0;

  private final List<Monkey> newbornMonkeys = Collections.synchronizedList(new LinkedList<>());
  private final List<Monkey> inladderMonkeys = Collections.synchronizedList(new LinkedList<>());
  private final List<Monkey> arrivedMonkeys = Collections.synchronizedList(new LinkedList<>());
  //private final MonkeyGenerator monkeyGenerator;
  private final LadderObserver ladderObserver;
  private Calculator calculator;
  private final Window window;
  
  private final ReadFile readFile;
  private final String readFilePath;
  
  //String format1 = "(n=)(\\d+)";
  //String format2 = "(h=)(\\d+)";
  String format3 = "(monkey=)<(\\w+,?|[\\w|->]+,?)+>";
  Matcher m3;
  File file;
  InputStreamReader reader;
  BufferedReader br;
  

  /**
   * constructor
   * 
   * @param filePath - to get the configuration.
   */
  public Run(String filePath) {
    /*
    // 获得配置信息
    Configurator configurator = new Configurator(filePath);
    */
    
    // 配置log
    recorder = new Recorder(filePath + "_log");
    
    readFilePath = filePath+".txt";
    readFile = new ReadFile(readFilePath);
    
    // 设置猴子最大总数
    numMonkeys = readFile.getNumMonkey();
    // 设置生成猴子周期
    timeInterval = readFile.getTimeInterval();
    /*
    // 配置猴子生成器
    monkeyGenerator = new MonkeyGenerator(configurator.getNumMonkey(),
        configurator.getGenerateSpeed(), configurator.getMaxMoveSpeed());
    */
    
    
    
    file = new File(readFilePath);
    try {
      reader = new InputStreamReader(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    br = new BufferedReader(reader);
    
    // 配置梯子选择器
    ladderObserver = new LadderObserver(readFile.getNumLadders(), readFile.getRungs(),
        readFile.getGenerateSpeed(), 1);
    // 配置GUI基础信息
    window = new Window(readFile.getNumLadders(), readFile.getNumMonkey());
    window.setParameter(readFile.getProperties());
    window.setMonkeyMessage(0, 0, 0, 0, time);
  }

  /**
   * launch the game.
   */
  public void launch() {
    scheduler.scheduleAtFixedRate(this, 0, timeInterval * 1000, TimeUnit.MILLISECONDS);
    
    try {
      localLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    calculator = new Calculator(arrivedMonkeys, time);
    double throughput = calculator.throughputRate();
    double equality = calculator.equality();
    recorder.log("吞吐率  : " + throughput);
    recorder.log("公平性  : " + equality);
    window.setCalculateMessage(throughput, equality);

  }

  /**
   * 运行一秒.
   * 
   */
  public void run() {

    recorder.log("-------------------------------------------------------------");
    recorder.log("time : " + time);

    CountDownLatch countDownLatch = new CountDownLatch(inladderMonkeys.size());

    // 梯子上的猴子先向前走
    runInladder(countDownLatch);

    // 每隔k秒生成猴子
    if (time % timeInterval == 0) {
      // 产生新猴子
      try {
        generateNewMonkey(time);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // 新猴子和未登上梯子的猴子 选择梯子
    runNewborn();

    // 调整到达对岸的猴子
    getArrived();

    time++;

    for (Monkey monkey : newbornMonkeys) {
      recorder.log(monkey.stateOnTheBank());
    }

    for (Monkey monkey : inladderMonkeys) {
      recorder.log(monkey.stateOnTheLadder());
    }

    for (Monkey monkey : arrivedMonkeys) {
      recorder.log(monkey.stateOnTheDestination());
    }

    // ladder 可视化
    window.setLadderView(ladderObserver.getLadderView());

    int born = newbornMonkeys.size();
    int inladder = inladderMonkeys.size();
    int arrived = arrivedMonkeys.size();
    int sum = born + inladder + arrived;
    window.setMonkeyMessage(sum, born, inladder, arrived, time);

    if (arrived == numMonkeys) {
      scheduler.shutdown();
      localLatch.countDown();
    }
  }

  // 在梯子上的猴子先向前走
  // 产生新猴子
  // 在岸上的猴子上梯子
  // 到达对岸的猴子数量等于总数量时 结束

  /**
   * 1.在梯子上的猴子向前走
   */
  private void runInladder(CountDownLatch countDownLatch) {

    for (Monkey monkey : inladderMonkeys) {
      monkey.setCountDownLatch(countDownLatch);
      new Thread(monkey).start();
      monkey.addAge();
    }

    try {
      // 等待所有子线程结束
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 2.产生新猴子
   * @throws IOException 
   */
  private void generateNewMonkey(int time) throws IOException {
    //newbornMonkeys.addAll(monkeyGenerator.generateMonkeysPerSec(time));
    String line;
    while((line = br.readLine()) != null) {
      m3 = Pattern.compile(format3).matcher(line);
      while(m3.find()) {
        String[] monkeyInfo = m3.group().split("monkey=<|,|>");
        int birthTime = Integer.valueOf(monkeyInfo[1]);
        if (birthTime == time) {
          int idNum = Integer.valueOf(monkeyInfo[2]);
          int speed = Integer.valueOf(monkeyInfo[4]);
          String direction = Direction.NO;
          if (monkeyInfo[3].equals("R->L")) {
            direction = Direction.RL;
          }
          else if (monkeyInfo[3].equals("L->R")) {
            direction = Direction.LR;
          }
          Monkey monkey = new Monkey(idNum,direction,speed,birthTime);
          newbornMonkeys.add(monkey);
        }
      }
    }
  }

  /**
   * 3.新生猴子登梯子
   */
  private void runNewborn() {
    List<Monkey> winer = new LinkedList<>();
    // 获得梯子
    for (Monkey monkey : newbornMonkeys) {
      Ladder ladder = ladderObserver.getLadder(monkey.getDirection());
      // 成功登梯，记录
      if (monkey.boardLadder(ladder)) {
        winer.add(monkey);
      }
      // 归还梯子
      ladderObserver.returnLadder(ladder);
      monkey.addAge();
    }
    // 更改成功获得梯子的猴子归属
    for (Monkey monkey : winer) {
      newbornMonkeys.remove(monkey);
      inladderMonkeys.add(monkey);
    }
  }

  /**
   * 4.调整到达对岸的猴子
   */
  private void getArrived() {
    Iterator<Monkey> iterator = inladderMonkeys.iterator();
    while (iterator.hasNext()) {
      Monkey monkey = iterator.next();
      if (monkey.hasArrived()) {
        iterator.remove();
        arrivedMonkeys.add(monkey);
      }
    }
  }

  public static void main(String[] args) {
    Run run = new Run("src/main/txt/Competition_1");
    run.launch();
  }

}
