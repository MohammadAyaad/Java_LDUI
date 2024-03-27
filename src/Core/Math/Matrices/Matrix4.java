package Core.Math.Matrices;

import Core.Math.Vectors.Vector4f;

public class Matrix4
{
	private float[] m;

	public Matrix4()
	{
		m = new float[16];
	}

	public Matrix4 InitIdentity()
	{
		m[0] = 1;	m[1] = 0;	m[2] = 0;	m[3] = 0;
		m[4] = 0;	m[5] = 1;	m[6] = 0;	m[7] = 0;
		m[8] = 0;	m[9] = 0;	m[10] = 1;	m[11] = 0;
		m[12] = 0;	m[13] = 0;	m[14] = 0;	m[15] = 1;

		return this;
	}

	public Matrix4 InitScreenSpaceTransform(float halfWidth, float halfHeight)
	{
		m[0] = halfWidth;	m[1] = 0;	m[2] = 0;	m[3] = halfWidth - 0.5f;
		m[4] = 0;	m[5] = -halfHeight;	m[6] = 0;	m[7] = halfHeight - 0.5f;
		m[8] = 0;	m[9] = 0;	m[10] = 1;	m[11] = 0;
		m[12] = 0;	m[13] = 0;	m[14] = 0;	m[15] = 1;

		return this;
	}

	public Matrix4 InitTranslation(float x, float y, float z)
	{
		m[0] = 1;	m[1] = 0;	m[2] = 0;	m[3] = x;
		m[4] = 0;	m[5] = 1;	m[6] = 0;	m[7] = y;
		m[8] = 0;	m[9] = 0;	m[10] = 1;	m[11] = z;
		m[12] = 0;	m[13] = 0;	m[14] = 0;	m[15] = 1;

		return this;
	}

	public Matrix4 InitRotation(float x, float y, float z, float angle)
	{
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);

		m[0] = cos+x*x*(1-cos); m[1] = x*y*(1-cos)-z*sin; m[2] = x*z*(1-cos)+y*sin; m[3] = 0;
		m[4] = y*x*(1-cos)+z*sin; m[5] = cos+y*y*(1-cos);	m[6] = y*z*(1-cos)-x*sin; m[7] = 0;
		m[8] = z*x*(1-cos)-y*sin; m[9] = z*y*(1-cos)+x*sin; m[10] = cos+z*z*(1-cos); m[11] = 0;
		m[12] = 0;	m[13] = 0;	m[14] = 0;	m[15] = 1;

