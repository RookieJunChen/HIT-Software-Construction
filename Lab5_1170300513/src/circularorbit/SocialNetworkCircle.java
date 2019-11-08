package circularorbit;

import applications.SocialNetworkCircleapp;
import centralobject.CentralObject;
import exceptions.ReadFileFailException;
import exceptions.SyntaxErrorException;
import exceptions.TheSameTagRException;
import io.OutStrategy;
import io.ReadStrategy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import physicalobject.PhysicalObject;
import relationship.RelationshipBetweenoo;
import relationship.Socialtie;
import socialnetworkcircleexceptions.CuagesException;
import socialnetworkcircleexceptions.CusexException;
import socialnetworkcircleexceptions.FriendAgesException;
import socialnetworkcircleexceptions.FriendSexException;
import socialnetworkcircleexceptions.IntimacyNumException;
import socialnetworkcircleexceptions.SocialTieLogicalException;
import thingintrack.ThinginTrack;
import track.Track;

/**
 * 一个实现CircularOrbit并继承了ConcreteCircularOrbit的类，用于完成Q5的SocialNetworkCircle.
 * 
 * @author junbaba
 *
 * @param <L> 中心物体类型
 * @param <E> 轨道物体类型
 */
public class SocialNetworkCircle<L, E> extends ConcreteCircularOrbit<L, E>
    implements CircularOrbit<L, E> {
  @Override
  public void addtoorbit(E object, Track orbit) {
    assert object != null;
    assert orbit != null;
    for (ThinginTrack<E> t : orbitscontainobjects) {
      if (t.gettrack().equals(orbit)) {
        t.addobject(object);
        log.info("Successful addition with " + object.toString() + ".");
        checkRep();
        return;
      }
    }
    checkRep();
    log.warning("Failed to add operation.");
    return;
  }



  @Override
  public void deleteorbitobj(E obj) {
    super.deleteorbitobj(obj);
    reset();
  }



  @Override
  public void removeorbit(Track deletedorbit) {
    super.removeorbit(deletedorbit);
    reset();
  }


  @Override
  public void addcontactoo(E object1, E object2, Number intimacy) {
    assert object1 != null;
    assert object2 != null;
    assert intimacy != null;
    int i = 0;
    int flag1 = 0;
    int flag2 = 0;
    for (i = 0; i < objects.size(); i++) {
      if (objects.get(i).equals(object1)) {
        flag1 = 1;
      }
      if (objects.get(i).equals(object2)) {
        flag2 = 1;
      }
    }
    if (flag1 == 0) {
      relbetweenoo.add(RelationshipBetweenoo.creator(object1, "Basics"));
      objects.add(object1);
    }
    if (flag2 == 0) {
      relbetweenoo.add(RelationshipBetweenoo.creator(object2, "Basics"));
      objects.add(object2);
    }
    super.addcontactoo(object1, object2, intimacy);
    reset();
  }



  @Override
  public void deletecontactoo(E object1, E object2) {
    assert object1 != null;
    assert object2 != null;
    int flag1 = 0;
    int flag2 = 0;
    int i = 0;
    int j = 0;
    int remi1 = 0;
    int remj1 = 0;
    int remi2 = 0;
    int remj2 = 0;
    for (i = 0; i < relbetweenoo.size(); i++) {
      if (relbetweenoo.get(i).geto().equals(object1)) {
        for (j = 0; j < relbetweenoo.get(i).getrel().size(); j++) {
          if (relbetweenoo.get(i).getrel().get(j).getobj().equals(object2)) {
            flag1 = 1;
            remi1 = i;
            remj1 = j;
          }
        }
      }
    }
    for (i = 0; i < relbetweenoo.size(); i++) {
      if (relbetweenoo.get(i).geto().equals(object2)) {
        for (j = 0; j < relbetweenoo.get(i).getrel().size(); j++) {
          if (relbetweenoo.get(i).getrel().get(j).getobj().equals(object1)) {
            flag2 = 1;
            remi2 = i;
            remj2 = j;
          }
        }
      }
    }

    if (flag1 == 1 && flag2 == 1) {
      relbetweenoo.get(remi1).getrel().remove(remj1);
      relbetweenoo.get(remi2).getrel().remove(remj2);
      reset();
      log.info("Delete operation succeed.");
    } else {
      log.warning("Delete operation failed.");
    }
    return;
  }


  @Override
  public void addcontactco(L c, E object, Number intimacy) {
    assert c != null;
    assert object != null;
    assert intimacy != null;
    int i = 0;
    int flag = 0;
    for (i = 0; i < objects.size(); i++) {
      if (objects.get(i).equals(object)) {
        flag = 1;
      }
    }
    if (flag == 0) {
      relbetweenoo.add(RelationshipBetweenoo.creator(object, "Basics"));
      objects.add(object);
    }
    super.addcontactco(c, object, intimacy);
    reset();
  }



  @Override
  public void deletecontactco(L c, E object) {
    assert c != null;
    assert object != null;
    int flag1 = 0;
    int flag2 = 0;
    int i;
    int remi = 0;
    if (c.equals(getcenter())) {
      flag1 = 1;
    }
    for (i = 0; i < relbetweenco.get(0).getrel().size(); i++) {
      if (relbetweenco.get(0).getrel().get(i).getobj().equals(object)) {
        flag2 = 1;
        remi = i;
      }
    }
    if (flag1 == 1 && flag2 == 1) {
      relbetweenco.get(0).getrel().remove(remi);
      reset();
      log.info("Delete operation succeed.");
    } else {
      log.warning("Delete operation failed.");
    }
    return;

  }

  @Override
  public void transit(E object, Track t) {
    assert object != null;
    assert t != null;
    int flag = 0;
    int i = 0;
    int j = 0;
    for (i = 0; i < orbitscontainobjects.size(); i++) {
      ThinginTrack<E> orbit = orbitscontainobjects.get(i);
      for (j = 0; j < orbit.getthingsintrack().size(); j++) {
        E o = orbit.getthingsintrack().get(j);
        if (o.equals(object)) {
          orbit.deleteobject(o);
        }
      }
    }
    for (i = 0; i < orbitscontainobjects.size(); i++) {
      ThinginTrack<E> orbit = orbitscontainobjects.get(i);
      if (orbit.gettrack().equals(t)) {
        orbit.addobject(object);
        flag++;
      }
    }
    if (flag == 1) {
      log.info("Successful transition.");
    } else {
      log.warning("Transfer failure.");
    }
    checkRep();
    return;
  }



  @Override
  public void writefile(int num) throws IOException {
    assert this.center.size() == 1;
    String pathname = "src\\writing\\SocialNetwork.txt";
    File file = new File(pathname);
    if (!file.exists()) {
      file.createNewFile();
    }
    OutStrategy fw = OutStrategy.empty(file, num);
    CentralObject center = (CentralObject) getcenter();
    fw.write("CentralUser ::= <" + center.getname() + "," + center.getages().intValue() + ","
        + center.getsex() + ">\n");
    for (Socialtie<E> socialtie : relbetweenco.get(0).getrel()) {
      CentralObject cuser = (CentralObject) relbetweenco.get(0).getc();
      PhysicalObject friend = (PhysicalObject) socialtie.getobj();
      fw.write("SocialTie ::= <" + cuser.getname() + ", " + friend.getname() + ", "
          + socialtie.getIntimacy().doubleValue() + ">\n");
    }
    for (int i = 0; i < relbetweenoo.size(); i++) {
      PhysicalObject friend1 = (PhysicalObject) relbetweenoo.get(i).geto();
      for (Socialtie<E> socialtie : relbetweenoo.get(i).getrel()) {
        PhysicalObject friend2 = (PhysicalObject) socialtie.getobj();
        fw.write("SocialTie ::= <" + friend1.getname() + ", " + friend2.getname() + ", "
            + socialtie.getIntimacy().doubleValue() + ">\n");
      }
    }
    for (int i = 0; i < orbitscontainobjects.size(); i++) {
      for (int j = 0; j < orbitscontainobjects.get(i).getthingsintrack().size(); j++) {
        PhysicalObject friend =
            (PhysicalObject) orbitscontainobjects.get(i).getthingsintrack().get(j);
        fw.write("Friend ::= <" + friend.getname() + "," + friend.getages().intValue() + ","
            + friend.getsex() + ">\n");
      }
    }
    fw.close();

  }



  @Override
  public void readfile(String filename, int number) throws IOException, ReadFileFailException {
    Logger log = ConcreteCircularOrbit.log;
    log.addHandler(SocialNetworkCircleapp.fh);
    String[] filenames = filename.split(" ");
    String pathname;
    if (filenames.length == 1) {
      pathname = "src\\txt\\" + filename;
    } else {
      pathname = "test\\txt\\" + filenames[1];
    }
    File file = new File(pathname);
    ReadStrategy br = ReadStrategy.empty(file, number);
    String format1 = "(CentralUser ::= )<(\\w+,?|[\\w|.]+,?)+>";
    String format2 = "(Friend ::= )<(\\w+,?|[\\w|.| ]+,?)+>";
    String format3 = "(SocialTie ::= )<(\\w+,?|[\\w|.| ]+,?)+>";
    String line;
    CentralObject center = null;
    // List<PhysicalObject> physicalObjects = new ArrayList<>();
    Map<String, PhysicalObject> physicalobjects = new HashMap<>();
    int linecounter = 1;
    int eflag = 0;
    long starttime = System.currentTimeMillis();
    while ((line = br.readLine()) != null) {
      log.info(line);
    }
    br.close();
    long endtime = System.currentTimeMillis();
    long exacttime = (endtime - starttime) / 1000;
    System.out.println("Cost " + exacttime + " s.");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    // 第一遍扫描先读CentralUser和Friend
    file = new File(pathname);
    br = ReadStrategy.empty(file, number);
    while ((line = br.readLine()) != null) {
      try {
        Matcher m1 = Pattern.compile(format1).matcher(line);
        Matcher m2 = Pattern.compile(format2).matcher(line);

        while (m1.find()) {
          String[] couples = m1.group().split("CentralUser ::= <|,|>");
          int num;
          try {
            num = Integer.valueOf(couples[2]);
          } catch (Exception e) {
            throw new CuagesException();
          }
          if ((!couples[3].equals("M")) && (!couples[3].equals("F"))) {
            throw new CusexException();
          }
          center = CentralObject.centralusercreator(couples[1], couples[3], num);
          super.addcenterobject((L) center);
        }
        while (m2.find()) {
          String[] couples = m2.group().split("Friend ::= <|, |>|,");
          int num;
          try {
            num = Integer.valueOf(couples[2]);
          } catch (Exception e) {
            throw new FriendAgesException();
          }
          if ((!couples[3].equals("M")) && (!couples[3].equals("F"))) {
            throw new FriendSexException();
          }
          physicalobjects.put(couples[1],
              PhysicalObject.friendcreator(couples[1], couples[3], num));
        }
        m1 = Pattern.compile(format1).matcher(line);
        m2 = Pattern.compile(format2).matcher(line);
        Matcher m3 = Pattern.compile(format3).matcher(line);
        if ((!m1.find()) && (!m2.find()) && (!m3.find())) {
          throw new SyntaxErrorException(linecounter, line);
        }
      } catch (SyntaxErrorException e) {
        eflag = 1;
        String suf = "line " + e.getlinenum() + " " + e.getline();
        String[] words = e.getline().split(" ");
        if ((!words[0].equals("CentralUser")) && (!words[0].equals("Friend"))
            && (!words[0].equals("SocialTie"))) {
          log.severe(suf + " : Prefix name mismatch.");
        } else {
          log.severe(suf + " : Syntax mismatch.");
        }
      } catch (CuagesException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line
            + " : The CentralUser's number of ages is not an integer.");
      } catch (FriendAgesException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line
            + " : The Friend's number of ages is not an integer.");
      } catch (CusexException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line + " : The CentralUser's sex is illegal.");
      } catch (FriendSexException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line + " : The Friend's sex is illegal.");
      } finally {
        linecounter++;
      }

    }
    br.close();
    // 第二遍扫描再读SocialTie
    file = new File(pathname);
    br = ReadStrategy.empty(file, number);
    linecounter = 1;
    while ((line = br.readLine()) != null) {
      try {
        Matcher m1 = Pattern.compile(format1).matcher(line);
        Matcher m2 = Pattern.compile(format2).matcher(line);
        Matcher m3 = Pattern.compile(format3).matcher(line);
        while (m3.find()) {
          String[] couples = m3.group().split("SocialTie ::= <|, |>|,");
          if (couples[1].equals(center.getname())) {
            double v = 0;
            try {
              v = Double.valueOf(couples[3]);
            } catch (Exception e) {
              throw new IntimacyNumException();
            }
            if (v < 0 || v > 1) {
              throw new IntimacyNumException();
            }
            int flag = 0;
            PhysicalObject p = null;
            if ((p = physicalobjects.get(couples[2])) != null) {
              flag = 1;
              myaddcontactco((L) center, (E) p, v);
            }
            if (flag == 0) {
              throw new SocialTieLogicalException(couples[2]);
            }
          } else {
            double v = 0;
            try {
              v = Double.valueOf(couples[3]);
            } catch (Exception e) {
              throw new IntimacyNumException();
            }
            if (v < 0 || v > 1) {
              throw new IntimacyNumException();
            }
            int flag1 = 0;
            int flag2 = 0;
            PhysicalObject p1 = null;
            PhysicalObject p2 = null;
            if ((p1 = physicalobjects.get(couples[1])) != null) {
              flag1 = 1;
              if ((p2 = physicalobjects.get(couples[2])) != null) {
                flag2 = 1;
                myaddcontactoo((E) p1, (E) p2, v);
              }
              if (flag2 == 0) {
                throw new SocialTieLogicalException(couples[2]);
              }
            }
            if (flag1 == 0) {
              throw new SocialTieLogicalException(couples[1]);
            }
          }

        }

      } catch (SocialTieLogicalException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line + " : " + e.showwrong());
      } catch (IntimacyNumException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line + " : The intimacy is illeagl.");
      } catch (NullPointerException e) {
        eflag = 1;
        log.severe("line " + linecounter + " : Lack of CentralUser.");
      } finally {
        linecounter++;
      }
    }
    br.close();
    if (eflag == 1) {
      throw new ReadFileFailException();
    }
    int max = 0;
    for (String p : physicalobjects.keySet()) {
      int logic;
      if ((logic = getminlogicdisfromc((E) physicalobjects.get(p))) != -1 && max < logic) {
        max = logic;
      }
    }
    for (int i = 1; i <= max; i++) {
      Track t = Track.roundcreator(i);
      try {
        super.addorbit(t);
      } catch (TheSameTagRException e) {
        log.severe(e.showwrong());
        System.out.println();
      }
      for (String p : physicalobjects.keySet()) {
        if (getminlogicdisfromc((E) physicalobjects.get(p)) == i) {
          addtoorbit((E) physicalobjects.get(p), t);
        }
      }
    }

    reset();
  }


  @Override
  public boolean islegal() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getlogicdistance(E e1, E e2) {
    assert e1 != null;
    assert e2 != null;
    int dis = super.getlogicdistance(e1, e2);
    if (dis != -1) {
      return dis;
    } else {
      dis = getminlogicdisfromc(e1) + getminlogicdisfromc(e2);
      return dis;
    }
  }


  @Override
  public int getinfodiffu(E friend) {
    assert friend != null;
    int friends = 0;
    for (E obj : super.objects) {
      if (strictlogicdistance(friend, obj) != -1) {
        friends++;
      }
    }
    return friends;
  }


  /**
   * 个人定义的辅助函数，计算参考亲密度的最短逻辑距离（亲密度超过0.5算相连）.
   * 
   * @param e1 轨道上物体
   * @param e2 轨道上物体
   * @return 如果e1与e2之间能相连，则返回其最短逻辑距离，否则返回-1.
   */
  private int strictlogicdistance(E e1, E e2) {
    assert e1 != null;
    assert e2 != null;
    int mycounter = 0;
    int pre = 0;
    int last = 1;
    int mid = 1;
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visit = new boolean[objects.size()];
    int e1num = find(objects, e1);
    int e2num = find(objects, e2);
    queue.offer(e1num);
    while (queue.peek() != null) {
      int i = queue.poll();
      visit[i] = true;
      if (visit[e2num] == true) {
        break;
      }
      pre++;
      E e = objects.get(i);
      for (Socialtie<E> obj : getlist(relbetweenoo, e)) {
        int m = find(objects, obj.getobj());
        if (visit[m] != true && obj.getIntimacy().doubleValue() > 0.5) {
          queue.offer(m);
          last++;
        }
      }
      if (pre == mid) {
        mid = last;
        mycounter++;
      }
    }
    checkRep();
    if (visit[e2num]) {
      return mycounter;
    } else {
      return -1;
    }
  }



  /**
   * 个人定义的辅助函数，用于计算轨道物体到中心物体的最短逻辑距离.
   * 
   * @return 计算轨道物体到中心物体的最短逻辑距离，如果无联系则返回-1.
   */
  private int getminlogicdisfromc(E e) {
    assert e != null;
    int mindistance = -1;
    for (Socialtie<E> s : super.relbetweenco.get(0).getrel()) {
      if (s.getobj().equals(e)) {
        mindistance = 1;
        return mindistance;
      }
    }

    for (Socialtie<E> s : super.relbetweenco.get(0).getrel()) {
      int logic;
      if (mindistance == -1 && (logic = super.getlogicdistance(s.getobj(), e)) != -1) {
        mindistance = logic + 1;
      }
      if ((logic = super.getlogicdistance(s.getobj(), e)) != -1 && mindistance > logic + 1) {
        mindistance = logic + 1;
      }
    }
    return mindistance;

  }


  /**
   * 个人定义的辅助函数，用于调整增加或删除后的轨道结构.
   */
  private void reset() {
    int max = 0;
    int i = 0;
    int j = 0;
    for (i = 0; i < objects.size(); i++) {
      E e = objects.get(i);
      int logic;
      if ((logic = getminlogicdisfromc(e)) != -1 && max < logic) {
        max = logic;
      }
    }

    for (i = 1; i <= max; i++) {
      int flag = 0;
      for (j = 0; j < super.tracks.size(); j++) {
        if (super.tracks.get(j).getridus().intValue() == i) {
          flag = 1;
          break;
        }
      }
      if (flag == 0) {
        Track t = Track.roundcreator(i);
        try {
          super.addorbit(t);
        } catch (TheSameTagRException e) {
          System.out.println(e.showwrong());
        }
      }
    }


    for (i = 0; i < objects.size(); i++) {
      E e = objects.get(i);
      int logic;
      if ((logic = getminlogicdisfromc(e)) == -1) {
        super.deleteorbitobj(e);
        i--;
      } else {

        transit(e, super.tracks.get(logic - 1));
      }
    }

    for (i = max; i < super.tracks.size(); i++) {
      super.removeorbit(super.tracks.get(i));
    }

  }



  /**
   * 个人定义的辅助函数，用于读文件时辅助构建轨道系统.
   * 
   * @param c L
   * @param object E
   * @param intimacy 亲密度
   */
  private void myaddcontactco(L c, E object, Number intimacy) {
    int i = 0;
    int flag = 0;
    for (i = 0; i < objects.size(); i++) {
      if (objects.get(i).equals(object)) {
        flag = 1;
      }
    }
    if (flag == 0) {
      relbetweenoo.add(RelationshipBetweenoo.creator(object, "Basics"));
      objects.add(object);
    }
    super.addcontactco(c, object, intimacy);
  }



  /**
   * 个人定义的辅助函数，用于读文件时辅助构建轨道系统.
   * 
   * @param object1 E
   * @param object2 E
   * @param intimacy 亲密度
   */
  private void myaddcontactoo(E object1, E object2, Number intimacy) {
    assert object1 != null;
    assert object2 != null;
    assert intimacy != null;
    int i = 0;
    int flag1 = 0;
    int flag2 = 0;
    for (i = 0; i < objects.size(); i++) {
      if (objects.get(i).equals(object1)) {
        flag1 = 1;
      }
      if (objects.get(i).equals(object2)) {
        flag2 = 1;
      }
    }
    if (flag1 == 0) {
      relbetweenoo.add(RelationshipBetweenoo.creator(object1, "Basics"));
      objects.add(object1);
    }
    if (flag2 == 0) {
      relbetweenoo.add(RelationshipBetweenoo.creator(object2, "Basics"));
      objects.add(object2);
    }
    super.addcontactoo(object1, object2, intimacy);
  }



  /**
   * 个人定义的辅助函数，用于寻找objects数组中object的下标.
   * 
   * @param objects 物体列表
   * @param object E
   * @return objects数组中object的下标
   */
  protected int find(List<E> objects, E object) {
    assert objects != null;
    assert object != null;
    int i;
    for (i = 0; i < objects.size(); i++) {
      if (objects.get(i).equals(object)) {
        return i;
      }
    }
    return -1;
  }


  /**
   * 个人定义的辅助函数，返回与轨道物体object有联系的所有轨道物体所组成的列表.
   * 
   * @param relbetweenoo relationshipbetweenoo
   * @param object E
   * @return 与轨道物体object有联系的所有轨道物体所组成的列表
   */
  protected List<Socialtie<E>> getlist(List<RelationshipBetweenoo<E>> relbetweenoo, E object) {
    assert relbetweenoo != null;
    assert object != null;
    int i;
    for (i = 0; i < relbetweenoo.size(); i++) {
      if (relbetweenoo.get(i).geto().equals(object)) {
        return relbetweenoo.get(i).getrel();
      }
    }
    return null;
  }


}
