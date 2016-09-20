package checkersia;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.awt.Color;

/**
 *
 * @author S583396
 */
public class Main {


    private Actor []white = new Actor[8];
    private Actor []black = new Actor[8];
    private World wld = null;

    public static void main(String[] args) {
        Menu mainmenu = new Menu();
        mainmenu.show();
        // TODO code application logic here
    }

    public static void CheckerRun(Color you, Color opnt,boolean first, Menu menu)
    {

        Color fst = null;
        Color snd = null;
       if(first==false){
           fst=you;
           snd=opnt;}
       else if(first = true)
        {
            fst=opnt;
            snd=you;}

         Piece piece1 = new Piece(snd);
         Piece piece2 = new Piece(snd);
         Piece piece3 = new Piece(snd);
         Piece piece4 = new Piece(snd);
         Piece piece5 = new Piece(snd);
         Piece piece6 = new Piece(snd);
         Piece piece7 = new Piece(snd);
         Piece piece8 = new Piece(snd);
         Piece piece9 = new Piece(snd);
         Piece piece10 = new Piece(snd);
         Piece piece11 = new Piece(snd);
         Piece piece12 = new Piece(snd);


        //snd pawns//
         Piece piece13 = new Piece(fst);
         Piece piece14 = new Piece(fst);
         Piece piece15 = new Piece(fst);
         Piece piece16 = new Piece(fst);
         Piece piece17 = new Piece(fst);
         Piece piece18 = new Piece(fst);
         Piece piece19 = new Piece(fst);
         Piece piece20 = new Piece(fst);
         Piece piece21 = new Piece(fst);
         Piece piece22 = new Piece(fst);
         Piece piece23 = new Piece(fst);
         Piece piece24 = new Piece(fst);

         //black spaces on board
        /* Space sp1 = new Space();
         Space sp2 = new Space();
         Space sp3 = new Space();
         Space sp4 = new Space();
         Space sp5 = new Space();
         Space sp6 = new Space();
         Space sp7 = new Space();
         Space sp8 = new Space();*/




       Actor[] white = {piece1,piece2,piece3,piece4,piece5,piece6,piece7,piece8,piece9,piece10,piece11,piece12};
       Actor[] black = {piece13,piece14,piece15,piece16,piece17,piece18,piece19,piece20,piece21,piece22,piece23,piece24};
     //  Actor[]spaces = {sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8};
       World World = new CheckerWorld(new BoundedGrid<Actor>(8,8),black,white,menu,fst,snd);

        //2nd PLAYER'S pieces//
        World.add(new Location(0,1),white[0]);
        World.add(new Location(0,3),white[1]);
        World.add(new Location(0,5),white[2]);
        World.add(new Location(0,7),white[3]);
        World.add(new Location(1,0),white[4]);
        World.add(new Location(1,2),white[5]);
        World.add(new Location(1,4),white[6]);
        World.add(new Location(1,6),white[7]);
        World.add(new Location(2,1),white[8]);
        World.add(new Location(2,3),white[9]);
        World.add(new Location(2,5),white[10]);
        World.add(new Location(2,7),white[11]);

        //1st player's pieces//
        World.add(new Location(5,0),black[0]);
        World.add(new Location(5,2),black[1]);
        World.add(new Location(5,4),black[2]);
        World.add(new Location(5,6),black[3]);
        World.add(new Location(6,1),black[4]);
        World.add(new Location(6,3),black[5]);
        World.add(new Location(6,5),black[6]);
        World.add(new Location(6,7),black[7]);
        World.add(new Location(7,0),black[8]);
        World.add(new Location(7,2),black[9]);
        World.add(new Location(7,4),black[10]);
        World.add(new Location(7,6),black[11]);

        //empty black spaces//
        /*World.add(new Location(3,0),spaces[0]);
        World.add(new Location(3,2),spaces[1]);
        World.add(new Location(3,4),spaces[2]);
        World.add(new Location(3,6),spaces[3]);
        World.add(new Location(4,1),spaces[4]);
        World.add(new Location(4,3),spaces[5]);
        World.add(new Location(4,5),spaces[6]);
        World.add(new Location(4,7),spaces[7]);*/

        World.show();
    }

}
