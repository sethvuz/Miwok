package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word ("vermelho", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word ("verde", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word ("marron", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word ("cinza", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word ("preto", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word ("branco", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word ("bege", "chiwiiṭә", R.drawable.color_dusty_yellow, R.raw.color_mustard_yellow));
        words.add(new Word ("amarelo", "topiise", R.drawable.color_mustard_yellow,R.raw.color_dusty_yellow));

        WordAdapter<Word> itemsAdapter = new WordAdapter<Word>(this, R.layout.list_item, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                player = MediaPlayer.create(Colors.this, words.get(position).getSoundId());
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releasePlayer();
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer(){
        if (player != null){
            player.release();
            player = null;
        }
    }
}
