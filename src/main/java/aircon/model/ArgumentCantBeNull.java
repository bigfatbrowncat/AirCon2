package aircon.model;

public class ArgumentCantBeNull extends Exception 
{
	private String argName;
	public String getArgName()
	{
		return argName;
	}
	public ArgumentCantBeNull(String argName)
	{
		super("Argument " + argName + " can't be null.");
		this.argName = argName;
	}
}
