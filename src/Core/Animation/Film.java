package Core.Animation;

import java.util.LinkedList;
import java.util.List;

import Core.Components.StructuredComponent;
import Core.Main.Program;

public class Film extends Scene {
	private List<Scene> Scenes;
	private int curScene = -1;
	public Film(String name,List<Scene> scenes) {
		super(name);
		this.Scenes = scenes;
		for(int i = 0; i < scenes.size();i++) {
			this.sceneLength += scenes.get(i).sceneLength;
		}
	}
	@Override
	public void InitializeComponents(StructuredComponent root) {
		super.InitializeComponents(root);
		System.out.println("[Film: "+this.name+"]: ROOT INIT");
	}
	@Override
	public void EventBegin(int width,int height,float fps) {
		super.EventBegin(width, height, fps);
	}
	@Override
	public void EventTick(double deltaTime,double frameTime,long frameIndex,double sceneFrameTime,double sceneFrameIndex) {
		System.out.println("[Film: "+this.name+"]: Rendering FI: " + frameIndex + " , FT: " + frameTime + " , DT: " + deltaTime + " , PER: " + (frameTime / this.sceneLength * 100) + "%");
		double sceneStartTime = 0;
		for(int i = 0;i < this.Scenes.size();i ++) {
			double curSceneTime = (frameTime - sceneStartTime);
			System.out.println("curSceneTime:" + curSceneTime);
			if(curSceneTime > this.Scenes.get(i).sceneLength) {
				sceneStartTime += this.Scenes.get(i).sceneLength;
				continue;
			}
			else if(curSceneTime >= 0.0d && curSceneTime <= this.Scenes.get(i).sceneLength) {
				if(curScene != i) {
					root.getChildren().clear();
					this.Scenes.get(i).InitializeComponents(root);
					this.Scenes.get(i).EventBegin(this.width, this.height, this.fps);
					curScene = i;
				}
				double st = frameTime - sceneStartTime;
				double fi = frameIndex - (sceneStartTime * this.fps);
				this.Scenes.get(i).EventTick(deltaTime, frameTime, frameIndex, st, fi);
				return;
			}
			else {
				//Dead States, Should never be reached
				System.out.println("[EXCEPTION] Dead State Reached!!!!");
				System.out.println("[EXCEPTION] i: " + i + " , curSceneTime:" + curSceneTime);
				Program.exit(-1);
			}

		}
		System.out.println("Film Rendered Successfully!");
		Program.exit(0);
		
	}
}
