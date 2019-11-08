package circularorbit;

import assist.Position;
import exceptions.ReadFileFailException;
import exceptions.TheSameTagRException;
import java.io.IOException;
import java.util.List;
import relationship.RelationshipBetweenco;
import relationship.RelationshipBetweenoo;
import thingintrack.ThinginTrack;
import track.Track;



/**
 * mutable的可复用类接口CircularOrbit，用于模拟多轨道系统.
 * 
 * @author junbaba
 *
 * @param <L> 多轨道系统的中心点物体类型
 * @param <E> 轨道物体的类型
 */
public interface CircularOrbit<L, E> {
  /**
   * 创建一个实现 CircularOrbit接口的对象的静态工厂方法.
   * 
   * @param choice 选择
   * @return boolean
   */
  public static <L, E> CircularOrbit<L, E> empty(String choice) {
    if (choice.equals("Q2")) {
      return new StellarSystem<L, E>();
    } else if (choice.equals("Q3")) {
      return new AtomStructure<L, E>();
    } else if (choice.equals("Q5")) {
      return new SocialNetworkCircle<L, E>();
    } else {
      throw new UnsupportedOperationException();
    }
  }


  /**
   * 增加一条轨道.
   * 
   * @param neworbit 需要增加的新的一条轨道
   */
  public void addorbit(Track neworbit) throws TheSameTagRException;


  /**
   * 移除一条轨道.
   * 
   * @param deletedorbit 需要被移除的轨道
   */
  public void removeorbit(Track deletedorbit);


  /**
   * 增加中心点物体.
   * 
   * @param object 需要增加的物体
   * @return 添加成功返回true，否则返回false
   */
  public boolean addcenterobject(L object);


  /**
   * 向特定轨道上添加一个物体.
   * 
   * @param object 被添加的物体
   * @param orbit 特定轨道
   */
  public void addtoorbit(E object, Track orbit) throws TheSameTagRException;


  /**
   * 增加中心点物体和一个轨道物体之间的关系.
   * 
   * @param c 中心物体
   * @param object 轨道物体
   * @param intimacy 亲密度
   */
  public void addcontactco(L c, E object, Number intimacy);


  /**
   * 删除中心点物体和一个轨道物体之间的关系.
   * 
   * @param c 中心物体
   * @param object 轨道物体
   */
  public void deletecontactco(L c, E object);


  /**
   * 增加两个轨道物体之间的关系.
   * 
   * @param object1 轨道物体
   * @param object2 轨道物体
   */
  public void addcontactoo(E object1, E object2, Number intimacy);

  /**
   * 删除两个轨道物体之间的关系.
   * 
   * @param object1 轨道物体
   * @param object2 轨道物体
   */
  public void deletecontactoo(E object1, E object2);

  /**
   * 从外部文件读取数据构造轨道系统对象.
   * 
   * @param filename 外部文件文件名
   * @throws IOException if has error
   */
  public void readfile(String filename, int num) throws IOException, ReadFileFailException;


  /**
   * 将轨道系统内容写入文件中.
   * 
   * @throws IOException if has error
   */
  public void writefile(int num) throws IOException;

  /**
   * 将 object 从当前所在轨道迁移到轨道 t.
   * 
   * @param object 轨道物体
   * @param t 目标轨道
   */
  public void transit(E object, Track t);


  /**
   * 寻找两轨道物体间的最短逻辑距离.
   * 
   * @param e1 轨道物体
   * @param e2 轨道物体
   * @return 如果e1与e2之间能相连，则返回其最短逻辑距离，否则返回-1.
   */
  public int getlogicdistance(E e1, E e2);


  /**
   * 改变某个物体的轨道角度.
   * 
   * @param object 物体
   * @param sitha 角度
   */
  public void move(E object, double sitha);


  /**
   * 计算两个物体间的物理距离.
   * 
   * @param e1 轨道物体
   * @param e2 轨道物体
   * @return 两个物体间的物理距离
   */
  public double getphysicaldistance(E e1, E e2);


  /**
   * 提供给客户查看轨道系统中轨道与其上物体的一个方法.
   * 
   * @return 包含轨道及其上物体的一个数组（不可改动）
   */
  public List<ThinginTrack<E>> getorbits();


  /**
   * 从轨道系统中删掉某个轨道物体.
   * 
   * @param obj 轨道物体
   */
  public void deleteorbitobj(E obj);


  /**
   * 从轨道系统中删掉某个中心物体.
   * 
   * @param obj 中心物体
   */
  public void deletecentralobj(L obj);


  /**
   * 用于判断轨道系统是否合法（针对StellarSystem）.
   * 
   * @return 合法返回true，不合法则返回false
   */
  public boolean islegal();


  /**
   * 计算某物体与轨道中心物体间的距离（针对Social Network Circle）.
   * 
   * @param e 物体
   * @return 物体e与轨道中心物体间的距离
   */
  public double getdistancefromco(E e);


  /**
   * 提供给客户端轨道中心物体的方法.
   * 
   * @return 轨道中心物体
   */
  public L getcenter();

  /**
   * 计算输出 t 时刻各行星的位置（针对StellarSystem）.
   * 
   * @param t 时刻
   * @param choice 选择，如为y或Y则打印，否则不打印
   */
  public void getvpositions(Number t, String choice);

  /**
   * 计算信息扩散度的方法（针对Social Network Circle）.
   * 
   * @param friend 好友
   * @return 通过friend能认识到的朋友
   */
  public int getinfodiffu(E friend);


  /**
   * 提供给客户查询某物体位置的方法.
   * 
   * @param obj 物体
   * @return 表示物体obj位置的Position类
   */
  public Position<E> getpositions(E obj);


  /**
   * 提供给客户已知的轨道物体间关系列表（只能查看无法修改）.
   * 
   * @return
   */
  public List<RelationshipBetweenoo<E>> getrelbetweenoo();


  /**
   * 提供给客户已知的中心物体与轨道物体间的关系列表（只能查看无法修改）.
   * 
   */
  public List<RelationshipBetweenco<L, E>> getrelbetweenco();
}
