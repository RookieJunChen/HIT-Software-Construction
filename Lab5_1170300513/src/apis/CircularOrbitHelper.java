package apis;

import assist.MyFrameone;
import assist.MyPaneltwo;
import centralobject.CentralObject;
import circularorbit.CircularOrbit;

import javax.swing.JFrame;

import physicalobject.PhysicalObject;


public class CircularOrbitHelper {

  public static void visualize(CircularOrbit<CentralObject, PhysicalObject> c) {
    new MyFrameone<CentralObject, PhysicalObject>(c);
  }


  /**
   * SocialNetwoekCircle的可视化操作(动图).
   * 
   * @param c Multi-track system
   */
  public static void visualizeSnc(CircularOrbit<CentralObject, PhysicalObject> c) {
    MyPaneltwo<CentralObject, PhysicalObject> p = new MyPaneltwo<>(c);
    JFrame frame = new JFrame();
    frame.add(p);
    frame.setBounds(100, 100, 500, 500);
    frame.setDefaultCloseOperation(1);
    frame.setVisible(true);
    Thread panelThread = new Thread(p);
    panelThread.start();
  }

}


