
package Core.Math.Points;

public class Point3i {
	public int x;
	public int y;
	public int z;
        public Point3i()
        {
                x = 0;
                y = 0;
                z = 0;
        }
        public Point3i(int X,int Y,int Z)
        {
                x = X;
                y = Y;
                z = Z;
        }
        public Point3i(Point3i copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
        }
        public void add(Point3i point)
        {
                this.x += point.x;
                this.y += point.y;
                this.z += point.z;
        }
        public void add(int num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
        }
        public void sub(Point3i point)
        {
                this.x -= point.x;
                this.y -= point.y;
                this.z -= point.z;
        }
        public void sub(int num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
        }
        public void mul(Point3i point)
        {
                this.x *= point.x;
                this.y *= point.y;
                this.z *= point.z;
        }
        public void mul(int num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
        }
        public void div(Point3i point)
        {
                this.x /= point.x;
                this.y /= point.y;
                this.z /= point.z;
        }
        public void div(int num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
        }
        public double len(Point3i point)
        {
                return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)));
        }

}