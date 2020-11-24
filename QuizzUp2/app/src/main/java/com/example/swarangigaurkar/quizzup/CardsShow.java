package com.example.swarangigaurkar.quizzup;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class CardsShow extends Fragment
{
    LinearLayout linearLayout;
    Animation updown;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_cards,container,false);
        List<CategoryList> categoriesList  =new ArrayList<CategoryList>();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_category);

        QuizDBHelper dbHelper = new QuizDBHelper(getActivity());
        dbHelper.addCategory(1,"C","Easy");
        dbHelper.addCategory(2,"C","Medium");
        dbHelper.addCategory(3,"C","Hard");
        dbHelper.addCategory(4,"C++","Easy");
        dbHelper.addCategory(5,"C++","Medium");
        dbHelper.addCategory(6,"C++","Hard");
        dbHelper.addCategory(7,"Java","Easy");
        dbHelper.addCategory(8,"Java","Medium");
        dbHelper.addCategory(9,"Java","Hard");
        dbHelper.addCategory(10,"Android","Easy");
        dbHelper.addCategory(11,"Android","Medium");
        dbHelper.addCategory(12,"Android","Hard");


        dbHelper.fillQuestionsTable();



        Cursor c = dbHelper.getAllCategories();


        linearLayout=v.findViewById(R.id.ll);
        updown= AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_top);
        linearLayout.setAnimation(updown);


        CategoryAdapter categoryAdapter = new CategoryAdapter(categoriesList,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryAdapter);

       // if(c.moveToFirst())
     //   {
      //      do {
               // String category = c.getString(c.getColumnIndex(QuizDBHelper.COLUMN_CATEGORY_NAME));
                categoriesList.add(
                        new CategoryList(
                                "C",
                                "4.7",
                                R.drawable.c));

                categoriesList.add(
                        new CategoryList(
                                "C++",
                                "4.3",
                                R.drawable.cpp));

                categoriesList.add(
                        new CategoryList(
                                "Java",
                                "3.1",
                                R.drawable.java
                                ));
                categoriesList.add(
                        new CategoryList(
                                "Android",
                                "4.0",
                                R.drawable.and));







                categoryAdapter.notifyDataSetChanged();
          //  }while(c.moveToNext());
      //  }

        return v;
    }
}
