package centralObject;



/**
 * immutable的可复用类接口CentralObject，用于模拟中心点物体。
 * @author junbaba
 *
 */
public interface CentralObject 
{
	/**
	 * 创建一个实现CentralObject接口且应用于Q2的对象的静态工厂方法
	 * 
	 * @param 恒星名name
	 * @param 恒星半径ridus
	 * @param 恒星质量quality
	 * @return 实现CentralObject接口且应用于Q2的对象
	 */
	public static CentralObject Q2creator(String name , Number ridus , Number quality)
	{
		return new Q2_Suns( name  , ridus ,  quality);
	}
	
	
	/**
	 * 创建一个实现CentralObject接口且应用于Q3的对象的静态工厂方法
	 * 
	 * @param 原子核名name
	 * @param 轨道数orbitnum
	 * @return 实现CentralObject接口且应用于Q3的对象
	 */
	public static CentralObject Q3creator(String name)
	{
		return new Q3_Atomic_nucleus(name);
	}
	
	/**
	 * 创建一个实现CentralObject接口且应用于Q5的对象的静态工厂方法
	 * 
	 * @param 名字name
	 * @param 性别sex
	 * @param 年龄ages
	 * @return 实现CentralObject接口且应用于Q5的对象
	 */
	public static CentralObject Q5creator(String name , String sex , Number ages)
	{
		return new Q5_CentralUser(name, sex, ages);
	}
	
	
	
	/**
	 * 得到该物体名称。
	 * @return 该物体名称。
	 */
	public String getname() ;
	
	/**
	 * 得到该物体的半径（针对Q2）
	 * @return 该物体半径
	 */
	public Number getridus();
	
	/**
	 * 得到该物体质量（针对Q2）
	 * @return 该物体质量
	 */
	public Number getquality();
	
	

	
	
	/**
	 * 得到该物体性别（针对Q5）
	 * @return 该物体性别
	 */
	public String getsex();
	
	
	
	/**
	 * 得到该物体年龄（针对Q5）
	 * @return 该物体年龄
	 */
	public Number getages();

		
	

	
	
}
