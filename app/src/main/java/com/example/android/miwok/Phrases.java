package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word ("Onde você está indo?", "minto wuksus?", R.raw.phrase_where_are_you_going));
        words.add(new Word ("Qual o seu nome?", "tinnә oyaase'nә?", R.raw.phrase_what_is_your_name));
        words.add(new Word ("Meu nome é...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word ("Como se sente?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word ("Eu estou bem.", "kuchi achit.", R.raw.phrase_im_feeling_good));
        words.add(new Word ("Você vem?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word ("Sim, estou indo.", "hәә’ әәnәm.", R.raw.phrase_yes_im_coming));
        words.add(new Word ("Estou indo.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word ("Vamos!", "yoowutis!", R.raw.phrase_lets_go));
        words.add(new Word ("Venha cá!", "әnni'nem!", R.raw.phrase_come_here));

        WordAdapter<Word> itemsAdapter = new WordAdapter<Word>(this, R.layout.list_item, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                player = MediaPlayer.create(Phrases.this, words.get(position).getSoundId());
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
