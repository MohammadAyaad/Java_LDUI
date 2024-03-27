
package Core.Math.Vectors;

public class Vector4d {
        double x;
        double y;
        double z;
        double w;
        public Vector4d()
        {
                x = 0;
                y = 0;
                z = 0;
                w = 0;
        }
        public Vector4d(double X,double Y,double Z,double W)
        {
                x = X;
                y = Y;
                z = Z;
                w = W;
        }
        public Vector4d(Vector4d copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
                w = copy.w;
        }
        public void add(Vector4d vector)
        {
                this.x += vector.x;
                this.y += vector.y;
                this.z += vector.z;
                this.w += vector.w;
        }
        public void add(double num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
                this.w += num;
        }
        public void sub(Vector4d vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
                this.z -= vector.z;
                this.w -= vector.w;
        }
        public void sub(double num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
                this.w -= num;
        }
        public void mul(Vector4d vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
                this.z *= vector.z;
                this.w *= vector.w;
        }
        public void mul(double num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
                this.w *= num;
        }
        public void div(Vector4d vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
                this.z /= vector.z;
                this.w /= vector.w;
        }
        public void div(double num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
                this.w /= num;
        }
        public double len(Vector4d vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)) + ((this.z * this.z) - (vector.z * vector.z)) + ((this.w * this.w) - (vector.w * vector.w)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w));
        }
        public double dot(Vector4d vector)
        {
                return (this.x * vector.x + this.y * vector.y + this.z * vector.z + this.w * vector.w);
        }
        public double getAngleCos() throws Exception
        {
                throw new Exception("NOT TESTED");
                //return this.x / this.det();
        }
        public double GetAngleWithVector(Vector4d vector)
        {
                return ((this.dot(vector) / this.det()) / vector.det());
        }
}
