package circularOrbit;


import java.io.IOException;
import java.util.List;

import Exceptions.ReadFileFailException;
import Exceptions.TheSameTagRException;
import assist.Position;
import relationship.relationshipbetweenco;
import relationship.relationshipbetweenoo;
import thinginTrack.ThinginTrack;
import track.Track;



/**
 * mutable的可复用类接口CircularOrbit<L,E>，用于模拟多轨道系统。
 * @author junbaba
 *
 * @param 多轨道系统的中心点物体类型<L>
 * @param 轨道物体的类型<E>
 */
public interface CircularOrbit<L,E> 
{
	
	/**
	 * 创建一个实现 CircularOrbit接口的对象的静态工厂方法
	 * @return 实现 CircularOrbit接口的对象
	 */
	public static <L,E> CircularOrbit<L,E> empty(String choice)
	{
		if(choice.equals("Q2"))
			return new StellarSystem<L,E>();
		else if(choice.equals("Q3"))
			return new AtomStructure<L,E>();
		else if(choice.equals("Q5"))
			return new SocialNetworkCircle<L,E>();
		else
			throw new UnsupportedOperationException();
	}
	
	
	/**
	 * 增加一条轨道
	 * @param 需要增加的新的一条轨道neworbit
	 */
	public void addorbit(Track neworbit) throws TheSameTagRException;

	
	/**
	 * 移除一条轨道
	 * @param 需要被移除的轨道deletedorbit
	 * @return 删除成功返回true，失败则返回false
	 */
	public void removeorbit(Track deletedorbit);
	
	
	/**
	 * 增加中心点物体
	 * @param 需要增加的物体object
	 * @return 添加成功返回true，否则返回false
	 */
	public boolean addcenterobject(L object);
	
	
	/**
	 * 向特定轨道上添加一个物体
	 * @param 被添加的物体object
	 * @param 特定轨道orbit
	 */
	public void addtoorbit(E object , Track orbit) throws TheSameTagRException;
	
	
	/**
	 * 增加中心点物体和一个轨道物体之间的关系
	 * @param 中心物体c
	 * @param 轨道物体object
	 * @param 亲密度intimacy
	 */
	public void addcontactco(L c , E object , Number intimacy);
	
	
	/**
	 * 删除中心点物体和一个轨道物体之间的关系
	 * @param 中心物体c
	 * @param 轨道物体object
	 */
	public void deletecontactco(L c , E object);
	
	
	/**
	 * 增加两个轨道物体之间的关系
	 * @param 轨道物体object1
	 * @param 轨道物体object2
	 */
	public void addcontactoo(E object1 , E object2 , Number intimacy);
	
	/**
	 * 删除两个轨道物体之间的关系
	 * @param 轨道物体object1
	 * @param 轨道物体object2
	 */
	public void deletecontactoo(E object1 , E object2);
	
	/**
	 * 从外部文件读取数据构造轨道系统对象
	 * @param 外部文件文件名filename
	 * @throws IOException
	 */
	public void readfile(String filename) throws IOException , ReadFileFailException; 
	
	
	/**
	 * 将 object 从当前所在轨道迁移到轨道 t：
	 * @param 轨道物体object
	 * @param 目标轨道t
	 */
	public void transit (E object, Track t);
	
	
	/**
	 * 寻找两轨道物体间的最短逻辑距离
	 * @param 轨道物体e1
	 * @param 轨道物体e2
	 * @return 如果e1与e2之间能相连，则返回其最短逻辑距离，否则返回-1.
	 */
	public int getlogicdistance(E e1 , E e2);

	
	/**
	 * 改变某个物体的轨道角度
	 * @param object
	 * @param sitha
	 */
	public void move(E object, double sitha);
	
	
	/**
	 * 计算两个物体间的物理距离
	 * @param e1
	 * @param e2
	 * @return 两个物体间的物理距离
	 */
	public double getphysicaldistance(E e1 , E e2);
	
	
	/**
	 *  提供给客户查看轨道系统中轨道与其上物体的一个方法
	 * @return 包含轨道及其上物体的一个数组（不可改动）
	 */
	public List<ThinginTrack<E>> getorbits();
	
	
	/**
	 * 从轨道系统中删掉某个轨道物体
	 * @param obj
	 */
	public void deleteorbitobj(E obj);
	
	
	/**
	 * 从轨道系统中删掉某个中心物体
	 * @param obj
	 */
	public void deletecentralobj(L obj);
	
	
	/**
	 * 用于判断轨道系统是否合法（针对StellarSystem）
	 * @return 合法返回true，不合法则返回false
	 */
	public boolean islegal();
	
	
	/**
	 * 计算某物体与轨道中心物体间的距离（针对Social Network Circle）
	 * @param 物体e
	 * @return 物体e与轨道中心物体间的距离
	 */
	public double getdistancefromco(E e);
	
	
	/**
	 * 提供给客户端轨道中心物体的方法
	 * @return 轨道中心物体
	 */
	public L getcenter();
	
	/**
	 * 计算输出 t 时刻各行星的位置（针对StellarSystem）
	 * @param 时刻t
	 * @param 选择choice，如为y或Y则打印，否则不打印
	 */
	public void getvpositions(Number t , String choice);
	
	/**
	 * 计算信息扩散度的方法（针对Social Network Circle）
	 * @param 好友friend
	 * @return 通过friend能认识到的朋友
	 */
	public int getinfodiffu(E friend);
	
	
	/**
	 * 提供给客户查询某物体位置的方法
	 * @param 物体obj
	 * @return 表示物体obj位置的Position类
	 */
	public Position<E> getpositions(E obj);
	
	
	/**
	 * 提供给客户已知的轨道物体间关系列表（只能查看无法修改）
	 * @return
	 */
	public List<relationshipbetweenoo<E>> getrelbetweenoo();
	
	
	/**
	 * 提供给客户已知的中心物体与轨道物体间的关系列表（只能查看无法修改）
	 * @return
	 */
	public List<relationshipbetweenco<L, E>> getrelbetweenco();
}
