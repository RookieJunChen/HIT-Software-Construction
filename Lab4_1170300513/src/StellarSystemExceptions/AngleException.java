package StellarSystemExceptions;

public class AngleException extends Exception{

	private Number angle;
	
	public AngleException(Number angle)
	{
		this.angle = angle;
	}
	
	public Number getangle()
	{
		return angle;
	}
}
