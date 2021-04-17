package com.example.notemanagement.ui.status;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;

public class StatusViewHolder  extends RecyclerView.ViewHolder {
    private View itemView;
    public TextView statusContent;
    public TextView createdDateContent;

    public StatusViewHolder(@NonNull View itemView){
        super(itemView);
        itemView = itemView;
        statusContent = itemView.findViewById(R.id.lbStatusContent);
        createdDateContent = itemView.findViewById(R.id.lbCreatedContent);
    }

    public TextView getCreatedDateContent() {
        return createdDateContent;
    }

    public TextView getStatusContent() {
        return statusContent;
    }
}
