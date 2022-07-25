// Sample Code for Making a Keyboard Controlled Hero


// Import section
// Use this section to add additional libaries for use in your program.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PlatformTemplate extends Applet implements  Runnable, KeyListener
{
   public Image heroPic, platformPic, enemyPic, backgroundPic, coinPic, youWon;
   public Hero myHero;
   public Platform[] platforms;
   public Enemy[] enemies;
   public Coin[] coins;
   public boolean jpressed, lostPoint;
   public int groundLevel;
   public AudioClip loss;
   
   public int score;      
   public String s;
   
   
   Thread thread;
   public int framecounter;	      //the number of the current frame being drawn
   public int threadCounter;			//used to keep count of how many times the thread executes
   public int frames;               //how many frames there are in the animation
   public int row;                  //how many rows there are
   public int rowcounter;	         //what row are you on	
   public Image rightspritePic, leftspritePic;   
   public int firstFrameXPos, firstFrameYPos;	//the x and y position of the upper left corner of the first frame.
   public int frameWidth, frameHeight;
 
	
   //double buffering variables
   Graphics bufferGraphics;
   Image offscreen;

	//Sets up a Thread called thread
  // Thread thread;

   public void init()
   {
      setSize(800,600);
      addKeyListener( this ); //add the keylistener to start using the keyboard
      
     //double buffering
      offscreen = createImage(800,600); //create a new image that's the size of the applet
      bufferGraphics = offscreen.getGraphics(); //set bufferGraphics to the graphics of the offscreen image.
      
      
      score=0;                                         
      s="Score: "; 
      
      loss=getAudioClip(getDocumentBase(),"loss.wav");
      
      myHero = new Hero(600, 300);
      
      platforms = new Platform[6];
      
      platforms[0] = new Platform(100, 200);
      platforms[1] = new Platform(250, 350);
      platforms[2] = new Platform(400, 75);
      platforms[3] = new Platform(300, 500);
      
      
      enemies = new Enemy[4];
      
      enemies[0] = new Enemy(100, 150);
      enemies[1] = new Enemy(250, 300);
      enemies[2] = new Enemy(400, 25);
      enemies[3] = new Enemy(300, 450);
      
      
      coins = new Coin[15];
      
      coins[0] = new Coin(50, 50);
      coins[1] = new Coin(50, 500);
      coins[2] = new Coin(750, 300);
      coins[3] = new Coin(200, 60);
      coins[4] = new Coin(550, 350);
      coins[5] = new Coin(360, 200);
      coins[6] = new Coin(400, 50);
      coins[7] = new Coin(100, 550);
      coins[8] = new Coin(600, 250);
      coins[9] = new Coin(200, 300);
      coins[10] = new Coin(320, 100);
      coins[11] = new Coin(360, 400);
      
      
      backgroundPic = getImage(getDocumentBase(),"background.gif");      
     // heroPic = getImage(getDocumentBase(),"gman.gif");
      platformPic = getImage(getDocumentBase(),"platform.gif");
      enemyPic = getImage(getDocumentBase(), "enemy.gif");
      coinPic = getImage(getDocumentBase(), "coin.gif");
      youWon = getImage(getDocumentBase(), "youWon.png");
    
      
      groundLevel = 550;
      
      
      
      rightspritePic = getImage(getDocumentBase(), "rightsmurf.gif");
      leftspritePic = getImage(getDocumentBase(), "leftsmurf.gif");
      frameWidth = 60;
      frameHeight = 60;
   
      firstFrameXPos = 0;
      firstFrameYPos = 0;
   
      frames = 4;			//how many frames in the animation
      framecounter = 0;
      
      
      row = 4;
      rowcounter = 0;
   
      
   

         
      thread = new Thread(this);  //constructs a new thread
      thread.start();				//starts the thread
   
   }//init()




	// paint() is used to display things on the screen
   public void paint(Graphics g)
   {
   
      //clear the offscreen image
      bufferGraphics.clearRect(0,0,800,600);
   
      bufferGraphics.drawImage(backgroundPic, 0, 0, 800, 600, this);
      
     
      for(int x=0;x<4;x++) 
      {
         bufferGraphics.drawImage(platformPic,platforms[x].xpos,platforms[x].ypos,platforms[x].width,platforms[x].height,this); 
         
         if(enemies[x].isAlive == true)
         {
            bufferGraphics.drawImage(enemyPic, enemies[x].xpos, enemies[x].ypos, enemies[x].width, enemies[x].height, this);
         }
         
      }
      
     
      
      for(int x=0;x<11;x++) 
      {
         if(coins[x].isAlive == true)
         {
            bufferGraphics.drawImage(coinPic, coins[x].xpos, coins[x].ypos, coins[x].width, coins[x].height, this);
         }
      }
      
    
   
   
      int dx1,dy1, sx1, sy1;
      int dx2, dy2, sx2, sy2;
   
   
      if(myHero.right==true)
      {
      //calculate the source window points
         sy1 = firstFrameYPos+(frameHeight*rowcounter);						//the y position of where the frames start
         sy2 = sy1 + frameHeight;
         sx1 = firstFrameXPos + framecounter*frameWidth;
         sx2 = sx1+frameWidth;
      
      //calculate the destination window points
         dx1 = myHero.xpos;
         dy1 = myHero.ypos;
         dx2 = dx1+frameWidth;
         dy2 = dy1+frameHeight;
      
         threadCounter++;
      
      //this has the framecounter increase after every 5 times the thread calls paint()
         if(threadCounter > 0.3)
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
         
         
         
            bufferGraphics.drawImage(rightspritePic, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,this);
      }
      
      if(myHero.right==false)
      {
      //calculate the source window points
         sy1 = firstFrameYPos+(frameHeight*rowcounter);						//the y position of where the frames start
         sy2 = sy1 + frameHeight;
         sx1 = firstFrameXPos + framecounter*frameWidth;
         sx2 = sx1+frameWidth;
      
      //calculate the destination window points
         dx1 = myHero.xpos;
         dy1 = myHero.ypos;
         dx2 = dx1+frameWidth;
         dy2 = dy1+frameHeight;
      
         threadCounter++;
      
      //this has the framecounter increase after every 5 times the thread calls paint()
         if(threadCounter > 0.3)
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
         
        
            bufferGraphics.drawImage(leftspritePic, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,this);
      }
     
      
   
      
   
   
      
      bufferGraphics.drawString(s+score, 450, 50);
      
      g.drawImage(offscreen,0,0,this);
     
      
      
   }// paint()

   public void update (Graphics g)
   {
      paint (g);
   }

   public void checkIntersections( )
   {
   
      for(int x=0;x<4;x++)
      {
      
         if(myHero.rec.intersects(platforms[x].leftrec) || myHero.rec.intersects(platforms[x].rightrec) || myHero.rec.intersects(platforms[x].upperrec) || myHero.rec.intersects(platforms[x].lowerrec))
         {
            if(myHero.dy>0)
            {
               myHero.dy = 0;
               myHero.ypos = platforms[x].ypos-myHero.height;
            }
           
         
         
         
            if(myHero.dy < 0)       //jumping
            {
               myHero.dy = 1;
               myHero.ypos = platforms[x].ypos + platforms[x].height;
            }
         }
      
      }
      
      
      //hero + platform intersections
      
      
      for(int x=0;x<4;x++)
      {
         if(myHero.rec.intersects(platforms[x].leftrec))
         {
            myHero.right=false;
         }
         
         if(myHero.rec.intersects(platforms[x].rightrec))
         {
            myHero.left=false;
         }
         
         if(myHero.rec.intersects(platforms[x].upperrec))
         {
            myHero.dy = 0;
            myHero.ypos = platforms[x].ypos-myHero.height;
         }
         
         if(myHero.rec.intersects(platforms[x].lowerrec))
         {
            myHero.dy = 0;
            myHero.ypos = platforms[x].ypos+myHero.height;
         }
         
      }
      
                     
         
         
      for(int x=0;x<4;x++)
      {
         //enemy movement
         if(enemies[0].xpos > 300-enemies[x].width)
            enemies[x].dx = -enemies[x].dx;
         
         if(enemies[0].xpos < 100)
            enemies[x].dx = -enemies[x].dx;
      }   
   
   

      

   
   
   
      for(int x=0;x<4;x++)
      {         
         if(myHero.rec.intersects(enemies[x].rec))
         {
         if(enemies[x].isAlive==true)
         {
           score--;
           enemies[x].isAlive=false;
           loss.play();
           }
         }
      }    
      
                
           
   
      
        
      for(int x=0;x<11;x++)
      {
         if(myHero.rec.intersects(coins[x].rec) && coins[x].isAlive==true)
         {
            coins[x].isAlive=false;
            score++;
         }   
      }                                                     
      
      
   
   
      
   }//checkIntersections()

   public void run() {
   
      while(true)
      {
         //MOVE THINGS
         myHero.move();
         for(int x=0;x<4;x++)
         {
            enemies[x].move();
         }
         
         if(myHero.ypos + myHero.height >= groundLevel)
         {
            myHero.ypos = groundLevel - myHero.height;
            myHero.dy = 0;
         }
         
         
         if(myHero.ypos <= 0)
         {
            myHero.ypos = 0;
            myHero.dy = 1;
         }                             //keeps Hero on screen
         
         checkIntersections();
         repaint();		// run the paint method.
      
      	//sleep
         try {
            thread.sleep(50);
         }
         catch (Exception e){ }
      }//while
   
   }// run()
  



//****************************************************************************************
// This section contains 3 required methods to get keyboard input
//****************************************************************************************

   //This runs whenever a key is pressed
   public void keyPressed( KeyEvent event )
   {
      String keyin;       
      keyin = ""+event.getKeyText( event.getKeyCode()); //getKeyCode returns the key code number
      //System.out.println("Key pressed "+keyin);
            
      //if you press the D make the hero go right by making its right boolean variable true.
      if(keyin.equals("D"))
      {
         myHero.right=true;   
      }
      
      if(keyin.equals("A"))
      {
         myHero.left=true;   
      }
      
      if(keyin.equals("W"))
      {
         myHero.dy = -20;
      }
   
   }//keyPressed()


   // This runs whenever a key is released
   public void keyReleased( KeyEvent event )
   {
      String keyin;
      keyin = ""+event.getKeyText( event.getKeyCode());
      //System.out.println ("Key released: "+ keyin);
      
      
      //if the D key is "unpressed" make the right motion stop.
      if(keyin.equals("D"))
      {
         myHero.right=false;   
      }
       
      if(keyin.equals("A"))
      {
         myHero.left=false;   
      }
       
      if(keyin.equals("W"))
      {
        
      }
       
      
      
      
   }//keyReleased() - don't use this one
   public void keyTyped( KeyEvent event )
   {
   //keyTyped() only runs if a printable key is pressed. It does not respond to arrow keys, space, tab, etc.
      char keyin;
      keyin = event.getKeyChar(); //getKeyChar() returns the character of the printable key pressed.
     // System.out.println ("Key Typed: "+ keyin);
   }//keyTyped()




}//CheeseWorld