package com.codingmel.nintendoquiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Question implements Parcelable {
    private String question;
    private String difficulty;
    private int imgId;
    private int soundId;
    private List<String> answers;
    private String rightAnswers;

    public Question(String question, String difficulty, int imgId, int soundId, List<String> answers, String rightAnswers) {
        this.question = question;
        this.difficulty = difficulty;
        this.imgId = imgId;
        this.soundId = soundId;
        this.answers = answers;
        this.rightAnswers = rightAnswers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public int getImgId() {
        return imgId;
    }

    public int getSoundId() {
        return soundId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getRightAnswers() {
        return rightAnswers;
    }

    protected Question(Parcel in) {
        question = in.readString();
        imgId = in.readInt();
        soundId = in.readInt();
        answers = in.createStringArrayList();
        rightAnswers = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeInt(imgId);
        dest.writeInt(soundId);
        dest.writeStringList(answers);
        dest.writeString(rightAnswers);
    }
}
