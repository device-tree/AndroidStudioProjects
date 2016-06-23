package com.bhcnav.jialin.crimeminalintent;

import java.util.UUID;

/**
 * Created by jialin on 16/6/19.
 */
public class Crime {
    private UUID mId;
    private String mTitle;

    public Crime(){
        //生成唯一标示码
        mId = UUID.randomUUID();
    }

    public UUID getID() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
