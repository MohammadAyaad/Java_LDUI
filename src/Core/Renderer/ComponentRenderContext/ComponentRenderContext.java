package Core.Renderer.ComponentRenderContext;

import Core.Render.Bitmaps.BitmapRGB;

import java.util.LinkedList;
import java.util.List;

import Core.Components.*;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;

public class ComponentRenderContext extends BitmapRGB{

	public ComponentRenderContext(int width, int height) {
		super(width, height);
	}
	
	public void DrawComponent(Structured base) {
		//get all components that are going to be drawn on a big list
		//create a z-index bitmap
		//from each component fetch all the pixels that are going to be drawn from it
		List<BaseComponent> all = FlattenComponentTree(base);
		int[] zbuffer = new int[base.getRectangle().getArea()];
		RenderToZBuffer(base,zbuffer);
		
		
	}
	
	public void RenderToZBuffer(BaseComponent component,int[] zbuffer) {
		if(component instanceof Visual) {
			
		}
	}
	
	
	private List<BaseComponent> FlattenComponentTree(BaseComponent component) {
		List<BaseComponent> components = new LinkedList();
		components.add(component);
		for(BaseComponent child : component.getChildren()) {
			components.addAll(FlattenComponentTree(child));
		}
		return components;
	}
	
	public void RenderComponent(BaseComponent component)
	{
		this._DrawComponent(component, null);
	}
	
	private void _DrawComponent(BaseComponent component,Rectangle2Di parentRectangle)
	{
		if(component instanceof Visual) {
		switch(((Visual)component).getRenderMode())
		{
		case InsideParent:
			if(component.getParent() != null) {
				this.DrawBitmapInRegion(((Visual)component).getPosition(), parentRectangle, ((Visual)component).getTarget());
			}
			else
				this.DrawBitmap(Math.round(((Visual)component).getPosition().x), Math.round(((Visual)component).getPosition().y), ((Visual)component).getTarget());
			break;
		case AsTopLayer:
			this.DrawBitmap(Math.round(((Visual)component).getPosition().x), Math.round(((Visual)component).getPosition().y), ((Visual)component).getTarget());
			break;
		}
		}
		for(BaseComponent s : component.getChildren())
		{
			if(component instanceof Structured)
				_DrawComponent(s,((Structured) component).getRectangle());
			else
				_DrawComponent(s,parentRectangle);
		}
	}
	
}
