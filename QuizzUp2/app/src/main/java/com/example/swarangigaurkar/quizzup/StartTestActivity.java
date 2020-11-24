package com.example.swarangigaurkar.quizzup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.disklrucache.DiskLruCache;

public class StartTestActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFERENCES ="sharedPreferences";
    public static final String KEY_HIGHSCORE ="highScoreKey";
    private int highScore;
    FloatingActionButton fab1,fab2;
    LinearLayout linearLayout;
    Animation downup;
    Animation updown;
private TextView textView;
    private TextView textViewHighScore;
    private CardView card;
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        textView= findViewById(R.id.tv2);
        SeekBar seekBar = (SeekBar) findViewById(R.id.sb1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fab1=findViewById(R.id.StartActivityFAB);
        fab2=findViewById(R.id.StartActivityFAB2);
        linearLayout=findViewById(R.id.ll1);
        card=findViewById(R.id.cardview1);

        downup= AnimationUtils.loadAnimation(this,R.anim.slide_out_top);
        fab1.setAnimation(downup);

        downup= AnimationUtils.loadAnimation(this,R.anim.slide_out_top);
        fab2.setAnimation(downup);

        downup= AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        linearLayout.setAnimation(downup);

        Animation cardslowappear= AnimationUtils.loadAnimation(this,R.anim.slowsee);
        card.setAnimation(cardslowappear);


        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        String category = globalClass.getCategory();
        String difficultyL =globalClass.getDifficulty();



        ((TextView) findViewById(R.id.textViewTitle)).setText(category + ":" + difficultyL);

        textViewHighScore = findViewById(R.id.textview_highscore);


        findViewById(R.id.StartActivityFAB2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartTestActivity.this,MainActivity.class);
                intent.putExtra("pos",0);
                startActivity(intent);
                finish();
            }
		
	 /*protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_QUIZ)
        {
            if(resultCode == RESULT_OK)
            {
                int score = data.getIntExtra(Endscore.EXTRA_SCORE,0);
                if(score > highScore)
                {
                    updateHighscore(score);
                }
            }
        }
    }*/

    private void loadHighScore()
    {
        SharedPreferences prefs1 = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        highScore = prefs1.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("HighScore: " + highScore);
    }
    private void updateHighscore(int highscoreNew)
    {
        highScore = highscoreNew;
        textViewHighScore.setText("HighScore: " + highScore);

        SharedPreferences prefs =  getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore);
        editor.apply();

    }

        });

        findViewById(R.id.StartActivityFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartTestActivity.this,QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}

