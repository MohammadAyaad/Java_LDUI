package Core.Math.Points;

public class Point4i {
	public int x;
	public int y;
	public int z;
	public int w;
        public Point4i()
        {
                x = 0;
                y = 0;
                z = 0;
                w = 0;
        }
        public Point4i(int X,int Y,int Z,int W)
        {
                x = X;
                y = Y;
                z = Z;
                w = W;
        }
        public Point4i(Point4i copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
                w = copy.w;
        }
        public void add(Point4i point)
        {
                this.x += point.x;
                this.y += point.y;
                this.z += point.z;
                this.w += point.w;
        }
        public void add(int num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
                this.w += num;
        }
        public void sub(Point4i point)
        {
                this.x -= point.x;
                this.y -= point.y;
                this.z -= point.z;
                this.w -= point.w;
        }
        public void sub(int num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
                this.w -= num;
        }
        public void mul(Point4i point)
        {
                this.x *= point.x;
                this.y *= point.y;
                this.z *= point.z;
                this.w *= point.w;
        }
        public void mul(int num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
                this.w *= num;
        }
        public void div(Point4i point)
        {
                this.x /= point.x;
                this.y /= point.y;
                this.z /= point.z;
                this.w /= point.z;
        }
        public void div(int num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
                this.w /= num;
        }
        public double len(Point4i point)
        {
                return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)) + ((this.w * this.w) - (point.w * point.w)));
        }

}
