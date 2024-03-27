package Core.Math.Vectors;

public class Vector2l {
        long x;
        long y;
        public Vector2l()
        {
                x = 0;
                y = 0;
        }
        public Vector2l(long X,long Y)
        {
                x = X;
                y = Y;
        }
        public Vector2l(Vector2l copy)
        {
                x = copy.x;
                y = copy.y;
        }
        public void add(Vector2l vector)
        {
                this.x += vector.x;
                this.y += vector.y;
        }
        public void add(long num)
        {
                this.x += num;
                this.y += num;
        }
        public void sub(Vector2l vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
        }
        public void sub(long num)
        {
                this.x -= num;
                this.y -= num;
        }
        public void mul(Vector2l vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
        }
        public void mul(long num)
        {
                this.x *= num;
                this.y *= num;
        }
        public void div(Vector2l vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
        }
        public void div(long num)
        {
                this.x /= num;
                this.y /= num;
        }
        public double len(Vector2l vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y));
        }
        public double dot(Vector2l vector)
        {
                return (this.x * vector.x + this.y * vector.y);
        }
        public double getAngleCos()
        {
                return this.x/this.det();
        }
        public double GetAngleWithVector(Vector2l vector)
        {
                return ((this.dot(vector)/this.det())/vector.det());
        }

}
