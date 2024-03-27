package Core.Math.Transform;

import Core.Math.Matrices.Matrix4;
import Core.Math.Vectors.Vector4f;

public class Transform4 {
	private Vector4f   Location;
	private Quaternion4 Rotation;
	private Vector4f   Scale;

	public Transform4()
	{
		this(new Vector4f(0,0,0,0));
	}

	public Transform4(Vector4f pos)
	{
		this(pos, new Quaternion4(0,0,0,1), new Vector4f(1,1,1,1));
	}

	public Transform4(Vector4f pos, Quaternion4 rot, Vector4f scale)
	{
		Location = pos;
		Rotation = rot;
		Scale = scale;
	}

	public Transform4 SetPos(Vector4f pos)
	{
		return new Transform4(pos, Rotation, Scale);
	}

	public Transform4 Rotate(Quaternion4 rotation)
	{
		return new Transform4(Location, rotation.Mul(Rotation).Normalized(), Scale);
	}

	public Transform4 LookAt(Vector4f point, Vector4f up)
	{
		return Rotate(GetLookAtRotation(point, up));
	}

	public Quaternion4 GetLookAtRotation(Vector4f point, Vector4f up)
	{
		return new Quaternion4(new Matrix4().InitRotation(point.Sub(Location).Normalized(), up));
	}

	public Matrix4 GetTransform4ation()
	{
		Matrix4 translationMatrix = new Matrix4().InitTranslation(Location.x, Location.y, Location.z);
		Matrix4 rotationMatrix = Rotation.ToRotationMatrix();
		Matrix4 scaleMatrix = new Matrix4().InitScale(Scale.x, Scale.y, Scale.z);

		return translationMatrix.Mul(rotationMatrix.Mul(scaleMatrix));
	}

	public Vector4f GetTransform4edPos()
	{
		return Location;
	}

	public Quaternion4 GetTransform4edRot()
	{
		return Rotation;
	}

	public Vector4f GetPos()
	{
		return Location;
	}

	public Quaternion4 GetRot()
	{
		return Rotation;
	}

	public Vector4f GetScale()
	{
		return Scale;
	}
}
