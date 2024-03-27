package Core.Window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;

import Core.Render.Bitmaps.BitmapRGB;
import Core.Window.Input.Input;
import Core.Window.Input.InputWithListeners;

public class Window extends Canvas
{
	/** The window being used for display */
	protected JFrame         frame;
	/** The bitmap representing the final image to display */
	protected final BitmapRGB  frameBuffer;
	/** Used to display the framebuffer in the window */
	protected BufferedImage  displayImage;
	/** The pixels of the display image, as an array of byte components */
	protected byte[]         displayComponents;
	/** The buffers in the Canvas */
	protected BufferStrategy bufferStrategy;
	/** A graphics object that can draw into the Canvas's buffers */
	protected Graphics       graphics;

	private final InputWithListeners          input;

	public BitmapRGB getFrameBuffer() { return frameBuffer; }
	public InputWithListeners GetInput() { return input; }

	/**
	 * Creates and initializes a new display.
	 *
	 * @param width  How wide the display is, in pixels.
	 * @param height How tall the display is, in pixels.
	 * @param title  The text displayed in the window's title bar.
	 */
	public Window(int width, int height, String title)
	{
		//Set the canvas's preferred, minimum, and maximum size to prevent
		//unintentional resizing.
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		//Creates images used for display.
		frameBuffer = new BitmapRGB(width, height);
		displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		displayComponents = 
			((DataBufferByte)displayImage.getRaster().getDataBuffer()).getData();

		//Create a JFrame designed specifically to show this Display.
		frame = new JFrame();
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setVisible(true);

		//Allocates 1 display buffer, and gets access to it via the buffer
		//strategy and a graphics object for drawing into it.
		createBufferStrategy(1);
		bufferStrategy = getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
		
		input = new InputWithListeners();
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);

		setFocusable(true);
		requestFocus();
		
	}
	public Window(BitmapRGB framebuffer, String title)
	{
		//Set the canvas's preferred, minimum, and maximum size to prevent
		//unintentional resizing.
		Dimension size = new Dimension(framebuffer.getWidth(), framebuffer.getHeight());
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		//Creates images used for display.
		frameBuffer = framebuffer;
		displayImage = new BufferedImage(framebuffer.getWidth(), framebuffer.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		displayComponents = 
			((DataBufferByte)displayImage.getRaster().getDataBuffer()).getData();

		//Create a JFrame designed specifically to show this Display.
		frame = new JFrame();
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle(title);
		frame.setSize(framebuffer.getWidth(), framebuffer.getHeight());
		frame.setVisible(true);

		//Allocates 1 display buffer, and gets access to it via the buffer
		//strategy and a graphics object for drawing into it.
		createBufferStrategy(1);
		bufferStrategy = getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
		
		input = new InputWithListeners();
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);

		setFocusable(true);
		requestFocus();
		
	}
	
	// Displays in the window.

	public void SwapBuffers()
	{
		//Display components should be the byte array used for displayImage's pixels.
		//Therefore, this call should effectively copy the frameBuffer into the
		//displayImage.
		frameBuffer.CopyToByteArray(displayComponents);
		graphics.drawImage(displayImage, 0, 0, 
			frameBuffer.getWidth(), frameBuffer.getHeight(), null);
		bufferStrategy.show();
	}
}