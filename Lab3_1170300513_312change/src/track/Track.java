package track;



/**
 * immutable的ADT，用来模拟轨道。
 * @author junbaba
 *
 */
 public interface Track
{
	/**
	 * 静态工厂方法，创建一个圆轨道
	 * @param 半径ridus
	 * @return 圆轨道
	 */
	 public static Track Roundcreator(Number ridus)
	 {
		 return new RoundTrack(ridus);
	 }
	 
	 
	 /**
	  *  静态工厂方法，创建一个椭圆轨道
	  * @param 椭圆长轴a
	  * @param 椭圆短轴b
	  * @return 椭圆轨道
	  */
	 public static Track Ovalcreator(Number a, Number b)
	 {
		 return new OvalTrack(a, b);
	 }
	 
	 
	 
	 /**
	   * 新增判断是否相等的方法
	   * @param obj
	   * @return 与自己定义相同符合返回true,否则返回false
	   */
	public boolean equals(Track obj);
	 
	 
    /**
     * 提供给clients得知轨道半径的方法
     * @return 轨道半径ridus
     */
	public Number getridus();
	

	/**
	 * 提供给clients得知椭圆长轴a的方法
	 * @return 椭圆长轴a
	 */
	public Number geta();
 
	
	/**
	 * 提供给clients得知椭圆短轴b的方法
	 * @return 椭圆短轴b
	 */
	public Number getb();
}
