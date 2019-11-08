package assist;

/**
 * 用于表示轨道系统中轨道物体坐标的mutable的类.
 * 
 * @author junbaba
 *
 * @param <E> 轨道物体类型
 */
public class Position<E> {
  private final E object;
  private Number ridus;
  private Number angle;

  // Abstraction function:
  // object对应轨道物体，ridus对应其极坐标的半径长，angle对应极坐标的角度

  // Representation invariant:
  // object != null
  // ridus > 0
  // 0 < angle < pi

  // Safety from rep exposure:
  // 通过private使其它非子类中无法得知本类中的rep


  // TODO checkRep
  /**
   * checkRep.
   */
  public void checkRep() {
    assert object != null;
    assert ridus.doubleValue() > 0;
    assert (angle.doubleValue() >= 0) && (angle.doubleValue() < 360);

  }

  // TODO constructor
  /**
   * constructor.
   * 
   * @param object 物体
   * @param ridus 半径
   * @param angle 角度
   */
  public Position(E object, Number ridus, Number angle) {
    this.ridus = ridus;
    this.object = object;
    this.angle = angle;
  }


  /**
   * 得到轨道物体的方法.
   * 
   * @return 轨道物体
   */
  public E getobject() {
    return object;
  }


  /**
   * 得到轨道物体极坐标半径的方法.
   * 
   * @return 轨道物体极坐标半径
   */
  public Number getridus() {
    checkRep();
    return ridus;
  }


  /**
   * 得到轨道物体极坐标角度的方法.
   * 
   * @return 轨道物体极坐标角度
   */
  public Number getangle() {

    checkRep();
    return angle;
  }


  /**
   * 改变轨道物体极坐标角度.
   * 
   * @param newangle 新的极坐标角度
   */
  public void changeangle(Number newangle) {
    this.angle = newangle;
  }

  /**
   * 改变轨道物体极坐标半径.
   * 
   * @param newridus 新的半径
   */
  public void changeridus(Number newridus) {
    this.ridus = newridus;
  }



  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(this.object + ": ");
    str.append("(" + this.ridus.doubleValue() + "," + this.angle.doubleValue() + ")");
    return str.toString();
  }
}
