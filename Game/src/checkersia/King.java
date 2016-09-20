package checkersia;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;


/**
 *
 * @author S583396
 */
public class King extends Piece {

    private String type;
    private Color color;
    public King()
    {
        type = "King";
    }
    public King(Color c)
    {
        type = "King";
        color = c;
        this.setColor(c);
    }

    @Override
    public void ChangeType()
    {
        type = "King";
    }
    public String getType()
    {
        return type;
    }
    @Override
    public Color getColor(){
        return color;
    }
    public void makeColor(Color c)
    {
        setColor(c);
    }

}



