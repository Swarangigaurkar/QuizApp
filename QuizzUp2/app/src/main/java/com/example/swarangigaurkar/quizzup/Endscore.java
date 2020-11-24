package com.example.swarangigaurkar.quizzup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class Endscore extends AppCompatActivity {
    private int wait_time=10000;
    public static final String EXTRA_SCORE = "newScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscore);
        TextView tv= findViewById(R.id.textscore);
        TextView tv1= findViewById(R.id.total);
        String newString=getIntent().getExtras().getString("Scoreid");
        int totQue=getIntent().getExtras().getInt("TotalQue");

        tv.setText("Your Score: "+newString);
        tv1.setText("Total Questions: "+totQue);

    final  int s= Integer.parseInt(newString);

        TextView remark= findViewById(R.id.remark);

        if(s==totQue)
        {
            remark.setText("Congrats!! Well Done ");
            remark.setTextColor(Color.GREEN);
        }
        else if(s>totQue/2)
        {
            remark.setText("Good. You are almost there.");
            remark.setTextColor(Color.BLUE);
        }
        else
        {
            remark.setText("Try better next time.");
            remark.setTextColor(Color.RED);
        }

        BarChart barChart =(BarChart)findViewById(R.id.bargraph);
        barChart.setDrawBarShadow(false);
        barChart.setMaxVisibleValueCount(50);
        barChart.setDrawValueAboveBar(true);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
	int a= totQue-s;

        ArrayList<BarEntry> entries =new ArrayList<>();
        entries.add(new BarEntry(totQue,0));
        entries.add(new BarEntry(s,1));
        entries.add(new BarEntry(a,2));

        BarDataSet barDataSet= new BarDataSet(entries,"Your Performance");
        ArrayList<String> labels= new ArrayList<>();
        labels.add("Total Questions");
        labels.add("Right Questions");
        labels.add("Wrong Questions");

        BarData data=new BarData(labels,barDataSet);
        //data.setBarWidth(1f);
        barChart.setDescription("graph try");
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barChart.animateY(5000);
        barChart.setData(data);



        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent resultIntent = new Intent(Endscore.this,StartTestActivity.class);
                resultIntent.putExtra("score",s);
		setResult(RESULT_OK , resultIntent);
                startActivity(resultIntent);
                finish();
            }
        },wait_time);



    }
}
