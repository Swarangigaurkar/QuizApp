package com.example.swarangigaurkar.quizzup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class TutorialAdapter extends  RecyclerView.Adapter<TutorialAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<TutorialList> TList;
    private OnItemClickListner mListener;
    public interface OnItemClickListner
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner listener)
    {
        mListener=listener;
    }

    public TutorialAdapter(Context mCtx, List<TutorialList> TList) {
        this.TList = TList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        //View view1=inflater.inflate(R.layout.list_layout,  null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_cardview, parent, false);
        return new ProductViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        TutorialList product = TList.get(position);

        holder.titletv.setText(product.getTitle());
        holder.linktv.setText(String.valueOf(product.getLink()));

    }

    @Override
    public int getItemCount() {
        return TList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public CardView cardView;
        TextView titletv, linktv;

        public ProductViewHolder(View itemView,final OnItemClickListner listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titletv = itemView.findViewById(R.id.titletv);
            linktv = itemView.findViewById(R.id.linktv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(listener!=null)
                    {
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }
}
