package com.example.notemanagement.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.notemanagement.ui.RegionalSalesData;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.notemanagement.R;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries = new ArrayList();
    ArrayList<RegionalSalesData> regionalSalesDataArrayList =new ArrayList<>();

    public  View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        pieChart = view.findViewById(R.id.pieChart);
        fillRegionalSalesArrayList();
        return view;
    }

    private void fillRegionalSalesArrayList(){
        // set data
        regionalSalesDataArrayList.add(new RegionalSalesData("Alex",242000));
        regionalSalesDataArrayList.add(new RegionalSalesData("Cairo",300000));
        regionalSalesDataArrayList.add(new RegionalSalesData("Suez",150000));
        regionalSalesDataArrayList.add(new RegionalSalesData("Upper EGypt",200000));
        for (int i =0; i < regionalSalesDataArrayList.size();i++){
            String region = regionalSalesDataArrayList.get(i).getRegion();
            int sales = regionalSalesDataArrayList.get(i).getSales();
            pieEntries.add(new PieEntry(sales,region));
        }


        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Regional Sales");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueTextSize(16);
        PieData pieData = new PieData(pieDataSet);
        pieChart = (PieChart) pieChart.findViewById(R.id.pieChart);
        pieChart.setData(pieData);
    }
}