// Threaded Applet Template that works with Macs


// Import section
// Use this section to add additional libaries for use in your program.
import java.applet.*;
import java.awt.*;

// This begins the class definition.
// Notice that this is a "world".  You can tell since it extends Applet.
// It also implement Runnable which allows it to be and use threads.

public class AnimationApplet2 extends Applet implements Runnable
{
	//variable declaration section

	//Sets up a Thread called thread
	Thread thread;
	public int framecounter;	      //the number of the current frame being drawn
	public int threadCounter;			//used to keep count of how many times the thread executes
	public int frames;               //how many frames there are in the animation
   public int row;                  //how many rows there are
   public int rowcounter;	         //what row are you on	
	public Image spritePic;   
	public int firstFrameXPos, firstFrameYPos;	//the x and y position of the upper left corner of the first frame.
	public int frameWidth, frameHeight;	//the width and height of the frames in the sprite sheet.
   


	//double buffering variables
	Graphics bufferGraphics;
	Image offscreen;


	// Method definition section

	// init() is the first method an Applet runs when started
	public void init()
	{

		setSize(800,800);

		//double buffering
		offscreen = createImage(800,800); //create a new image that's the size of the applet
		bufferGraphics = offscreen.getGraphics(); //set bufferGraphics to the graphics of the offscreen image.


		//Sprite Sheet Info
		spritePic = getImage(getDocumentBase(), "smurf.gif");
		frameWidth = 60;
		frameHeight = 60;

		firstFrameXPos = 0;
		firstFrameYPos = 0;

		frames = 4;			//how many frames in the animation
		framecounter = 0;
      
      
      row = 4;
      rowcounter = 0;



		//Set up the thread
		//This should be the LAST lines in your init( ) method.
		thread = new Thread(this);  //constructs a new thread
		thread.start();				//starts the thread

	}//init()


	// paint() is used to display things on the screen
	public void paint(Graphics g)
	{
		//clear the offscreen image
		bufferGraphics.clearRect(0,0,800,600);

		int dx1,dy1, sx1, sy1;
		int dx2, dy2, sx2, sy2;

		//calculate the source window points
		sy1 = firstFrameYPos+(frameHeight*rowcounter);						//the y position of where the frames start
		sy2 = sy1 + frameHeight;
		sx1 = firstFrameXPos + framecounter*frameWidth;
		sx2 = sx1+frameWidth;

		//calculate the destination window points
		dx1 = 200;
		dy1 = 200;
		dx2 = dx1+frameWidth;
		dy2 = dy1+frameHeight;

		threadCounter++;

		//this has the framecounter increase after every 5 times the thread calls paint()
		if(threadCounter > 20)
		{
			threadCounter=0;
			framecounter++;
		}


		//framecounter++;					//go to the next frame in the sprite sheet
		if (framecounter>frames -1)		//if you are past the last frame in the sprite sheet
		{
         framecounter = 0;			//go back to the first frame;
         rowcounter++;
         
         if(rowcounter>row-1)
         {
         rowcounter=0;
         }
		}
          

		bufferGraphics.drawImage(spritePic, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,this);
		g.drawImage(offscreen,0,0,this);
	}// paint()



	public void update (Graphics g)
	{
		paint (g);
	}


	// every thread needs a run method
	// this is what the thread will do
	public void run() {

		// this thread loop forever and runs the paint method and then sleeps.
		while(true)
		{

			//put what you want your program to do here.
			repaint();		// run the paint method.

			//sleep
			try {
			thread.sleep(1);
			}
			catch (Exception e){ }
		}//while

	}// run()






}//ForestWorld