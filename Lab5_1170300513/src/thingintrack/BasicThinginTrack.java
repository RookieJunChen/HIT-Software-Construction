package thingintrack;

import java.util.ArrayList;
import java.util.List;
import track.Track;

/**
 * 一个ThinginTrack接口的具体实现.
 * 
 * @author junbaba
 *
 * @param <E> 轨道物体的类型
 */
public class BasicThinginTrack<E> implements ThinginTrack<E> {
  protected final Track track;
  protected final List<E> orbitobjects = new ArrayList<>();

  // Abstraction function:
  // track对应轨道，orbitobjects上每一个元素对应轨道上一个物体

  // Representation invariant:
  // track != null
  // orbitobjects != null

  // Safety from rep exposure:
  // 通过protected使其它非子类中无法得知本类中的rep.


  // TODO checkRep
  public void checkRep() {
    assert track != null;
    assert orbitobjects != null;
  }

  // TODO constructor
  public BasicThinginTrack(Track track) {
    this.track = track;
  }

  @Override
  public Track gettrack() {
    return track;
  }

  @Override
  public void addobject(E e) {
    orbitobjects.add(e);
  }

  @Override
  public List<E> getthingsintrack() {
    return orbitobjects;
  }

  @Override
  public void deleteobject(E e) {
    int i;
    for (i = 0; i < orbitobjects.size(); i++) {
      E object = orbitobjects.get(i);
      if (object.equals(e)) {
        orbitobjects.remove(object);
      }
    }

  }

}
