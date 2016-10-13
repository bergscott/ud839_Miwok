package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.
            OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.raw.family_father, R.drawable.family_father));
        words.add(new Word("mother", "әṭa", R.raw.family_mother, R.drawable.family_mother));
        words.add(new Word("son", "angsi", R.raw.family_son, R.drawable.family_son));
        words.add(new Word("daughter", "tune", R.raw.family_daughter, R.drawable.family_daughter));
        words.add(new Word("older brother", "taachi", R.raw.family_older_brother,
                R.drawable.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.raw.family_younger_brother,
                R.drawable.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.raw.family_older_sister,
                R.drawable.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.raw.family_younger_sister,
                R.drawable.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.raw.family_grandmother,
                R.drawable.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.raw.family_grandfather,
                R.drawable.family_grandfather));


        // create ArrayAdapter that holds list of words
        WordAdapter wordAdapter =
                new WordAdapter(this, words, R.color.category_family);

        // retrieve the listView from the xml layout
        ListView listView = (ListView) findViewById(R.id.list);

        // set the adapter to the listView
        listView.setAdapter(wordAdapter);

        // set onItemClickListener to play appropriate pronunciation audio file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // release MediaPlayer resources to prepare for the initialization of the new player
                releaseMediaPlayer();

                // get audioResourceId of clicked Word
                int audioResourceId = words.get(position).getAudioResourceId();

                // initialize MediaPlayer with the proper audio resource
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, audioResourceId);

                // start playback of the audio
                mMediaPlayer.start();

                // set onCompletionListener to release the MediaPlayer resources upon completion
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    // release the MediaPlayer resources onStop
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
