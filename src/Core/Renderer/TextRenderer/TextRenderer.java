package Core.Renderer.TextRenderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Core.Render.Bitmaps.BitmapARGB;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class TextRenderer {
    
	public static void DrawText(String str,Font font,Color fontColor,Color background,BitmapARGB bitmap)
	{
		BufferedImage img = new BufferedImage(bitmap.getWidth(),bitmap.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D graphics = img.createGraphics();
		
		graphics.setBackground(background);
		graphics.setColor(fontColor);  
		graphics.setFont(font);
		//graphics.clearRect(0, 0, img.getWidth(), img.getHeight());
		FontMetrics metrics = graphics.getFontMetrics(font);
        int ascent = metrics.getAscent();
        
		graphics.drawString(str, 0, ascent);
		
		graphics.dispose();
		
		bitmap.DrawBufferedImage(0, 0, img);
		
	}public static BitmapARGB GenerateText(String str, Font font, Color fontColor, Color background, int dpi) {
	    // Create a temporary image to calculate font metrics
	    BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = tempImage.createGraphics();
	    
	    // Set up proper DPI scaling for font metrics calculation
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    
	    // Create a scaled font for the target DPI
	    float scaleFactor = dpi / 72.0f;
	    Font scaledFont = font.deriveFont(font.getSize2D() * scaleFactor);
	    g2d.setFont(scaledFont);
	    
	    // Get font metrics with the scaled font
	    FontMetrics metrics = g2d.getFontMetrics();
	    int textWidth = metrics.stringWidth(str);
	    int textHeight = metrics.getHeight();
	    
	    // Add some padding to avoid clipping (especially for descenders)
	    int padding = (int) (metrics.getDescent() * 0.5f);
	    int totalWidth = textWidth + padding * 2;
	    int totalHeight = textHeight + padding * 2;
	    
	    g2d.dispose();
	    
	    // Create the final image with proper dimensions
	    BufferedImage image = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
	    g2d = image.createGraphics();
	    
	    // Set rendering hints for better quality
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    
	    // Fill the background
	    g2d.setColor(background);
	    g2d.fillRect(0, 0, totalWidth, totalHeight);
	    
	    // Set the font and color
	    g2d.setFont(scaledFont);
	    g2d.setColor(fontColor);
	    
	    // Draw the text with proper positioning (accounting for padding and baseline)
	    int x = padding;
	    int y = padding + metrics.getAscent();  // Position at baseline with padding
	    
	    g2d.drawString(str, x, y);
	    
	    g2d.dispose();
	    
	    return new BitmapARGB(image);
	}
	
	
    public static BufferedImage createImage(String inputString) throws IOException{
        
        BufferedImage image = createGraphics(inputString);
        saveImage(image);
        
        return image;
    }
    
    public static BufferedImage createGraphics(String inputString) {
        
        BufferedImage bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Font font = new Font("Consolas", Font.BOLD, 40);
        Graphics2D graphics2D = prepareGraphics(bufferedImage);
        double baseY = getY(getBounds(font, inputString, graphics2D));
        
        if(isLatin(inputString)) {
        	System.out.println("latin");
            drawLinesAsWords(graphics2D, inputString, font, baseY);
        } else {
        	System.out.println("not latin");
            drawLinesAsCharacters(graphics2D, inputString, font, baseY);
        }
        
        graphics2D.dispose();

        return bufferedImage;
    }
    
    public static double getY(Rectangle2D bounds) {
        
        double ascent = - bounds.getY();
        double baseY = 0 + ascent;
        return baseY;
    }
    
    public static Rectangle2D getBounds(Font font, String content, Graphics2D graphics2D) {
        FontRenderContext context = graphics2D.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        
        return bounds;
    }
    
    public static Graphics2D prepareGraphics(BufferedImage bufferedImage) {
        Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setBackground(Color.GRAY);
        graphics2D.clearRect(0, 0, 800, 600);
        graphics2D.setPaint(Color.RED);
        
        return graphics2D;
    }
    
    public static boolean isLatin(String inputString) {
        String regex = "^.*\\p{IsLatin}.*";
        boolean result = inputString.matches(regex);
        if(result) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void saveImage(BufferedImage bufferedImage) throws IOException {
        
        ImageIO.write(bufferedImage, "png", new File("./Image.png"));
    }
    
    public static int getHeight(Font font, Graphics2D g) {
        
        int lineHeight = g.getFontMetrics(font).getHeight();
        return lineHeight;
    }

    public static void drawLinesAsWords(Graphics2D graphics2D, String content, Font font, double baseY) {
        
        int lineHeight = getHeight(font, graphics2D);
        int leftBorder = 5;
        String[] wordArray = content.split(" ");
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        String currentContent = "";
        double leftTopCornerLine = baseY;
        
        for(int i = 0; i < wordArray.length; i++) {
            
            if(leftTopCornerLine < 195) {

                if(fontMetrics.stringWidth(currentContent)>=240) {
                    if(fontMetrics.stringWidth(wordArray[i])<=240-fontMetrics.stringWidth(currentContent)) {
                        currentContent += " " + wordArray[i];
                        graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
                        currentContent ="";
                        leftTopCornerLine += lineHeight;
                    } else {
                        graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
                        currentContent ="";
                        leftTopCornerLine += lineHeight;
                    }
                } else {
                    int contentWidth = fontMetrics.stringWidth(currentContent);
                    if(contentWidth<=260) {
                        currentContent += " " + wordArray[i];
                    }
                }
            } else {
                System.out.println("Input was cut, because it was too long.");
                break;
            }
        }
    }
    
    public static void drawLinesAsCharacters(Graphics2D graphics2D, String content, Font font, double baseY) {
        int lineHeight = getHeight(font, graphics2D);
        int leftBorder = 5;
        String[] characterArray = content.split("");
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        String currentContent = "";
        double leftTopCornerLine = baseY;
        
        for(int i = 0; i < characterArray.length; i++) {
            
            if(leftTopCornerLine < 195) {

                if(fontMetrics.stringWidth(currentContent)>=210) {
                    if(fontMetrics.stringWidth(characterArray[i])<=210-fontMetrics.stringWidth(currentContent)) {
                        currentContent += characterArray[i];
                        graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
                        System.out.println("str drawen 0");
                        currentContent ="";
                        leftTopCornerLine += lineHeight;
                    } else {
                    	System.out.println("str drawen 1");
                        graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
                        currentContent ="";
                        leftTopCornerLine += lineHeight;
                    }
                } else {
                    int contentWidth = fontMetrics.stringWidth(currentContent);
                    if(contentWidth<=230) {
                        currentContent += characterArray[i];
                    }
                }
            } else {
                System.out.println("Input was cut, because it was too long.");
                break;
            }
        }
    }

    
}