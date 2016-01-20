package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;




public class Window extends JPanel implements KeyListener {
    
    //vars
private Timer _timer;
public  ArrayList<Cube> Cubes= new ArrayList<Cube>();
protected ImageIcon Front = new ImageIcon(getClass().getResource("images/BoundsFront.png"));
protected ImageIcon Back = new ImageIcon(getClass().getResource("images/BoundsBack.png"));
double length = 1300;
int randMove=10;
int MaxrandMove=10;
public static int Rotation = 1;
 Cube up = new Cube(0,0,1,1);
 Cube down = new Cube(0,0,-1,1);
 
 Cube forward = new Cube(1,0,0,1);
 Cube backward = new Cube(-1,0,0,1);

 Cube left = new Cube(0,-1,0,1);
 Cube right = new Cube(0,1,0,1);

 
Cube Direction = new Cube(0,0,1,1);
Cube Head = new Cube(0,0,0,1);


    public Window() {
       //start 
    	
    	
    	
    	 setDoubleBuffered(true);
         setFocusable(true);
         addKeyListener(this);
        _timer = new Timer(5, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                paintInterval();
            }

        });
        _timer.start();
        
              
          }
    private void paintInterval() {
        //maths 
    	//length = length+0.2;
    	randMove--;
    	 ArrayList<Cube> newCubes= new ArrayList<Cube>();
    	int newX = Direction.getX()+Head.getX();
    	int newY = Direction.getY()+Head.getY();
    	int newZ = Direction.getZ()+Head.getZ();
    	boolean intersects = false;
    	Cube newHead = new Cube(newX,newY, newZ,(int)length);
    	for (Cube cube2 : Cubes){
			if(newHead.equals(cube2))
				 intersects = true;
    	}
    	if(newX<11 && newX>-11 && newY<11 && newY>-11 && newZ<11 && newZ>-11&&!intersects){

    	Cubes.add(Head = newHead);
    	}
    	else{
    		int tries = 0;
    		while((newX>=11 || newX<=-11||newY>=11 || newY<=-11||newZ>=11 || newZ<=-11)|| (tries <20&&intersects)){
    		int random = (int)(1+Math.random()*4);
    		if(newX>=11 || newX<=-11 ){
    			switch (random) {

    	        case 1:
    	        	Direction= up;
    	            break;
    	        case 2:
    	        	Direction=down;
    	            break;
    	        case 3:
    	        	Direction=right;
    	            break;
    	        case 4:
    	        	Direction=left;
    	            break;
    			}
    		}
    		 if(newY>=11 || newY<=-11){
    			switch (random) {

    	        case 1:
    	        	Direction= up;
    	            break;
    	        case 2:
    	        	Direction=down;
    	            break;
    	        case 3:
    	        	Direction=forward;
    	            break;
    	        case 4:
    	        	Direction=backward;
    	            break;
    			}
    		}
    		 if(newZ>=11 || newZ<=-11){
    			switch (random) {

    	        case 1:
    	        	Direction= backward;
    	            break;
    	        case 2:
    	        	Direction=forward;
    	            break;
    	        case 3:
    	        	Direction=right;
    	            break;
    	        case 4:
    	        	Direction=left;
    	            break;
    			}
    		 }
    		 if (intersects){
    			 int random3 = (int)(1+Math.random()*6);
    		   		tries++;
    	    	   switch (random3) {
    	   	        case 1:
    	   	        	Direction= up;
    	   	            break;
    	   	        case 2:
    	   	        	Direction=down;
    	   	            break;
    	   	        case 3:
    	   	        	Direction=right;
    	   	            break;
    	   	        case 4:
    	   	        	Direction=left;
    	   	            break;
    	   	        case 5:
    	   	        	Direction=forward;
    	   	            break;
    	   	        case 6:
    	   	        	Direction=backward;
    	   	            break;
    	   			}  
    		 }
    		 newX = Direction.getX()+Head.getX();
        	 newY = Direction.getY()+Head.getY();
        	 newZ = Direction.getZ()+Head.getZ();
        	 intersects = false;
         	newHead = new Cube(newX,newY, newZ,(int)length);
         	for (Cube cube2 : Cubes){
     			if(newHead.equals(cube2))
     				 intersects = true;
        	
    		}
         	}
        	Cubes.add(Head = newHead);
        	
    	}
    	for (Cube cube : Cubes){
    		
        	cube.tick();
        	if (cube.CountDown!=0){
        		newCubes.add(cube);
        	}	
        }
    	Cubes = newCubes;
    	
    	 Collections.sort(Cubes);
       if(randMove ==0){
    	   randMove=MaxrandMove;
    	   int random2 = (int)(1+Math.random()*6);
   		
    	   switch (random2) {
   	        case 1:
   	        	Direction= up;
   	            break;
   	        case 2:
   	        	Direction=down;
   	            break;
   	        case 3:
   	        	Direction=right;
   	            break;
   	        case 4:
   	        	Direction=left;
   	            break;
   	        case 5:
   	        	Direction=forward;
   	            break;
   	        case 6:
   	        	Direction=backward;
   	            break;
   			} 
       }
         this.repaint();
    }
    
    public void paint(Graphics g) {
    	//draw
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
       // g2d.drawImage(Back.getImage(),400-295,400-305,null);
        for (Cube cube : Cubes){
        	cube.Draw(g2d,Rotation);
        }
   //     g2d.drawImage(Front.getImage(),400-295,400-305,null);
    }
 @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	switch (e.getKeyCode()) {

        case KeyEvent.VK_UP:
        	
        		MaxrandMove = Math.max(1,MaxrandMove-1 );
            break;
        case KeyEvent.VK_DOWN:
        	MaxrandMove = Math.min(100,MaxrandMove+1 );
            break;
        case KeyEvent.VK_LEFT:
        	Rotation++;
    		if(Rotation ==5) Rotation = 1;
    		 Collections.sort(Cubes);
        break;
    case KeyEvent.VK_RIGHT:
    	Rotation--;
    	if(Rotation==0) Rotation = 4;
    	 Collections.sort(Cubes);
        break;
    case KeyEvent.VK_BACK_SPACE:
    	Head = new Cube(0,0,0,1);
    	Cubes.clear();
    	break;
    	}
    	
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
