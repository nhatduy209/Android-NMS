package com.example.notemanagement.ui.friority;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.status.StatusAdapter;
import com.example.notemanagement.ui.status.StatusViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FriorityFragment extends Fragment {
    private RecyclerView recyclerFriorityView;
    FriorityAdapter friorityAdapter;
    ArrayList<FriorityViewModel> listFriority;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friority, container, false);
        recyclerFriorityView = view.findViewById(R.id.recyclerFriorityView);

//        FloatingActionButton floating = getActivity().findViewById(R.id.fab);
//        floating.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());//khởi tạo alert
//                alert.setView(R.layout.dialog_add_friority);
//                alert.setCancelable(true);
//                AlertDialog dialog = alert.create();
//                dialog.show();
//            }
//        });

        listFriority = new ArrayList<>();
        String timeStamp;
        for(int i =1; i<=10;i++)
        {
            String St = "Hight";
            if(i%3==1)
                St = "Medium";
            else if(i%3==2)
                St = "Slow";
            timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            listFriority.add(new FriorityViewModel(St,timeStamp));
        }
        friorityAdapter = new FriorityAdapter(getActivity().getApplicationContext(),listFriority);

//        createStatusList();
        recyclerFriorityView.setHasFixedSize(true);
        recyclerFriorityView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFriorityView.setAdapter(friorityAdapter);
        return view;
    }
}
