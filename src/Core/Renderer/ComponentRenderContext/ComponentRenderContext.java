package Core.Renderer.ComponentRenderContext;

import Core.Render.Bitmaps.BitmapRGB;
import Core.Components.BaseComponent.BaseComponent;
import Core.Components.BaseComponent.StructureComponent.StructureComponent;
import Core.Components.BaseComponent.StructureComponent.VisualComponent.VisualComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;

public class ComponentRenderContext extends BitmapRGB{

	public ComponentRenderContext(int width, int height) {
		super(width, height);
	}
	
	public void RenderComponent(BaseComponent component)
	{
		this._DrawComponent(component, null);
	}
	
	private void _DrawComponent(BaseComponent component,Rectangle2Di parentRectangle)
	{
		if(component instanceof VisualComponent) {
		switch(((VisualComponent)component).renderMode)
		{
		case InsideParent:
			if(component.getParent() != null) {
				this.DrawBitmapInRegion(((VisualComponent)component).getAbsolutePosition(), parentRectangle, ((VisualComponent)component).getTarget());
			}
			else
				this.DrawBitmap(Math.round(((VisualComponent)component).getPosition().x), Math.round(((VisualComponent)component).getPosition().y), ((VisualComponent)component).getTarget());
			break;
		case AsTopLayer:
			this.DrawBitmap(Math.round(((VisualComponent)component).getPosition().x), Math.round(((VisualComponent)component).getPosition().y), ((VisualComponent)component).getTarget());
			break;
		}
		}
		for(BaseComponent s : component.getChildren())
		{
			_DrawComponent(s,((VisualComponent)component).getBounds());
		}
	}
	
}
