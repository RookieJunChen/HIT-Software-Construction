package apis;

import assist.Difference;
import circularorbit.CircularOrbit;

public class CircularOrbitApis<L, E> {

  /**
   * 计算多轨道系统中各轨道上物体分布的熵值.
   * 
   * @param c Multi-track system
   * @return 多轨道系统中各轨道上物体分布的熵值
   */
  public double getObjectDistributionEntropy(CircularOrbit<L, E> c) {
    double entropy = 0;
    for (int i = 0; i < c.getorbits().size(); i++) {
      if (c.getorbits().get(i).getthingsintrack().size() > 0) {
        double p = (double) 1 / c.getorbits().get(i).getthingsintrack().size();
        for (int j = 0; j < c.getorbits().get(i).getthingsintrack().size(); j++) {
          entropy += p * Math.log(p);
        }
      }
    }
    return entropy;
  }

  /**
   * 计算任意两个物体之间的最短逻辑距离.
   * 
   * @param c Multi-track system
   * @param e1 Orbital object
   * @param e2 Orbital object
   * @return e1和e2之间的最短逻辑距离
   */
  public int getLogicalDistance(CircularOrbit<L, E> c, E e1, E e2) {
    return c.getlogicdistance(e1, e2);
  }

  /**
   * 计算任意两个物体之间的物理距离.
   * 
   * @param c Multi-track system
   * @param e1 Orbital object
   * @param e2 Orbital object
   * @return e1和e2之间的物理距离
   */
  public double getPhysicalDistance(CircularOrbit<L, E> c, E e1, E e2) {
    return c.getphysicaldistance(e1, e2);
  }

  /**
   * 计算两个多轨道系统之间的差异.
   * 
   * @param c1 Multi-track system
   * @param c2 Multi-track system
   * @return 表示两个轨道系统差异的Different类
   */
  public Difference<E> getDifference(CircularOrbit<L, E> c1, CircularOrbit<L, E> c2) {
    return new Difference<E>(c1.getorbits(), c2.getorbits());
  }

}
