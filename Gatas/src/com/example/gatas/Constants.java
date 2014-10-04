package com.example.gatas;

import android.net.Uri;
import android.provider.BaseColumns;

public interface Constants extends BaseColumns{
    public static final String EVENT_TABLE="events";
    
    public static final String NAME="name";
    public static final String GAME_ID="gameid";
    public static final String ROUND="round";
    public static final String PLAYER_LIST="players";
    public static final String DRAWING="drawing";
    public static final String PHRASE="phrase";
    public static final String _ID = BaseColumns._ID;
    
    public static final String AUTHORITY="com.example.meg.gatas";
    public static final Uri EVENT_CONTENT_URI = Uri.parse("content://"+ 
                AUTHORITY + "/" + EVENT_TABLE);
                
    public static final point pocket1= new point();
    public static final point pocket2= new point();
    public static final point pocket3= new point();
    public static final point pocket4= new point();
    public static final point pocket5= new point();
    public static final point pocket6= new point();
    public static final float tablelenx = //Define table dimensions
    public static final float tableleny = //Deine table dim
    public static final float radius = //define radius
    public static final float deceleration = //Define deceleration/friction constant
    
    

}