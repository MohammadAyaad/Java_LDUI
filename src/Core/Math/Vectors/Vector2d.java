package Core.Math.Vectors;

public class Vector2d {
        double x;
        double y;
        public Vector2d()
        {
                x = 0;
                y = 0;
        }
        public Vector2d(double X,double Y)
        {
                x = X;
                y = Y;
        }
        public Vector2d(Vector2d copy)
        {
                x = copy.x;
                y = copy.y;
        }
        public void add(Vector2d vector)
        {
                this.x += vector.x;
                this.y += vector.y;
        }
        public void add(double num)
        {
                this.x += num;
                this.y += num;
        }
        public void sub(Vector2d vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
        }
        public void sub(double num)
        {
                this.x -= num;
                this.y -= num;
        }
        public void mul(Vector2d vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
        }
        public void mul(double num)
        {
                this.x *= num;
                this.y *= num;
        }
        public void div(Vector2d vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
        }
        public void div(double num)
        {
                this.x /= num;
                this.y /= num;
        }
        public double len(Vector2d vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y));
        }
        public double dot(Vector2d vector)
        {
                return (this.x * vector.x + this.y * vector.y);
        }
        public double getAngleCos()
        {
                return this.x/this.det();
        }
        public double GetAngleWithVector(Vector2d vector)
        {
                return ((this.dot(vector)/this.det())/vector.det());
        }

}