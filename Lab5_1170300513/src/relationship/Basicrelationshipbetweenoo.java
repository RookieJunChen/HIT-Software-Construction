package relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个relationshipbetweenoo的具体实现.
 * 
 * @author junbaba
 *
 */
public class Basicrelationshipbetweenoo<E> implements RelationshipBetweenoo<E> {
  protected final E object;
  protected final List<Socialtie<E>> contacts = new ArrayList<>();

  // Abstraction function:
  // object对应一轨道物体，contacts列表中每一个元素对应与该轨道物体存在联系的一个轨道物体

  // Representation invariant:
  // contacts != null

  // Safety from rep exposure:
  // 通过protected使其它非字类中无法得知本类中的rep.

  // TODO constructor
  public Basicrelationshipbetweenoo(E object) {
    this.object = object;
  }


  // TODO checkRep
  public void checkRep() {
    assert contacts != null;

  }

  @Override
  public E geto() {
    return object;
  }

  @Override
  public List<Socialtie<E>> getrel() {
    return contacts;
  }

  @Override
  public void addconnection(E newobejct, Number intimacy) {
    Socialtie<E> s = new Socialtie<E>(newobejct, intimacy);
    contacts.add(s);
    checkRep();
  }
}


