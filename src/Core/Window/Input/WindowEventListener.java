package Core.Window.Input;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface WindowEventListener {
	/** Updates state when the mouse is dragged */
	public void mouseDragged(MouseEvent e);

	/** Updates state when the mouse is moved */
	public void mouseMoved(MouseEvent e);

	/** Updates state when the mouse is clicked */
	public void mouseClicked(MouseEvent e);

	/** Updates state when the mouse enters the screen */
	public void mouseEntered(MouseEvent e);

	/** Updates state when the mouse exits the screen */
	public void mouseExited(MouseEvent e);

	/** Updates state when a mouse button is pressed */
	public void mousePressed(MouseEvent e);

	/** Updates state when a mouse button is released */
	public void mouseReleased(MouseEvent e);

	/** Updates state when the window gains focus */
	public void focusGained(FocusEvent e);

	/** Updates state when the window loses focus */
	public void focusLost(FocusEvent e);

	/** Updates state when a key is pressed */
	public void keyPressed(KeyEvent e);

	/** Updates state when a key is released */
	public void keyReleased(KeyEvent e);

	/** Updates state when a key is typed */
	public void keyTyped(KeyEvent e);

}
