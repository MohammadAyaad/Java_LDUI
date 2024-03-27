package Core.Main;

import Core.Debug.Debug;
import Core.Graphics.Color.Color4b;
import Core.Math.Points.Point2i;
import Core.Renderer.ComponentRenderContext.ComponentRenderContext;

import java.awt.List;
import java.awt.MouseInfo;

import Core.Components.RenderMode;
import Core.Components.BaseComponent.StructureComponent.VisualComponent.ImageBox;
import Core.Graphics.Image.Image;
import Core.Window.Window;

public class Program {
	private static Window window;
	private static ComponentRenderContext target;
	private static ImageBox background;
	private static ImageBox b2;
	
	
	public static void main(String[] args) throws Exception 
	{
		
		initialize();
		
		Debug.println("Program Started");

		window.SwapBuffers();
		System.in.read();
		
		exit(0);
	}
	
	
	
	
	
	
	public static void initialize() throws Exception
	{
		Debug.DebugEnabled = true;
		Debug.DebugError = false;
		Debug.println("initializing");
		target = new ComponentRenderContext(800,600);

		ImageBox img = new ImageBox(new Point2i(0,0),new Point2i(800,600));
		
		img.setBackground(Image.fromText("Hello World!"));
		
		target.RenderComponent(img);
		
		window = new Window(target,"Hello World!");
		Thread.sleep(100);
		window.SwapBuffers();
		
		Debug.println("Program initialized");
	}
	
	public static void exit(int code) throws Exception
	{
		Debug.DebugError = false;
		Debug.println("Program Exit with status code " + code);
		System.exit(code);
	}

}
