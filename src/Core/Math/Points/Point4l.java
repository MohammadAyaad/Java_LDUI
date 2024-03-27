package Core.Math.Points;

public class Point4l {
	public long x;
	public long y;
	public long z;
	public long w;
        public Point4l()
        {
                x = 0;
                y = 0;
                z = 0;
                w = 0;
        }
        public Point4l(long X,long Y,long Z,long W)
        {
                x = X;
                y = Y;
                z = Z;
                w = W;
        }
        public Point4l(Point4l copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
                w = copy.w;
        }
        public void add(Point4l point)
        {
                this.x += point.x;
                this.y += point.y;
                this.z += point.z;
                this.w += point.w;
        }
        public void add(long num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
                this.w += num;
        }
        public void sub(Point4l point)
        {
                this.x -= point.x;
                this.y -= point.y;
                this.z -= point.z;
                this.w -= point.w;
        }
        public void sub(long num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
                this.w -= num;
        }
        public void mul(Point4l point)
        {
                this.x *= point.x;
                this.y *= point.y;
                this.z *= point.z;
                this.w *= point.w;
        }
        public void mul(long num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
                this.w *= num;
        }
        public void div(Point4l point)
        {
                this.x /= point.x;
                this.y /= point.y;
                this.z /= point.z;
                this.w /= point.z;
        }
        public void div(long num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
                this.w /= num;
        }
        public double len(Point4l point)
        {
                return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)) + ((this.w * this.w) - (point.w * point.w)));
        }

}

