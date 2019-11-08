package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
  private int numLadders;
  private int rungs;
  private int timeInterval;
  private int numMonkey;
  private int generateSpeed;
  //private int maxMoveSpeed;
  private List<String> properties = new ArrayList<>();
  
  public ReadFile(String filePath) {
    try{
      getMessage(filePath);
    }catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void getMessage(String filePath) throws IOException {
    String format1 = "(n=)(\\d+)";
    String format2 = "(h=)(\\d+)";
    String format3 = "(monkey=)<([\\w->]+,?)+>";
    
    Matcher m1,m2,m3;
    File file = new File(filePath);
    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
    BufferedReader br = new BufferedReader(reader);
    String line;
    int timeInt=0;
    boolean flag = false;
    while((line = br.readLine())!= null) {
      m1 = Pattern.compile(format1).matcher(line);
      m2 = Pattern.compile(format2).matcher(line);
      m3 = Pattern.compile(format3).matcher(line);
      while(m1.find()) {
        this.numLadders = Integer.valueOf(m1.group(2));
      }
      while(m2.find()) {
        this.rungs = Integer.valueOf(m2.group(2));
      }
      while(m3.find()) {
        String[] monkeyInfo = m3.group().split("monkey=<|,|>");
        int birthTime = Integer.valueOf(monkeyInfo[1]);
        /*
        int idNum = Integer.valueOf(monkeyInfo[2]);
        int speed = Integer.valueOf(4);
        
        String direction = Direction.NO;
        if (monkeyInfo[3].equals("R->L")) {
          direction = Direction.RL;
        }
        else if (monkeyInfo[3].equals("L->R")) {
          direction = Direction.LR;
        }
        */
        this.numMonkey++;
        if (!flag && birthTime!=0) {
          this.timeInterval=birthTime;
          this.generateSpeed = Integer.valueOf(monkeyInfo[2])-1;
          flag = true;
        }
      }
    }
  }
  
  public int getNumLadders() {
    return this.numLadders;
  }
  
  public int getNumMonkey() {
    return this.numMonkey;
  }
  
  public int getRungs() {
    return this.rungs;
  }
  
  public int getGenerateSpeed() {
    return this.generateSpeed;
  }
  
  public int getTimeInterval() {
    return this.timeInterval;
  }
  
  public List<String> getProperties(){
    properties.add(Integer.toString(numLadders));
    properties.add(Integer.toString(rungs));
    properties.add(Integer.toString(timeInterval));
    properties.add(Integer.toString(numMonkey));
    properties.add(Integer.toString(generateSpeed));
    properties.add("unknown_MV");
    properties.add("1");
    return properties;
  }
  
  /*
  public static void main(String[] args) {
    ReadFile readFile = new ReadFile("src/main/txt/Competition_1.txt");
    System.out.println(readFile.getNumLadders());
    System.out.println(readFile.getRungs());
    System.out.println(readFile.getNumMonkey());
    System.out.println(readFile.getGenerateSpeed());
    System.out.println(readFile.getTimeInterval());
  }
  */
}
