// Platform class

import java.awt.*;
	// METHOD DEFINITION SECTION

	// Constructor Definition
	// A constructor builds the object when called and sets variable values.
public class Platform
{
   //VARIABLE DECLARATION SECTION
   public int xpos;
   public int ypos;
   public int height;
   public int width;
   public Rectangle leftrec;
   public Rectangle rightrec; 
   public Rectangle upperrec;
   public Rectangle lowerrec;
  

	// Need to specify the values of your Hero?
	// Add parameters to the constructor to create a second one that lets you pass values into it.
   public Platform(int x, int y)
   {
      xpos = x;
      ypos = y;
      height = 40;
      width = 200;
      
   

      leftrec = new Rectangle(xpos, ypos, 1, height);
      rightrec = new Rectangle(xpos + width,ypos, 1, height);
      upperrec = new Rectangle(xpos, ypos, width, 1);
      lowerrec = new Rectangle(xpos, ypos + height, width, 1);



   
   } // constructor



} //end of the generic platform object class  definition

