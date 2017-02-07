package com.tal.testapp.Modul;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tal on 02/12/16.
 */
public class PlayList implements ParentListItem {

    public String title;
    public List<PlayListItem> playListItems;

    public PlayList (JSONObject jsonObject) {
        this.title = jsonObject.optString("ListTitle");
        this.playListItems = getItemsList(jsonObject.optJSONArray("ListItems"));
    }

    public String getTitle(){return title;}

    @Override
    public List<PlayListItem> getChildItemList() {
        return playListItems;
    }

    private List<PlayListItem> getItemsList(JSONArray jsonArray) {
        if (jsonArray != null) {
            List<PlayListItem> playListItems = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                PlayListItem playListItem = new PlayListItem(jsonArray.optJSONObject(i));
                playListItems.add(playListItem);
            }

            return playListItems;
        }
        return null;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}



