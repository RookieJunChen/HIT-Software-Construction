package applications;

import apis.CircularOrbitApis;
import apis.CircularOrbitHelper;
import centralobject.CentralObject;
import circularorbit.AtomStructure;
import circularorbit.CircularOrbit;
import exceptions.ReadFileFailException;
import exceptions.TheSameTagRException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import physicalobject.PhysicalObject;
import thingintrack.ThinginTrack;
import track.Track;

public class AtomStructureapp {
  public static FileHandler fh = null;

  static {
    try {
      fh = new FileHandler("src\\logging\\AtomStructure.txt");
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert fh != null;
    fh.setFormatter(new SimpleFormatter());
  }

  /**
   * main函数.
   * 
   * @param args Parameter array
   */
  public static void main(String[] args) {
    List<String> logs = new ArrayList<>();
    CircularOrbit<CentralObject, PhysicalObject> c = CircularOrbit.empty("Q3");
    CircularOrbitApis<CentralObject, PhysicalObject> apIs = new CircularOrbitApis<>();
    Logger log = AtomStructure.log;
    int choice = 0;
    String filename = null;
    int number = 1;
    long starttime = 0;
    long endtime = 0;
    System.out.println("Atom Structure:");
    System.out.println();
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("1.Read data files.");
      System.out.println("2.Adding new track.");
      System.out.println("3.Adding object to a particular track.");
      System.out.println("4.Delete a object.");
      System.out.println("5.Delete a track.");
      System.out.println("6.Calculating the Entropy Value of Object Distribution in Orbits.");
      System.out.println("7.Electron transition.");
      System.out.println("8.Visualization of Multi-track Structure on GUI.");
      System.out.println("9.Show the difference from the original.");
      System.out.println("10.View the log.");
      System.out.println("11.Write to the file.");
      System.out.println("0.Quit.");
      System.out.println("Please input your choice:");
      choice = sc.nextInt();
      switch (choice) {
        case 0:
          fh.close();
          System.exit(0);
          break;
        case 1:
          System.out.println("Input the file name:");
          filename = sc.next();
          System.out.println("I/O Strategy:");
          number = sc.nextInt();
          try {
            starttime = System.currentTimeMillis();
            c.readfile(filename, number);
          } catch (ReadFileFailException e) {
            log.warning("The file is illegal, please select another text file.");
            c = CircularOrbit.empty("Q3");
            break;
          } catch (Exception e) {
            log.warning("The path is not valid, please re-enter it.");
            c = CircularOrbit.empty("Q3");
            break;
          } finally {
            endtime = System.currentTimeMillis();
            long exacttime = (endtime - starttime) / 1000;
            System.out.println("Cost " + exacttime + " s.");
          }
          log.info("Successful reading of " + filename);
          break;
        case 2:
          System.out.println("Please enter track layers:");
          int r1 = sc.nextInt();
          Track t1 = Track.roundcreator(r1);
          try {
            c.addorbit(t1);
          } catch (TheSameTagRException e) {
            log.severe(e.showwrong());
          }
          break;
        case 3:
          System.out.println("Which track do you want to add:");
          int r2 = sc.nextInt();
          Track t2 = Track.roundcreator(r2);
          try {
            PhysicalObject p = PhysicalObject.electronicscreator();
            c.addtoorbit(p, t2);
          } catch (TheSameTagRException e) {
            log.severe(e.showwrong());
          }
          break;
        case 4:
          System.out.println("1.Delete a central object.");
          System.out.println("2.Delete a physical object.");
          System.out.println("Input your choice:");
          choice = sc.nextInt();
          if (choice == 1) {
            System.out.println("Input central object information:");
            System.out.println("name:");
            String name = sc.next();
            c.deletecentralobj(CentralObject.atomcreator(name));
          } else if (choice == 2) {
            System.out.println("Which level of orbital electrons to delete:");
            int n1 = sc.nextInt();
            int flag = 0;
            int i;
            for (i = 0; i < c.getorbits().size(); i++) {
              ThinginTrack<PhysicalObject> l = c.getorbits().get(i);
              if (l.gettrack().getridus().intValue() == n1 && l.getthingsintrack().size() >= 1) {
                c.deleteorbitobj(l.getthingsintrack().get(0));
                flag++;
                break;
              }
            }
            if (flag == 0) {
              System.out.println("Orbital absence or orbital absence of electrons.");
            }
          } else {
            System.out.println("Input wrong!");
          }
          break;
        case 5:
          System.out.println("Please enter track layers:");
          int r3 = sc.nextInt();
          Track t3 = Track.roundcreator(r3);
          c.removeorbit(t3);
          break;
        case 6:
          System.out.println("The Entropy Value of Object Distribution in Orbits："
              + apIs.getObjectDistributionEntropy(c));
          break;
        case 7:
          System.out.println("Which level of orbital electrons to choose:");
          int n1 = sc.nextInt();
          System.out.println("To what level:");
          int r4 = sc.nextInt();
          Track t4 = Track.roundcreator(r4);
          int flag2 = 0;
          for (ThinginTrack<PhysicalObject> l : c.getorbits()) {
            if (l.gettrack().getridus().intValue() == n1 && l.getthingsintrack().size() >= 1) {
              c.transit(l.getthingsintrack().get(0), t4);
              flag2++;
            }
          }
          if (flag2 == 0) {
            log.warning("Orbital absence or orbital absence of electrons");
          }

          break;
        case 8:
          CircularOrbitHelper.visualize(c);
          break;
        case 9:
          CircularOrbit<CentralObject, PhysicalObject> co = CircularOrbit.empty("Q3");
          try {
            co.readfile(filename, number);
          } catch (Exception e) {
            log.warning("Failed to read the file.");
          }
          System.out.println(apIs.getDifference(c, co));
          break;
        case 10:
          try {
            File file = new File("src\\logging\\AtomStructure.txt");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line1 = null;
            String line2 = null;
            String matcher = null;
            while ((line1 = br.readLine()) != null && (line2 = br.readLine()) != null) {
              logs.add(line1 + "\n" + line2);
            }
            System.out.println("1.Filtration by time interval.");
            System.out.println("2.Type-by-type filtering.");
            System.out.println("3.Filtering by class.");
            System.out.println("4.Filtering by operation.");
            System.out.println("Please input your choice:");
            choice = sc.nextInt();
            switch (choice) {
              case 1:
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

              case 2:
                System.out.println("1.信息.");
                System.out.println("2.警告.");
                System.out.println("3.严重.");
                choice = sc.nextInt();
                matcher = "严重";
                switch (choice) {
                  case 1:
                    matcher = "信息";
                    break;
                  case 2:
                    matcher = "警告";
                    break;
                  case 3:
                    matcher = "严重";
                    break;
                  default:
                    log.warning("Input wrong!");
                    break;
                }
                for (String line : logs) {
                  if (line.indexOf(matcher) != -1) {
                    System.out.println(line);
                  }
                }
                break;

              case 3:
                System.out.println("Enter the class:");
                matcher = sc.next();
                for (String line : logs) {
                  if (line.indexOf(matcher) != -1) {
                    System.out.println(line);
                  }
                }
                break;

              case 4:
                System.out.println("Enter the operation:");
                matcher = sc.next();
                for (String line : logs) {
                  if (line.indexOf(matcher) != -1) {
                    System.out.println(line);
                  }
                }
                break;

              default:
                log.warning("Input wrong!");
                break;
            }
            logs = new ArrayList<>();
          } catch (RuntimeException e) {
            throw e;
          } catch (Exception e) {
            log.warning("Failed to read the log.");
          }
          break;
        case 11:
          try {
            starttime = System.currentTimeMillis();
            c.writefile(number);
          } catch (Exception e) {
            log.warning("Failed to write to the file.");
          } finally {
            long exacttime = (endtime - starttime) / 1000;
            System.out.println("Cost " + exacttime + " s.");
          }
          break;
        default:
          log.warning("Input wrong!");
          break;
      }
      sc.nextLine();
      sc.nextLine();
      System.out.println();
    }
  }
}
