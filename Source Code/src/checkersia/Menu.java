package checkersia;


import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.awt.Color;


import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Menu extends JFrame implements ActionListener, ItemListener{

    private JPanel pane = new JPanel();
    private JButton START = new JButton("Start");
    private JButton exit = new JButton("Exit");
    private JButton menu = new JButton("Main Menu");
    private JButton p1 = new JButton();
    private JButton p2 = new JButton();
    private Container cont = null;
    private boolean first = false;
    private Choice ch = new Choice();
    private Choice ch2 = new Choice();
    private Label field1 = new Label("Welcome, Press start to begin.");
    private Label field2 = new Label("Now, pick the color you would like your pieces to be.");
    private Color you=null;
    private Color opnt=null;
    private String mode="";

    private Actor []white = new Actor[8];
    private Actor []black = new Actor[8];
    private World wld = null;

    public Menu()
    {
        super("Main Menu");
        this.setBounds(100, 100, 550, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        cont = this.getContentPane();
        cont.add(pane);
        pane.add(START);
        START.setBounds(170, 230, 15, 15);
        exit.setBounds(20, 20, 15, 15);
        pane.add(exit);
        pane.add(field1);
        exit.addActionListener(this);
        START.addActionListener(this);
        exit.setEnabled(true);
        START.setEnabled(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(exit))
        {
            System.exit(0);
        }
 if(e.getSource().equals(START)){

            mode="Chess";
        
        field1.setVisible(false);
        pane.add(menu);
        menu.addActionListener(this);
        menu.setEnabled(true);
        menu.setVisible(true);
        pane.add(field2);
        field2.setVisible(true);
        initColor();
        }
        if(e.getSource().equals(menu))
        {
            this.setVisible(false);
            Menu restart = new Menu();
        }
        if(e.getSource().equals(p1)||e.getSource().equals(p2))
        {
        if(e.getSource().equals(p1)){
            first=true;
        }
        if(e.getSource().equals(p2))
        {
            first=false;
        }
            p1.setVisible(false);
            p2.setVisible(false);
            field2.setVisible(false);
            field2.setText("Press Close at any time to close the game, or Main Menu to close the game and return to the main menu.");
            field2.setVisible(true);
            System.out.println(you.toString());
            System.out.println(opnt.toString());
        CheckerRun(you, opnt, first, this);
        }
    }
    public void initColor()
    {
        exit.setVisible(false);
        menu.setVisible(false);
        START.setVisible(false);
        ch.addItem("Bright Red");
        ch.addItem("Orange");
        ch.addItem("Dark Blue");
        ch.addItem("Light Blue");
        ch.addItem("Dark Green");
        ch.addItem("Light Green");
        ch.addItem("Yellow");
        ch.addItem("Purple");
        ch.addItem("Black");
        ch.addItem("White");
        ch.addItem("Gray");
        ch.addItem("Brown");
        ch.addItem("Dark Red");
        cont.add(ch,0);
        ch.addItemListener(this);
        ch.setVisible(true);
        ch.setBounds(30, 30, 10, 10);
        menu.setVisible(true);
        exit.setVisible(true);
        }

    public void itemStateChanged(ItemEvent e) {
        String s = e.getItem().toString();
        Color c = null;
        if(s.contains("Dark Red")){
        c = new Color(140,0,0);
        }
        if(s.contains("Bright Red")){
        c = new Color(240,0,0);
        }
        if(s.contains("Orange")){
        c = new Color(255,69,0);
        }
        if(s.contains("Dark Blue")){
        c = new Color(0,0,205);
        }
        if(s.contains("Light Blue")){
        c = new Color(0,255,255);
        }
        if(s.contains("Dark Green")){
        c = new Color(0,153,0);
        }
        if(s.contains("Light Green")){
        c = new Color(128,255,0);
        }
        if(s.contains("Yellow")){
        c = new Color(255,255,0);
        }
        if(s.contains("Purple")){
        c = new Color(127,0,255);
        }
        if(s.contains("Black")){
        c = new Color(0,0,0);
        }
        if(s.contains("White")){
        c = new Color(255,255,255);
        }
        if(s.contains("Gray")){
        c = new Color(150,150,150);
        }
        if(s.contains("Brown")){
        c = new Color(100,50,0);
        }
        ch.remove(s);
        this.setColor(c);
    }
    
    public void setColor(Color c)
    {
        if(you==null)
        {
            you=c;
            field2.setVisible(false);
            field2.setText("How about your opponent's pieces?");
            field2.setVisible(true);
        }
 else if ((you != null) && (opnt == null)){
            opnt=c;
            field2.setVisible(false);
            field2.setText("Who will go first?");
            field2.setVisible(true);
            ch.setVisible(false);
            pane.add(p1);
            pane.add(p2);
            p1.setText(getName(you)+"");
            p2.setText(getName(opnt)+"");
            p1.setVisible(true);
            p2.setVisible(true);
            p1.addActionListener(this);
            p1.setEnabled(true);
            p2.addActionListener(this);}
    }
    public String getName(Color c)
    {
        if(c.equals(new Color(140,0,0))){
            return "Dark Red";}
        if(c.equals(new Color(240,0,0))){
            return "Bright Red";}
        if(c.equals(new Color(255,69,0))){
            return "Orange";}
        if(c.equals(new Color(0,0,205))){
            return "Dark Blue";}
        if(c.equals(new Color(0,255,255))){
            return "Light Blue";}
        if(c.equals(new Color(0,153,0))){
            return "Dark Green";}
        if(c.equals(new Color(128,255,0))){
            return "Light Green";}
        if(c.equals(new Color(255,255,0))){
            return "Yellow";}
        if(c.equals(new Color(127,0,255))){
            return "Purple";}
        if(c.equals(new Color(0,0,0))){
            return "Black";}
        if(c.equals(new Color(255,255,255))){
            return "White";}
        if(c.equals(new Color(150,150,150))){
            return "Gray";}
        if(c.equals(new Color(100,50,0))){
            return "Brown";}
        return "";
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
