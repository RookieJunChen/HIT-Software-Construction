package relationship;

import java.util.List;



/**
 * mutable的可复用类接口relationshipbetweenco，用于表示多轨道系统中中心点物体和轨道物体的关系.
 * 
 * @author junbaba
 *
 * @param <L> 多轨道系统的中心点物体类型
 * @param <E> 轨道物体的类型
 */
public interface RelationshipBetweenco<L, E> {

  /**
   * 创建一个实现relationshipbetweenco接口的对象的静态工厂方法.
   * 
   * @return 实现 relationshipbetweenco接口的对象
   */
  public static <L, E> RelationshipBetweenco<L, E> creator(L c, String choice) {
    if (choice.equals("Basics")) {
      return new Basicrelationshipbetweenco<L, E>(c);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * 返回关系中的中心点物体.
   * 
   * @return 中心点物体
   */
  public L getc();

  /**
   * 返回与中心点物体存在联系的轨道物体的列表.
   * 
   * @return 关系列表
   */
  public List<Socialtie<E>> getrel();

  /**
   * 添加与中心点存在联系的轨道物体.
   * 
   * @param object 需要添加联系的轨道物体
   */
  public void addconnection(E object, Number intimacy);
}


