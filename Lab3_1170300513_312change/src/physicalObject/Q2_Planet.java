package physicalObject;


/**
 * 一个PhysicalObject接口的具体实现，用于StellarSystem中
 * @author junbaba
 *
 */
public class Q2_Planet implements PhysicalObject {


	private final String name;
	private final String form;
	private final String color;
	private final Number planetr;
	//private final Number orbitr;
	private final Number v;
	//private final Number angle;
	private final String direction;
	
	// Abstraction function:
	// name对应行星名称，form对应行星形态，color对应行星颜色，planetr对应行星半径，
	// orbitr对应轨道半径，v对应行星公转速度，angle对应行星角度，direction对应行星公转方向
	
	// Representation invariant:
	// name != null
	// form != null
	// color != null
	// planetr > 0 , v > 0 
	// direction != null
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	// TODO checkRep
	public void checkRep()
	{
		assert name != null;
	    assert form != null;
		assert color != null;
		assert planetr.doubleValue() > 0;
		//assert orbitr.doubleValue() > 0;
		assert v.doubleValue() > 0;
		//assert (angle.doubleValue() > 0) && (angle.doubleValue() < 360);
		assert direction != null;

	}

	
	// TODO constructor
	public Q2_Planet(String name , String form , String color , Number planetr , 
			 Number v ,  String direction )
	 {
		 this.name = name;
		 this.form = form;
		 this.color = color;
		 this.planetr = planetr;
		// this.orbitr = orbitr;
		 this.v = v;
		 //this.angle = angle;
		 this.direction = direction;
	 }
	
	@Override
	public String getform()
	{
		return form;
	}
	
	@Override
	public String getcolor()
	{
		return color;
	}
	
	@Override
	public Number getplanetr()
	{
		checkRep();
		return planetr;
	}
	
	
	@Override
	public Number getv()
	{
		checkRep();
		return v;
	}
	

	
	@Override
	public String getdirection()
	{
		return direction;
	}
	
	@Override
	public String getname() 
	{
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
	public boolean equals(PhysicalObject obj)
	{
		if(this.name.equals(obj.getname()) && this.form.equals(obj.getform()) && this.color.equals(obj.getcolor())
				&& this.direction.equals(obj.getdirection()) && this.planetr.doubleValue() == obj.getplanetr().doubleValue())
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<" + name + ": " + form + "," + color + "," + planetr.doubleValue() + "km," + v.doubleValue() + "km/s," + direction + ">");
		return str.toString();
	}

}
