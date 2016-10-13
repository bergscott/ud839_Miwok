package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Word} objects.
 *
 * Created by bergs on 9/28/2016.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    /** Color of translations linear layout background */
    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> wordList, int colorResourceId) {
        super(context, 0, wordList);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view and set its
        // text view linear layout's background color
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            // set the text view linear layout's background color
            listItemView.findViewById(R.id.translations_linear_layout).
                    setBackgroundResource(mColorResourceId);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml for the Miwok Translation
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Set the text of the miwokTextView to the miwok translation in currentWord
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml for the Default Translation
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Set the text of the defaultTextView to the default translation in currentWord
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        // If the current Word has an image
        if(currentWord.hasImage()) {
            // Make sure the ImageView is visible
            imageView.setVisibility(View.VISIBLE);
            // Set the source of the ImageView to the Word's image attribute
            imageView.setImageResource(currentWord.getImageResourceId());
        } else {
            // Otherwise set the ImageView's visibility to GONE
            imageView.setVisibility(View.GONE);
        }

        // Get the resource ID for the proper Miwok pronunciation audio
        int pronunciationAudioId = currentWord.getAudioResourceId();
        // Initialize media player for pronunciation audio
        final MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), pronunciationAudioId);

        // Set onClick listener for translations linear layout to play pronunciation audio
        LinearLayout translationsLayout = (LinearLayout) listItemView.
                findViewById(R.id.translations_linear_layout);
        translationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        // return the listItemView complete with the correct image and text views
        return listItemView;
    }
}
