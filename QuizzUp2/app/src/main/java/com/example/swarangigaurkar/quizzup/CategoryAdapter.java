package com.example.swarangigaurkar.quizzup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    List<CategoryList> Clist;
    Context context;


    public CategoryAdapter(List<CategoryList> clist, Context context){
        ;Clist=clist;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_view,viewGroup,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        CategoryList product = Clist.get(i);

        categoryViewHolder.categoryName.setText(product.getCategory());
        categoryViewHolder.categoryRating.setText(product.getrating());
        Glide.with(context).load(product.getImage()).into(categoryViewHolder.categoryImage);

    }

    @Override
    public int getItemCount() {
        return Clist.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        public TextView categoryName;
        public TextView categoryRating;
        ImageView categoryImage;

        private TextView EasyD;
        private TextView MediumD;
        private TextView HardD;

        public CategoryViewHolder(View view)
        {
            super(view);
            categoryImage = (ImageView) view.findViewById(R.id.imageView);
            categoryName =  (TextView)   view.findViewById(R.id.textview_categoryName);
            categoryRating =(TextView) view.findViewById(R.id.textview_rating);

           EasyD=(TextView) view.findViewById(R.id.EasyDifficulty);
            MediumD=(TextView) view.findViewById(R.id.MediumDifficulty);
            HardD=(TextView) view.findViewById(R.id.HardDifficulty);


            view.findViewById(R.id.EasyDifficulty).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalClass globalClass = (GlobalClass) context.getApplicationContext();
                    String category = categoryName.getText().toString();
                    String difficultyLevel = "Easy";
                    globalClass.setCategory(category);
                    globalClass.setDifficulty(difficultyLevel);
                    EasyD.setTextColor(Color.BLUE);
                    context.startActivity(new Intent(context,StartTestActivity.class));

                }
            });
            view.findViewById(R.id.MediumDifficulty).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalClass globalClass = (GlobalClass) context.getApplicationContext();
                    String category = categoryName.getText().toString();
                    String difficultyLevel = "Medium";
                    globalClass.setCategory(category);
                    globalClass.setDifficulty(difficultyLevel);
                    MediumD.setTextColor(Color.BLUE);
                    context.startActivity(new Intent(context,StartTestActivity.class));

                }
            });
            view.findViewById(R.id.HardDifficulty).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalClass globalClass = (GlobalClass) context.getApplicationContext();
                    String category = categoryName.getText().toString();
                    String difficultyLevel = "Hard";
                    globalClass.setCategory(category);
                    globalClass.setDifficulty(difficultyLevel);
                    HardD.setTextColor(Color.BLUE);
                    context.startActivity(new Intent(context,StartTestActivity.class));
                }
            });

        }
    }
}


