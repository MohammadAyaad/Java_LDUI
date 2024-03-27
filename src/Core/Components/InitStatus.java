package Core.Components;

public enum InitStatus {
	OK(0x00000001),
	BAD(0x00000002),
	BAD_OK(0x00000003),
	Error(0x80000000),
	Critical(0x40000000 | 0x80000000);

	int i;
	InitStatus(int i) {
		this.i = i;
	}
	public String toString()
	{
		String Status = "";
		if((i & Critical.i) > 0) Status += " Critical ";
		if((i & Error.i) > 0) Status += " Error ";
		if((i & BAD.i) > 0) Status += " BAD ";
		if((i & OK.i) > 0) Status += " OK ";
		return Status;
	}
	public int getCode()
	{
		return i;
	}
	
}
