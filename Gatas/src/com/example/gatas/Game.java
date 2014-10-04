package com.example.gatas;


import java.util.ArrayList;

import com.example.gatas.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
//import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class Point
{
    float x, y;
    Point (float xp, float yp) {
        this.x = xp;
        this.y = yp;
    }
    
    float getX() {
    	return this.x;
    }
    
    float getY() {
    	return this.y;
    }
    
    void set(float newX, float newY) {
    	this.x = newX;
    	this.y = newY;
    }
    
    float distanceTo(Point p) {
    	return FloatMath.sqrt(FloatMath.pow((p.x-this.x),2) + 
    			FloatMath.pow((p.y-this.y),2));
    }
}

public class Ball
{
    Point position;
    Velocity velocity;
    float radius;
    boolean isBlack;
    boolean isBotSide;
    boolean isInPocket;
    boolean isPickedUp;
    long lastTimeUpdatedMs;
    
    public Ball(boolean black) {
        this.position = new Point(Constants.startWhiteX, Constants.startWhiteY); // Define startx starty
        this.velocity = new Velocity(0.0,0.0);
        this.radius = Constants.radius;
        this.isBlack = black;
        this.isBotSide = true;
        this.lastTimeUpdatedMs = SystemClock.elapsedRealtime();
        
        if(isBlack){
            isBotSide = false;
        }
        
        this.isInPocket = false;
        this.isPickedUp = false;
    }
    
    void updatePosition() {
    	long delta_t = SystemClock.elapsedRealtime() - this.lastTimeUpdatedMs;
    	this.position.set(this.position.x+delta_t * this.velocity.xVel,
    			this.position.y + delta_t * this.velocity.yVel);
    	this.lastTimeUpdatedMs = SystemClock.elapsedRealtime();
    }
}

public class Velocity
{
    
    float xVel;
    float yVel;
    
    public Velocity(float initx, float inity){
     xVel = initx;
     yVel = inity;
     
    }
    
    public void collisionWithBall(float otherXVel, float otherYVel){
        Point distance = new point (xVel - otherXVel, yVel - otherYVel);
        Point otherDistance = new point (otherXVel - xVel, otherYVel - yVel);
        float cosTheta = (distance.x * xVel + distance.y * yVel)/(sqrt(distance.x^2 + distance.y^2) * sqrt(xVel^2 + yVel^2)));
        float cosPhi = (otherDistance.x * otherXVel + otherDistance.y * otherYVel)/(sqrt(otherDistance.x^2 + otherDistance.y^2) * sqrt(otherXVel^2 + otherYVel^2)));
    
    	xVel = otherXVel * cosPhi/cosTheta;
    	yVel = otherYVel * cosPhi/cosTheta;
    }
    
    public void collisionWithWall(boolean isVertWall){
     if(isVertWall){
         xVel = -xVel;
     }   
     else{
         yVel = -yVel;
     }
     
     public void collisionWithPocket(){
     	xVel = 0;
     	yVel = 0;
     }
    }
    
}


public class Game extends View implements OnTouchListener {

    private float width;    // width of one tile
    private float height;   // height of one tile
    Paint background, translucentRedPen, bluePen;

    ArrayList<Point> pointList = new ArrayList<Point>();   

    private void init()
    {
        background = new Paint();
        //background.setStyle(Paint.Style.FILL_AND_STROKE);
        background.setColor(0xffcfffff);

        translucentRedPen = new Paint();
        translucentRedPen.setColor(getResources().getColor(R.color.translucentRedPen));

        bluePen = new Paint();
        bluePen.setColor(getResources().getColor(R.color.bluePen));
        bluePen.setStyle(Paint.Style.STROKE);
        bluePen.setTextSize(24);
        bluePen.setTextScaleX(.75f);
        bluePen.setTextAlign(Paint.Align.CENTER); // Try LEFT and RIGHT
        //FontMetrics fm = bluePen.getFontMetrics();
        //float textHeight = fm.descent + fm.ascent;       

        //setFocusable(true);
        //setFocusableInTouchMode(true);
    }
    
    public Game(Context context) {
        super(context);
        init();
    }

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Game(Context context, 
            AttributeSet ats, 
            int defaultStyle) {
        super(context, ats, defaultStyle);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the background...
        width = getWidth();
        height = getHeight();

        for (int i=1; i < pointList.size(); i++)
        {
            Point d1 = pointList.get(i-1);
            Point d2 = pointList.get(i);
            canvas.drawLine(d1.x, d1.y, d2.x, d2.y, bluePen);
        }

    }

    public boolean onTouch(View v, MotionEvent event) {     

        // Handle touch events here...
        float x = event.getX();
        float y = event.getY();
        String type="unknown";
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        //Finger is down
        case MotionEvent.ACTION_DOWN:            
            type="DOWN ";
//            pointList.clear();
            pointList.add(new Point(x,y));
            break;
        case MotionEvent.ACTION_POINTER_DOWN:
            type="ACTION_POINTER_DOWN ";        
            break;
        case MotionEvent.ACTION_UP:
        	//finger is picked up off screen
        case MotionEvent.ACTION_POINTER_UP:
            pointList.add(new Point(x,y));                 
            type="UP";
            break;
            //finger is moving
        case MotionEvent.ACTION_MOVE:          
            type= "ACTION_MOVE";
            pointList.add(new Point(x,y));              
            break;
        }
        invalidate();
        return true; // indicate event was handled
    }
}
