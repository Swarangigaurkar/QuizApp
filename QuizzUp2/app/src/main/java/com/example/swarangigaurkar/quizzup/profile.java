package com.example.swarangigaurkar.quizzup;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profile extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_profile,container,false);
        TextView em= v.findViewById(R.id.emailtv);
        String ss= MainActivity.newString;
        em.setText("Email: "+ss);
        return v;
    }
}

