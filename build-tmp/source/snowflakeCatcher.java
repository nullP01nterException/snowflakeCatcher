import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class snowflakeCatcher extends PApplet {

//Jolina Lam, CP2, Snowflake Catcher, Mods 4/5
//url:http://starfalleclipse.webs.com/snowflakeCatcher.html

/*
*
*/

Snowflake [] snow = new Snowflake[500];
Catcher basket = new Catcher();

public void setup()
{
  size(500,500);
  background(0);
  for(int i = 0; i < snow.length; i++)
  {
    snow[i] = new Snowflake();
  }
}

public void draw()
{
  for(int i = 0; i < snow.length; i++)
  {
    snow[i].move();
    snow[i].canMove();
    snow[i].snowHide();
      if (snow[i].y > 494)
    {
      snow[i].resetPos();
    }
    snow[i].snowShow();
  }
    fill(0);
    stroke(0);
    rect(0,495, width, 5 );
}

public void mouseDragged()
{
  if(mousePressed == true && mouseButton == LEFT)
  {
    basket.show();
  }
  if(mousePressed == true && mouseButton == RIGHT)
  {
    basket.release();
  }
}

public class Catcher{
  public Catcher(){
    
  }  
  public void release(){
    fill(0);
    rectMode(CENTER);
    rect(mouseX,mouseY,20,20);
  }
  public void show(){
    fill(206,255,249);
    noStroke();
    ellipse(mouseX,mouseY,25,25);
  }
}

public class Snowflake
{
  int x, y;
  float xvel, yvel;
  boolean isMoving;
  public Snowflake() {
    resetPos();
    y = PApplet.parseInt(random(0, 500));
  }

  public void resetPos() {
    x = PApplet.parseInt(random(0, 500));
    y = 0;
    xvel = 0;
    yvel = PApplet.parseInt(random(1,4));
    //yvel = 2;
    isMoving = true;
  }

  public void move() {
    if (isMoving == true)
    {
      x += xvel;
      y += yvel;
    }
    if (y > 499)
    {
      resetPos();
    }
  }

  public void snowShow() {
   noStroke();
    fill(PApplet.parseInt(random(200, 255)));
    ellipse(x, y, random(1,5), random(1,5));
  }

  public void snowHide() {
    stroke(0);
    fill(0);
    ellipse(x, y-yvel, 6, 6);
  }
  
  public void canMove(){
    if(get(x, y + 6) == color(206,255,249)){
     if (get(x, y + 6) != color(0))
    {
      isMoving = false;
    } else {
      isMoving = true;
    }
    }else{
      isMoving = true;
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "snowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
