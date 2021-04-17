package com.example.notemanagement.ui.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class StatusAdapter<random> extends
        RecyclerView.Adapter<StatusViewHolder> {

    private List<StatusViewModel> mStatus;
//    private Context mContext;

    private Random random;

    public StatusAdapter(int seed,List<StatusViewModel> mStatus){
        this.random = new Random(seed);
        this.mStatus = mStatus;
    }

    public String statusProcess(int value){
        if(value%3==0){
            return "Done";
        }
        else if(value%3==1){
            return "Proccessing";
        }
        else{
            return "Pending";
        }

    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_status_view;
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        StatusViewModel statusViewModel = mStatus.get(position);

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        holder.statusContent.setText(statusViewModel.getStatusName());
        holder.createdDateContent.setText(statusViewModel.getCreatedDate());
//        holder.getStatusContent().setText(statusProcess(random.nextInt()));
//        holder.getCreatedDateContent().setText(timeStamp);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
