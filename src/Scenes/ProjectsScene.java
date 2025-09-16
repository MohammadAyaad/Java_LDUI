package Scenes;

import java.io.File;
import java.util.LinkedList;

import Core.Animation.Scene;
import Core.Components.StructuredComponent;
import Core.Components.UIElements.ImageBox;
import Core.Debug.Debug;
import Core.Graphics.Image.Image;
import Core.Math.Points.Point2i;
import Core.Math.Vectors.Vector2f;
import Core.Render.Bitmaps.BitmapARGB;
import Core.Render.Bitmaps.BitmapMask;
import Core.Services.SVGRasterizer;

public class ProjectsScene extends Scene{
	static BitmapARGB l1b;
	static BitmapARGB l2b;
	static BitmapARGB l3b;
	static BitmapARGB l4b;
	static ImageBox l1;
	static ImageBox l2;
	static ImageBox l3;
	static ImageBox l4;
	static Vector2f centerUp;
	static Vector2f center;
		public ProjectsScene(String name) {
		super(name);
		this.sceneLength = 1000;
	}
		public void InitializeComponents(StructuredComponent root) {
			super.InitializeComponents(root);
			
		}
		public void EventBegin(int width,int height,float fps) {
			super.EventBegin(width, height, fps);
			center = new Vector2f((width/2),height/2);
			centerUp = new Vector2f(0,-1);
			
try {
				float scalingFactor = 0.4f;
				l1b = new BitmapARGB("E:\\emf.png");
				BitmapARGB l1bt = new BitmapARGB(l1b);
				BitmapMask mask = new BitmapMask(l1b);
				mask.applyThreshold((byte)128, (byte)255, (byte)0);
				l1bt = mask.ApplyToBitmap(l1bt);
				l1bt = l1bt.scale((float)height/(float)l1bt.getHeight() * scalingFactor);
				l1 = new ImageBox(new Point2i(CenterOffset(l1bt.getWidth(),(width/2)),CenterOffset(l1bt.getHeight(),(height/2))),new LinkedList(),new Image(l1bt));
				
				BitmapARGB logo2 = new BitmapARGB("E:\\sc.png");
				BitmapMask mask2 = new BitmapMask(logo2);
				mask2.applyThreshold((byte)128, (byte)255, (byte)0);
				logo2 = mask2.ApplyToBitmap(logo2);
				logo2 = logo2.scale((float)height/(float)logo2.getHeight() * scalingFactor);
				l2 = new ImageBox(new Point2i(CenterOffset(logo2.getWidth(),(int)center.x),CenterOffset(logo2.getHeight(),(int)center.y)),new LinkedList(),new Image(logo2));
				
				
				BitmapARGB logo3 = new BitmapARGB("E:\\sfc.png");
				BitmapMask mask3 = new BitmapMask(logo3);
				mask3.applyThreshold((byte)128, (byte)255, (byte)0);
				logo3 = mask3.ApplyToBitmap(logo3);
				logo3 = logo3.scale((float)height/(float)logo3.getHeight() * scalingFactor);
				l3 = new ImageBox(new Point2i(CenterOffset(logo3.getWidth(),(width/2)),CenterOffset(logo3.getHeight(),(height/2))),new LinkedList(),new Image(logo3));
				
				
				BitmapARGB logo4 = new BitmapARGB("E:\\csa.png");
				BitmapMask mask4 = new BitmapMask(logo4);
				mask4.applyThreshold((byte)128, (byte)255, (byte)0);
				logo4 = mask4.ApplyToBitmap(logo4);
				logo4 = logo4.scale((float)height/(float)logo4.getHeight() * scalingFactor);
				l4 = new ImageBox(new Point2i(CenterOffset(logo4.getWidth(),(width/2)),CenterOffset(logo4.getHeight(),(height/2))),new LinkedList(),new Image(logo4));
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			root.addChild(l2);
			root.addChild(l3);
			root.addChild(l4);
			root.addChild(l1);
		}
		public void EventTick(double deltaTime,double frameTime,long frameIndex,double sceneFrameTime,double sceneFrameIndex) {
			super.EventTick(deltaTime, frameTime, frameIndex, sceneFrameTime, sceneFrameIndex);
			
			
			float sceneScale = 1.0f;
			Vector2f minBounds = new Vector2f(), maxBounds = new Vector2f();
			
			minBounds.x = Math.min(l1.getPosition().x,
					Math.min(l2.getPosition().x,
					Math.min(l3.getPosition().x,
							l4.getPosition().x)));
			
			minBounds.y = Math.min(l1.getPosition().y,
					Math.min(l2.getPosition().y,
					Math.min(l3.getPosition().y,
							l4.getPosition().y)));
			
			maxBounds.x = Math.max(l1.getPosition().x + l1.getTarget().getWidth(),
					Math.max(l2.getPosition().x + l2.getTarget().getWidth(),
					Math.max(l3.getPosition().x + l3.getTarget().getWidth(),
							l4.getPosition().x) + l4.getTarget().getWidth()));
			
			maxBounds.y = Math.max(l1.getPosition().y + l1.getTarget().getHeight(),
					Math.max(l2.getPosition().y + l2.getTarget().getHeight(),
					Math.max(l3.getPosition().y + l3.getTarget().getHeight(),
							l4.getPosition().y) + l4.getTarget().getHeight()));
			
			Vector2f sceneSize = new Vector2f(maxBounds.x - minBounds.x,maxBounds.y -  minBounds.y);
			sceneScale = this.height / sceneSize.y * 0.9f;
//			
//			BitmapARGB logo1 = new BitmapARGB("E:\\emf.png");
//			BitmapMask mask = new BitmapMask(logo1);
//			mask.applyThreshold((byte)128, (byte)255, (byte)0);
//			logo1 = mask.ApplyToBitmap(logo1);
//			logo1 = logo1.scale((float)height/(float)logo1.getHeight() * scalingFactor);
//			l1 = new ImageBox(new Point2i(CenterOffset(logo1.getWidth(),(width/2)),CenterOffset(logo1.getHeight(),(height/2))),new LinkedList(),new Image(logo1));
//			
//			BitmapARGB logo2 = new BitmapARGB("E:\\sc.png");
//			BitmapMask mask2 = new BitmapMask(logo2);
//			mask2.applyThreshold((byte)128, (byte)255, (byte)0);
//			logo2 = mask2.ApplyToBitmap(logo2);
//			logo2 = logo2.scale((float)height/(float)logo2.getHeight() * scalingFactor);
//			l2 = new ImageBox(new Point2i(CenterOffset(logo2.getWidth(),(int)center.x),CenterOffset(logo2.getHeight(),(int)center.y)),new LinkedList(),new Image(logo2));
//			
//			
//			BitmapARGB logo3 = new BitmapARGB("E:\\sfc.png");
//			BitmapMask mask3 = new BitmapMask(logo3);
//			mask3.applyThreshold((byte)128, (byte)255, (byte)0);
//			logo3 = mask3.ApplyToBitmap(logo3);
//			logo3 = logo3.scale((float)height/(float)logo3.getHeight() * scalingFactor);
//			l3 = new ImageBox(new Point2i(CenterOffset(logo3.getWidth(),(width/2)),CenterOffset(logo3.getHeight(),(height/2))),new LinkedList(),new Image(logo3));
//			
//			
//			BitmapARGB logo4 = new BitmapARGB("E:\\csa.png");
//			BitmapMask mask4 = new BitmapMask(logo4);
//			mask4.applyThreshold((byte)128, (byte)255, (byte)0);
//			logo4 = mask4.ApplyToBitmap(logo4);
//			logo4 = logo4.scale((float)height/(float)logo4.getHeight() * scalingFactor);
//			l4 = new ImageBox(new Point2i(CenterOffset(logo4.getWidth(),(width/2)),CenterOffset(logo4.getHeight(),(height/2))),new LinkedList(),new Image(logo4));
//			
			
			
			center.x = (width/2.0f);
			center.y = height/2;
			Vector2f dirc = new Vector2f(0,0);
			dirc.add(centerUp);
			dirc.mul(-1.0f);
			dirc.mul((float)slowinout((clamp(frameTime,1.0d,2.5d) - 1) / 1.5f, 2) * 100.0f);
			center.add(dirc);
		
			Vector2f dir2 = new Vector2f(0,0);
			dir2.add(centerUp.rotate(60));
			dir2.mul((float)slowinout((clamp(frameTime,1.0d,1.5d) - 1) * 2.0f, 2) * 250.0f);
			dir2.add(new Vector2f(l2.getTarget().getWidth() / -2.0f,l2.getTarget().getHeight() / -2.0f));
			dir2.add(new Vector2f(center));
			l2.getPosition().x = (int)dir2.x;
			l2.getPosition().y = (int)dir2.y;
			
			Vector2f dir3 = new Vector2f(0,0);
			dir3.add(centerUp.rotate(-60));
			dir3.mul((float)slowinout((clamp(frameTime,1.5d,2d) - 1.5f) * 2.0f, 2) * 250.0f);
			dir3.add(new Vector2f(l3.getTarget().getWidth() / -2.0f,l3.getTarget().getHeight() / -2.0f));
			dir3.add(new Vector2f(center));
			l3.getPosition().x = (int)dir3.x;
			l3.getPosition().y = (int)dir3.y;
			
			
			
			Vector2f dir4 = new Vector2f(0,0);
			dir4.add(centerUp.rotate(0));
			dir4.mul((float)slowinout((clamp(frameTime,2.0d,2.5d) - 2) * 2.0f, 2) * 250.0f);
			dir4.add(new Vector2f(l4.getTarget().getWidth() / -2.0f,l4.getTarget().getHeight() / -2.0f));
			dir4.add(new Vector2f(center));
			l4.getPosition().x = (int)dir4.x;
			l4.getPosition().y = (int)dir4.y;
			
			l1.getPosition().x = CenterOffset(l1.getTarget().getWidth(),(int)center.x);
			l1.getPosition().y = CenterOffset(l1.getTarget().getHeight(),(int)center.y);
			
			l1.EventTick(deltaTime);
			l2.EventTick(deltaTime);
			l3.EventTick(deltaTime);
			l4.EventTick(deltaTime);
		}
		
	
}
