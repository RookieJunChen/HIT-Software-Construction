package thingintrack;

import java.util.List;
import track.Track;

/**
 * mutable的可复用类接口ThinginTrack，用于模拟轨道与轨道上物体的关系.
 * 
 * @author junbaba
 *
 * @param <E> 轨道物体的类型
 */
public interface ThinginTrack<E> {

  /**
   * 构造方法.
   */
  public static <E> ThinginTrack<E> creator(Track track, String choice) {
    if (choice.equals("Basics")) {
      return new BasicThinginTrack<>(track);
    } else {
      throw new UnsupportedOperationException();
    }
  }


  /**
   * 得到该关系中轨道Track的方法.
   * 
   * @return Track 轨道
   */
  public Track gettrack();

  /**
   * 向该轨道上添加物体.
   * 
   * @param e 物体
   */
  public void addobject(E e);


  /**
   * 删除位于该轨道上的一个物体.
   * 
   * @param e 物体
   */
  public void deleteobject(E e);


  /**
   * 得到该轨道上所有物体的构成的列表.
   * 
   * @return 所以物体构成的列表
   */
  public List<E> getthingsintrack();
}
