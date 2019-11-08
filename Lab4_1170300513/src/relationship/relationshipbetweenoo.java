package relationship;

import java.util.*;


/**
 * mutable的可复用类接口relationshipbetweenoo<E>，用于表示多轨道系统中轨道物体物体和轨道物体的关系。
 * @author junbaba
 *
 * @param 轨道物体的类型<E>
 */
public interface relationshipbetweenoo<E> 
{
	
	/**
	 * 创建一个实现relationshipbetweenoo接口的对象的静态工厂方法
	 * @return 实现 relationshipbetweenoo接口的对象
	 */
	public static <E> relationshipbetweenoo<E>  creator(E object , String choice)
	{
		if(choice.equals("Basics"))
		{
			return new Basicrelationshipbetweenoo<E>(object);
		}
		else 
			throw new UnsupportedOperationException();
	}
	
	
	/**
	 * 返回该轨道物体
	 * @return 轨道物体
	 */
	public E geto();
	
	
	/**
	 * 返回与该轨道物体存在联系的轨道物体的列表
	 * @return 轨道物体的列表
	 */
	public List<Socialtie<E>> getrel();
	
	
	/**
	 * 添加与该轨道物体存在联系的轨道物体
	 * @param 需要添加联系的轨道物体newobejct
	 */
	public void addconnection(E newobejct , Number intimacy);
}




