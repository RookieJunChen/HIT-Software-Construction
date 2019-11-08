package relationship;

/**
 * 用于辅助表示关系immutable的类.
 * 
 * @author junbaba
 *
 */
public class Socialtie<E> {
  protected final E object;
  protected final Number intimacy;

  // Abstraction function:
  // object对应物体，intimacy对应亲密度

  // Representation invariant:
  // object != null
  // intimacy != null

  // Safety from rep exposure:
  // 通过protected使其它非子类中无法得知本类中的rep,并且get方法所得到的返回值均仅只能查看无法修改.

  // TODO checkRep
  /**
   * checkRep.
   */
  public void checkRep() {
    assert object != null;
    assert intimacy != null;

  }

  // TODO constructor
  public Socialtie(E object, Number intimacy) {
    this.object = object;
    this.intimacy = intimacy;
  }


  /**
   * 提供给使用者的得知object的方法.
   * 
   * @return object
   */
  public E getobj() {
    checkRep();
    return object;
  }


  /**
   * 提供给使用者得知亲密度intimacy的方法.
   * 
   * @return 亲密度intimacy
   */
  public Number getIntimacy() {
    checkRep();
    return intimacy;
  }
}
