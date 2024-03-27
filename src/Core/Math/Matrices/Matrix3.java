package Core.Math.Matrices;

import Core.Math.Vectors.Vector3f;

public class Matrix3 {
	private float[] mat;
	public Matrix3(float[] m)
	{
		mat = m;
	}
	public static Matrix3 GetInitMatrix()
	{
		return new Matrix3(new float[]
				{
			1,0,0,
			0,1,0,
			0,0,1,
		});
	}
	public static Matrix3 ScaleMatrix(Vector3f Scale)
	{
		return new Matrix3(new float[]
				{
			Scale.x,0,0,
			0,Scale.y,0,
			0,0,Scale.z,
		});
	}
	
	public Matrix3 rotate(float x, float y, float z)
	{
		mul(Matrix3.mul( new Matrix3(
			new float[] {
				(float)Math.cos(z),-(float)Math.sin(z),0,
				(float)Math.sin(z),(float)Math.cos(z),0,
				0,0,1
			}
		),Matrix3.mul(new Matrix3(new float[] {
				(float)Math.cos(y),0, -(float)Math.sin(y),
				0,1,0,
				(float)Math.sin(y),0,(float)Math.cos(y)
			}),new Matrix3(new float[] {
				1,0,0,
				0,(float)Math.cos(x),-(float)Math.sin(x),
				0,(float)Math.sin(x),(float)Math.cos(x)
			}))));

		return this;
	}

	public static Matrix3 getRotation(float x, float y, float z)
	{
		return Matrix3.mul( new Matrix3(
			new float[] {
				(float)Math.cos(z),-(float)Math.sin(z),0,
				(float)Math.sin(z),(float)Math.cos(z),0,
				0,0,1
			}
		),Matrix3.mul(new Matrix3(new float[] {
				(float)Math.cos(y),0, -(float)Math.sin(y),
				0,1,0,
				(float)Math.sin(y),0,(float)Math.cos(y)
			}),new Matrix3(new float[] {
				1,0,0,
				0,(float)Math.cos(x),-(float)Math.sin(x),
				0,(float)Math.sin(x),(float)Math.cos(x)
			})));
	}
	
	public Matrix3 scale(Vector3f p)
	{
		ScaleMatrix(p).mul(this);
		return this;
	}
	public Matrix3 mul(Matrix3 m)
	{
		Matrix3 n = new Matrix3(new float[]
				{
					
							this.mat[0] * m.mat[0] + this.mat[1] * m.mat[3] + this.mat[2] * m.mat[6],
							this.mat[0] * m.mat[1] + this.mat[1] * m.mat[4] + this.mat[2] * m.mat[7],
							this.mat[0] * m.mat[2] + this.mat[1] * m.mat[5] + this.mat[2] * m.mat[8],
					
							this.mat[3] * m.mat[0] + this.mat[4] * m.mat[3] + this.mat[5] * m.mat[6],
							this.mat[3] * m.mat[1] + this.mat[4] * m.mat[4] + this.mat[5] * m.mat[7],
							this.mat[3] * m.mat[2] + this.mat[4] * m.mat[5] + this.mat[5] * m.mat[8],
					
							this.mat[6] * m.mat[0] + this.mat[7] * m.mat[3] + this.mat[8] * m.mat[6],
							this.mat[6] * m.mat[1] + this.mat[7] * m.mat[4] + this.mat[8] * m.mat[7],
							this.mat[6] * m.mat[2] + this.mat[7] * m.mat[5] + this.mat[8] * m.mat[8],
				});
		this.mat = n.mat;
		return this;
	}
	public static Matrix3 mul(Matrix3 a,Matrix3 b)
	{
		return new Matrix3(new float[]
				{
					
							a.mat[0] * b.mat[0] + a.mat[1] * b.mat[3] + a.mat[2] * b.mat[6],
							a.mat[0] * b.mat[1] + a.mat[1] * b.mat[4] + a.mat[2] * b.mat[7],
							a.mat[0] * b.mat[2] + a.mat[1] * b.mat[5] + a.mat[2] * b.mat[8],
					
							a.mat[3] * b.mat[0] + a.mat[4] * b.mat[3] + a.mat[5] * b.mat[6],
							a.mat[3] * b.mat[1] + a.mat[4] * b.mat[4] + a.mat[5] * b.mat[7],
							a.mat[3] * b.mat[2] + a.mat[4] * b.mat[5] + a.mat[5] * b.mat[8],
					
							a.mat[6] * b.mat[0] + a.mat[7] * b.mat[3] + a.mat[8] * b.mat[6],
							a.mat[6] * b.mat[1] + a.mat[7] * b.mat[4] + a.mat[8] * b.mat[7],
							a.mat[6] * b.mat[2] + a.mat[7] * b.mat[5] + a.mat[8] * b.mat[8],
				});
	}
	
	public Vector3f TransformVector(Vector3f vector)
	{
		return new Vector3f(
				vector.x * mat[0] + vector.y * mat[1] + vector.z * mat[2],
				vector.x * mat[3] + vector.y * mat[4] + vector.z * mat[5],
				vector.x * mat[6] + vector.y * mat[7] + vector.z * mat[8]
				);
	}
	
	@Override
	public String toString()
	{
		return       "[" + mat[0] + " " + mat[1] + " " + mat[2] 
				+ "]\n[" + mat[3] + " " + mat[4] + " " + mat[5]
				+ "]\n[" + mat[6] + " " + mat[7] + " " + mat[8] + "]\n";
	}
}
