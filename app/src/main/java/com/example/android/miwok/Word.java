package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 * Created by Scott Berg on 9/28/2016.
 */

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** Resource ID for the image that represents the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Resource ID for audio of Miwok pronunciation */
    private int mAudioResourceId;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param audioResourceId is the raw resource ID of the Miwok pronunciation audio file
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param audioResourceId is the raw resource ID of the Miwok pronunciation audio file
     * @param imageResourceId is the drawable resource ID of the image associated with the word
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId,
                int imageResourceId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the default translation of the word
     * @return default translation of the word
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word
     * @return Miwok translation of the word
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Get the resource ID of the image that represents the word
     * @return resource ID of image that represents the word
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Get the resource ID of the Miwok pronunciation audio file
     * @return resource ID of the Miwok pronunciation audio file
     */
    public int getAudioResourceId() { return mAudioResourceId; }

    /**
     * Declares if the Word instance has an image
     * @return true if Word has image, otherwise false
     */
    public boolean hasImage() {
        return getImageResourceId() != NO_IMAGE_PROVIDED;
    }
}
