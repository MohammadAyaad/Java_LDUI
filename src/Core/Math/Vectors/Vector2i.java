package Core.Math.Vectors;

public class Vector2i {
        int x;
        int y;
        public Vector2i()
        {
                x = 0;
                y = 0;
        }
        public Vector2i(int X,int Y)
        {
                x = X;
                y = Y;
        }
        public Vector2i(Vector2i copy)
        {
                x = copy.x;
                y = copy.y;
        }
        public void add(Vector2i vector)
        {
                this.x += vector.x;
                this.y += vector.y;
        }
        public void add(int num)
        {
                this.x += num;
                this.y += num;
        }
        public void sub(Vector2i vector)
        {
                this.x -= vector.x;
                this.y -= vector.y;
        }
        public void sub(int num)
        {
                this.x -= num;
                this.y -= num;
        }
        public void mul(Vector2i vector)
        {
                this.x *= vector.x;
                this.y *= vector.y;
        }
        public void mul(int num)
        {
                this.x *= num;
                this.y *= num;
        }
        public void div(Vector2i vector)
        {
                this.x /= vector.x;
                this.y /= vector.y;
        }
        public void div(int num)
        {
                this.x /= num;
                this.y /= num;
        }
        public double len(Vector2i vector)
        {
                return Math.sqrt(((this.x * this.x) - (vector.x * vector.x)) + ((this.y * this.y) - (vector.y * vector.y)));
        }
        public double det()
        {
                return Math.sqrt((this.x * this.x) + (this.y * this.y));
        }
        public double dot(Vector2i vector)
        {
                return (this.x * vector.x + this.y * vector.y);
        }
        public double getAngleCos()
        {
                return this.x/this.det();
        }
        public double GetAngleWithVector(Vector2i vector)
        {
                return ((this.dot(vector)/this.det())/vector.det());
        }

}
