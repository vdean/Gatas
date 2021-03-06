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
    public static final float tableWidth = 200f;//Define table dimensions
    public static final float tableLength = 400f;//Define table dim
    public static final Point pocket1= new Point(0f, 0f);
    public static final Point pocket2= new Point(0f, tableLength/2);
    public static final Point pocket3= new Point(0f, tableLength);
    public static final Point pocket4= new Point(tableWidth, tableLength);
    public static final Point pocket5= new Point(tableWidth, tableLength/2);
    public static final Point pocket6= new Point(tableWidth, 0f);
    public static final float radius = 5f;//define radius
    public static final float deceleration = 0.5f;//Define deceleration/friction constant
    
    

}