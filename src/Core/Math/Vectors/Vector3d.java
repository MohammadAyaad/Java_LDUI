package Core.Math.Vectors;

public class Vector3d {
        double x;
        double y;
        double z;
        public Vector3d()
        {
                x = 0;
                y = 0;
                z = 0;
        }
        public Vector3d(double X,double Y,double Z)
        {
                x = X;
                y = Y;
                z = Z;
        }
        public Vector3d(Vector3d copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
        }
        public void add(Vector3d vector)
        {
                this.x += vector.x;
                this.y += vector.y;
                this.z += vector.z;
        }
        public void add(double num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
        }
        public void sub(Vector3d vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
                this.z -= vector.z;
        }
        public void sub(double num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
        }
        public void mul(Vector3d vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
                this.z *= vector.z;
        }
        public void mul(double num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
        }
        public void div(Vector3d vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
                this.z /= vector.z;
        }
        public void div(double num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
        }
        public double len(Vector3d vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)) + ((this.z * this.z) - (vector.z * vector.z)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
        }
        public double dot(Vector3d vector)
        {
                return (this.x * vector.x + this.y * vector.y + this.z * vector.z);
        }
        public double getAngleCos() throws Exception
        {
                throw new Exception("NOT TESTED");
                //return this.x / this.det();
        }
        public double GetAngleWithVector(Vector3d vector)
        {
                return ((this.dot(vector) / this.det()) / vector.det());
        }

}