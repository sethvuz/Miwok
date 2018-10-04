package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class NumbersFragment extends Fragment {

    private MediaPlayer player;

    public NumbersFragment() {

    }

    @Override
    public void onStop() {
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
