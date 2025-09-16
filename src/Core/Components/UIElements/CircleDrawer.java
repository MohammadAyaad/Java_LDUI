package Core.Components.UIElements;

import java.util.List;

import Core.Components.BaseComponent;
import Core.Components.VisualComponent;
import Core.Graphics.Color.Color4b;
import Core.Math.Points.*;
import Core.Math.Vectors.*;

public class CircleDrawer extends VisualComponent {
	public float radius, startingAngle, ringThiccness,innerCompleteness,outerCompleteness;
	public int numOfPoints;
	public Color4b ringColor, innerColor,backgroundColor;
	public Vector2f circleCenter;
	CircleDrawingDirection direction;
	public CircleDrawer(Point2i position, Point2i dimensions,List<BaseComponent> children,Vector2f circleCenter,float radius,int numberOfPoints,float startingAngle,float ringThiccness,float innerCompleteness,float outerCompleteness,CircleDrawingDirection dir,Color4b ringColor,Color4b innerColor,Color4b backgroundColor) {
		super(position, dimensions,children);
		this.circleCenter = circleCenter;
		this.radius = radius;
		this.numOfPoints = numberOfPoints;
		this.direction = dir;
		this.startingAngle = startingAngle;
		this.ringThiccness = ringThiccness;
		this.ringColor = ringColor;
		this.innerColor = innerColor;
		this.backgroundColor = backgroundColor;
		this.innerCompleteness = innerCompleteness;
		this.outerCompleteness = outerCompleteness;
	}
	
	@Override
	public void EventTick(double delta) {
		updateAndRender(delta);
	}
	
	private void updateAndRender(double delta) {
		this.getTarget().Fill((byte)backgroundColor.alpha,(byte)backgroundColor.red,(byte)backgroundColor.green,(byte)backgroundColor.blue);
		Vector2f cur = new Vector2f(0,0);
		Vector2f startVector = new Vector2f(0,-1).rotate(startingAngle);
		Vector2f curFromOrigin = new Vector2f(0,0);
		float divisionAngle = 360.0f / this.numOfPoints;
		Vector2f[] startVectors = new Vector2f[this.numOfPoints];
		Vector2f r = new Vector2f(startVector);
		for(int i = 0;i < this.numOfPoints;i++) {
			r.x = startVector.x;
			r.y = startVector.y;
			for(int t = 0;t < i;t ++) {
				r = r.rotate(divisionAngle);
			}
			startVectors[i] = new Vector2f(r);
		}
		for(int y = 0;y < this.getRectangle().getSize().y;y++) {
			for(int x = 0;x < this.getRectangle().getSize().x;x++) {
				cur.x = x;
				cur.y = y;
				double dist = circleCenter.len(cur);
				if(dist > this.radius + this.ringThiccness) continue;
				curFromOrigin.x = cur.x;
				curFromOrigin.y = cur.y;
				curFromOrigin.sub(this.circleCenter);
				//System.out.println();
				//System.out.println("COMA: " + comA);
				

				double angle = 0;
				double cross = 0;
				boolean validPixel = false;
				Vector2f tempVec = new Vector2f();
				if(dist > this.radius) {
					double comA = this.outerCompleteness * Math.PI / (float)(this.numOfPoints);
					switch(this.direction) {
					case Clockwise:
						comA *= 2.0d;
						for(int i = 0;i < this.numOfPoints;i ++) {
							tempVec.x = startVectors[i].x;
							tempVec.y = startVectors[i].y;
							angle = curFromOrigin.getAngleWithVector(tempVec);
							cross = curFromOrigin.Cross(tempVec);
							//System.out.println("ANG: " + angle + " , COMA: " + comA + " , TVEC: (" + tempVec.x + ", " + tempVec.y + ")");
							validPixel |= ((angle <= comA && cross <= 0) || ((Math.PI - angle) <= (comA - Math.PI) && cross >= 0));
						}
						if(!validPixel)
							continue;
						break;
					case CounterClockwise:
						comA *= 2.0d;
						for(int i = 0;i < this.numOfPoints;i ++) {
							angle = curFromOrigin.getAngleWithVector(startVectors[i]);
							cross = curFromOrigin.Cross(startVectors[i]);
							validPixel |= ((angle <= comA && cross >= 0) || ((Math.PI - angle) <= (comA - Math.PI) && cross <= 0));
						}
						if(!validPixel)
							continue;
						break;
					case Both:
						for(int i = 0;i < this.numOfPoints;i ++) {
							angle = curFromOrigin.getAngleWithVector(startVectors[i]);
							cross = curFromOrigin.Cross(startVectors[i]);
							validPixel |= !(angle > comA);
						}
						if(!validPixel)
							continue;
						break;
					}
				
					this.getTarget().DrawPixel((int)cur.x, (int)cur.y, ringColor.alpha, ringColor.red, ringColor.green, ringColor.blue);
				}
				else {
					double comA = this.innerCompleteness * Math.PI / (float)(this.numOfPoints);
					switch(this.direction) {
					case Clockwise:
						comA *= 2.0d;
						for(int i = 0;i < this.numOfPoints;i ++) {
							angle = curFromOrigin.getAngleWithVector(startVectors[i]);
							cross = curFromOrigin.Cross(startVectors[i]);
							validPixel |= ((angle <= comA && cross <= 0) || ((Math.PI - angle) <= (comA - Math.PI) && cross >= 0));
						}
						if(!validPixel)
							continue;
						break;
					case CounterClockwise:
						comA *= 2.0d;
						for(int i = 0;i < this.numOfPoints;i ++) {
							angle = curFromOrigin.getAngleWithVector(startVectors[i]);
							cross = curFromOrigin.Cross(startVectors[i]);
							validPixel |= ((angle <= comA && cross >= 0) || ((Math.PI - angle) <= (comA - Math.PI) && cross <= 0));
						}
						if(!validPixel)
							continue;
						break;
					case Both:
						for(int i = 0;i < this.numOfPoints;i ++) {
							angle = curFromOrigin.getAngleWithVector(startVectors[i]);
							cross = curFromOrigin.Cross(startVectors[i]);
							validPixel |= !(angle > comA);
						}
						if(!validPixel)
							continue;
						break;
					}
					this.getTarget().DrawPixel((int)cur.x, (int)cur.y, innerColor.alpha, innerColor.red, innerColor.green, innerColor.blue);
				}
			}
		}
	}
}
