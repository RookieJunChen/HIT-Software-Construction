package thinginTrack;

import java.util.List;

import track.Track;

/**
 * mutable的可复用类接口ThinginTrack<E>，用于模拟轨道与轨道上物体的关系。
 * @author junbaba
 *
 * @param 轨道物体的类型<E>
 */
public interface ThinginTrack<E> 
{
	
	public static <E> ThinginTrack<E> creator(Track track , String choice)
	{
		if(choice.equals("Basics"))
			return new BasicThinginTrack<>(track);
		else
			throw new UnsupportedOperationException();
	}
	
	
	/**
	 * 得到该关系中轨道Track的方法
	 * @return 轨道Track
	 */
	public Track gettrack();
	
	/**
	 * 向该轨道上添加物体
	 * @param 物体e
	 */
	public void addobject(E e);
	
	
	/**
	 * 删除位于该轨道上的一个物体
	 * @param e
	 */
	public void deleteobject(E e);
	
	
	/**
	 * 得到该轨道上所有物体的构成的列表
	 * @return 所以物体构成的列表
	 */
	public List<E> getthingsintrack();
}
