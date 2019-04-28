package com.example.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.test.Data.OnListItemClickListener;
import com.example.test.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VisitedCountriesFragment extends Fragment implements OnListItemClickListener {


    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_visited_countries, container, false);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("visited");
        final ArrayList<String> countries = new ArrayList<>();

        final TextView textView = rootView.findViewById(R.id.visitedCountriesText);
        databaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()
                ) {
                    textView.append((CharSequence) ds.getValue() + "\n \n" );

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}