package centralObject;

import physicalObject.PhysicalObject;

/**
 * 一个CentralObject接口的具体实现，用于StellarSystem中
 * @author junbaba
 *
 */
public class Q2_Suns implements CentralObject {

	private final String name;
	private final Number ridus;
	private final Number quality;
	
	// Abstraction function:
	// name对应恒星名称，ridus对应恒星半径，quality对应恒星质量
	
	// Representation invariant:
	// name != null
	// ridus > 0
	// quality > 0
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	// TODO checkRep
	public void checkRep()
	{
		assert name != null;
		assert ridus.doubleValue() > 0;
		assert quality.doubleValue() > 0;

	}
	
	// TODO constructor
	public Q2_Suns(String name , Number ridus , Number quality) 
	{
		this.name = name;
		this.ridus = ridus;
		this.quality = quality;
	}
	
	@Override
	public Number getridus()
	{
		checkRep();
		return ridus;
	}
	
	@Override
	public Number getquality()
	{
		checkRep();
		return quality;
	}
	
	@Override
	public String getname() 
	{
		checkRep();
		return name;
	}

	@Override
	public String getsex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Number getages() {
		throw new UnsupportedOperationException();
	}


	@Override
	public boolean equals(Object obje) {
		if(obje == null)
			return false;
		CentralObject obj = (CentralObject)obje;
		if(this.name.equals(obj.getname()) && this.quality.doubleValue() == obj.getquality().doubleValue() &&
				this.ridus.doubleValue() == obj.getridus().doubleValue())
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<" + name + ": " + ridus.doubleValue() + "km," + quality.doubleValue() + "kg>" );
		return str.toString();
	}
	
	@Override
	public int hashCode() {
		return name.length() + quality.intValue() + ridus.intValue(); 
	}

}
