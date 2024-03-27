package Core.Components;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Core.Components.BaseComponent.StructureComponent.StructureComponent;
import Core.Components.BaseComponent.StructureComponent.VisualComponent.VisualComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;
import Core.Window.Input.WindowEventListener;

public abstract class Component extends VisualComponent implements WindowEventListener {

	public Component(Point2i position, Point2i size) {
		super(position, size);
	}

}
