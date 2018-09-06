package com.example.android.miwok;

public class Word {

    private String wordDefault, wordMiwok;
    private int mImageResourceId = SEM_IMAGEM;
    private int backgroundColorId, soundId;

    private final static int SEM_IMAGEM = -1;

    public Word(String br, String mw, int sound){
        this.wordDefault = br;
        this.wordMiwok = mw;
        this.soundId = sound;

    }

    public Word(String br, String mw, int imageId, int sound){
        this.wordDefault = br;
        this.wordMiwok = mw;
        this.mImageResourceId = imageId;
        this.soundId = sound;

    }

    public String getDefautTranslation() {
        return wordDefault;
    }

    public String getMiwokTranslation() {
        return wordMiwok;
    }

    public int getImageResourceId() {return mImageResourceId;}

    public int getBackgroundColorId(){return backgroundColorId;}

    public boolean temImagem(){return mImageResourceId != -1;}

    public int getSoundId(){ return soundId; }

    @Override
    public String toString() {
        return "Word{" +
                "wordDefault='" + wordDefault + '\'' +
                ", wordMiwok='" + wordMiwok + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", backgroundColorId=" + backgroundColorId +
                ", soundId=" + soundId +
                '}';
    }
}
