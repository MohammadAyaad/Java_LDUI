package Core.Main;

import Core.Debug.Debug;
import Core.Graphics.Color.Color3b;
import Core.Graphics.Color.Color4b;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Math.Vectors.Vector2f;
import Core.Renderer.ComponentRenderContext.ComponentRenderContext;
import Core.Services.VideoCreator;

import java.awt.Font;
import java.awt.List;
import java.awt.MouseInfo;
import java.util.Arrays;
import java.util.LinkedList;

import Core.Animation.Film;
import Core.Animation.Scene;
import Core.Components.BaseComponent;
import Core.Components.RenderMode;
import Core.Components.StructuredComponent;
import Core.Components.UIElements.*;
import Core.Graphics.Image.Image;
import Core.Window.Window;
import Scenes.*;

public class Program {
	private static Window window;
	private static ComponentRenderContext windowTarget;
	private static ComponentRenderContext target;
	private static StructuredComponent root;
	private static VideoCreator video;
	private static int renderWidth = 800, renderHeight = 600,
			windowWidth = 800, windowHeight = 600;
	private static float renderFps = 30.0f;
	private static Film film;
	
	public static void main(String[] args) throws Exception 
	{
		initialize();
		
		Debug.println("Program Started");

		System.in.read();
		
		exit(0);
	}
	
	public static void initialize() throws Exception
	{
		Debug.DebugEnabled = true;
		Debug.println("initializing");
		target = new ComponentRenderContext(renderWidth,renderHeight);
		windowTarget = new ComponentRenderContext(windowWidth,windowHeight);
		window = new Window(target,"Preview from " + renderWidth + "x" + renderHeight + " to " + windowWidth + "x" + windowHeight);
		//video = new VideoCreator("D:\\renderout\\r0.mp4",renderWidth,renderHeight,renderFps);
		//video.CreateStream();

		root = new StructuredComponent(new Point2i(0,0),new Point2i(renderWidth,renderHeight),new LinkedList<BaseComponent>());
		
		InitializeComponents(root);
		
		DrawOut();
		
		FrameLoop();
		
		Debug.println("Program initialized");
	}
	
	public static void InitializeComponents(StructuredComponent root) {
		//test = new ImageBox(new Point2i(0,0),new LinkedList<BaseComponent>(),Image.fromText("Why!"));
		//Point2i position, Point2i dimensions,List<BaseComponent> children,int numStars, float spread, float speed,float distance,int antiAnaliasingLimit,float fps
		LinkedList<Scene> scenes = new LinkedList();
		//scenes.add(new IntroScene("Intro Scene"));
		scenes.add(new ProjectsScene("First Scene"));
		//scenes.add(new OutroScene("Outro Scene"));
		film = new Film("First Film",scenes);
		film.InitializeComponents(root);
		film.EventBegin(renderWidth, renderHeight, renderFps);
	}
	//fi = frame index, t = time
	public static void FrameLoop() throws InterruptedException {
	    long tf = 10000;
	    boolean limitFpsToRender = false;
	    long fi = 0;
	    double constDelta = 1.0d / renderFps;
	    double t = 0;

	    long pt = System.nanoTime();
	    while (Thread.currentThread().isAlive()) {
	        long ct = System.nanoTime();
	        long deltanano = (ct - pt);
	        double DeltaTime = (double) (deltanano / 1_000_000_000.0);
	        pt = ct;
	        System.out.println("[RENDER] DELTA: " + DeltaTime + ", FI: " + fi + ", OFPS: " + (1.0f / constDelta) + ", RFPS: " + (1 / DeltaTime));
	        
	        EventTick(constDelta, t, fi);
	        DrawOut();
	        
	        if (fi == tf) { 
	            exit(0);
	        }
	        fi++;
	        t += constDelta;
	        
	        // FPS Limiter
	        long frameTimeNano = System.nanoTime() - ct;
	        long targetFrameTimeNano = (long)(1_000_000_000.0 / renderFps);
	        long sleepTimeNano = targetFrameTimeNano - frameTimeNano;
	        
	        if (sleepTimeNano > 0) {
	            // Convert nanoseconds to milliseconds for Thread.sleep
	            long sleepTimeMs = sleepTimeNano / 1_000_000;
	            int sleepTimeNs = (int)(sleepTimeNano % 1_000_000);
	            
	            Thread.sleep(sleepTimeMs, sleepTimeNs);
	        }
	    }
	}
	
	private static void DrawOut() {
		target.Fill((byte)0, (byte)0, (byte)0);
		target.RenderComponent(root);
		windowTarget.DrawBitmapInRegion(new Point2i(0,0), new Rectangle2Di(new Point2i(0,0),new Point2i(windowWidth,windowHeight)), target);
		//video.AddFrameWithCorrection(target.ToBufferedImage());
		window.SwapBuffers();
	}
	
	public static void EventTick(double delta,double t,long fi) {
		film.EventTick(delta, t, fi, t, fi);
	}
	
	public static double expEaseIn(double x) {
	    return Math.pow(x,2); 
	}

	
	public static void exit(int code)
	{
		//video.Close();
		Debug.println("Program Exit with status code " + code);
		System.exit(code);
	}

}
