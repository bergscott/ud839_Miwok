package com.example.android.miwok;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // initialize the list of Words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.raw.color_red, R.drawable.color_red));
        words.add(new Word("green", "chokokki", R.raw.color_green, R.drawable.color_green));
        words.add(new Word("brown", "ṭakaakki", R.raw.color_brown, R.drawable.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.raw.color_gray, R.drawable.color_gray));
        words.add(new Word("black", "kululli", R.raw.color_black, R.drawable.color_black));
        words.add(new Word("white", "kelelli", R.raw.color_white, R.drawable.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow,
                R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow,
                R.drawable.color_mustard_yellow));

        // create ArrayAdapter that holds list of words
        WordAdapter wordAdapter =
                new WordAdapter(this, words, R.color.category_colors);

        // retrieve the listView from the xml layout
        ListView listView = (ListView) findViewById(R.id.list);

        // set the adapter to the listView
        listView.setAdapter(wordAdapter);

        // set onItemClickListener to play appropriate pronunciation audio file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // get audioResourceId of clicked Word
                int audioResourceId = words.get(position).getAudioResourceId();

                // initialize MediaPlayer with the proper audio resource
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, audioResourceId);

                // start playback of the audio
                mMediaPlayer.start();
            }
        });

    }
}
