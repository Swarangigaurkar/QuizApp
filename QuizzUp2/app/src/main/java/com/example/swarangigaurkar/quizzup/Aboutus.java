package com.example.swarangigaurkar.quizzup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Aboutus extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_aboutus, container, false);
        TextView tv=v.findViewById(R.id.tv3);
        tv.setText("As Android is rapidly getting famous day by day and the number of its users are increasing with every blink of eye, because it is easy to access the necessary android based applications on smart phones and tablets in your hands.\n" +
                " Therefore, we found this idea interesting, easy and time efficient to facilitate the users in this way without any difficulty. \n" +
                "There are many online quiz applications available on internet, but most of them are only for entertainment and fun. Moreover, if one is " +
                "going to appear in any test or interview, then it is difficult and time consuming for them to read the full books or articles related to specific fields for the preparation or revising their knowledge. \n" +
                "\n" +
                " Our app provides them the facility to revise their knowledge or to learn something advantageous at one place without wasting their time.\n");
        return v;
    }
}
