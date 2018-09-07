package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class WordAdapter<W> extends ArrayAdapter<Word> {



    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context - The current context. Used to inflate the layout file.
     * @param objects - A List of AndroidFlavor objects to display in a list
     */

    int textBackgroundColor;
    Context context;

    public WordAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Word> objects, int colorId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextViews
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, textViewResourceId, objects);
        textBackgroundColor = colorId;
        this.context = context;

    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);


        TextView wordDefault = (TextView) listItemView.findViewById(R.id.default_word);
        wordDefault.setText(currentWord.getDefautTranslation());

        TextView wordMiwok = (TextView) listItemView.findViewById(R.id.miwok_word);
        wordMiwok.setText(currentWord.getMiwokTranslation());

        //MediaPlayer mediaPlayer = MediaPlayer.create(context, currentWord.getSoundId());
        View textBox = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), textBackgroundColor);
        textBox.setBackgroundColor(color);

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.temImagem()) {
            // Get the image resource ID from the current Word object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getImageResourceId());
        } else {
            iconView.setVisibility(View.GONE);

            // Modifica as margens do layout da activity Phrases
            ViewGroup.LayoutParams lp = textBox.getLayoutParams();
                if (lp instanceof ViewGroup.MarginLayoutParams){
                    ViewGroup.MarginLayoutParams mpl = (ViewGroup.MarginLayoutParams) lp;
                    ((ViewGroup.MarginLayoutParams) lp).leftMargin = 0;
                    textBox.requestLayout();
            }
        }

        return listItemView;
    }
}
