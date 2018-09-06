package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

       final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word ("um", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word ("dois", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word ("três", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word ("quatro", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word ("cinco", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word ("seis", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word ("sete", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word ("oito", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word ("nove", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word ("dez", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter<Word> itemsAdapter = new WordAdapter<Word>(this, R.layout.list_item, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        //Defines os eventos de clique em cada palavra
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Salva em uma variável a palavra que está sendo clicada
                Word word = words.get(position);

                //cria o player e toca o som correspondente à palavra clicada
                player = MediaPlayer.create(Numbers.this, word.getSoundId());

                //Para mostra no LOG as informações referentes ao objeto Word clicado
                Log.v("Numbers", "Word atual:" + word);

               //Inicia o som
                player.start();

                //Aguarda o som acabar para, então, liberar o recurso da variável player
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //Chama a função para liberação de recursos do MediaPlayer
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

    //Função para a liberação de recurso da variável player
    private void releasePlayer(){
        if (player != null){
            player.release();
            player = null;
        }
    }
}
