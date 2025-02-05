package Core.Math.Transform;

import Core.Math.Vectors.Vector4f;
import Core.Math.Matrices.Matrix4;

public class Quaternion4 {
	private float m_x;
	private float m_y;
	private float m_z;
	private float m_w;

	public Quaternion4(float x, float y, float z, float w)
	{
		this.m_x = x;
		this.m_y = y;
		this.m_z = z;
		this.m_w = w;
	}

	public Quaternion4(Vector4f axis, float angle)
	{
		float sinHalfAngle = (float)Math.sin(angle / 2);
		float cosHalfAngle = (float)Math.cos(angle / 2);

		this.m_x = axis.x * sinHalfAngle;
		this.m_y = axis.y * sinHalfAngle;
		this.m_z = axis.z * sinHalfAngle;
		this.m_w = cosHalfAngle;
	}

	public float Length()
	{
		return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
	}
	
	public Quaternion4 Normalized()
	{
		float length = Length();
		
		return new Quaternion4(m_x / length, m_y / length, m_z / length, m_w / length);
	}
	
	public Quaternion4 Conjugate()
	{
		return new Quaternion4(-m_x, -m_y, -m_z, m_w);
	}

	public Quaternion4 Mul(float r)
	{
		return new Quaternion4(m_x * r, m_y * r, m_z * r, m_w * r);
	}

	public Quaternion4 Mul(Quaternion4 r)
	{
		float w_ = m_w * r.GetW() - m_x * r.GetX() - m_y * r.GetY() - m_z * r.GetZ();
		float x_ = m_x * r.GetW() + m_w * r.GetX() + m_y * r.GetZ() - m_z * r.GetY();
		float y_ = m_y * r.GetW() + m_w * r.GetY() + m_z * r.GetX() - m_x * r.GetZ();
		float z_ = m_z * r.GetW() + m_w * r.GetZ() + m_x * r.GetY() - m_y * r.GetX();
		
		return new Quaternion4(x_, y_, z_, w_);
	}
	
	public Quaternion4 Mul(Vector4f r)
	{
		float w_ = -m_x * r.x - m_y * r.y - m_z * r.z;
		float x_ =  m_w * r.x + m_y * r.z - m_z * r.y;
		float y_ =  m_w * r.y + m_z * r.x - m_x * r.z;
		float z_ =  m_w * r.z + m_x * r.y - m_y * r.x;
		
		return new Quaternion4(x_, y_, z_, w_);
	}

	public Quaternion4 Sub(Quaternion4 r)
	{
		return new Quaternion4(m_x - r.GetX(), m_y - r.GetY(), m_z - r.GetZ(), m_w - r.GetW());
	}

	public Quaternion4 Add(Quaternion4 r)
	{
		return new Quaternion4(m_x + r.GetX(), m_y + r.GetY(), m_z + r.GetZ(), m_w + r.GetW());
	}

	public Matrix4 ToRotationMatrix()
	{
		Vector4f forward =  new Vector4f(2.0f * (m_x * m_z - m_w * m_y), 2.0f * (m_y * m_z + m_w * m_x), 1.0f - 2.0f * (m_x * m_x + m_y * m_y));
		Vector4f up = new Vector4f(2.0f * (m_x * m_y + m_w * m_z), 1.0f - 2.0f * (m_x * m_x + m_z * m_z), 2.0f * (m_y * m_z - m_w * m_x));
		Vector4f right = new Vector4f(1.0f - 2.0f * (m_y * m_y + m_z * m_z), 2.0f * (m_x * m_y - m_w * m_z), 2.0f * (m_x * m_z + m_w * m_y));

		return new Matrix4().InitRotation(forward, up, right);
	}

	public float Dot(Quaternion4 r)
	{
		return m_x * r.GetX() + m_y * r.GetY() + m_z * r.GetZ() + m_w * r.GetW();
	}

	public Quaternion4 NLerp(Quaternion4 dest, float lerpFactor, boolean shortest)
	{
		Quaternion4 correctedDest = dest;

		if(shortest && this.Dot(dest) < 0)
			correctedDest = new Quaternion4(-dest.GetX(), -dest.GetY(), -dest.GetZ(), -dest.GetW());

		return correctedDest.Sub(this).Mul(lerpFactor).Add(this).Normalized();
	}

