package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player {
    private Rectangle hitbox;
     Vector2 Player_position;
    private final float width = 32;
    private final float height = 32;
    Texture player_t;


    public Player() {
        Player_position = new Vector2(0, 0); // Initial position
        hitbox = new Rectangle(Player_position.x, Player_position.y, width, height); // Player's bounding box
        player_t = new Texture("Player.png");
    }

    public boolean move(Vector2 delta, Array<Rectangle> collisionRectangles) {
        Rectangle tempBox = new Rectangle(hitbox);
        tempBox.setPosition(Player_position.x + delta.x, Player_position.y + delta.y);
        for (Rectangle rect : collisionRectangles) {
            if (Intersector.overlaps(tempBox, rect)) {
                return true;
            }
        }
        

        Player_position.add(delta);
        hitbox.setPosition(Player_position.x, Player_position.y);
        return false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(player_t, Player_position.x, Player_position.y);
    }


    public Rectangle getHitbox() {
        return hitbox;
    }
    public float Return_x(){return Player_position.x;}
    public float Returnr_y(){return  Player_position.y;}
}
