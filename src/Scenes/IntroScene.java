package Scenes;

import java.awt.Font;
import java.util.LinkedList;

import Core.Animation.Scene;
import Core.Components.BaseComponent;
import Core.Components.StructuredComponent;
import Core.Components.UIElements.CircleDrawer;
import Core.Components.UIElements.CircleDrawingDirection;
import Core.Components.UIElements.ImageBox;
import Core.Components.UIElements.StarsBackground;
import Core.Graphics.Color.Color3b;
import Core.Graphics.Color.Color4b;
import Core.Graphics.Image.Image;
import Core.Math.Points.Point2i;
import Core.Math.Vectors.Vector2f;
import Core.Render.Bitmaps.*;

public class IntroScene extends Scene {
	
	static Image nameimg;
	static ImageBox name;
	static StarsBackground stars;
	static CircleDrawer circle;
	
	public IntroScene(String name) {
		super(name);
		this.sceneLength = 4;
	}
	@Override
	public void InitializeComponents(StructuredComponent root) {
		super.InitializeComponents(root);

	}
	@Override
	public void EventBegin(int Width,int Height,float renderFps) {
		super.EventBegin(Width, Height, renderFps);
		stars = new StarsBackground(
				new Point2i(0,0),
				new Point2i(this.width,this.height),
				new LinkedList<BaseComponent>(),
				4096,
				0.3f,
				0.1f,
				1f,
				10,
				3000
				);
		circle = new CircleDrawer(
				new Point2i(0,0),
				new Point2i(this.width,this.height), 
				new LinkedList<BaseComponent>(), 
				new Vector2f(this.width/2,this.height/2),
				this.height * 0.15f,
				3, 
				0,
				this.height * 0.008f, 
				1.0f,
				0.0f,
				CircleDrawingDirection.Clockwise,
				new Color4b((byte)255,(byte)255,(byte)255,(byte)255),
				new Color4b((byte)255,(byte)0,(byte)0,(byte)0),
				new Color4b((byte)0,(byte)0,(byte)0,(byte)0)
				);
		nameimg = Image.fromText("Light Dragon",new Color3b((byte)255,(byte)255,(byte)255),new Color4b((byte)0,(byte)0,(byte)0,(byte)0),new Font("Good Timing Rg",Font.BOLD,this.height / 40),200);
		name = new ImageBox(new Point2i((nameimg.bitmap.getWidth() / -2) + (int)(this.width * 0.49f),(nameimg.bitmap.getHeight() / -2) + (int)(this.height * 0.76f)),new Point2i(nameimg.bitmap.getWidth(),nameimg.bitmap.getHeight()),new LinkedList<BaseComponent>());
		
		root.addChild(name);
		root.addChild(circle);
		//root.addChild(stars);
	}
	@Override
	public void EventTick(double deltaTime, double frameTime, long frameIndex,double sceneFrameTime,double sceneFrameIndex) {
		//test.getPosition().x =(int)frameIndex;
		
		
				stars.EventTick(deltaTime);
				if(sceneFrameIndex > 0 ) {
					//stars.setSpeed(frameIndex * 0.00003f);
					//stars.setantiAnaliasingLimit((int)(frameIndex / 100f) + 1);
				}
				else {
					stars.setSpeed((float)(10000 - sceneFrameIndex) * 0.000003f);
					stars.setantiAnaliasingLimit((int)((10000 - sceneFrameIndex) / 100f) + 1);
				}
				if(sceneFrameTime >= 0 && sceneFrameTime <= 0.5f) {
					circle.outerCompleteness = (float)slowinout(sceneFrameTime * 2.0d,2);
					circle.innerCompleteness = (float)slowinout(sceneFrameTime * 2.0d,2);					circle.outerCompleteness = (float)slowinout(sceneFrameTime * 2.0d,2);
					circle.startingAngle = (float)slowinout(sceneFrameTime * 2.0d,2) * 45.0f;
					circle.EventTick(deltaTime);
					
					name.setImage(new Image(nameimg.bitmap.Cut(0, 0, (int)((float)nameimg.bitmap.getWidth() * slowinout(sceneFrameTime * 2,2)), nameimg.bitmap.getHeight())));
					
					
				}
				if(sceneFrameTime >= 1.5f && sceneFrameTime <= 2.0f) {
					circle.outerCompleteness = (float)slowinout((2 - sceneFrameTime)  * 2.0f,2);
					circle.startingAngle = (float)slowinout((2 - sceneFrameTime) * 2.0f,2) * 45.0f;
					circle.radius = (this.height * 0.15f) + (float)(slowinout((sceneFrameTime - 1.5f) * 2.0f,2) * this.height / 1.5f);
					circle.EventTick(deltaTime);
					name.getTarget().Fill((byte)0,(byte)0,(byte)0, (byte)0);
					name.setImage(new Image(nameimg.bitmap.Cut(0, 0, (int)((float)nameimg.bitmap.getWidth() * slowinout((2.0f - sceneFrameTime) * 2.0f,2)), nameimg.bitmap.getHeight())));
					
				}
				BitmapMask innermask = BitmapMask.ColorIntoMask(new Color4b((byte)255,(byte)255,(byte)0,(byte)0), circle.getTarget());
				stars.getTarget().DrawBitmap(0, 0, innermask.ApplyToBitmap(stars.getTarget()));
				//circle.completeness = (float)((Math.sin(frameIndex / 100.0f) + 1.0f) / 2.0f);
				//System.out.println("COM:" + circle.completeness);
	}

}
