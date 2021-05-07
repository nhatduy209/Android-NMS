package com.example.notemanagement.ui.note;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    static Context context;
    List<Note> listNote;
    private  int position;
    private Database database ;
    Session session;

    NoteDao noteDao;
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public NoteAdapter(Context context, List<Note> listNote){
        this.context = context;
        this.listNote = listNote;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_view, parent, false);

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){
        //Gán dữ liệu

        Note note = listNote.get(position);
        holder.txtName.setText(note.getName());
        holder.txtCategory.setText(note.getCategory());
        holder.txtPriority.setText(note.getPriority());
        holder.txtStatus.setText(note.getStatus());
        holder.txtPlanDate.setText(note.getPlanDate());
        holder.txtCreateDate.setText(note.getCreateDate());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });



    }



    @Override
    public int getItemCount() {
        return listNote.size(); // trả item tại vị trí postion
    }


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener{
        TextView txtName,txtCategory,txtPriority, txtStatus, txtPlanDate, txtCreateDate;
        TextView txtvName, txtvCategory, txtvPriority, txtvStatus, txtvPlanDate;






        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //ánh xạ view
            txtName = itemView.findViewById((R.id.txtName));
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtPriority = itemView.findViewById(R.id.txtPriority);
            txtPlanDate = itemView.findViewById(R.id.txtPlanDate);
            txtCreateDate = itemView.findViewById(R.id.txtCreateDate);
            txtvName = itemView.findViewById(R.id.txtvName);
            txtvCategory = itemView.findViewById(R.id.txtvCategory);
            txtvStatus = itemView.findViewById(R.id.txtvStatus);
            txtvPriority = itemView.findViewById(R.id.txtvPriority);
            txtvPlanDate = itemView.findViewById(R.id.txtvPlanDate);




            itemView.setOnCreateContextMenuListener(this);



        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, final View v,
                                        ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.add(contextMenu.NONE,R.id.MenuEditNote,contextMenu.NONE,"Edit"); //groupId, itemId, order, title

            contextMenu.add(contextMenu.NONE,R.id.MenuDeleteNote,contextMenu.NONE,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    session = new Session(v.getContext());
                    Note selectedNote = listNote.get(position);
                    database = Database.getInstance(context);
                    noteDao = database.noteDao();
                    noteDao.deleteNotes(selectedNote);
                    listNote = noteDao.getAll(session.getIdAccount());
                    //setItems(listNote);
                    notifyDataSetChanged();
                    return false;
                }
            });



        }



    }

}