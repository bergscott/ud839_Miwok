package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


    public WordAdapter(Context context, ArrayList<Word> wordList) {
        super(context, 0, wordList);
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

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
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

        return listItemView;
    }
}
