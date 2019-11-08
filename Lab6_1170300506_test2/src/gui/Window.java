package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Window {
  private JFrame jframe;
  private JPanel basicPanel = new JPanel();
  private JLabel parameterMessages = new JLabel("loading...");
  private List<JLabel> monkeyMessages = new ArrayList<>();
  private List<JLabel> ladderMessages = new ArrayList<>();

  /**
   * construct window.
   */
  public Window(int ladders, int maxMonkeys) {
    // TODO Auto-generated constructor stub
    jframe = new JFrame("猴子过河");
    jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    jframe.setSize(1000, Math.min(1500, ladders * 50 + 300));
    jframe.setLocation(500, 500);
    jframe.setFont(new Font("宋体", Font.PLAIN, 32));

    // 容器
    final Container container = jframe.getContentPane();
    JPanel allPanel = new JPanel();
    allPanel.setLayout(new BorderLayout());

    // 参数信息
    JPanel paraterp = new JPanel();
    paraterp.setPreferredSize(new Dimension(950, 50));
    paraterp.setBorder(BorderFactory.createTitledBorder("Parameter:"));
    paraterp.add(parameterMessages);
    allPanel.add(paraterp, BorderLayout.NORTH);

    // 基本信息
    basicPanel.setPreferredSize(new Dimension(950, 150));
    basicPanel.setBorder(BorderFactory.createTitledBorder("Message ："));
    basicPanel.setLayout(new GridLayout(2, 4, 10, 10));

    // 设置显示monkey信息
    initMonkeyMessages();

    // 设置猴子最大总数max显示
    JPanel maxp = new JPanel();
    JLabel max = new JLabel(String.valueOf(maxMonkeys));
    maxp.setBorder(BorderFactory.createTitledBorder("Max:"));
    maxp.add(max);
    basicPanel.add(maxp);

    // 设置时间
    JPanel timep = new JPanel();
    JLabel time = new JLabel();
    timep.setBorder(BorderFactory.createTitledBorder("Time :"));
    timep.add(time);
    monkeyMessages.add(time);
    basicPanel.add(timep);

    // 设置其他信息
    initCalculateMessage(); // 设置显示统计信息

    // 非梯子部分信息设置完成
    allPanel.add(basicPanel, BorderLayout.CENTER);

    // 梯子信息
    JPanel ladderPanel = new JPanel(new GridLayout(ladders, 1));
    ladderPanel.setBounds(10, 200, 950, ladders * 50);
    ladderPanel.setPreferredSize(new Dimension(950, ladders * 50));
    ladderPanel.setBorder(BorderFactory.createTitledBorder("Ladders :"));

    for (int i = 0; i < ladders; i++) {
      JPanel panel = new JPanel();
      panel.setBorder(BorderFactory.createTitledBorder("ladder : " + i));
      panel.setPreferredSize(new Dimension(900, 100));

      JLabel label = new JLabel("ladder : " + i);
      ladderMessages.add(label);
      panel.add(label, BorderLayout.LINE_START);
      ladderPanel.add(panel, BorderLayout.LINE_START);
    }

    allPanel.add(ladderPanel, BorderLayout.SOUTH);

    // 设置滚动条
    JScrollPane scrollPane = new JScrollPane(allPanel);
    scrollPane.setViewportView(allPanel);
    container.add(scrollPane);

    jframe.setVisible(true);
  }

  private void initMonkeyMessages() {
    JPanel sump = new JPanel();
    JLabel sum = new JLabel();
    sump.setBorder(BorderFactory.createTitledBorder("Alive :"));
    sump.add(sum);
    monkeyMessages.add(sum);
    basicPanel.add(sump);

    JPanel bornp = new JPanel();
    JLabel born = new JLabel();
    bornp.setBorder(BorderFactory.createTitledBorder("New Born :"));
    bornp.add(born);
    monkeyMessages.add(born);
    basicPanel.add(bornp);

    JPanel inladderp = new JPanel();
    JLabel inladder = new JLabel();
    inladderp.setBorder(BorderFactory.createTitledBorder("In Ladder :"));
    inladderp.add(inladder);
    monkeyMessages.add(inladder);
    basicPanel.add(inladderp);

    JPanel arrivedp = new JPanel();
    JLabel arrived = new JLabel();
    arrivedp.setBorder(BorderFactory.createTitledBorder("Arrived :"));
    arrivedp.add(arrived);
    monkeyMessages.add(arrived);
    basicPanel.add(arrivedp);

  }

  private void initCalculateMessage() {
    JPanel throughp = new JPanel();
    JLabel through = new JLabel("calculating...");
    throughp.setBorder(BorderFactory.createTitledBorder("Throughput Rate :"));
    throughp.add(through);
    monkeyMessages.add(through);
    basicPanel.add(throughp);

    JPanel equalityp = new JPanel();
    JLabel equality = new JLabel("calculating...");
    equalityp.setBorder(BorderFactory.createTitledBorder("Equality :"));
    equalityp.add(equality);
    monkeyMessages.add(equality);
    basicPanel.add(equalityp);
  }

  /**
   * 设置显示参数.
   * 
   * @param properties - 参数字符串，来自configurator
   */
  public void setParameter(List<String> properties) {
    String message = "";
    message = " n=" + properties.get(0) + "     h=" + properties.get(1) + "     t="
        + properties.get(2) + "     N=" + properties.get(3) + "     k=" + properties.get(4)
        + "     MV=" + properties.get(5) + "     Strategy=" + properties.get(6);

    parameterMessages.setText(message);
  }

  /**
   * 设置基本数量信息数字.
   * 
   * @param sum      - 猴子总数
   * @param newborn  - 新生（未登梯）猴子
   * @param inladder - 在梯子上的猴子
   * @param arrived  - 到达对岸的猴子
   * @param time     - 当前时间
   */
  public void setMonkeyMessage(int sum, int newborn, int inladder, int arrived, int time) {
    monkeyMessages.get(0).setText(String.valueOf(sum));
    monkeyMessages.get(1).setText(String.valueOf(newborn));
    monkeyMessages.get(2).setText(String.valueOf(inladder));
    monkeyMessages.get(3).setText(String.valueOf(arrived));
    monkeyMessages.get(4).setText(String.valueOf(time));
  }

  /**
   * 设置ladder可视化字符串.
   * 
   * @param view - ladder信息字符串
   */
  public void setLadderView(List<String> view) {
    for (int i = 0; i < ladderMessages.size(); i++) {
      ladderMessages.get(i).setText(view.get(i));
    }
  }

  /**
   * 设置吞吐率和公平性.
   * 
   * @param throughPut - 吞吐率
   * @param equality   - 公平性
   */
  public void setCalculateMessage(Double throughPut, Double equality) {
    monkeyMessages.get(5).setText(String.valueOf(throughPut));
    monkeyMessages.get(6).setText(String.valueOf(equality));
  }

  /**
   * .
   */
  public static void main(String[] args) throws InterruptedException {
    Window window = new Window(30, 20);
    window.setMonkeyMessage(100, 10, 20, 30, 20);
  }
}
