package checkersia;






import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static java.lang.System.*;


public class CheckerWorld extends ActorWorld{

    public static Scanner keyb = new Scanner(in);
    private BoundedGrid<Actor> boundedGrid;
    private boolean isValid = false;
    private boolean TakeAgain = false;
    private Actor[] black, white,spaces = null;
    private Actor piece= null;
    private int loccount = 0;
    private int count=0;
    private Color c1,c2 = null;
    private String name1, name2 = null;
    private Menu mn = null;
    boolean take;
    boolean hasClicked;
    Grid<Actor> gr = null;

    public CheckerWorld(BoundedGrid<Actor> bg, Actor[]b, Actor[]w, Menu menu,Color fst, Color snd)
    {
            setGrid(bg);
            gr = getGrid();
            white = w;
            black = b;
            for(int x = 0;x<8;x++)
            {
            black[x] = b[x];
            white[x] = w[x];
            }
            //spaces = sp;
            c1=w[0].getColor();
            c2=b[0].getColor();
            name1=menu.getName(c1);
            name2=menu.getName(c2);
            setMessage("This is the 1st move! - " + name1+" Player click the piece you would like to move!");
    }
public boolean isEmpty(Location loc)
    {
        Actor atloc = gr.get(loc);
        if(atloc==null){
            return true;
        }
               /* for(int x = 0; x<8;x++)
                {
/*                    if((atloc==spaces[x])||(setType(atloc).equals("Empty Space")))
                    {
                        return true;
                    }*/
                    /*if((atloc==black[x])||(atloc==white[x]))
                    {
                        System.out.println("Loc is full");
                        return false;
                    }*/
        if(atloc instanceof Piece){
            //System.out.println("Loc at "+loc+ " is full");
            return false;}
        return true;
    }
    public void movePiece(Actor piece, Location start, Location loc)
    {
        String a = "Press 'ENTER' to confirm your move, press 'BACKSPACE' to cancel your move";
        out.println(setType(piece));
        isValid = false;
        if(isValidinCheckers(piece, start, loc,setType(piece),setColor(piece))==true)//calls the isValidinCheckers method, which checks if the move designated by the two locations is valid, according to the rules of chess
        {
            isValid=true;
//if it is valid, then it sets the isValid condition to true (which will be checked later), and tells the player to press enter to confirm their move, or backspace to reject it. It then waits for those responses before doing anything.

          // if(!setType(piece).equalsIgnoreCase("King")){kingloc = black[6].getLocation(); kingloc2 = white[6].getLocation();}
          //  if((((count)%2==1)&&(isInCheck(kingloc,1)==false))||(((count)%2==0)&&(isInCheck(kingloc2,0)==false))){
            if(((canTake(piece.getColor())==true)&&(!((loc.getRow()==piece.getLocation().getRow()+2)||(loc.getRow()==piece.getLocation().getRow()-2)))))//if the location being moved to is two up or two down (meaning there is a difference of 2 by rows), then the move is piece taking move
            {
                isValid=false;
                take=false;
                a="You can take a piece, so you must take it. \nClick a piece, make a move, and this time capture the piece.";
                loccount=0;
                hasClicked=false;
                }
             else if(((canTake(piece.getColor())==true)&&(((loc.getRow()==piece.getLocation().getRow()+2)||(loc.getRow()==piece.getLocation().getRow()-2))))){
                if(gr.get(getTaken(piece, piece.getLocation(),loc,setType(piece), setColor(piece)))==null){
                    a = "You can't take that piece, because it is YOUR piece. You can only take your opponent's pieces. \n Click a piece again, and make a different move.";
                    isValid = false;
                    loccount = 0;
                    piece = null;
                    hasClicked=false;
                    take = false;}}
                setMessage(a);
            }
            /*When player presses C or R, the keyPressed method is called*/
           //else
            //{setMessage("That move cannot be made, that would put you in check - Click another move");}
            //x=1;
            /*The program checks if after this move is made the king is in check, if he is, that move cannot be made*/

        else if(isValidinCheckers(piece, start, loc, setType(piece),setColor(piece))==false)
        {
            if(TakeAgain == true)
            {
                loccount = 1;
                hasClicked=false;
                setMessage(loc+" was an invalid move, but you can take again with the same piece\n, don't click the piece this time, just click where you want to move it.");
            }
            if(TakeAgain == false){
                piece=null;
                loccount=0;
                hasClicked=false;
                setMessage( loc + " is an invalid move - Click on another piece and try another move!");
            }
        }
    }


