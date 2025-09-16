package Core.Components.UIElements;

import java.util.ArrayList;
import java.util.List;

import Core.Components.*;
import Core.Graphics.Color.Color4b;
import Core.Graphics.Color.Color4f;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;

public class StarsBackground extends VisualComponent {

	public StarsBackground(Point2i position, Point2i dimensions,List<BaseComponent> children,int numStars, float spread, float speed,float distance,int antiAnaliasingLimit,float fps) {
		super(position, dimensions,children);
		init(numStars,spread,speed,distance,antiAnaliasingLimit,fps,dimensions);
	}
	
	@Override
	public void EventTick(double delta) {
		updateAndRender(delta);
	}
	
	
	private float spread; // Distance between stars
    public float speed; // Speed at which stars move towards the camera
    public float distance; // Minimum distance of stars from the camera
    public float fps;
    
    private float[] starXPositions;
    private float[] starYPositions;
    private float[] starZPositions;
    private float[] starZPositionsExtra;
    private Color4b[] starColors;
    
    private int antiAnaliasingLimit; // Number of frames to store for anti-aliasing
    private int frameLoop; // Current frame index for anti-aliasing

    private void init(int numStars, float spread, float speed, float distance,int antiAnaliasingLimit,float fps, Point2i dimensions) {
    	this.spread = spread;
        this.speed = speed;
        this.fps = fps;
        this.antiAnaliasingLimit = antiAnaliasingLimit;
        starXPositions = new float[numStars];
        starYPositions = new float[numStars];
        starZPositions = new float[numStars];
        starZPositionsExtra = new float[numStars];
        starColors = new Color4b[numStars];
        for(int i = 0;i < starXPositions.length;i++)
        {
            initStar(i);
        }
    }

    private void initStar(int index) {
        starXPositions[index] = 2 * (float)(Math.random() - 0.5f) * spread;
        starYPositions[index] = 2 * (float)(Math.random() - 0.5f) * spread;
        starZPositions[index] = (float)(Math.random() + distance) * spread;
        starZPositionsExtra[index] = starZPositions[index];
        starColors[index] = new Color4b((byte)255,(byte)(Math.random()*255),(byte)(Math.random()*255),(byte)(Math.random()*255));
    }


    public void updateAndRender(double d) {
    	
        final float TanHalFOV = (float)Math.tan(Math.toRadians(70.0/2.0));
        this.getTarget().Fill((byte)0,(byte)0, (byte)0);
        float halfwidth = this.getTarget().getWidth() / 2.0f;
        float halfhight = this.getTarget().getHeight() / 2.0f;

        System.arraycopy(starZPositions, 0, starZPositionsExtra, 0, starZPositions.length);
        for(int i = 0;i< starXPositions.length;i++)
        {
            starZPositions[i] -= d * speed;
            
            if(starZPositions[i] <=0)
            {
                initStar(i);
            }
            int x = (int)((starXPositions[i]/(starZPositionsExtra[i] * TanHalFOV)) * halfwidth + halfwidth);
            int y = (int)((starYPositions[i]/(starZPositionsExtra[i] * TanHalFOV)) * halfhight + halfhight);
            
            if((x < 0 || x >= this.getTarget().getWidth())||(y<0|| y >= this.getTarget().getHeight()))
            {
                initStar(i);
            }
        }
        for(int f = 0; f < antiAnaliasingLimit;f++) {
            double delta = 1/fps;
        
        for(int i = 0;i< starXPositions.length;i++)
        {
        	starZPositionsExtra[i] -= delta * speed;
           
            
            int x = (int)((starXPositions[i]/(starZPositionsExtra[i] * TanHalFOV)) * halfwidth + halfwidth);
            int y = (int)((starYPositions[i]/(starZPositionsExtra[i] * TanHalFOV)) * halfhight + halfhight);
            
            if((x < 0 || x >= this.getTarget().getWidth())||(y<0|| y >= this.getTarget().getHeight()))
            {
                //initStar(i);
            }
            else
            {
            	Color4b p = this.getTarget().getPixel(x, y);
            	
            	this.getTarget().DrawPixel(x, y, starColors[i].alpha, (byte)(starColors[i].red + p.red), (byte)(starColors[i].green + p.green), (byte)(starColors[i].blue + p.blue));
            }
        }
        }
    }

    public void createNewStar() {
        float[] xPositions = new float[starXPositions.length + 1];
        float[] yPositions = new float[starYPositions.length + 1];
        float[] zPositions = new float[starZPositions.length + 1];
        float[] zPositionsX = new float[starZPositions.length + 1];
        
        System.arraycopy(starXPositions, 0, xPositions, 0, starXPositions.length);
        System.arraycopy(starYPositions, 0, yPositions, 0, starYPositions.length);
        System.arraycopy(starZPositions, 0, zPositions, 0, starZPositions.length);
        System.arraycopy(starZPositionsExtra, 0, zPositionsX, 0, starZPositionsExtra.length);
        
        starXPositions = xPositions;
        starYPositions = yPositions;
        starZPositions = zPositions;
        starZPositionsExtra = zPositionsX;
        
        initStar(xPositions.length - 1);
    }

    public void createStars(int amount) {
        for (int i = 0; i < amount; i++) {
            createNewStar();
        }
    }

    // Getters and Setters
    public float getSpread() {
        return spread;
    }

    public void setSpread(float spread) {
        this.spread = spread;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getantiAnaliasingLimit() {
        return this.antiAnaliasingLimit;
    }

    public void setantiAnaliasingLimit(int antiAnaliasingLimit) {
        this.antiAnaliasingLimit = antiAnaliasingLimit;
    }

    public int getFrameLoop() {
        return frameLoop;
    }

    public void setFrameLoop(int frameLoop) {
        this.frameLoop = frameLoop;
    }

}
