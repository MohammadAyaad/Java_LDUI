package Core.Renderer.Polygon3D.RenderContext;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Core.Components.Component;
import Core.Components.InitStatus;
import Core.Components.BaseComponent.StructureComponent.StructureComponent;
import Core.Components.BaseComponent.StructureComponent.VisualComponent.VisualComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Window.Window;
import Core.Math.Matrices.Matrix4;

public class ComponentRenderContext
{
	private Component base;
	public ComponentRenderContext(Window window)
	{
		base = new Component(new Point2i(), new Point2i(window.WIDTH,window.HEIGHT))
				{

					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void paintTarget(Rectangle2Di rectangle) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public InitStatus initialize() {
						// TODO Auto-generated method stub
						return null;
					}
			
				};
			window.GetInput().listeners.add(base);
	}
	/*

private float[] m_zBuffer;

	public RenderContext(int width, int height)
	{
		super(width, height);
		m_zBuffer = new float[width * height];
	}

	public void ClearDepthBuffer()
	{
		for(int i = 0; i < m_zBuffer.length; i++)
		{
			m_zBuffer[i] = Float.MAX_VALUE;
		}
	}

	public void DrawTriangle(Vertex v1, Vertex v2, Vertex v3, Bitmap texture)
	{
		if(v1.IsInsideViewFrustum() && v2.IsInsideViewFrustum() && v3.IsInsideViewFrustum())
		{
			FillTriangle(v1, v2, v3, texture);
			return;
		}

		List<Vertex> vertices = new ArrayList<>();
		List<Vertex> auxillaryList = new ArrayList<>();
		
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);

		if(ClipPolygonAxis(vertices, auxillaryList, 0) &&
				ClipPolygonAxis(vertices, auxillaryList, 1) &&
				ClipPolygonAxis(vertices, auxillaryList, 2))
		{
			Vertex initialVertex = vertices.get(0);

			for(int i = 1; i < vertices.size() - 1; i++)
			{
				FillTriangle(initialVertex, vertices.get(i), vertices.get(i + 1), texture);
			}
		}
	}

	private boolean ClipPolygonAxis(List<Vertex> vertices, List<Vertex> auxillaryList,
			int componentIndex)
	{
		ClipPolygonComponent(vertices, componentIndex, 1.0f, auxillaryList);
		vertices.clear();

		if(auxillaryList.isEmpty())
		{
			return false;
		}

		ClipPolygonComponent(auxillaryList, componentIndex, -1.0f, vertices);
		auxillaryList.clear();

		return !vertices.isEmpty();
	}

	private void ClipPolygonComponent(List<Vertex> vertices, int componentIndex, 
			float componentFactor, List<Vertex> result)
	{
		Vertex previousVertex = vertices.get(vertices.size() - 1);
		float previousComponent = previousVertex.Get(componentIndex) * componentFactor;
		boolean previousInside = previousComponent <= previousVertex.GetPosition().w;

		Iterator<Vertex> it = vertices.iterator();
		while(it.hasNext())
		{
			Vertex currentVertex = it.next();
			float currentComponent = currentVertex.Get(componentIndex) * componentFactor;
			boolean currentInside = currentComponent <= currentVertex.GetPosition().w;

			if(currentInside ^ previousInside)
			{
				float lerpAmt = (previousVertex.GetPosition().w - previousComponent) /
					((previousVertex.GetPosition().w - previousComponent) - 
					 (currentVertex.GetPosition().w - currentComponent));

				result.add(previousVertex.Lerp(currentVertex, lerpAmt));
			}

			if(currentInside)
			{
				result.add(currentVertex);
			}

			previousVertex = currentVertex;
			previousComponent = currentComponent;
			previousInside = currentInside;
		}
	}

	private void FillTriangle(Vertex v1, Vertex v2, Vertex v3, Bitmap texture)
	{
		Matrix4 screenSpaceTransform = 
				new Matrix4().InitScreenSpaceTransform(GetWidth()/2, GetHeight()/2);
		Matrix4 identity = new Matrix4().InitIdentity();
		Vertex minYVert = v1.Transform(screenSpaceTransform, identity).PerspectiveDivide();
		Vertex midYVert = v2.Transform(screenSpaceTransform, identity).PerspectiveDivide();
		Vertex maxYVert = v3.Transform(screenSpaceTransform, identity).PerspectiveDivide();

		if(minYVert.TriangleAreaTimesTwo(maxYVert, midYVert) >= 0)
		{
			return;
		}

		if(maxYVert.GetY() < midYVert.GetY())
		{
			Vertex temp = maxYVert;
			maxYVert = midYVert;
			midYVert = temp;
		}

		if(midYVert.GetY() < minYVert.GetY())
		{
			Vertex temp = midYVert;
			midYVert = minYVert;
			minYVert = temp;
		}

		if(maxYVert.GetY() < midYVert.GetY())
		{
			Vertex temp = maxYVert;
			maxYVert = midYVert;
			midYVert = temp;
		}

		ScanTriangle(minYVert, midYVert, maxYVert, 
				minYVert.TriangleAreaTimesTwo(maxYVert, midYVert) >= 0,
				texture);
	}

	private void ScanTriangle(Vertex minYVert, Vertex midYVert, 
			Vertex maxYVert, boolean handedness, Bitmap texture)
	{
		Gradients gradients = new Gradients(minYVert, midYVert, maxYVert);
		PolygonEdge topToBottom    = new PolygonEdge(gradients, minYVert, maxYVert, 0);
		PolygonEdge topToMiddle    = new PolygonEdge(gradients, minYVert, midYVert, 0);
		PolygonEdge middleToBottom = new PolygonEdge(gradients, midYVert, maxYVert, 1);

		ScanEdges(gradients, topToBottom, topToMiddle, handedness, texture);
		ScanEdges(gradients, topToBottom, middleToBottom, handedness, texture);
	}

	private void ScanEdges(Gradients gradients, PolygonEdge a, PolygonEdge b, boolean handedness, Bitmap texture)
	{
		PolygonEdge left = a;
		PolygonEdge right = b;
		if(handedness)
		{
			PolygonEdge temp = left;
			left = right;
			right = temp;
		}

		int yStart = b.GetYStart();
		int yEnd   = b.GetYEnd();
		for(int j = yStart; j < yEnd; j++)
		{
			DrawScanLine(gradients, left, right, j, texture);
			left.Step();
			right.Step();
		}
	}

	private void DrawScanLine(Gradients gradients, PolygonEdge left, PolygonEdge right, int j, Bitmap texture)
	{
		int xMin = (int)Math.ceil(left.GetX());
		int xMax = (int)Math.ceil(right.GetX());
		float xPrestep = xMin - left.GetX();

//		float xDist = right.GetX() - left.GetX();
//		float texCoordXXStep = (right.GetTexCoordX() - left.GetTexCoordX())/xDist;
//		float texCoordYXStep = (right.GetTexCoordY() - left.GetTexCoordY())/xDist;
//		float oneOverZXStep = (right.GetOneOverZ() - left.GetOneOverZ())/xDist;
//		float depthXStep = (right.GetDepth() - left.GetDepth())/xDist;

		// Apparently, now that stepping is actually on pixel centers, gradients are
		// precise enough again.
		float texCoordXXStep = gradients.GetTexCoordXXStep();
		float texCoordYXStep = gradients.GetTexCoordYXStep();
		float oneOverZXStep = gradients.GetOneOverZXStep();
		float depthXStep = gradients.GetDepthXStep();
		float lightAmtXStep = gradients.GetLightAmtXStep();

		float texCoordX = left.GetTexCoordX() + texCoordXXStep * xPrestep;
		float texCoordY = left.GetTexCoordY() + texCoordYXStep * xPrestep;
		float oneOverZ = left.GetOneOverZ() + oneOverZXStep * xPrestep;
		float depth = left.GetDepth() + depthXStep * xPrestep;
		float lightAmt = left.GetLightAmt() + lightAmtXStep * xPrestep;

		for(int i = xMin; i < xMax; i++)
		{
			int index = i + j * GetWidth();
			if(depth < m_zBuffer[index])
			{
				m_zBuffer[index] = depth;
				float z = 1.0f/oneOverZ;
				int srcX = (int)((texCoordX * z) * (float)(texture.GetWidth() - 1) + 0.5f);
				int srcY = (int)((texCoordY * z) * (float)(texture.GetHeight() - 1) + 0.5f);

				CopyPixel(i, j, srcX, srcY, texture, lightAmt);
			}

			oneOverZ += oneOverZXStep;
			texCoordX += texCoordXXStep;
			texCoordY += texCoordYXStep;
			depth += depthXStep;
			lightAmt += lightAmtXStep;
		}
	}
	
	
	
	*/
	
	
}