		return this;
	}

	public Matrix4 InitRotation(float x, float y, float z)
	{
		Matrix4 rx = new Matrix4();
		Matrix4 ry = new Matrix4();
		Matrix4 rz = new Matrix4();

		rz.m[0] = (float)Math.cos(z);rz.m[1] = -(float)Math.sin(z);rz.m[2] = 0;				rz.m[3] = 0;
		rz.m[4] = (float)Math.sin(z);rz.m[5] = (float)Math.cos(z);rz.m[6] = 0;					rz.m[7] = 0;
		rz.m[8] = 0;					rz.m[9] = 0;					rz.m[10] = 1;					rz.m[11] = 0;
		rz.m[12] = 0;					rz.m[13] = 0;					rz.m[14] = 0;					rz.m[15] = 1;

		rx.m[0] = 1;					rx.m[1] = 0;					rx.m[2] = 0;					rx.m[3] = 0;
		rx.m[4] = 0;					rx.m[5] = (float)Math.cos(x);rx.m[6] = -(float)Math.sin(x);rx.m[7] = 0;
		rx.m[8] = 0;					rx.m[9] = (float)Math.sin(x);rx.m[10] = (float)Math.cos(x);rx.m[11] = 0;
		rx.m[12] = 0;					rx.m[13] = 0;					rx.m[14] = 0;					rx.m[15] = 1;

		ry.m[0] = (float)Math.cos(y);ry.m[1] = 0;					ry.m[2] = -(float)Math.sin(y);ry.m[3] = 0;
		ry.m[4] = 0;					ry.m[5] = 1;					ry.m[6] = 0;					ry.m[7] = 0;
		ry.m[8] = (float)Math.sin(y);ry.m[9] = 0;					ry.m[10] = (float)Math.cos(y);ry.m[11] = 0;
		ry.m[12] = 0;					ry.m[13] = 0;					ry.m[14] = 0;					ry.m[15] = 1;

		m = rz.Mul(ry.Mul(rx)).GetM();

		return this;
	}

	public Matrix4 InitScale(float x, float y, float z)
	{
		m[0] = x;	m[1] = 0;	m[2] = 0;	m[3] = 0;
		m[4] = 0;	m[5] = y;	m[6] = 0;	m[7] = 0;
		m[8] = 0;	m[9] = 0;	m[10] = z;	m[11] = 0;
		m[12] = 0;	m[13] = 0;	m[14] = 0;	m[15] = 1;

		return this;
	}

	public Matrix4 InitPerspective(float fov, float aspectRatio, float zNear, float zFar)
	{
		float tanHalfFOV = (float)Math.tan(fov / 2);
		float zRange = zNear - zFar;

		m[0] = 1.0f / (tanHalfFOV * aspectRatio);	m[1] = 0;					m[2] = 0;	m[3] = 0;
		m[4] = 0;						m[5] = 1.0f / tanHalfFOV;	m[6] = 0;	m[7] = 0;
		m[8] = 0;						m[9] = 0;					m[10] = (-zNear -zFar)/zRange;	m[11] = 2 * zFar * zNear / zRange;
		m[12] = 0;						m[13] = 0;					m[14] = 1;	m[15] = 0;


		return this;
	}

	public Matrix4 InitOrthographic(float left, float right, float bottom, float top, float near, float far)
	{
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		m[0] = 2/width;m[1] = 0;	m[2] = 0;	m[3] = -(right + left)/width;
		m[4] = 0;	m[5] = 2/height;m[6] = 0;	m[7] = -(top + bottom)/height;
		m[8] = 0;	m[9] = 0;	m[10] = -2/depth;m[11] = -(far + near)/depth;
		m[12] = 0;	m[13] = 0;	m[14] = 0;	m[15] = 1;

		return this;
	}

	public Matrix4 InitRotation(Vector4f forward, Vector4f up)
	{
		Vector4f f = forward.Normalized();

		Vector4f r = up.Normalized();
		r = r.Cross(f);

		Vector4f u = f.Cross(r);

		return InitRotation(f, u, r);
	}

	public Matrix4 InitRotation(Vector4f forward, Vector4f up, Vector4f right)
	{
		Vector4f f = forward;
		Vector4f r = right;
		Vector4f u = up;

		m[0] = r.x;	m[1] = r.y;	m[2] = r.z;	m[3] = 0;
		m[4] = u.x;	m[5] = u.y;	m[6] = u.z;	m[7] = 0;
		m[8] = f.x;	m[9] = f.y;	m[10] = f.z;	m[11] = 0;
		m[12] = 0;		m[13] = 0;		m[14] = 0;		m[15] = 1;

		return this;
	}

	public Vector4f Transform(Vector4f r)
	{
		return new Vector4f(m[0] * r.x + m[1] * r.y + m[2] * r.z + m[3] * r.w,
		                    m[4] * r.x + m[5] * r.y + m[6] * r.z + m[7] * r.w,
		                    m[8] * r.x + m[9] * r.y + m[10] * r.z + m[11] * r.w,
							m[12] * r.x + m[13] * r.y + m[14] * r.z + m[15] * r.w);
	}

	public Matrix4 Mul(Matrix4 r)
	{
		Matrix4 res = new Matrix4();

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				res.Set(i, j, m[i] * r.Get(0, j) +
						m[i + 4] * r.Get(1, j) +
						m[i + 8] * r.Get(2, j) +
						m[i + 12] * r.Get(3, j));
			}
		}

		return res;
	}

	public float[] GetM()
	{
		float[] res = new float[16];

		for(int i = 0; i < 16; i++)
				res[i] = m[i];

		return res;
	}

	public float Get(int x, int y)
	{
		return m[x + 4 * y];
	}

	public void SetM(float[] m)
	{
		this.m = m;
	}

	public void Set(int x, int y, float value)
	{
		m[x + y * 4] = value;
	}
}
