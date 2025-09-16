package Core.Debug;

public class Debug {
	//public static boolean DebugError = false;
	public static boolean DebugEnabled = false;
	private static boolean DebuggerCheck()
	{
		//if(DebugError) throw new Exception("Debugger Message not removed!");
		return DebugEnabled;
	}
	public static void print(String str)
	{
		if(!DebuggerCheck()) return; 
		System.out.print("[Debug] : " + str);
	}
	public static void print(Object obj)
	{
		if(!DebuggerCheck()) return; 
		System.out.print("[Debug] : " + obj.toString());
	}
	public static void println(String str)
	{
		if(!DebuggerCheck()) return; 
		System.out.println("[Debug] : " + str);
	}
	public static void println(Object obj)
	{
		if(!DebuggerCheck()) return; 
		System.out.println("[Debug] : " + obj.toString());
	}
}
