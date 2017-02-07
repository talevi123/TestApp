package com.tal.testapp.Activites;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.tal.testapp.Adapter.MyRecyclerAdapter;
import com.tal.testapp.Modul.PlayList;
import com.tal.testapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    final static String json = "{\n" +
            "  \"Playlists\": [\n" +
            "    {\n" +
            "      \"ListTitle\": \"Zen Work Music\",\n" +
            "      \"ListItems\": [\n" +
            "        {\n" +
            "          \"Title\": \"HEALING ZEN Music\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=SbCpzWMWb68\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/SbCpzWMWb68/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"Relaxing Music - Meditation\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=qrx1vyvtRLY\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/qrx1vyvtRLY/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"Relaxing Music - Background\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=loIZy6GqhUw\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/loIZy6GqhUw/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"Delta Waves Sleep Music\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=EshmcHB3yMg\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/EshmcHB3yMg/mqdefault.webp\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"ListTitle\": \"Movie Trailers\",\n" +
            "      \"ListItems\": [\n" +
            "        {\n" +
            "          \"Title\": \"Chappie\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=lyy7y0QOK-0\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/lyy7y0QOK-0/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"Ex Machina\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=PI8XBKb6DQk\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/PI8XBKb6DQk/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"The Martian\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=u-duq1iMkWU\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/u-duq1iMkWU/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"Gods of Egypt\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=IJBnK2wNQSo\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/IJBnK2wNQSo/mqdefault.webp\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"Title\": \"Angry Birds\",\n" +
            "          \"link\": \"https://www.youtube.com/watch?v=4pIi4tMJoUs\",\n" +
            "          \"thumb\": \"https://i.ytimg.com/vi_webp/4pIi4tMJoUs/mqdefault.webp\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showRecycler();
    }

    public void showRecycler() {
        new getPlaylists().execute(json);
    }

    private List<ParentListItem> getPlayList (String json) {
        List<ParentListItem> playLists = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("Playlists");
            for (int i = 0; i < jsonArray.length(); i++) {
                PlayList playList = new PlayList(jsonArray.optJSONObject(i));
                playLists.add(playList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playLists;
    }

    private void putPlaylistInRecycler(List<ParentListItem> playList) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyRecyclerAdapter(this, playList));
    }

    public class getPlaylists extends AsyncTask<String, Void, List<ParentListItem>> {

        @Override
        protected List<ParentListItem> doInBackground(String... strings) {
            return getPlayList(strings[0]);
        }

        @Override
        protected void onPostExecute(List<ParentListItem> playLists) {
            putPlaylistInRecycler(playLists);
        }
    }
}
