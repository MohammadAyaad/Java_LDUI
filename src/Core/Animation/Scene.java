package Core.Animation;

import Core.Components.StructuredComponent;
import Core.Renderer.ComponentRenderContext.ComponentRenderContext;

public abstract class Scene {
	protected float sceneLength,fps;
	protected int width,height;
	protected StructuredComponent root;
	protected String name;
	public Scene(String name) {
		this.name = name;
	}
	public void InitializeComponents(StructuredComponent root) {
		this.root = root;
	}
	public void EventBegin(int width,int height,float fps) {
		this.width = width;
		this.height = height;
		this.fps = fps;
	}
	public void EventTick(double deltaTime,double frameTime,long frameIndex,double sceneFrameTime,double sceneFrameIndex) {
		System.out.println("[Film]: Rendering FI: " + frameIndex + " , FT: " + frameTime + " , DT: " + deltaTime + " , PER: " + (frameTime / this.sceneLength * 100) + "%");
	}
	
	public static double slowinout(double x,double k) 
	{
		double xk = Math.pow(x, k);
		double kx = Math.pow(1 - x,k);
		return xk / (xk + kx);
	}
	public static int CenterOffset(int length,int offset) {
		return (-length/2) + offset;
	}
	public static double clamp(double val, double min, double max) {
	    return Math.max(min, Math.min(max, val));
	}
 }
