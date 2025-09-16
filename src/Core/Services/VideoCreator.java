package Core.Services;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

public class VideoCreator {
	private IMediaWriter writer;
	private Dimension VideoBounds;
	private long FrameCount;
	private double FrameRateDelta;
	private double VideoTime;
	public VideoCreator(String Path,int width,int height,double FrameRate)
	{
		writer = ToolFactory.makeWriter(Path);
		VideoBounds = new Dimension(width,height);
		FrameCount = 0;
		FrameRateDelta = (double)1/FrameRate;
		VideoTime = 0;
	}
	public void CreateStream()
	{
		writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, 
				VideoBounds.width, VideoBounds.height);
	}
	//BufferedImage frame = convertToType(screen,BufferedImage.TYPE_3BYTE_BGR);
	public void AddFrame(BufferedImage frame)
	{
		writer.encodeVideo(0, frame, (long)(FrameRateDelta * 1000000000.0 * FrameCount), TimeUnit.NANOSECONDS);
		FrameCount++;
		VideoTime += FrameRateDelta;
	}
	public void AddFrameWithCorrection(BufferedImage frame)
	{
		BufferedImage f = convertToType(frame,BufferedImage.TYPE_3BYTE_BGR);
		writer.encodeVideo(0, f, (long)(FrameRateDelta * 1000000000.0 * FrameCount), TimeUnit.NANOSECONDS);
		FrameCount++;
		VideoTime += FrameRateDelta;
	}
	public void Close()
	{
		writer.close();
	}
	public double GetCurrentVideoLength()
	{
		return VideoTime;
	}
	public long GetFrameCount()
	{
		return FrameCount;
	}
	
	private static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
		 
		  BufferedImage image;
		 
		  // if the source image is already the target type, return the source image
		 
		  if (sourceImage.getType() == targetType) {
		 
		image = sourceImage;
		 
		  }
		 
		  // otherwise create a new image of the target type and draw the new image
		 
		  else {
		 
		image = new BufferedImage(sourceImage.getWidth(), 
		 
		     sourceImage.getHeight(), targetType);
		 
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		 
		  }
		 
		  return image;
		 
	}
}
