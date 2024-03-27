package Core.Window.Input;

import java.awt.MouseInfo;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class InputWithListeners extends Input {
	public List<WindowEventListener> listeners;


	public InputWithListeners()
	{
		super();
		listeners = new ArrayList<WindowEventListener>();
	}
	/** Updates state when the mouse is dragged */
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		for(WindowEventListener l : listeners) l.mouseDragged(e);
	}

	/** Updates state when the mouse is moved */
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		for(WindowEventListener l : listeners) l.mouseMoved(e);
	}

	/** Updates state when the mouse is clicked */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		for(WindowEventListener l : listeners) l.mouseClicked(e);
	}

	/** Updates state when the mouse enters the screen */
	@Override
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		for(WindowEventListener l : listeners) l.mouseEntered(e);
	}

	/** Updates state when the mouse exits the screen */
	@Override
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		for(WindowEventListener l : listeners) l.mouseExited(e);
	}

	/** Updates state when a mouse button is pressed */
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		for(WindowEventListener l : listeners) l.mousePressed(e);
	}

	/** Updates state when a mouse button is released */
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		for(WindowEventListener l : listeners) l.mouseReleased(e);
	}

	/** Updates state when the window gains focus */
	@Override
	public void focusGained(FocusEvent e) {
		super.focusGained(e);
		for(WindowEventListener l : listeners) l.focusGained(e);
	}

	/** Updates state when the window loses focus */
	@Override
	public void focusLost(FocusEvent e) {
		super.focusLost(e);
		for(WindowEventListener l : listeners) l.focusLost(e);
	}

	/** Updates state when a key is pressed */
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		for(WindowEventListener l : listeners) l.keyPressed(e);
	}

	/** Updates state when a key is released */
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
	for(WindowEventListener l : listeners) l.keyReleased(e);
	}

	/** Updates state when a key is typed */
	@Override
	public void keyTyped(KeyEvent e) {
		super.keyTyped(e);
		for(WindowEventListener l : listeners) l.keyTyped(e);
	}
}
