package Core.Math.Vectors;

public class Vector2f {
        public float x;
        public float y;
        public Vector2f()
        {
                x = 0;
                y = 0;
        }
        public Vector2f(float X,float Y)
        {
                x = X;
                y = Y;
        }
        public Vector2f(Vector2f copy)
        {
                x = copy.x;
                y = copy.y;
        }
        public void add(Vector2f vector)
        {
                this.x += vector.x;
                this.y += vector.y;
        }
        public static Vector2f add(Vector2f v1,Vector2f v2)
        {
        	return new Vector2f(v1.x + v2.x ,v1.y + v2.y);
        }

        public static Vector2f add(Vector2f v1,float number)
        {
        	return new Vector2f(v1.x + number ,v1.y + number);
        }
        public void add(float num)
        {
                this.x += num;
                this.y += num;
        }
        public void sub(Vector2f vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
        }
        public void sub(float num)
        {
                this.x -= num;
                this.y -= num;
        }
        public void mul(Vector2f vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
        }
        public void mul(float num)
        {
                this.x *= num;
                this.y *= num;
        }
        public void div(Vector2f vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
        }
        public void div(float num)
        {
                this.x /= num;
                this.y /= num;
        }
        public double len(Vector2f vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y));
        }
        public double dot(Vector2f vector)
        {
                return (this.x * vector.x + this.y * vector.y);
        }
        public Vector2f Normalize()
        {
        	this.div((float)this.det());
        	return this;
        }
        public Vector2f rotate(float angle)
        {
        	double radians = Math.toRadians(angle);
        	double cos = Math.cos(radians);
        	double sin = Math.sin(radians);
        	return new Vector2f((float)(x * cos - y * sin),(float)(x * sin + y * cos));
        }
        public double getAngleCos()
        {
                return this.x/this.det();
        }
        public double GetAngleWithVector(Vector2f vector)
        {
                return ((this.dot(vector)/this.det())/vector.det());
        }

        @Override
        public String toString()
        {
        	return "(" + this.x + "," + this.y + ")";
        }

}