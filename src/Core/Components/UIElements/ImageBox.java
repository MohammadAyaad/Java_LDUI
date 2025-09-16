package Core.Components.UIElements;

import java.util.List;

import Core.Components.BaseComponent;
import Core.Components.VisualComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.*;

public class ImageBox extends VisualComponent {
	
	public ImageBox(Point2i position, Point2i dimensions,List<BaseComponent> children) {
		super(position, dimensions,children);
	}
	
	public ImageBox(Point2i position,List<BaseComponent> children,Core.Graphics.Image.Image img) {
		super(position, new Point2i(img.bitmap.getWidth(),img.bitmap.getHeight()),children);
		this.getTarget().DrawBitmap(0, 0, img.bitmap);
		
	}
	
	public void setImage(Core.Graphics.Image.Image img) {
		this.getRectangle().setSize(img.bitmap.getWidth(), img.bitmap.getHeight());
		this.getTarget().DrawBitmap(0, 0, img.bitmap);
	}
}
