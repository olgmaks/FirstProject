package com.teamvoy.dbhelper;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class HelperFactory{

    private static DBHelper dBHelper;
   
    public static DBHelper getHelper(){
        return dBHelper;
    }
    public static void setHelper(Context context){
        dBHelper = OpenHelperManager.getHelper(context, DBHelper.class);
    }
    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        dBHelper = null;
    }
}