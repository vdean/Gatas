package com.example.gatas;


import java.util.ArrayList;

import com.example.gatas.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
//import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class Data
{
    float x, y;
    Data (float xp, float yp)
    {
        x = xp;
        y = yp;
    }
}

class Point
{
	
}


public class Ball
{
    point Position;
    velocity velocity;
    float Radius;
    boolean IsBlack;
    boolean IsBotSide;
    boolean IsInPocket;
    boolean IsPickedUp;
    
    public Ball(boolean black){
        Position = new point(Constants.startx, Constants.starty); // Define startx starty
        Velocity = new velocity(0.0,0.0);
        Radius = Constants.radius;
        IsBlack = black;
        IsBotSide = true;
        
        if(IsBlack){
            IsBotSide = false;
        }
        
        IsInPocket = false;
        IsPicketUp = false;
    }
    
}
public class velocity
{
    
    float xVel;
    float yVel;
    
    public velocity(float initx, float inity){
     xVel = initx;
     yVel = inity;
     
    }
    
    public void collisionWithBall(float otherXVel, float otherYVel){
        
    }
    
    public void collisionWithWall(boolean isVertWall){
     if(isVertWall){
         xVel = -xVel;
     }   
     else{
         yVel = -yVel;
     }
    }
    
}

public class Game extends View implements OnTouchListener {

    private float width;    // width of one tile
    private float height;   // height of one tile
    Paint background, translucentRedPen, bluePen;

    ArrayList<Data> pointList = new ArrayList<Data>();   

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
            Data d1 = pointList.get(i-1);
            Data d2 = pointList.get(i);
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
            pointList.add(new Data(x,y));
            break;
        case MotionEvent.ACTION_POINTER_DOWN:
            type="ACTION_POINTER_DOWN ";        
            break;
        case MotionEvent.ACTION_UP:
        	//finger is picked up off screen
        case MotionEvent.ACTION_POINTER_UP:
            pointList.add(new Data(x,y));                 
            type="UP";
            break;
            //finger is moving
        case MotionEvent.ACTION_MOVE:          
            type= "ACTION_MOVE";
            pointList.add(new Data(x,y));              
            break;
        }
        invalidate();
        return true; // indicate event was handled
    }
}