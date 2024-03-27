package Core.Graphics.Image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Core.Graphics.Color.Color4b;
import Core.Render.Bitmaps.BitmapARGB;
import Core.Renderer.TextRenderer.TextRenderer;

public class Image {
	public BitmapARGB bitmap;
	public Image(BitmapARGB bitmap)
	{
		this.bitmap = new BitmapARGB(bitmap);
	}
	public static Image fromFile(String s) throws IOException
	{
		return new Image(new BitmapARGB(s));
	}
	public static Image solidColor(int width,int height,Color4b color)
	{
		BitmapARGB r = new BitmapARGB(width,height);
		r.Fill(color.alpha, color.red, color.green, color.blue);
		return new Image(r);
	}
	public static Image Iterrated(int width,int height)
	{
		BitmapARGB r = new BitmapARGB(width,height);
		r.Fill((byte)0, (byte)0, (byte)0);
		for(int i = 0;i < width * height;i++)
		{
			r.getBuffer()[i * 4 + 1] = (byte)(i & 0x000000FF);
			r.getBuffer()[i * 4 + 2] = (byte)((i & 0x0000FF00) >> 8);
			r.getBuffer()[i * 4 + 3] = (byte)((i & 0x00FF0000) >> 16);
		}
		return new Image(r);
		
	}
	
	public static Image fromText(String text)
	{
		BitmapARGB bitmap = new BitmapARGB(800,600);
		TextRenderer.DrawText(text,Color.white, bitmap);
		return new Image(bitmap);
	}
}
