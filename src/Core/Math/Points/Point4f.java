package Core.Math.Points;

public class Point4f {
	public float x;
	public float y;
	public float z;
	public float w;
        public Point4f()
        {
                x = 0;
                y = 0;
                z = 0;
                w = 0;
        }
        public Point4f(float X,float Y,float Z,float W)
        {
                x = X;
                y = Y;
                z = Z;
                w = W;
        }
        public Point4f(Point4f copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
                w = copy.w;
        }
        public void add(Point4f point)
        {
                this.x += point.x;
                this.y += point.y;
                this.z += point.z;
                this.w += point.w;
        }
        public void add(float num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
                this.w += num;
        }
        public void sub(Point4f point)
        {
                this.x -= point.x;
                this.y -= point.y;
                this.z -= point.z;
                this.w -= point.w;
        }
        public void sub(float num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
                this.w -= num;
        }
        public void mul(Point4f point)
        {
                this.x *= point.x;
                this.y *= point.y;
                this.z *= point.z;
                this.w *= point.w;
        }
        public void mul(float num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
                this.w *= num;
        }
        public void div(Point4f point)
        {
                this.x /= point.x;
                this.y /= point.y;
                this.z /= point.z;
                this.w /= point.z;
        }
        public void div(float num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
                this.w /= num;
        }
        public double len(Point4f point)
        {
                return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)) + ((this.w * this.w) - (point.w * point.w)));
        }

}
