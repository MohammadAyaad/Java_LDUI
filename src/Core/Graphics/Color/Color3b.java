package Core.Graphics.Color;

public class Color3b {
	public byte red;
	public byte green;
	public byte blue;
	public Color3b()
	{
		this.red = 0;
		this.green = 0;
		this.blue = 0;
	}
	public Color3b(byte red,byte green,byte blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public void set(byte red,byte green,byte blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
}
