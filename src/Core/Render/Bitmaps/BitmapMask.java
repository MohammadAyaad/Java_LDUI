package Core.Render.Bitmaps;

import Core.Graphics.Color.Color4b;

public class BitmapMask {
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
		public BitmapMask(int width, int height)
		{
			this.width      = width;
			this.height     = height;
			this.buffer = new byte[width * height];
		}
		public BitmapMask(BitmapMask bitmap)
		{
			this.width      = bitmap.width;
			this.height     = bitmap.height;
			this.buffer = new byte[width * height];
			for(int i = 0;i < width * height;i++)
				this.buffer[i] = bitmap.buffer[i];
		}
		public BitmapMask(BitmapARGB bitmap)
		{
			this.width      = bitmap.width;
			this.height     = bitmap.height;
			this.buffer = new byte[width * height];
			for(int i = 0;i < width * height;i++)
				this.buffer[i] = bitmap.buffer[i * 4];
		}
		public static BitmapMask ColorIntoMask(Color4b color, BitmapARGB bitmap) {
			BitmapMask mask = new BitmapMask(bitmap.width,bitmap.height);
			Color4b pixel = new Color4b();
			for(int y = 0;y < mask.height;y++) {
				for(int x = 0;x < mask.width;x++) {
					pixel = bitmap.getPixel(x, y);
					if(pixel.EqualsColor(color)) {
						mask.buffer[(mask.width * y) + x] = (byte)255;
					}
					else {
						mask.buffer[(mask.width * y) + x] = (byte)0;
						
					}
				}
			}
			return mask;
		}
		public BitmapARGB ApplyToBitmap(BitmapARGB bitmap) {
			BitmapARGB result = new BitmapARGB(bitmap);
			Color4b pixel = new Color4b();
			for(int y = 0;y < result.height;y++) {
				for(int x = 0;x < result.width;x++) {
					pixel = bitmap.getPixel(x, y);
					result.DrawPixel(x, y, this.getPixel(x, y), pixel.red, pixel.green, pixel.blue);
				}
			}
			return result;
		}

		public byte getPixel(int x,int y)
		{
			int index = (x + y * this.width);
			return this.buffer[index];
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
		public void DrawPixel(int x, int y, byte a)
		{
			int index = (x + y * this.width);
			this.buffer[index    ] = a;
		}


		
		public void Fill(byte alpha)
		{
			for(int p = 0;p < this.width * this.height;p++)
			{
				this.buffer[p] = alpha;
			}
		}
		/**
		 * Applies a threshold to the mask - values above threshold become a, values below or equal become b
		 * @param threshold The threshold value (0-255)
		 * @param a Value to set for pixels above threshold
		 * @param b Value to set for pixels below or equal to threshold
		 */
		public void applyThreshold(byte threshold, byte a, byte b) {
		    for (int i = 0; i < this.width * this.height; i++) {
		        if ((this.buffer[i] & 0xFF) > (threshold & 0xFF)) {
		            this.buffer[i] = a;
		        } else {
		            this.buffer[i] = b;
		        }
		    }
		}
		
		
		public void DrawBitmap_NOTREADY(int x, int y, BitmapARGB bitmap)
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
		public BitmapARGB Cut_NOTREADY(int x, int y, int w, int h) {
		    // Ensure the requested area is within the bounds of the source image
		    int startX = Math.max(0, x);
		    int startY = Math.max(0, y);
		    int endX = Math.min(this.width, x + w);
		    int endY = Math.min(this.height, y + h);
		    
		    // Calculate actual dimensions of the output image
		    int outputWidth = endX - startX;
		    int outputHeight = endY - startY;
		    
		    // Create the output bitmap
		    BitmapARGB output = new BitmapARGB(outputWidth, outputHeight);
		    
		    // Copy the relevant pixels from the source to the output
		    for (int destY = 0; destY < outputHeight; destY++) {
		        for (int destX = 0; destX < outputWidth; destX++) {
		            int srcX = startX + destX;
		            int srcY = startY + destY;
		            
		            // Calculate indices in both source and destination buffers
		            int srcIndex = (srcX + srcY * this.width) * 4;
		            int destIndex = (destX + destY * outputWidth) * 4;
		            
		            // Copy all 4 components (ARGB)
		            output.buffer[destIndex] = this.buffer[srcIndex];         // Alpha
		            output.buffer[destIndex + 1] = this.buffer[srcIndex + 1]; // Blue
		            output.buffer[destIndex + 2] = this.buffer[srcIndex + 2]; // Green
		            output.buffer[destIndex + 3] = this.buffer[srcIndex + 3]; // Red
		        }
		    }
		    
		    return output;
		}
}
