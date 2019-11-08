package track;



/**
 * 实现接口Track的具体类，用于表示椭圆轨道
 * @author junbaba
 *
 */
public class OvalTrack implements Track {

	protected final Number a;
	protected final Number b;
	
	// Abstraction function:
    // a对应椭圆长轴，b对应椭圆短轴.
	
	// Representation invariant:
	// a >= 0 , b>=0 , a > b
	
	
	// Safety from rep exposure:
	// 通过protected使其它非子类中无法得知本类中的rep.
	
	
	// TODO checkRep
    public void checkRep()
    {
    	assert a.doubleValue() >= 0;
    	assert b.doubleValue() >= 0;
    	assert a.doubleValue() > b.doubleValue();
    }
	
    
	// TODO constructor
	public OvalTrack(Number a , Number b)
	{
		this.a = a;
		this.b = b;
	}
	
	@Override
	public boolean equals(Track obj) {
		if(this.a.doubleValue() == obj.geta().doubleValue() && this.b.doubleValue() == obj.getb().doubleValue())
			return true;
		return false;
	}

	@Override
	public Number getridus() 
	{
		Number c = (a.doubleValue() + b.doubleValue())/2;
		return c;
	}

	@Override
	public Number geta() {
		return a;
	}

	@Override
	public Number getb() {
		return b;
	}

}
