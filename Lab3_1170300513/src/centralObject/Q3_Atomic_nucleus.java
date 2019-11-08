package centralObject;

/**
 * 一个CentralObject接口的具体实现，用于AtomStructure中
 * @author junbaba
 *
 */
public class Q3_Atomic_nucleus implements CentralObject {

	
	private final String name;

	
	// Abstraction function:
	// name对应元素名称，orbitnum对应该元素轨道数目。
	
	// Representation invariant:
	// name != null
	// orbitnum > 0
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	// TODO checkRep
	public void checkRep()
	{
		assert name != null;


	}
	
	// TODO constructor
	public Q3_Atomic_nucleus(String name )
	{
		this.name = name;
	}
	
	
	@Override
	public String getname() {
		return name;
	}
	


	
	@Override
	 public String getsex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Number getages()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Number getridus() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Number getquality() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(CentralObject obj) {
		if(this.name.equals(obj.getname()))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<" + name + ">");
		return str.toString();
	}

}
