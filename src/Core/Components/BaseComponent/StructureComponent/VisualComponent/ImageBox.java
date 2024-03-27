package Core.Components.BaseComponent.StructureComponent.VisualComponent;

import Core.Components.InitStatus;
import Core.Components.BaseComponent.StructureComponent.StructureComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;

public class ImageBox extends VisualComponent {
	
	//private ImageBoxRenderState {stretch , fit, ...}
	
	public ImageBox(Point2i position, Point2i size) {
		super(position, size);
	}

	@Override
	public void paintTarget(Rectangle2Di rectangle) {}

	@Override
	public BitmapARGB getTarget() {
		return this.target;
	}

	@Override
	public InitStatus initialize() {
		
		this.visible = true;
		
		try {
			this.target = new BitmapARGB(this.bounds.getSize().x,this.bounds.getSize().y);
			this.target.Fill((byte)255,(byte)255,(byte)255,(byte)255);
		} 
		catch(Exception ex)
		{
			this.exception = ex;
			return InitStatus.Error;
		}
		return InitStatus.OK;
	}

	
	
	public void setBackground(Core.Graphics.Image.Image background)
	{
		this.bounds.setSize(background.bitmap.getWidth(), background.bitmap.getHeight());
		this.target = background.bitmap;
	}
	
	
	
	public Core.Graphics.Image.Image getBackground()
	{
		return new Core.Graphics.Image.Image(this.target);
	}
	

}
