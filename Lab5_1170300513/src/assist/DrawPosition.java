package assist;

/**
 * 用于辅助画图使用的数据结构，故无RI,AF等.
 * 
 * @author junbaba
 *
 * @param <E> 轨道物体类型
 */
public class DrawPosition<E> {
  public int xpos;
  public int ypos;
  public E object;

  /**
   * 构造方法.
   */
  public DrawPosition(E object, int x, int y) {
    this.xpos = x;
    this.ypos = y;
    this.object = object;
  }
}
