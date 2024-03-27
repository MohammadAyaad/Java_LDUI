package Core.Math.Points;

public class Point4d {
	public double x;
	public double y;
	public double z;
	public double w;
        public Point4d()
        {
                x = 0;
                y = 0;
                z = 0;
                w = 0;
        }
        public Point4d(double X,double Y,double Z,double W)
        {
                x = X;
                y = Y;
                z = Z;
                w = W;
        }
        public Point4d(Point4d copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
                w = copy.w;
        }
        public void add(Point4d point)
        {
                this.x += point.x;
                this.y += point.y;
                this.z += point.z;
                this.w += point.w;
        }
        public void add(double num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
                this.w += num;
        }
        public void sub(Point4d point)
        {
                this.x -= point.x;
                this.y -= point.y;
                this.z -= point.z;
                this.w -= point.w;
        }
        public void sub(double num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
                this.w -= num;
        }
        public void mul(Point4d point)
        {
                this.x *= point.x;
                this.y *= point.y;
                this.z *= point.z;
                this.w *= point.w;
        }
        public void mul(double num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
                this.w *= num;
        }
        public void div(Point4d point)
        {
                this.x /= point.x;
                this.y /= point.y;
                this.z /= point.z;
                this.w /= point.z;
        }
        public void div(double num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
                this.w /= num;
        }
        public double len(Point4d point)
        {
                return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)) + ((this.w * this.w) - (point.w * point.w)));
        }

}
