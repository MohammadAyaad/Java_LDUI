package Core.Render.Bitmaps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import Core.Graphics.Color.Color4b;

public class BitmapARGB {
	/** The width, in pixels, of the image */
	protected int  width;
	/** The height, in pixels, of the image */
	protected int  height;
	/** Every pixel component in the image */
	protected byte buffer[];

	/**
	 * 
	 * @param index
	 * @return red , green or blue component of the buffer
	 */
	public byte getComponent(int index) { return buffer[index]; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public byte[] getBuffer() {return this.buffer;}

	/**
	 * Creates and initializes a Bitmap.
	 *
	 * @param width The width, in pixels, of the image.
	 * @param height The height, in pixels, of the image.
	 */
	public BitmapARGB(int width, int height)
	{
		this.width      = width;
		this.height     = height;
		this.buffer = new byte[width * height * 4];
	}
	public BitmapARGB(BitmapARGB bitmap)
	{
		this.width      = bitmap.width;
		this.height     = bitmap.height;
		this.buffer = new byte[width * height * 4];
		for(int i = 0;i < width * height * 4;i++)
			this.buffer[i] = bitmap.buffer[i];
	}

	public BitmapARGB(String fileName) throws IOException
	{
		int width = 0;
		int height = 0;
		byte[] components = null;

		BufferedImage image = ImageIO.read(new File(fileName));

		width = image.getWidth();
		height = image.getHeight();

		int imgPixels[] = new int[width * height];
		image.getRGB(0, 0, width, height, imgPixels, 0, width);
		components = new byte[width * height * 4];

		for(int i = 0; i < width * height; i++)
		{
			int pixel = imgPixels[i];

			components[i * 4]     = (byte)((pixel >> 24) & 0xFF); // A
			components[i * 4 + 1] = (byte)((pixel      ) & 0xFF); // B
			components[i * 4 + 2] = (byte)((pixel >> 8 ) & 0xFF); // G
			components[i * 4 + 3] = (byte)((pixel >> 16) & 0xFF); // R
		}

		this.width = width;
		this.height = height;
		this.buffer = components;
	}
	
	public BitmapARGB(BufferedImage image)
	{
		int width = 0;
		int height = 0;
		byte[] components = null;

		width = image.getWidth();
		height = image.getHeight();

		int imgPixels[] = new int[width * height];
		image.getRGB(0, 0, width, height, imgPixels, 0, width);
		components = new byte[width * height * 4];

		for(int i = 0; i < width * height; i++)
		{
			int pixel = imgPixels[i];

			components[i * 4]     = (byte)((pixel >> 24) & 0xFF); // A
			components[i * 4 + 1] = (byte)((pixel      ) & 0xFF); // B
			components[i * 4 + 2] = (byte)((pixel >> 8 ) & 0xFF); // G
			components[i * 4 + 3] = (byte)((pixel >> 16) & 0xFF); // R
		}

		this.width = width;
		this.height = height;
		this.buffer = components;
	}
	
	public Color4b getPixel(int x,int y)
	{
		int index = (x + y * this.width) * 4;
		return new Color4b(this.buffer[index + 3],this.buffer[index],this.buffer[index + 1],this.buffer[index + 2]);
	}
	public byte getPixelComponent(int x,int y, int component)
	{
		return this.buffer[((x + y * this.width) * 4) + component];
	}
	/**
	 * Sets the pixel at (x, y) to the color specified by (a,b,g,r).
	 *
	 * @param x Pixel location in X
	 * @param y Pixel location in Y
	 * @param a Alpha component
	 * @param b Blue component
	 * @param g Green component
	 * @param r Red component
	 */
	public void DrawPixel(int x, int y, byte a, byte b, byte g, byte r)
	{
		int index = (x + y * this.width) * 4;
		this.buffer[index    ] = a;
		this.buffer[index + 1] = b;
		this.buffer[index + 2] = g;
		this.buffer[index + 3] = r;
	}

	public void CopyPixel(int destX, int destY, int srcX, int srcY, BitmapARGB src, float lightAmt)
	{
		int destIndex = (destX + destY * this.width) * 4;
		int srcIndex = (srcX + srcY * src.width) * 4;
		
		this.buffer[destIndex    ] = (byte)((src.getComponent(srcIndex) & 0xFF) * lightAmt);
		this.buffer[destIndex + 1] = (byte)((src.getComponent(srcIndex + 1) & 0xFF) * lightAmt);
		this.buffer[destIndex + 2] = (byte)((src.getComponent(srcIndex + 2) & 0xFF) * lightAmt);
		this.buffer[destIndex + 3] = (byte)((src.getComponent(srcIndex + 3) & 0xFF) * lightAmt);
	}

	/**
	 * Copies the Bitmap into a BGR byte array.
	 *
	 * @param dest The byte array to copy into.
	 */
	public void CopyToByteArray(byte[] dest)
	{
		for(int i = 0; i < this.width * this.height; i++)
		{
			dest[i * 3    ] = this.buffer[i * 4 + 1];
			dest[i * 3 + 1] = this.buffer[i * 4 + 2];
			dest[i * 3 + 2] = this.buffer[i * 4 + 3];
		}
	}
	
	public void Fill(byte alpha,byte red,byte green,byte blue)
	{
		for(int p = 0;p < this.width * this.height;p++)
		{
			this.buffer[p * 4    ] = alpha;
			this.buffer[p * 4 + 1] = red;
			this.buffer[p * 4 + 2] = green;
			this.buffer[p * 4 + 3] = blue;
		}
	}
	public void Fill(byte red,byte green,byte blue)
	{
		for(int p = 0;p < this.width * this.height;p++)
		{
			this.buffer[p * 4 + 1] = red;
			this.buffer[p * 4 + 2] = green;
			this.buffer[p * 4 + 3] = blue;
		}
	}
	
	public void FillChannel(int channel,byte value)
	{
		for(int p = 0;p < this.width * this.height;p++)
		{
			this.buffer[p * 4 + channel] = value;
		}
	}
	
	
	
	public void DrawBitmap(int x, int y, BitmapARGB bitmap)
	{
		int WIDTH = 0;
    	int HEIGHT = 0;
    	int bitmapextraX = 0;
    	int bitmapextraY = 0;
    	if(x < 0)
    	{
    		if(x * -1 > bitmap.width)
    		{
    			return;
    		}
    		bitmapextraX = x * -1;
    	}
    	if(y < 0)
    	{
    		if(y * -1 > bitmap.height)
    		{
    			return;
    		}
    		bitmapextraY = y * -1;
    	}
    	if((x + bitmap.width) > this.width) {
    		WIDTH = bitmap.width - (x + bitmap.width - this.width);
    	}
    	else {
    		WIDTH = bitmap.width;
    	}
    	if((y + bitmap.height) > this.height) {
    		HEIGHT = bitmap.height - (y + bitmap.height - this.height);
    	}
    	else {
    		HEIGHT = bitmap.height;
    	}
    	
    	for(int Y = y + bitmapextraY;Y < HEIGHT + y;Y++)
    	{
    		for(int X = x + bitmapextraX;X < WIDTH + x;X++)
    		{
    			int index = (X + (Y * this.width)) * 4;
    			int tindex = (((X + bitmapextraX) - (x + bitmapextraX)) + (((Y + bitmapextraY) - (y + bitmapextraY)) * bitmap.width)) * 4;
    			buffer[index    ] = bitmap.buffer[tindex    ];
    			buffer[index + 1] = bitmap.buffer[tindex + 1];
    			buffer[index + 2] = bitmap.buffer[tindex + 2];
    			buffer[index + 3] = bitmap.buffer[tindex + 3];
    			
    		}
    	}
	}
	public void DrawBufferedImage(int x, int y, BufferedImage img)
	{
		int WIDTH = 0;
    	int HEIGHT = 0;
    	int bitmapextraX = 0;
    	int bitmapextraY = 0;
    	if(x < 0)
    	{
    		if(x * -1 > img.getWidth())
    		{
    			return;
    		}
    		bitmapextraX = x * -1;
    	}
    	if(y < 0)
    	{
    		if(y * -1 > img.getHeight())
    		{
    			return;
    		}
    		bitmapextraY = y * -1;
    	}
    	if((x + img.getWidth()) > this.width) {
    		WIDTH = img.getWidth() - (x + img.getWidth() - this.width);
    	}
    	else {
    		WIDTH = img.getWidth();
    	}
    	if((y + img.getHeight()) > this.height) {
    		HEIGHT = img.getHeight() - (y + img.getHeight() - this.height);
    	}
    	else {
    		HEIGHT = img.getHeight();
    	}
    	
    	for(int Y = y + bitmapextraY;Y < HEIGHT + y;Y++)
    	{
    		for(int X = x + bitmapextraX;X < WIDTH + x;X++)
    		{
    			int index = (X + (Y * this.width)) * 4;
    			int tindex = (((X + bitmapextraX) - (x + bitmapextraX)) + (((Y + bitmapextraY) - (y + bitmapextraY)) * img.getWidth())) * 4;
    			
    			int pixel = img.getRGB(X, Y);
    			
    			buffer[index    ] = (byte)((pixel >> 24) & 0xFF);// A
    			buffer[index + 1] = (byte)((pixel >> 16) & 0xFF);// R
    			buffer[index + 2] = (byte)((pixel >> 8 ) & 0xFF);// G
    			buffer[index + 3] = (byte)((pixel >> 0 ) & 0xFF);// B
    			
    		}
    	}
	}
	
	
}





















