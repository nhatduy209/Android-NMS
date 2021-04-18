package com.example.notemanagement.ui.status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.slideshow.SlideshowViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StatusFragment extends Fragment {

    // Add RecyclerView member
    private RecyclerView recyclerStatusView;
    StatusAdapter adapter;
    ArrayList<StatusViewModel> status;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        status = new ArrayList<StatusViewModel>();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        for (int value = 1; value <= 50; value++) {

            String st;
            if(value%3==0){
                st="Done";
            }
            else if(value%3==1){
                st = "Proccessing";
            }
            else{
                st = "Pending";
            }
            status.add(new StatusViewModel("Name:   " + st ,"Created Date:  "+timeStamp ));
        }
//        adapter = new StatusAdapter(status,this.getContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
//        recyclerStatusView.setAdapter(adapter);
//        recyclerStatusView.setLayoutManager(linearLayoutManager);
//        final TextView textView = root.findViewById(R.id.statustext);
        recyclerStatusView = view.findViewById(R.id.recyclerStatusView);

//        createStatusList();
        recyclerStatusView.setHasFixedSize(true);
        recyclerStatusView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerStatusView.setAdapter(new StatusAdapter(1234,status));
        return view;
    }
}
