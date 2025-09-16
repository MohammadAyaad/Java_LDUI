package Core.Graphics.Color;

public class Color4b {
	public byte red;
	public byte green;
	public byte blue;
	public byte alpha;
	public Color4b()
	{
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		this.alpha = 0;
	}
	public Color4b(byte alpha,byte red,byte green,byte blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	public void set(byte alpha,byte red,byte green,byte blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	public boolean EqualsColor(Color4b color) {
		return (this.alpha == color.alpha) &&
				(this.red == color.red) &&
				(this.green == color.green) &&
				(this.blue == color.blue);
	}
	public static final Color4b WHITE = new Color4b((byte)255,(byte)255,(byte)255,(byte)255);
	public static final Color4b BLUE = new Color4b((byte)255,(byte)0,(byte)0,(byte)255);
	public static final Color4b RED = new Color4b((byte)255,(byte)255,(byte)0,(byte)0);
	public static final Color4b LIME = new Color4b((byte)255,(byte)0,(byte)255,(byte)0);
	public static final Color4b GRAY = new Color4b((byte)255,(byte)128,(byte)128,(byte)128);
	public static final Color4b YELLOW = new Color4b((byte)255,(byte)255,(byte)255,(byte)0);
	public static final Color4b BLACK = new Color4b((byte)255,(byte)0,(byte)0,(byte)0);
	public static final Color4b GREEN = new Color4b((byte)255,(byte)0,(byte)128,(byte)0);
	public static final Color4b CYAN = new Color4b((byte)255,(byte)0,(byte)255,(byte)255);
	public static final Color4b MAGENTA = new Color4b((byte)255,(byte)255,(byte)0,(byte)255);
	public static final Color4b SILVER = new Color4b((byte)255,(byte)192,(byte)192,(byte)192);
	public static final Color4b MAROON = new Color4b((byte)255,(byte)128,(byte)0,(byte)0);
	public static final Color4b OLIVE = new Color4b((byte)255,(byte)128,(byte)128,(byte)0);
	public static final Color4b PURPLE = new Color4b((byte)255,(byte)128,(byte)0,(byte)128);
	public static final Color4b TEAL = new Color4b((byte)255,(byte)0,(byte)128,(byte)128);
	public static final Color4b NAVY = new Color4b((byte)255,(byte)0,(byte)0,(byte)128);
	







}
