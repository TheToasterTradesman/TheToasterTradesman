package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class Cursor {
    private float x;
    private float y;
    Vector2 Cursor_position;
    Vector2 Change_position;
    Texture Cursor_T;
    public Cursor(float x, float y){
        Cursor_position = new Vector2(x,y);
        Change_position = new Vector2(0,0);
        Cursor_T = new Texture("Cursor.png");
    }
    public void real_position(Vector2 delta){
        Change_position.x += 2*delta.x;
        Change_position.y += 2*delta.y;
       Cursor_position.x =  Gdx.input.getX() + Change_position.x;
       Cursor_position.y =  1088 -Gdx.input.getY() + Change_position.y;

    }
    public void tracking(float x , float y){


    }


    public void render(SpriteBatch batch){
        batch.draw(Cursor_T,Cursor_position.x,Cursor_position.y);
    }
    public float Cursor_x(){return Cursor_position.x;}
    public float Cursor_y(){return Cursor_position.y;}
    public void Set_x(float x){Cursor_position.x += x;}
    public void Set_y(float y){Cursor_position.y += y;}
}
