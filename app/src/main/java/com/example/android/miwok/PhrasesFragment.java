package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private MediaPlayer player;

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

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

        WordAdapter<Word> itemsAdapter = new WordAdapter<Word>(getActivity(), R.layout.list_item, words, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                player = MediaPlayer.create(getActivity(), words.get(position).getSoundId());
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releasePlayer();
                    }
                });
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
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