	public Quaternion4 SLerp(Quaternion4 dest, float lerpFactor, boolean shortest)
	{
		final float EPSILON = 1e3f;

		float cos = this.Dot(dest);
		Quaternion4 correctedDest = dest;

		if(shortest && cos < 0)
		{
			cos = -cos;
			correctedDest = new Quaternion4(-dest.GetX(), -dest.GetY(), -dest.GetZ(), -dest.GetW());
		}

		if(Math.abs(cos) >= 1 - EPSILON)
			return NLerp(correctedDest, lerpFactor, false);

		float sin = (float)Math.sqrt(1.0f - cos * cos);
		float angle = (float)Math.atan2(sin, cos);
		float invSin =  1.0f/sin;

		float srcFactor = (float)Math.sin((1.0f - lerpFactor) * angle) * invSin;
		float destFactor = (float)Math.sin((lerpFactor) * angle) * invSin;

		return this.Mul(srcFactor).Add(correctedDest.Mul(destFactor));
	}

	//From Ken Shoemake's "Quaternion4 Calculus and Fast Animation" article
	public Quaternion4(Matrix4 rot)
	{
		float trace = rot.Get(0, 0) + rot.Get(1, 1) + rot.Get(2, 2);

		if(trace > 0)
		{
			float s = 0.5f / (float)Math.sqrt(trace+ 1.0f);
			m_w = 0.25f / s;
			m_x = (rot.Get(1, 2) - rot.Get(2, 1)) * s;
			m_y = (rot.Get(2, 0) - rot.Get(0, 2)) * s;
			m_z = (rot.Get(0, 1) - rot.Get(1, 0)) * s;
		}
		else
		{
			if(rot.Get(0, 0) > rot.Get(1, 1) && rot.Get(0, 0) > rot.Get(2, 2))
			{
				float s = 2.0f * (float)Math.sqrt(1.0f + rot.Get(0, 0) - rot.Get(1, 1) - rot.Get(2, 2));
				m_w = (rot.Get(1, 2) - rot.Get(2, 1)) / s;
				m_x = 0.25f * s;
				m_y = (rot.Get(1, 0) + rot.Get(0, 1)) / s;
				m_z = (rot.Get(2, 0) + rot.Get(0, 2)) / s;
			}
			else if(rot.Get(1, 1) > rot.Get(2, 2))
			{
				float s = 2.0f * (float)Math.sqrt(1.0f + rot.Get(1, 1) - rot.Get(0, 0) - rot.Get(2, 2));
				m_w = (rot.Get(2, 0) - rot.Get(0, 2)) / s;
				m_x = (rot.Get(1, 0) + rot.Get(0, 1)) / s;
				m_y = 0.25f * s;
				m_z = (rot.Get(2, 1) + rot.Get(1, 2)) / s;
			}
			else
			{
				float s = 2.0f * (float)Math.sqrt(1.0f + rot.Get(2, 2) - rot.Get(0, 0) - rot.Get(1, 1));
				m_w = (rot.Get(0, 1) - rot.Get(1, 0) ) / s;
				m_x = (rot.Get(2, 0) + rot.Get(0, 2) ) / s;
				m_y = (rot.Get(1, 2) + rot.Get(2, 1) ) / s;
				m_z = 0.25f * s;
			}
		}

		float length = (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
		m_x /= length;
		m_y /= length;
		m_z /= length;
		m_w /= length;
	}

	public Vector4f GetForward()
	{
		return new Vector4f(0,0,1,1).Rotate(this);
	}

	public Vector4f GetBack()
	{
		return new Vector4f(0,0,-1,1).Rotate(this);
	}

	public Vector4f GetUp()
	{
		return new Vector4f(0,1,0,1).Rotate(this);
	}

	public Vector4f GetDown()
	{
		return new Vector4f(0,-1,0,1).Rotate(this);
	}

	public Vector4f GetRight()
	{
		return new Vector4f(1,0,0,1).Rotate(this);
	}

	public Vector4f GetLeft()
	{
		return new Vector4f(-1,0,0,1).Rotate(this);
	}
	
	public float GetX()
	{
		return m_x;
	}

	public float GetY()
	{
		return m_y;
	}

	public float GetZ()
	{
		return m_z;
	}

	public float GetW()
	{
		return m_w;
	}

	public boolean equals(Quaternion4 r)
	{
		return m_x == r.GetX() && m_y == r.GetY() && m_z == r.GetZ() && m_w == r.GetW();
	}
}
