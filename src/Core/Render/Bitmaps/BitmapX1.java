package Core.Render.Bitmaps;

import Core.SpecialTypes.BitArray;

public class BitmapX1 {
	//a bitmap of single bit values
	BitArray buffer;
	private int width;
	private int height;
	public BitmapX1(int width,int height)
	{
		this.width = width;
		this.height = height;
		this.buffer = new BitArray(width * height);
	}
	public void setPixel(int x,int y,boolean value)
	{
		this.buffer.writeBit(x + y * width, value);
	}
	public boolean getPixel(int x,int y)
	{
		return this.buffer.readBit(x + y * width);
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
}
