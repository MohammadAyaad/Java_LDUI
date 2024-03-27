package Core.Math.Vectors;

public class Vector3f {
        public float x;
        public float y;
        public float z;
        public Vector3f()
        {
                x = 0;
                y = 0;
                z = 0;
        }
        public Vector3f(float X,float Y,float Z)
        {
                x = X;
                y = Y;
                z = Z;
        }
        public Vector3f(Vector3f copy)
        {
                x = copy.x;
                y = copy.y;
                z = copy.z;
        }
        public void add(Vector3f vector)
        {
                this.x += vector.x;
                this.y += vector.y;
                this.z += vector.z;
        }
        public void add(float num)
        {
                this.x += num;
                this.y += num;
                this.z += num;
        }
        public static Vector3f add(Vector3f a,Vector3f b)
        {
        	return new Vector3f(
        			a.x + b.x,
        			a.y + b.y,
        			a.z + b.z);
        }
        public void sub(Vector3f vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
                this.z -= vector.z;
        }
        public void sub(float num)
        {
                this.x -= num;
                this.y -= num;
                this.z -= num;
        }
        public static Vector3f sub(Vector3f a,Vector3f b)
        {
        	return new Vector3f(a.x - b.x,a.y - b.y,a.z - b.z);
        }
        public void mul(Vector3f vector)
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
        public static Vector3f mul(Vector3f a,Vector3f b)
        {
        	return new Vector3f(a.x * b.x,a.y * b.y,a.z * b.z);
        }

        public static Vector3f mul(Vector3f a,float b)
        {
        	return new Vector3f(a.x * b,a.y * b,a.z * b);
        }
        public void div(Vector3f vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
                this.z /= vector.z;
        }
        public void div(float num)
        {
                this.x /= num;
                this.y /= num;
                this.z /= num;
        }
        public double len(Vector3f vector)
        {
        	float xd = (this.x - vector.x);
        	float yd = (this.y - vector.y);
        	float zd = (this.z - vector.z);
        	return Math.sqrt((xd * xd) + (yd * yd) + (zd * zd));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
        }
        public double dot(Vector3f vector)
        {
                return (this.x * vector.x + this.y * vector.y + this.z * vector.z);
        }
        public Vector3f cross(Vector3f r)
        {
        	float x = this.y * r.z - this.z * r.y;
        	float y = this.z * r.x - this.x * r.z;
        	float z = this.x * r.y - this.y * r.x;
        	
        	return new Vector3f(x,y,z);
        }
        public Vector3f rotate()
        {
        	return null;
        }
        public double getAngleCos() throws Exception
        {
                throw new Exception("NOT TESTED");
                                //return this.x / this.det();
        }
        public double GetAngleWithVector(Vector3f vector)
        {
                return ((this.dot(vector) / this.det()) / vector.det());
        }
        @Override
        public String toString()
        {
        	return "(" + this.x + "," + this.y + "," + this.z + ")";
        }
        
}