package Core.Math.Vectors;

public class Vector3i {
        int x;
        int y;
        int z;
        public Vector3i()
        {
                x = 0;
                y = 0;
                z = 0;
        }
        public Vector3i(int X,int Y,int Z)
        {
                x = X;
                y = Y;
                z = Z;
        }
        public Vector3i(Vector3i copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
        }
        public void add(Vector3i vector)
        {
                this.x += vector.x;
                this.y += vector.y;
                this.z += vector.z;
        }
        public void add(int num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
        }
        public void sub(Vector3i vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
                this.z -= vector.z;
        }
        public void sub(int num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
        }
        public void mul(Vector3i vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
                this.z *= vector.z;
        }
        public void mul(int num)
        {
                this.x *= num;
                this.y *= num;
                this.z *= num;
        }
        public void div(Vector3i vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
                this.z /= vector.z;
        }
        public void div(int num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
        }
        public double len(Vector3i vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)) + ((this.z * this.z) - (vector.z * vector.z)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
        }
        public double dot(Vector3i vector)
        {
                return (this.x * vector.x + this.y * vector.y + this.z * vector.z);
        }
        public double getAngleCos() throws Exception
        {
                throw new Exception("NOT TESTED");
                                //return this.x / this.det();
        }
        public double GetAngleWithVector(Vector3i vector)
        {
                return ((this.dot(vector) / this.det()) / vector.det());
        }
}
