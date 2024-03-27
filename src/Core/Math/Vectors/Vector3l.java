package Core.Math.Vectors;

public class Vector3l {
        long x;
        long y;
        long z;
        public Vector3l()
        {
                x = 0;
                y = 0;
                z = 0;
        }
        public Vector3l(long X,long Y,long Z)
        {
                x = X;
                y = Y;
                z = Z;
        }
        public Vector3l(Vector3l copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
        }
        public void add(Vector3l vector)
        {
                this.x += vector.x;
                this.y += vector.y;
                this.z += vector.z;
        }
        public void add(long num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
        }
        public void sub(Vector3l vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
                this.z -= vector.z;
        }
        public void sub(long num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
        }
        public void mul(Vector3l vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
                this.z *= vector.z;
        }
        public void mul(long num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
        }
        public void div(Vector3l vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
                this.z /= vector.z;
        }
        public void div(long num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
        }
        public double len(Vector3l vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)) + ((this.z * this.z) - (vector.z * vector.z)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
        }
        public double dot(Vector3l vector)
        {
                return (this.x * vector.x + this.y * vector.y + this.z * vector.z);
        }
        public double getAngleCos() throws Exception
    {
                throw new Exception("NOT TESTED");
                                //return this.x / this.det();
        }
        public double GetAngleWithVector(Vector3l vector)
        {
                return ((this.dot(vector) / this.det()) / vector.det());
        }

}
