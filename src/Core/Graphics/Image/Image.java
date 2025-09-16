package Core.Graphics.Image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Core.Graphics.Color.Color3b;
import Core.Graphics.Color.Color4b;
import Core.Math.Points.Point2i;
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
	
	public static Image fromText(String text,Color3b textColor,Color4b backgroundColor,Font font, int dpi)
	{
		return new Image(TextRenderer.GenerateText(text, font, new Color(textColor.red & 0xFF,textColor.green& 0xFF,textColor.blue& 0xFF), new Color(backgroundColor.red& 0xFF,backgroundColor.green& 0xFF,backgroundColor.blue& 0xFF,backgroundColor.alpha& 0xFF), dpi));
	}
}
