package physicalobject;



/**
 * immutable的可复用类接口CentralObject，用于模拟轨道上.
 * 
 * @author junbaba
 *
 */
public interface PhysicalObject {
  /**
   * 创建一个实现PhysicalObject接口且应用于Q2的对象的静态工厂方法.
   * 
   * @param name 行星名
   * @param form 行星形态
   * @param color 行星颜色
   * @param planetr 行星半径
   * @param v 行星公转速度
   * @param direction 行星公转方向
   * @return 实现PhysicalObject接口且应用于Q2的对象
   */
  public static PhysicalObject planetcreator(String name, String form, String color, Number planetr,
      Number v, String direction) {
    return new Planet(name, form, color, planetr, v, direction);
  }



  /**
   * 创建一个实现PhysicalObject接口且应用于Q3的对象的静态工厂方法.
   * 
   * @return 实现PhysicalObject接口且应用于Q3的对象
   */
  public static PhysicalObject electronicscreator() {
    return new Electronics();
  }


  /**
   * 创建一个实现PhysicalObject接口且应用于Q5的对象的静态工厂方法.
   * 
   * @param name 名字
   * @param sex 性别
   * @param ages 年龄
   * @return 实现PhysicalObject接口且应用于Q5的对象
   */
  public static PhysicalObject friendcreator(String name, String sex, Number ages) {
    return new Friend(name, sex, ages);
  }


  /**
   * 得到该物体名称.
   * 
   * @return 该物体名称。
   */
  public String getname();


  /**
   * 得到该物体的形态（针对Q2）.
   * 
   * @return 该物体的形态
   */
  public String getform();


  /**
   * 得到该物体的颜色（针对Q2）.
   * 
   * @return 该物体颜色
   */
  public String getcolor();


  /**
   * 得到该物体行星半径（针对Q2）.
   * 
   * @return 该物体行星半径
   */
  public Number getplanetr();



  /**
   * 得到该物体的公转速度（针对Q2）.
   * 
   * @return 该物体的公转速度
   */
  public Number getv();



  /**
   * 得到该物体的公转方向（针对Q2）.
   * 
   * @return 该物体的公转方向
   */
  public String getdirection();



  /**
   * 得到该物体性别（针对Q5）.
   * 
   * @return 该物体性别
   */
  public String getsex();



  /**
   * 得到该物体年龄（针对Q5）.
   * 
   * @return 该物体年龄
   */
  public Number getages();



}
