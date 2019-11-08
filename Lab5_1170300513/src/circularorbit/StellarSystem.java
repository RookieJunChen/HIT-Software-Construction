package circularorbit;

import applications.StellarSystemapp;
import assist.NumBer;
import assist.Position;
import centralobject.CentralObject;
import exceptions.ReadFileFailException;
import exceptions.SyntaxErrorException;
import exceptions.TheSameTagRException;
import io.OutStrategy;
import io.ReadStrategy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import physicalobject.PhysicalObject;
import stellarsystemexceptions.AngleException;
import stellarsystemexceptions.DirctionException;
import stellarsystemexceptions.PlanetNumberException;
import stellarsystemexceptions.SunNumberException;
import track.Track;

/**
 * 一个实现CircularOrbit并继承了ConcreteCircularOrbit的类，用于完成Q2的StellarSystem.
 * 
 * @author junbaba
 *
 * @param <L> 中心物体类型
 * @param <E> 轨道物体类型
 */
public class StellarSystem<L, E> extends ConcreteCircularOrbit<L, E>
    implements CircularOrbit<L, E> {

  private final Map<PhysicalObject, Position<E>> connections =
      new HashMap<PhysicalObject, Position<E>>();

  /**
   * 辅助函数，用于判断StellarSystem是否合法.
   * 
   * @return 合法返回true，不合法则返回false
   */
  public boolean islegal() {
    int flag = 0;
    if (super.center.size() == 1) {
      flag = 1;
    }
    for (int i = 0; i < super.orbitscontainobjects.size(); i++) {
      if (super.orbitscontainobjects.get(i).getthingsintrack().size() != 1) {
        flag = 0;
      }
    }
    if (flag == 1) {
      return true;
    } else {
      return false;
    }
  }



  @Override
  public void addtoorbit(E object, Track orbit) throws TheSameTagRException {
    assert object != null;
    assert orbit != null;

    objects.add(object);
    return;
    /*
     * int i; PhysicalObject p1 = (PhysicalObject) object; for (i = 0; i <
     * super.orbitscontainobjects.size(); i++) { if (super.orbitscontainobjects.size() == 1) {
     * super.addtoorbit(object, orbit); break; } if
     * (super.orbitscontainobjects.get(i).gettrack().equals(orbit)) { if (i == 0) { double deltar2 =
     * super.orbitscontainobjects.get(i + 1).gettrack().getridus().doubleValue() -
     * super.orbitscontainobjects.get(i).gettrack().getridus().doubleValue(); PhysicalObject p3 =
     * (PhysicalObject) super.orbitscontainobjects.get(i + 1).getthingsintrack().get(0); if
     * ((p1.getplanetr().doubleValue() + p3.getplanetr().doubleValue() < deltar2) &&
     * (super.orbitscontainobjects.get(i).getthingsintrack().size() == 0)) {
     * super.addtoorbit(object, orbit); } else { throw new UnsupportedOperationException(); } } else
     * if (i == super.orbitscontainobjects.size() - 1) { double deltar1 =
     * super.orbitscontainobjects.get(i).gettrack().getridus().doubleValue() -
     * super.orbitscontainobjects.get(i - 1).gettrack().getridus().doubleValue(); PhysicalObject p2
     * = (PhysicalObject) super.orbitscontainobjects.get(i - 1).getthingsintrack().get(0); if
     * ((p1.getplanetr().doubleValue() + p2.getplanetr().doubleValue() < deltar1) &&
     * (super.orbitscontainobjects.get(i).getthingsintrack().size() == 0)) {
     * super.addtoorbit(object, orbit); } else { throw new UnsupportedOperationException(); } }
     * 
     * 
     * 
     * } }
     */

  }

  @Override
  public void readfile(String filename, int num) throws IOException, ReadFileFailException {

    Logger log = ConcreteCircularOrbit.log;
    log.addHandler(StellarSystemapp.fh);
    String[] filenames = filename.split(" ");
    String pathname;
    if (filenames.length == 1) {
      pathname = "src\\txt\\" + filename;
    } else {
      pathname = "test\\txt\\" + filenames[1];
    }
    File file = new File(pathname);
    ReadStrategy br = ReadStrategy.empty(file, num);
    String format1 = "(Stellar ::= )<(\\w+,?|[\\w|.]+,?)+>";
    String format2 = "(Planet ::= )<(\\w+,?|[\\w|.]+,?)+>";
    String line;
    int linecounter = 1;
    int eflag = 0;
    while ((line = br.readLine()) != null) {
      try {
        Matcher m1 = Pattern.compile(format1).matcher(line);
        Matcher m2 = Pattern.compile(format2).matcher(line);
        while (m1.find()) {
          String[] couples = m1.group().split("Stellar ::= <|,|>");
          NumBer r = null;
          NumBer quality = null;
          try {
            r = new NumBer(couples[2]);
            quality = new NumBer(couples[3]);
          } catch (Exception e) {
            throw new SunNumberException();
          }
          CentralObject center =
              CentralObject.sunscreator(couples[1], r.doubleValue(), quality.doubleValue());
          super.addcenterobject((L) center);
        }
        while (m2.find()) {
          String[] couples = m2.group().split("Planet ::= <|,|>");
          NumBer v = null;
          NumBer objectr = null;
          NumBer orbitr = null;
          try {
            objectr = new NumBer(couples[4]);
            orbitr = new NumBer(couples[5]);
            v = new NumBer(couples[6]);
          } catch (Exception e) {
            throw new PlanetNumberException();
          }
          double angle360 = Double.valueOf(couples[8]);
          double angle = 0;
          if (angle360 >= 0 && angle360 < 360) {
            angle = angle360;
          } else {
            throw new AngleException(angle360);
            // angle = (((angle360%360)+360)%360) ;
          }
          if ((!couples[7].equals("CW")) && (!couples[7].equals("CCW"))) {
            throw new DirctionException();
          }
          Track t = Track.roundcreator(orbitr.doubleValue());
          super.addorbit(t);
          PhysicalObject object = PhysicalObject.planetcreator(couples[1], couples[2], couples[3],
              objectr.doubleValue(), v.doubleValue(), couples[7]);
          addtoorbit((E) object, t);
          Position<E> p = new Position<E>((E) object, orbitr.doubleValue(), angle);
          super.positions.add(p);
          connections.put(object, p);
        }
        m1 = Pattern.compile(format1).matcher(line);
        m2 = Pattern.compile(format2).matcher(line);
        if ((!m1.find()) && (!m2.find())) {
          throw new SyntaxErrorException(linecounter, line);
        }
      } catch (TheSameTagRException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line + " : " + e.showwrong());
      } catch (SyntaxErrorException e) {
        eflag = 1;
        String suf = "line " + e.getlinenum() + ": " + e.getline();
        String[] words = e.getline().split(" ");
        if ((!words[0].equals("Stellar")) && (!words[0].equals("Planet"))) {
          log.severe(suf + " : Prefix name mismatch.");
        } else {
          log.severe(suf + " : Syntax mismatch.");
        }
      } catch (AngleException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line + " : " + e.getangle().doubleValue()
            + " is not in [0,360).");
      } catch (SunNumberException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line
            + " : Radius and quality do not meet the number's requirement.");
      } catch (PlanetNumberException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line
            + " : Numbers of planets that do not meet the number's requirement.");
      } catch (DirctionException e) {
        eflag = 1;
        log.severe(
            "line " + linecounter + ": " + line + " : Direction does not meet the requirements.");
      } catch (UnsupportedOperationException e) {
        eflag = 1;
        log.severe("line " + linecounter + ": " + line
            + " : The add operation causes the system to be illegal.");
      } finally {
        linecounter++;
      }
    }
    br.close();
    // if (!islegal()) {
    // log.severe("This Stellar System is illegal!");
    // System.out.println();
    // throw new ReadFileFailException();
    // }
    // if (eflag == 1) {
    // throw new ReadFileFailException();
    // }

  }


  @Override
  public void deleteorbitobj(E obj) {
    assert obj != null;
    int i = 0;
    int j = 0;
    for (i = 0; i < objects.size(); i++) {
      E o = objects.get(i);
      if (o.equals(obj)) {
        objects.remove(o);
        for (j = 0; j < positions.size(); j++) {
          Position<E> p = positions.get(j);
          if (p.getobject().equals(obj)) {
            positions.remove(p);
            deleteinrelco(obj, relbetweenco);
            deleteinreloo(obj, relbetweenoo);
            log.info("Delete " + obj.toString() + "'s operation succeeded.");
            checkRep();
            return;
          }
        }
      }
    }
    log.warning("Delete operation failed.");
    checkRep();
    return;
  }



  @Override
  public void writefile(int num) throws IOException {
    assert this.center.size() == 1;
    String pathname = "src\\writing\\StellarSystem.txt";
    File file = new File(pathname);
    if (!file.exists()) {
      file.createNewFile();
    }
    OutStrategy fw = OutStrategy.empty(file, num);
    CentralObject center = (CentralObject) getcenter();
    fw.write("Stellar ::= <" + center.getname() + "," + center.getquality() + ","
        + center.getridus() + ">\n");
    for (E obj : objects) {
      PhysicalObject phy = (PhysicalObject) obj;
      Number angle = null;
      Number ridus = null;
      Position<E> pos = null;
      if ((pos = connections.get(phy)) != null) {
        angle = pos.getangle();
        ridus = pos.getridus();
        StringBuffer str = new StringBuffer();
        str.append("Planet ::= <" + phy.getname() + "," + phy.getform() + "," + phy.getcolor() + ","
            + phy.getplanetr().doubleValue() + "," + ridus.doubleValue() + ","
            + phy.getv().doubleValue() + "," + phy.getdirection() + "," + angle.doubleValue()
            + ">\n");
        fw.write(str.toString());
      }
    }
    fw.close();
  }

}
