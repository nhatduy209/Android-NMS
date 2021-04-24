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


import com.example.notemanagement.DB.DaoClass.NoteDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.NoteModel;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    static Context context;
    List<NoteModel> listNote;
    private  int position;
    public int getPosition() {
        return position;
    }
    private Database database ;
    Session session;




    public NoteAdapter(Context context, List<NoteModel> listNote){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        //Gán dữ liệu

        NoteModel note = listNote.get(position);
        holder.txtName.setText(note.getName());
        holder.txtCategory.setText(note.getCategory());
        holder.txtPriority.setText(note.getPriority());
        holder.txtStatus.setText(note.getStatus());
        holder.txtPlanDate.setText(note.getPlanDate());
        holder.txtCreateDate.setText(note.getCreateDate());

    }

    @Override
    public int getItemCount() {
        return listNote.size(); // trả item tại vị trí postion
    }

  /*  public void deleteItem(int position) {
        listNote.remove(position);
        notifyItemRemoved(position);

    }

    public Context getContext() {
        return NoteAdapter.context;
    }*/


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtName,txtCategory,txtPriority, txtStatus, txtPlanDate, txtCreateDate;
        TextView txtvName, txtvCategory, txtvPriority, txtvStatus, txtvPlanDate, txtvCreateDate;

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
            txtvCreateDate = itemView.findViewById(R.id.txtvCreateDate);
            itemView.setOnCreateContextMenuListener(this);



        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 0, 0, "Edit"); //groupId, itemId, order, title
            MenuItem Delete = menu.add(Menu.NONE, 1, 0, "Delete");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);



        }
        public void setItems(List<NoteModel> notes)
        {
            listNote = notes;
        }
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NoteModel temp = listNote.get(position);
                database = Database.getInstance(context);
                NoteDaoClass noteDao = database.noteDao();

                NoteModel selectedNote = noteDao.getNote(temp.id);

                session = new Session(context);
                session.setIdNote(selectedNote.id);




                switch (item.getItemId()) {
                    case 0:
                        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                        FragmentTransaction ft =  fragmentManager.beginTransaction();

                        DialogFragment newFragment = EditNoteDialog.newInstance();

                        newFragment.show(ft, "edit_note_dialog");

                       // notifyItemChanged(position);
                        break;

                    case 1:
                        noteDao.deleteNotes(selectedNote);
                        //notifyItemRemoved(position);
                        listNote = noteDao.getAll(session.getIdAccount());
                        setItems(listNote);
                        notifyDataSetChanged();


                        break;
                }

                return true;
            }
        };


    }
}

