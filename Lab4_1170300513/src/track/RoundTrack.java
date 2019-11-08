package track;

public class RoundTrack implements Track
{
	// Abstraction function:
    // ridus对应轨道的半径.
	
	// Representation invariant:
	// ridus >= 0
	// objects != null
	
	// Safety from rep exposure:
	// 通过protected使其它非子类中无法得知本类中的rep.
	
	protected final Number ridus;
	
	
	// TODO constructor
	public RoundTrack(Number ridus)
	{
		this.ridus = ridus;
	}
	
	// TODO checkRep
    public void checkRep()
    {
    	assert ridus.doubleValue() >= 0;
    }
	
    @Override
	public Number getridus()
	{
		return ridus;
	}

	@Override
	public boolean equals(Object obje) {
		if(obje == null)
			return false;
		RoundTrack obj = (RoundTrack)obje;
		if(this.ridus.doubleValue() == obj.getridus().doubleValue())
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<Track : " + this.ridus +">");
		return str.toString();
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ridus.intValue();
	}
}
