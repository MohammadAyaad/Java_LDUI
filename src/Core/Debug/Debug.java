package Core.Debug;

public class Debug {
	public static boolean DebugError = false;
	public static boolean DebugEnabled = false;
	private static boolean DebuggerCheck() throws Exception
	{
		if(DebugError) throw new Exception("Debugger Message not removed!");
		return DebugEnabled;
	}
	public static void print(String str) throws Exception 
	{
		if(!DebuggerCheck()) return; 
		System.out.print("[Debug] : " + str);
	}
	public static void print(Object obj) throws Exception 
	{
		if(!DebuggerCheck()) return; 
		System.out.print("[Debug] : " + obj.toString());
	}
	public static void println(String str) throws Exception 
	{
		if(!DebuggerCheck()) return; 
		System.out.println("[Debug] : " + str);
	}
	public static void println(Object obj) throws Exception 
	{
		if(!DebuggerCheck()) return; 
		System.out.println("[Debug] : " + obj.toString());
	}
}
