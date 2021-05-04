package com.example.notemanagement.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.DashBoard;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries = new ArrayList();
    Database database ;
    public  View onCreateView(@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        //connect database
        database = Database.getInstance(getActivity().getApplicationContext());

        final NoteDao noteDao = database.noteDao();
        pieChart = view.findViewById(R.id.pieChart);
        fillRegionalSalesArrayList();
        return view;
    }

    private void fillRegionalSalesArrayList(){
        Session session = new Session( getActivity());
        // set data
        final NoteDao noteDao = database.noteDao();
        final List<Note> ListNote  =  noteDao.getAll(session.getIdAccount());
        final StatusDaoClass statusDao = database.statusDaoClass();
        final List<StatusModel> ListStatus  =  statusDao.getAllData();
        List<DashBoard>  ListDashBoard = new ArrayList<>();
        for (StatusModel status : ListStatus){
            int count = 0;
            for (Note note  : ListNote) {
                if(note.status.equals(status.getName())){
                    count++;
                }
            }
            DashBoard dashboard = new DashBoard(status.getName() , count);
            ListDashBoard.add(dashboard);
        }


        // for each items in dashboard
        for (int i =0; i < ListDashBoard.size();i++){
            String status = ListDashBoard.get(i).getStatus();
            int countStatus = ListDashBoard.get(i).getCount();
            if(countStatus > 0 ){
                float Rate = (float)countStatus/ ListNote.size();
                float Percentage =  (float)Rate * 100;
                pieEntries.add(new PieEntry(Percentage,status));
            }
        }


        PieDataSet pieDataSet = new PieDataSet(pieEntries,"DashBoard");
        //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueTextSize(16);
        PieData pieData = new PieData(pieDataSet);

        pieChart = pieChart.findViewById(R.id.pieChart);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);

        //pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);
    }
}