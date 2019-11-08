package SocialNetworkCircleExceptions;

public class SocialTieLogicalException extends Exception
{
	private String o;
	
	public SocialTieLogicalException(String o) {
		this.o = o;
	}
	
	public String showwrong() 
	{
		StringBuilder str = new StringBuilder();
		str.append(o + " is not defined in Friend and CentralUser.");
		return str.toString();
	}
}
