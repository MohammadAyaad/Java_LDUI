package Core.Window.Input;

import java.awt.MouseInfo;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Core.Math.Points.Point2i;

public class Input implements KeyListener, FocusListener,
MouseListener, MouseMotionListener {
public boolean resetStateOnFocusLost = true;
private boolean[] keys = new boolean[65536];
private boolean[] mouseButtons;
private Point2i mousePosition;
private enum Status
{
	OK,
	MissingMouse,
	BAD
}
private Status status;

public Input()
{
	if(MouseInfo.getNumberOfButtons() != -1) {
		this.mouseButtons = new boolean[MouseInfo.getNumberOfButtons()];
	}
	else
	{
		this.status = Status.MissingMouse;
	}
	mousePosition = new Point2i();
}



/** Updates state when the mouse is dragged */
public void mouseDragged(MouseEvent e) {
	mousePosition.x = e.getX();
	mousePosition.y = e.getY();
}

/** Updates state when the mouse is moved */
public void mouseMoved(MouseEvent e) {
	mousePosition.x = e.getX();
	mousePosition.y = e.getY();
}

/** Updates state when the mouse is clicked */
public void mouseClicked(MouseEvent e) {
}

/** Updates state when the mouse enters the screen */
public void mouseEntered(MouseEvent e) {
}

/** Updates state when the mouse exits the screen */
public void mouseExited(MouseEvent e) {
}

/** Updates state when a mouse button is pressed */
public void mousePressed(MouseEvent e) {
	int code = e.getButton();
	
	if (code > 0 && code < mouseButtons.length) 
		mouseButtons[code] = true;
	
}

/** Updates state when a mouse button is released */
public void mouseReleased(MouseEvent e) {
	int code = e.getButton();
	
	if (code > 0 && code < mouseButtons.length)
		mouseButtons[code] = false;
	
}

/** Updates state when the window gains focus */
public void focusGained(FocusEvent e) {
}

/** Updates state when the window loses focus */
public void focusLost(FocusEvent e) {
	if(resetStateOnFocusLost) {
		for (int i = 0; i < keys.length; i++)
			keys[i] = false;
	
		for (int i = 0; i < mouseButtons.length; i++)
			mouseButtons[i] = false;
	}
	
}

/** Updates state when a key is pressed */
public void keyPressed(KeyEvent e) {
int code = e.getKeyCode();
if (code > 0 && code < keys.length)
	keys[code] = true;
}

/** Updates state when a key is released */
public void keyReleased(KeyEvent e) {
int code = e.getKeyCode();
if (code > 0 && code < keys.length)
	keys[code] = false;
}

/** Updates state when a key is typed */
public void keyTyped(KeyEvent e) {
}

/**
* Gets whether or not a particular key is currently pressed.
* 
* @param key The key to test
* @return Whether or not key is currently pressed.
*/
public boolean getKey(int key) {
	return keys[key];
}

/**
* Gets whether or not a particular mouse button is currently pressed.
* 
* @param button The button to test
* @return Whether or not the button is currently pressed.
*/
public boolean getMouseButton(int button) {
	return mouseButtons[button];
}

/**
* Gets the location of the mouse cursor on x, in pixels.
* @return The location of the mouse cursor on x, in pixels
*/
public int getMouseX() {
	return this.mousePosition.x;
}

/**
* Gets the location of the mouse cursor on y, in pixels.
* @return The location of the mouse cursor on y, in pixels
*/
public int getMouseY() {
	return this.mousePosition.y;
}

public Point2i getMousePosition()
{
	return this.mousePosition;
}

}
