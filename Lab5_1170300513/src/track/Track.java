package track;



/**
 * immutable的ADT，用来模拟轨道.
 * 
 * @author junbaba
 *
 */
public interface Track {
  /**
   * 静态工厂方法，创建一个圆轨道.
   * 
   * @param ridus 半径
   * @return 圆轨道
   */
  public static Track roundcreator(Number ridus) {
    return new RoundTrack(ridus);
  }



  /**
   * 提供给clients得知轨道半径的方法.
   * 
   * @return 轨道半径ridus
   */
  public Number getridus();


}
