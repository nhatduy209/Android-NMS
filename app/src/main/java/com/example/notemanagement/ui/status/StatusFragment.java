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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StatusFragment extends Fragment {

    // Add RecyclerView member
    private RecyclerView recyclerStatusView;
    StatusAdapter statusAdapter;
    ArrayList<StatusViewModel> listStatus;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        recyclerStatusView = view.findViewById(R.id.recyclerStatusView);

        listStatus = new ArrayList<>();
        String timeStamp;
        for(int i =1; i<=10;i++)
        {
            String St = "Done";
            if(i%3==1)
                St = "Proccessing";
            else if(i%3==2)
                St = "Pending";
            timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            listStatus.add(new StatusViewModel(St,timeStamp));
        }
        statusAdapter = new StatusAdapter(getActivity().getApplicationContext(),listStatus);

//        createStatusList();
        recyclerStatusView.setHasFixedSize(true);
        recyclerStatusView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerStatusView.setAdapter(statusAdapter);
        return view;
    }
}
