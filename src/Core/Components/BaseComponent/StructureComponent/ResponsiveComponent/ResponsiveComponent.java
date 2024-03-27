package Core.Components.BaseComponent.StructureComponent.ResponsiveComponent;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Core.Components.InitStatus;
import Core.Components.BaseComponent.StructureComponent.StructureComponent;
import Core.Math.Points.Point2i;
import Core.Window.Input.WindowEventListener;

public abstract class ResponsiveComponent extends StructureComponent implements WindowEventListener {

	public ResponsiveComponent(Point2i position, Point2i size) {
		super(position, size);
	}
}
