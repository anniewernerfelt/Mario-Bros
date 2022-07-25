// Hero class from platforms
import java.awt.*;

	// METHOD DEFINITION SECTION

	// Constructor Definition
	// A constructor builds the object when called and sets variable values.
public class Hero
{
		//VARIABLE DECLARATION SECTION
		//Here's where you state which variables you are going to use.
      //use this format public datatype variablename;
   public int xpos;
   public int ypos;
   public int height;
   public int width;
   public int dx, dy;
   public boolean up, down, left, right, jump, doubleJump;
   public boolean onPlatform, onGround;
   public boolean isAlive;
   public int gravity;
   public Rectangle rec; 


		// METHOD DEFINITION SECTION

   public Hero(int x, int y)
   {
      xpos = x;
      ypos = y;
      height = 60;
      width = 60;
      dx = 5;
      dy = 20;
      gravity = 1;
      rec= new Rectangle (xpos,ypos,width,height); 
   } // constructor


   public void move()
   {
   
    ypos = ypos + dy;
   dy = dy + gravity;
         
      if (right==true)
      {
         moveRight();
      }
         
      if (left==true)
      {
         moveLeft();
      }
           
      rec= new Rectangle (xpos,ypos,width,height); 
   
   }
 
 
   
      //Separate move methods
   public void moveRight()
   {
      xpos = xpos+dx;
         
      if(xpos+width>800)
      {
         xpos=800-width;
      }
         
         
   }
   public void moveLeft()
   {
      xpos = xpos-dx;
         
      if(xpos<0)
      {
         xpos=0;
      }
         
   }
	

} //end of the generic hero with keyboard control object class  definition

