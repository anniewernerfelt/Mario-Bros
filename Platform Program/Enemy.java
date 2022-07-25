// Platform class

import java.awt.*;
	// METHOD DEFINITION SECTION

	// Constructor Definition
	// A constructor builds the object when called and sets variable values.
public class Enemy
{
   //VARIABLE DECLARATION SECTION
   public int xpos;
   public int ypos;
   public int height;
   public int width;
   public boolean isAlive;
   public int dx;					
   public int dy;	
   public Rectangle rec;
  

	// Need to specify the values of your Hero?
	// Add parameters to the constructor to create a second one that lets you pass values into it.
   public Enemy(int x, int y)
   {
      xpos = x;
      ypos = y;
      width = 35;
      height = 50;
      dx = 6;
      isAlive = true;
      
      rec = new Rectangle(xpos, ypos, width, height);

      
       
   
   } // constructor

   public void move()
   {
      xpos = xpos + dx;
      


         if(xpos>800-width || xpos < 0)
         {
            dx=-dx;
         }
      

     rec = new Rectangle(xpos, ypos, width, height);
   }



} //end of the generic platform object class  definition

