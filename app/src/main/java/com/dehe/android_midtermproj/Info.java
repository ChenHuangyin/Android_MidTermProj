package com.dehe.android_midtermproj;

/**
 * Created by lenovo on 2017/11/7.
 */

public class Info {
    private String name;
    private String force;
    private String from;
    public Info(String name,String force,String from){
        this.name=name;
        this.force=force;
        this.from=from;
    }
    public String getName(){
        return name;
    }
    public String getForce(){
        return force;
    }
    public String getFrom(){
        return from;
    }
}
