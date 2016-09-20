package checkersia;


import java.awt.Color;
import info.gridworld.actor.Actor;


public class Piece extends Actor{
    private String type;
    private Color color;
    public Piece()
    {
        type = "Piece";
    }
    public Piece(Color c)
    {
        type = "Piece";
        color = c;
        this.setColor(c);
    }

    public void ChangeType()
    {
        type = "King";
    }
    public String getType()
    {
        return type;
    }

}
