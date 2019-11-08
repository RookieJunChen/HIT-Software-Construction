package centralobject;



/**
 * immutable的可复用类接口CentralObject，用于模拟中心点物体.
 * 
 * @author junbaba
 *
 */
public interface CentralObject {
  /**
   * 创建一个实现CentralObject接口且应用于Q2的对象的静态工厂方法.
   * 
   * @param name 恒星名
   * @param ridus 恒星半径
   * @param quality 恒星质量
   * @return 实现CentralObject接口且应用于Q2的对象
   */
  public static CentralObject sunscreator(String name, Number ridus, Number quality) {
    return new Suns(name, ridus, quality);
  }


  /**
   * 创建一个实现CentralObject接口且应用于Q3的对象的静态工厂方法.
   * 
   * @param name 原子核名
   * @return 实现CentralObject接口且应用于Q3的对象
   */
  public static CentralObject atomcreator(String name) {
    return new Atomicnucleus(name);
  }

  /**
   * 创建一个实现CentralObject接口且应用于Q5的对象的静态工厂方法.
   * 
   * @param name 名字
   * @param sex 性别
   * @param ages 年龄
   * @return 实现CentralObject接口且应用于Q5的对象
   */
  public static CentralObject centralusercreator(String name, String sex, Number ages) {
    return new CentralUser(name, sex, ages);
  }



  /**
   * 得到该物体名称.
   * 
   * @return 该物体名称。
   */
  public String getname();

  /**
   * 得到该物体的半径（针对Q2）.
   * 
   * @return 该物体半径
   */
  public Number getridus();

  /**
   * 得到该物体质量（针对Q2）.
   * 
   * @return 该物体质量
   */
  public Number getquality();



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
