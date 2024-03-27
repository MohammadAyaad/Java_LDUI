package Core.Math.Vertex;

import Core.Math.Vectors.Vector4f;
import Core.Math.Matrices.Matrix4;

public class Vertex {
	private Vector4f m_pos;
	private Vector4f m_texCoords;
	private Vector4f m_normal;

	/** Basic Getter */
	public float GetX() { return m_pos.GetX(); }
	/** Basic Getter */
	public float GetY() { return m_pos.GetY(); }

	public Vector4f GetPosition() { return m_pos; }
	public Vector4f GetTexCoords() { return m_texCoords; }
	public Vector4f GetNormal() { return m_normal; }

	/**
	 * Creates a new Vertex in a usable state.
	 */
	public Vertex(Vector4f pos, Vector4f texCoords, Vector4f normal)
	{
		m_pos = pos;
		m_texCoords = texCoords;
		m_normal = normal;
	}

	public Vertex Transform(Matrix4 transform, Matrix4 normalTransform)
	{
		// The normalized here is important if you're doing scaling.
		return new Vertex(transform.Transform(m_pos), m_texCoords, 
				normalTransform.Transform(m_normal).Normalized());
	}

	public Vertex PerspectiveDivide()
	{
		return new Vertex(new Vector4f(m_pos.x/m_pos.w, m_pos.y/m_pos.w, 
						m_pos.z/m_pos.GetW(), m_pos.w),	
				m_texCoords, m_normal);
	}

	public float TriangleAreaTimesTwo(Vertex b, Vertex c)
	{
		float x1 = b.GetX() - m_pos.x;
		float y1 = b.GetY() - m_pos.y;

		float x2 = c.GetX() - m_pos.x;
		float y2 = c.GetY() - m_pos.y;

		return (x1 * y2 - x2 * y1);
	}

	public Vertex Lerp(Vertex other, float lerpAmt)
	{
		return new Vertex(
				m_pos.Lerp(other.GetPosition(), lerpAmt),
				m_texCoords.Lerp(other.GetTexCoords(), lerpAmt),
				m_normal.Lerp(other.GetNormal(), lerpAmt)
				);
	}

	public boolean IsInsideViewFrustum()
	{
		return 
			Math.abs(m_pos.x) <= Math.abs(m_pos.w) &&
			Math.abs(m_pos.y) <= Math.abs(m_pos.w) &&
			Math.abs(m_pos.z) <= Math.abs(m_pos.w);
	}

	public float Get(int index)
	{
		switch(index)
		{
			case 0:
				return m_pos.x;
			case 1:
				return m_pos.y;
			case 2:
				return m_pos.z;
			case 3:
				return m_pos.w;
			default:
				throw new IndexOutOfBoundsException();
		}
	}
}
