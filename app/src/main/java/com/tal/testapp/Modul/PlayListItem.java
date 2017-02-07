package com.tal.testapp.Modul;

import org.json.JSONObject;

/**
 * Created by tal on 02/01/17.
 */
public class PlayListItem {

    private String title;
    private String video;
    private String image;

    public PlayListItem (JSONObject jsonObject) {
        this.title = jsonObject.optString("Title");
        this.video = getVideoId(jsonObject.optString("link"));
        this.image = jsonObject.optString("thumb");
    }

    public String getTitle() { return title;}

    public String getVideo() { return video;}

    public String getImage() { return image;}

    private String getVideoId(String link) {
        return link.substring(link.indexOf("=") + 1);
    }

}
