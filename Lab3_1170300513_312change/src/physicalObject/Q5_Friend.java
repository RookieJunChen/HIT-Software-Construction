package physicalObject;



/**
 * 一个PhysicalObject接口的具体实现，用于SocialNetworkCircle中
 * @author junbaba
 *
 */
public class Q5_Friend implements PhysicalObject 
{
	
	private final String sex;
	private final String name;
	private final Number ages;
	
	
	// Abstraction function:
	// sex对应性别，name对应名字，ages对应年龄。
	
	// Representation invariant:
	// sex != null
	// name != null
	// ages > 0
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	// TODO checkRep
    public void checkRep()
    {
    	assert name != null;
    	assert sex != null;
    	assert ages.intValue() > 0;

    }
	
	
	// TODO constructor
	public Q5_Friend(String name , String sex , Number ages)
	{
		this.name = name;
		this.sex = sex;
		this.ages = ages;
	}
	
	@Override
	public String getname() 
	{
		return name;
	}
	
	
	@Override
	public String getsex()
	{
		return sex;
	}
	
	
	@Override
	public Number getages()
	{
		checkRep();
		return ages;
	}


	@Override
	public String getform() {
		throw new UnsupportedOperationException();
	}


	@Override
	public String getcolor() {
		throw new UnsupportedOperationException();
	}


	@Override
	public Number getplanetr() {
		throw new UnsupportedOperationException();
	}





	@Override
	public Number getv() {
		throw new UnsupportedOperationException();
	}





	@Override
	public String getdirection() {
		throw new UnsupportedOperationException();
	}


	@Override
	public boolean equals(PhysicalObject obj) {
		if(this.ages.intValue() == obj.getages().intValue() && this.sex.equals(obj.getsex()) && this.name.equals(obj.getname()))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<" + name + ": " + sex + "," + ages.intValue() + "years>");
		return str.toString();
	}
}
