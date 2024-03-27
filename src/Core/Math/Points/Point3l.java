package Core.Math.Points;

public class Point3l {
	public long x;
	public long y;
	public long z;
        public Point3l()
        {
                x = 0;
                y = 0;
                z = 0;
        }
        public Point3l(long X,long Y,long Z)
        {
                x = X;
                y = Y;
                z = Z;
        }
        public Point3l(Point3l copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
        }
        public void add(Point3l point)
        {
                this.x += point.x;
                this.y += point.y;
                this.z += point.z;
        }
        public void add(long num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
        }
        public void sub(Point3l point)
        {
                this.x -= point.x;
                this.y -= point.y;
                this.z -= point.z;
        }
        public void sub(long num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
        }
        public void mul(Point3l point)
        {
                this.x *= point.x;
                this.y *= point.y;
                this.z *= point.z;
        }
        public void mul(long num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
        }
        public void div(Point3l point)
        {
                this.x /= point.x;
                this.y /= point.y;
                this.z /= point.z;
        }
        public void div(long num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
        }
        public double len(Point3l point)
        {
                return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)));
        }

}