package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
public class Bullet {
    private Rectangle hitbox;
    private float x;
    private float y;

    private float target_x;
    private float target_y;
    private float width = 10;
    private  float height = 10;
    private float x_vel = 0;
    private float y_vel = 0;
    private float bullet_speed;
    private float angle;
    Texture bullet_t;
 public Bullet(float x,float y,float target_x, float target_y){
     hitbox = new Rectangle(x,y,width,height);
     this.y = y;
     this.x = x;

     this.target_x = target_x  ;
     this.target_y = target_y ;
     float new_x = target_x-this.x;
     float new_y = (target_y)-this.y;
     float m = (float)Math.sqrt(new_x * new_x + new_y * new_y);
     this.x_vel = (new_x / m);
     this.y_vel = (new_y / m);
     this.angle = (float) Math.toDegrees(Math.atan(new_y/new_x));


     bullet_t = new Texture("Bullet.png");

    }
    public boolean move( Array<Rectangle> collisionRectangles){
     Rectangle tempox = new Rectangle(hitbox);
     tempox.setPosition(x += x_vel, y +=y_vel);

     for (Rectangle rect : collisionRectangles) {
            if (Intersector.overlaps(tempox, rect)) {
                return true;
            }
        }
     x += x_vel;
     y += y_vel;
     hitbox.setPosition(x,y);
     return false;
    }
    public void render(SpriteBatch batch){

        batch.draw(bullet_t,x,y,0,0,width+ 30,height,1,1,angle,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false,false);
    }




    public Rectangle getHitbox(){return hitbox;}
}