    @Override
    public boolean locationClicked(Location loc)
    {
        String color = name2;
        if(loccount == 0)
        {
            piece = getGrid().get(loc);//piece is the piece at the clicked location
            if(piece != null) {
                if((count%2==1)&&(piece.getColor()==c1))                            //if count is odd, then it is p2's turn, so if one of player1's pieces has been clicked, an error message will be displayed
                {
                    piece =null;
                    setMessage("It's "+name2+"'s move, not "+name1+"'s!");
                    return false;
                }
                if((count%2==0)&&(piece.getColor()==c2))                            //if count is even, it's p1's turn, so if one of p2's pieces has been clicked, an error message will appear
                {
                    piece =null;
                    setMessage("It's "+name1+"'s move, not "+name2+"'s!");
                    return false;
                }
                if(count%2==0){//if the count of moves is even, then it is player 1's move, so the piece's color will be name 1, player 1's color.
                    color=name1;}
                if((canTake(piece.getColor())==true)&&(canTake(piece)==false))
                {
                    setMessage("You can and must take a piece this move, but you selected a piece that cannot capture.\n Select another piece, one that can capture, and capture one of the opponent's pieces.");
                    return false;
                }
             if((canMove(piece,color)+"").equalsIgnoreCase("false"))//calls the canMove method, which checks if the piece clicked has any possible moves
             {
                 setMessage("The "+setType(piece)+" you picked has no possible moves, click on another piece.");
                 return false;
             }
                loccount++;
                

            //if the correct player's piece has been clicked, meaning p1 is moving on p1's turn or vice versa, then the player will be prompted to click where they want to move their piece
        setMessage("Your piece was a " + setType(piece) + " - Now click to where you would like to move it");
        return true;
        }

            else if((piece == null)||(piece+"").contains("space"))//if, when selecting a piece (loccount=0)an empty spot is clicked, an error message will appear
            {
            setMessage("Try again - Click a piece, not an empty square");
            return true;
        }
        }
        if (loccount == 1)//if loccount is 1, that means a piece has already been selected, so a location to move it must be clicked (which is loc), so then movePiece is called to move the piece
            {
            hasClicked = true;
        movePiece(piece, piece.getLocation(),loc);//in this case, piece is private, so it was saved beforehand, when the piece to move was supposed to be selected (when loccount=0)
        return true;//in this case, loc is the 2nd location that was clicked, the location where the piece that was first clicked is to be moved.
            }
        return false;
    }
    public String setColor(Actor piece)
    {
     String type = "";
            if(piece.getColor()==c1){
              type =  name1;
            }

            if(piece.getColor()==c2){
              type =  name2;
            }
    return type;
    }
    public boolean isValidinCheckers(Actor piece, Location start, Location loc, String type, String color)
    {
        Boolean output =false;
        Actor taken = null;
        String type2 = setType(getGrid().get(loc));
        if(gr.isValid(loc)){
            if(piece==null){return false;}
        if(((type2.equalsIgnoreCase("King"))||(type2.equalsIgnoreCase("Piece")))&&(isEmpty(loc)==false))//if the location being moved to is full, then it is an illegal move
        {
               // out.println("loc at "+loc+" was full");
                return false;
        }
        if((piece instanceof King)){
                if(((loc.getRow()==start.getRow()-1)&&((loc.getCol()==start.getCol()-1)||(loc.getCol()==start.getCol()+1)))||((loc.getRow()==start.getRow()+1)&&((loc.getCol()==start.getCol()-1)||(loc.getCol()==start.getCol()+1))))//if the move is NW, NE, SW, or SE
                {
                    take = false;
                    return true;
                }
                if((loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()-2))                      //if the move is NW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()+1));                    //this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if((!taken.getColor().equals(piece.getColor()))){                                       //if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                if((loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()+2))//if the move is NE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                if((loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()-2))//if the move is SW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal                        take = true;
                        take = true;
                        return true;}
                    return false;
                }
                if((loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()+2))//if the move is SE 2 spaces
                {
                    taken = getGrid().get(new Location(start.getRow()+1, start.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                    return false;
            }
         
        if((piece instanceof Piece)&&(((Piece)piece).getType().equalsIgnoreCase("piece"))&&(!((Piece)piece).getType().equalsIgnoreCase("King")))
            {
            if(piece==null){return false;}
            if(piece.getColor()==null)
                out.println(piece.getColor());
                if(((setColor(piece).equals(name1))&&(loc.getRow()==start.getRow()+1))&&((loc.getCol()==start.getCol()-1)||(loc.getCol()==start.getCol()+1)))//if the move is NW or NE
                {
                    take = false;
                    return true;
                }
                if(((setColor(piece).equals(name2))&&(loc.getRow()==start.getRow()-1))&&((loc.getCol()==start.getCol()-1)||(loc.getCol()==start.getCol()+1)))//if the move is NW or NE
                {
                    take = false;
                    return true;
                }
                if((setColor(piece).equals(name2))&&(loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()-2))//if the move is NW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()+1));                                //this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if(((Piece)taken).getType().equalsIgnoreCase("King")){return false;}
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(((Piece)piece).getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                if((setColor(piece).equals(name2))&&(loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()+2))//if the move is NE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if(((Piece)taken).getType().equalsIgnoreCase("King")){return false;}
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(((Piece)piece).getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                if((setColor(piece).equals(name1))&&(loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()-2))//if the move is NW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if(((Piece)taken).getType().equalsIgnoreCase("King")){return false;}
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(((Piece)piece).getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                if((setColor(piece).equals(name1))&&(loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()+2))//if the move is NE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if(taken==null){return false;}
                    if(((Piece)taken).getType().equalsIgnoreCase("King")){return false;}
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(((Piece)piece).getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        take = true;
                        return true;}
                    return false;
                }
                    return false;
            }
        }
                    return false;
    }
    @Override
    public boolean keyPressed(String ans, Location loc)
    {
        Location replace;
        King king = null;
        Actor taken = null;
        Color inputc = null;
        Location start = new Location(piece.getLocation().getRow()+0,piece.getLocation().getCol()+0);
        if (ans.contains("BACK")){
            if(TakeAgain==true)
            {
                setMessage("You can't take the piece back, you have to take with that piece;\n remember, if you can take a piece, you have to.");
                loccount = 1;
                return true;
            }

            //if the player pressed back, he can take back the move and move another piece or the same piece to a new location instead
            setMessage("You chose to cancel your initial move - Click another piece to move");
            taken = null;
            take = false;
            loccount=0;
            return true;
            }
        
        String whosemove = name1+"'s ";
        String ifCheck = "";
        String winner = "";
        if(ans.contains("ENTER"))                                                                   //if ENTER is pressed, meaning the player has agreed to make the move
        {
            if(hasClicked==false)
            {
                loccount=0;
                setMessage("Please only press enter after you have chosen a piece and a move");
                return false;
            }
            //take determines whether the move being made is a piece taking move or not
            if(take==true)
            {
                taken = gr.get(getTaken(piece, piece.getLocation(),loc,setType(piece), setColor(piece)));
                out.println(taken+" was taken");
            }
            if(isValid==true)
            {//if the move is valid, and the location being moved to is empty the move is made
                if(count%2==0)
                    inputc=c1;
                if(count%2==1)
                    inputc=c2;
                if(canTake(inputc)==true)
                {
                if((take==true))//MustTake is true if the player can take a piece but hasn't, in which case he can't press make a move and press enter until that move being made is a piece capturing move
                {
                piece.moveTo(loc);//move the piece to the location selected
                }
                if((take==false))
                {
                    setMessage("You can't move there, because you have to take the piece. \n Pick another move, and this time, capture the piece.");
                    return true;
                }
                }
                else
                {
                    piece.moveTo(loc);                    
                }
                count++;
                
            if(take==true){
                taken.removeSelfFromGrid();
                for(int x = 0;x<8;x++)
                {
                    if(taken==white[x]){
                        white[x]=null;
                        break;
                    }
                    if(taken==black[x]){
                        white[x]=null;
                        break;
                    }
                }
                if(canTake(piece)==true)
                {
                    take=false;
                    taken=null;
                    loccount = 1;
                    TakeAgain = true;
                    count--;
                    setMessage("You can take again with the same piece. You don't need to click the piece, \njust click where you want to move it. YOU MUST TAKE THE PIECE");
                    return true;
                }
                }
                take=false;
                taken=null;
                TakeAgain = false;
           }
            //if a piece is moved, then a move has been made, so it adds 1 to count, meaning another move has been made. Count keeps track of the number of moves made
            loccount = 0;
            if(count%2==0)//if count is even, then it is the 1st player's move, so it sends a message
            {
                whosemove = name1+"'s";
            }
            if(count%2==1)//if count is odd, then it is the 2nd player's move, so it sends a message, otherwise, it is the 1st player's move
            {
                whosemove = name2+"'s";
            }
           // if((count%2==1)&&(isInCheck(black[6].getLocation(),1)==true))
           //{ifCheck = "black is in check";}
           // if((count%2==0)&&(isInCheck(white[6].getLocation(),0)==true))
            //{ifCheck = "white is in check";}

            if((piece instanceof Piece)&&(!((Piece)piece).getType().equalsIgnoreCase("King"))){
                if((setColor(piece).equals(name1))&&(piece.getLocation().getRow()==7)){                                      //if the piece reaches the other side{
                    for(int x = 0;x<8;x++){
                      if((white[x]!=null)&&(white[x]==piece)){
                            replace = new Location(piece.getLocation().getRow()+(1-1),piece.getLocation().getCol()+(1-1));
                            piece.removeSelfFromGrid();
                            king = new King(c1);
                            king.putSelfInGrid(gr,replace);
                            white[x]=king;
                            piece=null;}}}
                else if((setColor(piece).equals(name2)) && (piece.getLocation().getRow() == 0))                             //if the piece reaches the other side
                {
                    for(int x = 0;x<8;x++)
                    {
                      if((black[x]!=null)&&(black[x]==piece))
                      {
                            replace = new Location(piece.getLocation().getRow()+0,piece.getLocation().getCol()+0);
                            piece.removeSelfFromGrid();
                            king = new King(c2);
                            king.putSelfInGrid(gr,replace);
                            black[x]=king;
                            piece=null;
                      }
                    }
                }
            }
            boolean win;
            for(int x = 0;x<8;x++)
            {
                if(count%2==1)
                    if(black[x]!=null)
                        win = false;
                if(count%2==0)
                    if(white[x]!=null)
                        win = false;
            }
            if(canMoveAtAll()==false)
                         {

                             if(count%2==1){
                                 setMessage("Congratulations "+name1+",you won! \n Go to the main menu");}
                             if(count%2==0){
                                 setMessage("Congratulations "+name2+",you won! \n Go to the main menu");}
                             return true;
                         }
          setMessage("This is move number "+(count+1) + " - " + whosemove + " move - ");
            
            if(isValid==false){
                setMessage("The move was invalid! Pick Another move!");//if the move to begin with wasn't valid, no move is made, and an error message is sent
            }
        }

        return true;
    }
    public String setType(Actor piece)
    {
        Actor p = piece;
/*        for(int x = 0;x<spaces.length;x++)
        {
            if(piece==spaces[x])
                return "Empty Space";
        }*/
     if( p instanceof Piece)
         return "piece";
     if( p instanceof King)
         return "King";
        return "unrecognized piece";
    }
   
    public boolean canMove(Actor piece,String color)
    {
        Location loc=null;
        Location start = piece.getLocation();
        for(int x = 0;x<8;x++)
        {
            for(int y = 0;y<8;y++)
            {
                loc = new Location(x,y);
                if(start!=loc)
                {
                if(isValidinCheckers(piece, start, loc, setType(piece), color)==true)
                {
                    System.out.println("The piece at " +piece.getLocation()+" can move");
                    return true;
                }
                }
            }
        }
        System.out.println("The piece at "+piece.getLocation()+" canNOT move");
        return false;
    }

    public boolean canTake(Color color)
    {
        if(color==c1){
         for(int i = 0;i<white.length;i++){
            for(int x = 0;x<8;x++){
            for(int y = 0;y<8;y++){
                if(white[i]!=null){
                if(white[i].getLocation()!=null){
                if((isValidinCheckers(white[i], white[i].getLocation(), new Location(x,y), setType(white[i]), name1)==true)&&(take==true)){
                    System.out.println("The piece can move");
                    return true;
                }}}}}}}
        if(color==c2)
        {
         for(int i = 0;i<8;i++){
            for(int x = 0;x<8;x++){
            for(int y = 0;y<8;y++){
                if(black[i].getLocation()!=null){
                if((isValidinCheckers(black[i], black[i].getLocation(), new Location(x,y), setType(black[i]), name2)==true)&&(take==true)){
                    System.out.println("The piece can move");
                    return true;
                }}}}}}

        return false;
    }
    public boolean canTake(Actor piece)
    {
        
            for(int x = 0;x<8;x++)
        {
            for(int y = 0;y<8;y++)
            {
                if((isValidinCheckers(piece, piece.getLocation(), new Location(x,y), setType(piece), setColor(piece))==true)&&(take==true))
                {
                    System.out.println("The piece can move");
                    return true;
                }
            }
        }
        return false;
    }

    public Location getTaken(Actor Piece, Location start, Location loc, String Type, String color)
    {
        Actor taken = null;
        if(((Piece)piece).getType().equalsIgnoreCase("King"))
            {
                if((loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()-2))//if the move is NW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;
                }
                if((loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()+2))//if the move is NE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;
                }
                if((loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()-2))//if the move is SW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal                        take = true;
                        return taken.getLocation();}
                    return null;
                }
                if((loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()+2))//if the move is SE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((!taken.getColor().equals(piece.getColor()))){//if the piece being taken is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;

                }

            }
        if(setType(piece).equalsIgnoreCase("Piece"))
            {
                if((piece.getColor().equals(c2))&&(loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()-2))//if the move is NW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(piece.getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;
                }
                if((piece.getColor().equals(c2))&&(loc.getRow()==start.getRow()-2)&&(loc.getCol()==start.getCol()+2))//if the move is NE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()+1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(piece.getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;
                }
                if((piece.getColor().equals(c1))&&(loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()-2))//if the move is NW 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()+1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(piece.getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;
                }
                if((piece.getColor().equals(c1))&&(loc.getRow()==start.getRow()+2)&&(loc.getCol()==start.getCol()+2))//if the move is NE 2 spaces
                {
                    taken = getGrid().get(new Location(loc.getRow()-1, loc.getCol()-1));//this is the piece in between the start and end locations, that the player is attempting to capture.
                    if((setType(taken).equalsIgnoreCase("Piece"))&&(!taken.getColor().equals(piece.getColor()))){//if the piece being taken is a piece (not a king) and it is of the opposite color (meaning it's one of the opponent's pieces), then the move is legal
                        return taken.getLocation();}
                    return null;
                }
                    return null;
            }
            
        return null;
    }

    public boolean canMoveAtAll()
    {
        if(count%2==0){
            out.println("p1's move");
            for(int x = 0;x<8;x++){
                if(white[x]!=null){
                if(canMove(white[x],name1)==true){
                    out.println("The piece can move");
                    return true;
                }}}}
        else if(count%2==1){
            out.println("p2's move");
            for(int y = 0;y<8;y++){
                if(black[y]!=null){
                if(canMove(black[y],name2)==true){
                    out.println("The piece can move");
                    return true;}}}}
        out.println("Done");
        return false;
    }
    


}
