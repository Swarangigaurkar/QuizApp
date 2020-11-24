package com.example.swarangigaurkar.quizzup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class tutorials extends Fragment {
    RecyclerView recyclerView;
    TutorialAdapter adapter;
    List<TutorialList> TList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_tutorial,container,false);

        TList=new ArrayList<>();
        recyclerView=(RecyclerView) v.findViewById(R.id.tutrecycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TList.add(
                new TutorialList(
                        "C",
                        "https://www.youtube.com/results?search_query=c+tutorial"));

        TList.add(
                new TutorialList(
                        "Cpp","https://www.youtube.com/watch?v=MhYECGUzdA4" ));

        TList.add(
                new TutorialList(
                        "Java",
                        "https://www.youtube.com/results?search_query=java+tutorial"
                ));


        TList.add(
                new TutorialList(
                        "Python",
                        "https://www.youtube.com/results?search_query=python+tutorial")
                        );

        TList.add(
                new TutorialList(
                        "Android",
                        "https://www.youtube.com/results?search_query=android+tutorial")
        );


        adapter=new TutorialAdapter(getContext(),TList);
        recyclerView.setAdapter(adapter);

        TextView tv=v.findViewById(R.id.linktv);

        adapter.setOnItemClickListener(new TutorialAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position)
            {
                String linktv1 = ((TextView)recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.linktv)).getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linktv1));
                startActivity(intent);

            }
        });

        return v;

    }
}
