package Core.Components.Exceptions;

import Core.Components.InitStatus;

public class ComponentInitializationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1477992161454873330L;
	private InitStatus status;
	public InitStatus getStatus()
	{
		return this.status;
	}
	public String getStatusMessage()
	{
		return "Init State : " + status.toString() + "\nInit Status code : " + status.toString();
	}
	public ComponentInitializationException(InitStatus status)
	{
		
	}
}
