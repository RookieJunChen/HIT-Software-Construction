package physicalObject;



/**
 * immutable的可复用类接口CentralObject，用于模拟轨道上。
 * @author junbaba
 *
 */
public interface PhysicalObject 
{
	/**
	 * 创建一个实现PhysicalObject接口且应用于Q2的对象的静态工厂方法
	 * 
	 * @param 行星名name
	 * @param 行星形态form
	 * @param 行星颜色color
	 * @param 行星半径planetr
	 * @param 行星轨道半径orbitr
	 * @param 行星公转速度v
	 * @param 行星公转角度angle
	 * @param 行星公转方向direction
	 * @return 实现PhysicalObject接口且应用于Q2的对象
	 */
	public static PhysicalObject Q2creator(String name , String form , String color , Number planetr , 
			Number v , String direction)
	{
		return new Q2_Planet(name, form, color, planetr,  v,  direction);
	}
	
	
	/**
	 * 创建一个实现PhysicalObject接口且应用于Q3的对象的静态工厂方法
	 * @return 实现PhysicalObject接口且应用于Q3的对象
	 */
	public static PhysicalObject Q3creator()
	{
		return new Q3_Electronics();
	}
	
	
	/**
	 * 创建一个实现PhysicalObject接口且应用于Q5的对象的静态工厂方法
	 * 
	 * @param 名字name
	 * @param 性别sex
	 * @param 年龄ages
	 * @return 实现PhysicalObject接口且应用于Q5的对象
	 */
	public static PhysicalObject Q5creator(String name, String sex , Number ages)
	{
		return new Q5_Friend(name, sex, ages);
	}
	
	
	/**
	 * 得到该物体名称。
	 * @return 该物体名称。
	 */
	public String getname() ;
	
	
	/**
	 * 得到该物体的形态（针对Q2）
	 * @return 该物体的形态
	 */
	public String getform();
	
	
	/**
	 * 得到该物体的颜色（针对Q2）
	 * @return 该物体颜色
	 */
	public String getcolor();
	
	
	/**
	 * 得到该物体行星半径（针对Q2）
	 * @return 该物体行星半径
	 */
	public Number getplanetr();
	

	
	/**
	 * 得到该物体的公转速度（针对Q2）
	 * @return 该物体的公转速度
	 */
	public Number getv();
	
	

	
	
	
	/**
	 * 得到该物体的公转方向（针对Q2）
	 * @return 该物体的公转方向
	 */
	public String getdirection();
	
	
	
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
	
	
	/**
	 * 新增判断是否相等的方法
	 * @param obj
	 * @return 与自己定义相同符合返回true,否则返回false
	 */
	boolean equals(PhysicalObject obj);
	
}
