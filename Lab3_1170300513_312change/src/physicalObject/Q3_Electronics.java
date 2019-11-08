package physicalObject;



/**
 * 一个PhysicalObject接口的具体实现，用于AtomStructure中
 * @author junbaba
 *
 */

public class Q3_Electronics implements PhysicalObject {

	private final String name = "Electronics";

	// Abstraction function:
	// name对应名称
	
	// Representation invariant:
	// name != null
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	
	
	// TODO checkRep
	public void checkRep()
	{
		assert name != null;

	}
	
	
	// TODO constructor
	public Q3_Electronics() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String getname() {
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
		return super.equals(obj);
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<" + name + ">");
		return str.toString();
	}
	
}


