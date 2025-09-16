package Core.Render.Bitmaps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;

public class BitmapRGB {
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
	public BitmapRGB(int width, int height)
	{
		this.width      = width;
		this.height     = height;
		this.buffer = new byte[width * height * 3];
	}
	public BitmapRGB(BitmapRGB copy)
	{
		this.width      = copy.width;
		this.height     = copy.height;
		this.buffer = new byte[width * height * 3];
		for(int i = 0;i < copy.width * copy.height * 3;i++)
			this.buffer[i] = copy.buffer[i];
	}

	public BitmapRGB(String fileName) throws IOException
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

			components[i * 3    ] = (byte)((pixel      ) & 0xFF); // B
			components[i * 3 + 1] = (byte)((pixel >> 8 ) & 0xFF); // G
			components[i * 3 + 2] = (byte)((pixel >> 16) & 0xFF); // R
		}

		this.width = width;
		this.height = height;
		this.buffer = components;
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
		int index = (x + y * this.width) * 3;
		this.buffer[index    ] = a;
		this.buffer[index + 1] = b;
		this.buffer[index + 2] = g;
		this.buffer[index + 3] = r;
	}

	public void CopyPixel(int destX, int destY, int srcX, int srcY, BitmapRGB src, float lightAmt)
	{
		int destIndex = (destX + destY * this.width) * 3;
		int srcIndex = (srcX + srcY * src.width) * 3;
		
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
			dest[i * 3    ] = this.buffer[i * 3 + 2];
			dest[i * 3 + 1] = this.buffer[i * 3 + 1];
			dest[i * 3 + 2] = this.buffer[i * 3    ];
		}
	}
	public BufferedImage ToBufferedImage()
	{
		BufferedImage img = new BufferedImage(this.width, this.height,2/*TYPE_INT_ARGB*/);
	      if(img == null)
	    	  return null;
	      for (int y = 0; y < img.getHeight(); y++) {
	         for (int x = 0; x < img.getWidth(); x++) {
	            //Retrieving contents of a pixel
	            int pixel = 0;
	            //Creating a Color object from pixel value
	            int p = 0;
	            
	            pixel = 0xFF;
	            pixel <<= 8;
	            pixel += this.buffer[(x + (y * this.width)) * 3]& 0xFF;
	            pixel <<= 8;
	            pixel +=  this.buffer[(x + (y * this.width)) * 3 + 1] & 0xFF;
	            pixel <<= 8;
	            pixel += this.buffer[(x + (y * this.width)) * 3 + 2] & 0xFF;
	            img.setRGB(x, y, pixel);
	         }
	      }
	      return img;
	}
	public void Fill(byte red,byte green,byte blue)
	{
		for(int p = 0;p < this.width * this.height;p++)
		{
			this.buffer[p * 3    ] = red;
			this.buffer[p * 3 + 1] = green;
			this.buffer[p * 3 + 2] = blue;
		}
	}
	
	public void FillChannel(int channel,byte value)
	{
		for(int p = 0;p < this.width * this.height;p++)
		{
			this.buffer[p * 3 + channel] = value;
		}
	}
	
	public void DrawBitmap(int X, int Y, BitmapRGB bitmap)
	{
		int MXS = 0,
				MYS = 0,
				SXS = 0,
				SYS = 0,
				MXE = 0,
				MYE = 0,
				SXE = 0,
				SYE = 0;
			if((X > this.width) || (Y > this.height) || ((-X) > bitmap.width) || ((-Y) > bitmap.height)) return;
			if(X < 0)
			{
				MXS = 0;
				SXS = -X;
			}
			if(Y < 0)
			{
				MYS = 0;
				SYS = -Y;
			}
			if((X + bitmap.width) > this.width)
			{
				MXE = this.width;
				SXE = this.width - X;
			}
			if((Y + bitmap.height) > this.height)
			{
				MXE = this.height;
				SXE = this.height - Y;
			}
			for(int y = MYS,y2 = SYS;y < MYE;y++,y2++)
			{
				for(int x = MXS,x2 = SXS;x < MXE;x++,x2++)
				{
					int index0 = (X + x + (y + Y) * this.width) * 3;
					int index1 = (x2 + y2 * bitmap.width) * 3;
					this.buffer[index0    ] = bitmap.buffer[index1    ];
	    			this.buffer[index0 + 1] = bitmap.buffer[index1 + 1];
	    			this.buffer[index0 + 2] = bitmap.buffer[index1 + 2];
				}
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
    			int index = (X + (Y * this.width)) * 3;
    			int tindex = (((X + bitmapextraX) - (x + bitmapextraX)) + (((Y + bitmapextraY) - (y + bitmapextraY)) * bitmap.width)) * 4;

				float alpha = (float)toUnsignedByte(bitmap.buffer[tindex]) / 255.0f;
    			this.buffer[index    ] = (byte)(((float)toUnsignedByte(this.buffer[index    ]) * (1.0f - alpha)) + ((float)toUnsignedByte(bitmap.buffer[tindex + 1]) * alpha));
    			this.buffer[index + 1] = (byte)(((float)toUnsignedByte(this.buffer[index + 1]) * (1.0f - alpha)) + ((float)toUnsignedByte(bitmap.buffer[tindex + 2]) * alpha));
    			this.buffer[index + 2] = (byte)(((float)toUnsignedByte(this.buffer[index + 2]) * (1.0f - alpha)) + ((float)toUnsignedByte(bitmap.buffer[tindex + 3]) * alpha));
    			
    		}
    	}
	}
	/*
	 * not comfirmed
	 * */
	public void DrawPartOfBitmap(Point2i bitmap_position,Point2i bitmap_part_start,Point2i bitmap_part_end,BitmapARGB bitmap)
	{
		Point2i start_src = new Point2i(bitmap_part_start);
		Point2i start_dst = Point2i.add(bitmap_position, bitmap_part_start);
		//check if the start is outside on the top left
		if(start_dst.x < 0) 
		{
			start_src.x -= start_dst.x;
			start_dst.x = 0;
		}
		if(start_dst.y < 0) 
		{
			start_src.y -= start_dst.y;
			start_dst.y = 0;
		}
		//check if the start is outside on the bottom right
		if(start_dst.x > this.width || start_dst.y > this.height) return; //bitmap is outside of the screen
		Point2i end_src = new Point2i(bitmap_part_end);
		Point2i end_dst = Point2i.add(bitmap_position, bitmap_part_end);
		//check if the end is outside on the bottom right
		if(end_dst.x > this.width) 
		{
			end_src.x -= (end_dst.x - this.width);
			end_dst.x = this.width;
		}
		if(end_dst.y < 0) 
		{
			end_src.y -= (end_dst.y - this.height);
			end_dst.y = this.height;
		}
		//check if the start is outside on the bottom right
		if(end_dst.x < 0 || end_dst.y < 0) return; //bitmap is outside of the screen
		//copy the pixels
		for(int dst_y = start_dst.y,src_y = start_src.y;dst_y < end_dst.y && src_y < end_src.y;dst_y++,src_y++)
		{
			for(int dst_x = start_dst.x,src_x = start_src.x;dst_x < end_dst.x && src_x < end_src.x;dst_x++,src_x++)
			{
				//copy the pixel
				int src_i = (src_x + (src_y * bitmap.width)) * 4;
				int dst_i = (dst_x + (dst_y * this.width)) * 3;
				this.buffer[dst_i    ] = bitmap.buffer[src_i + 1];
				this.buffer[dst_i + 1] = bitmap.buffer[src_i + 2];
				this.buffer[dst_i + 2] = bitmap.buffer[src_i + 3];
			}
		}
		
	}
	public void DrawBitmapInRegion(Point2i bitmap_position,Rectangle2Di rect,BitmapARGB bitmap)
	{
		//src/dst+start/end+x/y
		int 
		SSX = 0,
		SSY = 0,
		SEX = bitmap.width,
		SEY = bitmap.height,
		DSX = bitmap_position.x,
		DSY = bitmap_position.y,
		DEX = this.width,
		DEY = this.height;
		//trim to the region & the bounds
		//check if something is outside the drawing area
		//if the initial destenation position is outside this bitmap
		if((((DSX > this.width || DSY > this.height) ||
				(DEX < 0 || DEY < 0)) ||
				//if the source position is outside the source bitmap
				((SSX > bitmap.width || SSY > bitmap.height) ||
				(SEX < 0 || SEY < 0))) ||
				//if the destenation region is outside this bitmap
				((DSX > (rect.getSize().x + rect.getPosition().x) || DSY > (rect.getSize().y + rect.getPosition().y)) ||
				((DEX < rect.getPosition().x || DEY < rect.getPosition().y) ||
						((bitmap_position.x + bitmap.width) < rect.getPosition().x || (bitmap_position.y + bitmap.height) < rect.getPosition().y)))) 
				{
					return;
				}
		if(DSX < 0)
		{
			SSX = -DSX;
			DSX = 0;
		}
		if(DSX < rect.getPosition().x)
		{
			SSX += (rect.getPosition().x - DSX);
			DSX += (rect.getPosition().x - DSX);
		}
		if(DSY < 0)
		{
			SSY = -DSY;
			DSY = 0;
		}
		if(DSY < rect.getPosition().y)
		{
			SSY += (rect.getPosition().y - DSY);
			DSY += (rect.getPosition().y - DSY);
		}
		//DSX,DSY,SSX,SSY ready
		if(DEX > this.width)
		{
			SEX = (DEX - this.width);
			DEX = this.width;
		}
		if(DEX > (rect.getPosition().x + rect.getSize().x))
		{
			int Deff = DEX - (rect.getPosition().x + rect.getSize().x);
			SEX -= Deff;
			DEX -= Deff;
		}
		if(DEY > this.height)
		{
			SEY = (DEY - this.height);
			DEY = this.height;
		}
		if(DEY > (rect.getPosition().y + rect.getSize().y))
		{
			int Deff = DEY - (rect.getPosition().y + rect.getSize().y);
			SEY -= Deff;
			DEY -= Deff;
		}
		
		
		
		/*System.out.println(
				"POX="+bitmap_position.x+
				"POY="+bitmap_position.y+
				"SSX=" + SSX +
				"SSY=" + SSY +
				"SEX=" + SEX +
				"SEY=" + SEY +
				"DSX=" + DSX +
				"DSY=" + DSY +
				"DEX=" + DEX +
				"DEY=" + DEY +
				"OSX=" + (((DEX < SEX)?DEX:SEX) - ((DSX > SSX)?DSX:SSX)) +
				"OSY=" + (((DEY < SEY)?DEY:SEY) - ((DSY > SSY)?DSY:SSY))
				);*/
		//all ready
		for(int dst_y = DSY,src_y = SSY;dst_y < DEY && src_y < SEY;dst_y++,src_y++)
		{
			for(int dst_x = DSX,src_x = SSX;dst_x < DEX && src_x < SEX;dst_x++,src_x++)
			{
				int dst_i = (dst_x + (dst_y * this.width)) * 3;
				int src_i = (src_x + (src_y * bitmap.width)) * 4;
				float alpha = (float)toUnsignedByte(bitmap.buffer[src_i]) / 255.0f;
    			this.buffer[dst_i    ] = (byte)(((float)toUnsignedByte(this.buffer[dst_i    ]) * (1.0f - alpha)) + ((float)toUnsignedByte(bitmap.buffer[src_i + 1]) * alpha));
    			this.buffer[dst_i + 1] = (byte)(((float)toUnsignedByte(this.buffer[dst_i + 1]) * (1.0f - alpha)) + ((float)toUnsignedByte(bitmap.buffer[src_i + 2]) * alpha));
    			this.buffer[dst_i + 2] = (byte)(((float)toUnsignedByte(this.buffer[dst_i + 2]) * (1.0f - alpha)) + ((float)toUnsignedByte(bitmap.buffer[src_i + 3]) * alpha));
			}
		}
		
		
	}
	private int toUnsignedByte(byte x) {
		return x & 0xFF;
	}
	public void DrawBitmapInRegion(Point2i bitmap_position,Rectangle2Di rect,BitmapRGB bitmap)
	{
		//src/dst+start/end+x/y
		int 
		SSX = 0,
		SSY = 0,
		SEX = bitmap.width,
		SEY = bitmap.height,
		DSX = bitmap_position.x,
		DSY = bitmap_position.y,
		DEX = this.width,
		DEY = this.height;
		//trim to the region & the bounds
		//check if something is outside the drawing area
		//if the initial destenation position is outside this bitmap
		if((((DSX > this.width || DSY > this.height) ||
				(DEX < 0 || DEY < 0)) ||
				//if the source position is outside the source bitmap
				((SSX > bitmap.width || SSY > bitmap.height) ||
				(SEX < 0 || SEY < 0))) ||
				//if the destenation region is outside this bitmap
				((DSX > (rect.getSize().x + rect.getPosition().x) || DSY > (rect.getSize().y + rect.getPosition().y)) ||
				((DEX < rect.getPosition().x || DEY < rect.getPosition().y) ||
						((bitmap_position.x + bitmap.width) < rect.getPosition().x || (bitmap_position.y + bitmap.height) < rect.getPosition().y)))) 
				{
					return;
				}
		if(DSX < 0)
		{
			SSX = -DSX;
			DSX = 0;
		}
		if(DSX < rect.getPosition().x)
		{
			SSX += (rect.getPosition().x - DSX);
			DSX += (rect.getPosition().x - DSX);
		}
		if(DSY < 0)
		{
			SSY = -DSY;
			DSY = 0;
		}
		if(DSY < rect.getPosition().y)
		{
			SSY += (rect.getPosition().y - DSY);
			DSY += (rect.getPosition().y - DSY);
		}
		//DSX,DSY,SSX,SSY ready
		if(DEX > this.width)
		{
			SEX = (DEX - this.width);
			DEX = this.width;
		}
		if(DEX > (rect.getPosition().x + rect.getSize().x))
		{
			int Deff = DEX - (rect.getPosition().x + rect.getSize().x);
			SEX -= Deff;
			DEX -= Deff;
		}
		if(DEY > this.height)
		{
			SEY = (DEY - this.height);
			DEY = this.height;
		}
		if(DEY > (rect.getPosition().y + rect.getSize().y))
		{
			int Deff = DEY - (rect.getPosition().y + rect.getSize().y);
			SEY -= Deff;
			DEY -= Deff;
		}
		
		
		
		/*System.out.println(
				"POX="+bitmap_position.x+
				"POY="+bitmap_position.y+
				"SSX=" + SSX +
				"SSY=" + SSY +
				"SEX=" + SEX +
				"SEY=" + SEY +
				"DSX=" + DSX +
				"DSY=" + DSY +
				"DEX=" + DEX +
				"DEY=" + DEY +
				"OSX=" + (((DEX < SEX)?DEX:SEX) - ((DSX > SSX)?DSX:SSX)) +
				"OSY=" + (((DEY < SEY)?DEY:SEY) - ((DSY > SSY)?DSY:SSY))
				);*/
		//all ready
		for(int dst_y = DSY,src_y = SSY;dst_y < DEY && src_y < SEY;dst_y++,src_y++)
		{
			for(int dst_x = DSX,src_x = SSX;dst_x < DEX && src_x < SEX;dst_x++,src_x++)
			{
				int dst_i = (dst_x + (dst_y * this.width)) * 3;
				int src_i = (src_x + (src_y * bitmap.width)) * 3;
				this.buffer[dst_i    ] = bitmap.buffer[src_i    ];
				this.buffer[dst_i + 1] = bitmap.buffer[src_i + 1];
				this.buffer[dst_i + 2] = bitmap.buffer[src_i + 2];
			}
		}
		
		
	}
}
