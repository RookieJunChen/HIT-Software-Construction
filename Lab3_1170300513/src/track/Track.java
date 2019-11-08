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
	
 
}
