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
public class FamilyFragment extends Fragment {

    private MediaPlayer player;

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word ("pai", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word ("mãe", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word ("filho", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word ("filha", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word ("irmão mais velho", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word ("irmão mais novo", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word ("irmã mais velha", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word ("irmã mais nova", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word ("vovó", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word ("vovô", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter<Word> itemsAdapter = new WordAdapter<Word>(getActivity(), R.layout.list_item, words, R.color.category_family);
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
