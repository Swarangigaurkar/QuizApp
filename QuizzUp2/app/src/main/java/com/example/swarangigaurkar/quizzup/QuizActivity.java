package com.example.swarangigaurkar.quizzup;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {


    private static final long COUNTDOWN_IN_MILLIS =30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisleft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST ="keyQuestionsList";

    private TextView textViewQuestion;
    // private  TextView textViewScore;
    private TextView textViewQuestionNumber;
    /* private TextView textViewCategory;
     private  TextView textViewDifficulty;*/
    private TextView textViewCountDown;
    private RadioGroup rGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonSubmitNext;

    private ColorStateList textColorDefaultrb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeft;

    private List<Questions> questionsList = new ArrayList<>();

    private int questionCounter;
    public int questionTotal;
    private Questions currentQuestion;
    private int score;
    private boolean answered;

    private long backPressedTime;

    private QuizDBHelper quizDBHelper;

    private  Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_question_statement);
        textViewQuestionNumber = findViewById(R.id.text_question);

        textViewCountDown = findViewById(R.id.text_countdown);
        rGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_btn1);
        rb2 = findViewById(R.id.radio_btn2);
        rb3 = findViewById(R.id.radio_btn3);
        rb4 = findViewById(R.id.radio_btn4);
        buttonSubmitNext = findViewById(R.id.btn_submit_next);

        textColorDefaultrb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        String category1 = globalClass.getCategory();
        String difficulty1 = globalClass.getDifficulty();

        this.quizDBHelper = QuizDBHelper.getInstance(this);
        int categoryId = quizDBHelper.getCategoryID(category1,difficulty1);
        cursor  =  quizDBHelper.getAllQuestions(categoryId);
        questionsList = getQuestionsList(categoryId);
        questionTotal = questionsList.size();
        Collections.shuffle(questionsList);
        showNextQuestion();

        buttonSubmitNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!answered)
                {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    {
                        checkAnswer();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this,"Please select an answer",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    showNextQuestion();
                }
            }
        });
    }



    @Override
    protected void onDestroy() {
        this.quizDBHelper.close();
        super.onDestroy();

        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000 > System.currentTimeMillis())
        {
           Intent intent = new Intent(QuizActivity.this,StartTestActivity.class);
           startActivity(intent);
           finish();
        }
        else
        {
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    private void showNextQuestion()
    {
        rb1.setTextColor(textColorDefaultrb);
        rb2.setTextColor(textColorDefaultrb);
        rb3.setTextColor(textColorDefaultrb);
        rb4.setTextColor(textColorDefaultrb);
        rGroup.clearCheck();

        if(questionCounter < questionTotal)
        {
            currentQuestion = questionsList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionNumber.setText("Question: "+questionCounter + "/" +questionTotal);
            answered =false;
            buttonSubmitNext.setText("Confirm");

            timeLeft = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else {
            finishQuiz();
        }
    }

    private void checkAnswer()
    {
        answered =true;
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rGroup.getCheckedRadioButtonId());
        int ansNo = rGroup.indexOfChild(rbSelected) + 1;

        if(ansNo == currentQuestion.getAnsNr())
        {
            score++;
            //textViewScore.setText("Score: " + score);
        }

        showSolution();

    }

    private void startCountDown()
    {
        countDownTimer =  new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeft =0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText()
    {
        int mins =(int)(timeLeft/1000)/60;
        int secs = (int) (timeLeft/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",mins,secs);

        textViewCountDown.setText(timeFormatted);
        if(timeLeft < 10000)
        {
            textViewCountDown.setTextColor(Color.RED);
        }
        else
        {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }


    private void showSolution()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch(currentQuestion.getAnsNr())
        {
            case 1: rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2: rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3: rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4: rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 4 is correct");
                break;


        }

        if(questionCounter < questionTotal)
        {
            buttonSubmitNext.setText("Next");
        }
        else
        {
            buttonSubmitNext.setText("Finish");
        }

    }

    public List<Questions> getQuestionsList(int categoryId)
    {
        if(cursor.moveToFirst())
        {
            do {
                Questions question = new Questions();
                question.setQuestionId(cursor.getColumnIndex(QuizDBHelper.COLUMN_QUESTION_ID));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuizDBHelper.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuizDBHelper.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuizDBHelper.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuizDBHelper.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuizDBHelper.COLUMN_OPTION4)));
                question.setAnsNr(cursor.getInt(cursor.getColumnIndex(QuizDBHelper.COLUMN_ANS)));
                question.setCategoryId(cursor.getInt(cursor.getColumnIndex(QuizDBHelper.COLUMN_CATEGORY_ID)));

                questionsList.add(question);

            }while(cursor.moveToNext());
        }
        return questionsList;
    }


    private void finishQuiz()
    {
        Intent intent=new Intent(QuizActivity.this,Endscore.class);
        intent.putExtra("Scoreid",Integer.toString(score));
        intent.putExtra("TotalQue",questionTotal);
        startActivity(intent);
        finish();
    }


}

