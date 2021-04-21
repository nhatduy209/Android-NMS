package com.example.notemanagement.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.friority.FriorityAdapter;
import com.example.notemanagement.ui.friority.FriorityViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerCategoryView;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryViewModel> listCategory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerCategoryView = view.findViewById(R.id.recyclerCategoryView);

        listCategory = new ArrayList<>();
        String timeStamp;
        for (int i = 1; i <= 10; i++) {
            String St = "Hight";
            if (i % 3 == 1)
                St = "Medium";
            else if (i % 3 == 2)
                St = "Slow";
            timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            listCategory.add(new CategoryViewModel(St, timeStamp));
        }
        categoryAdapter = new CategoryAdapter(getActivity().getApplicationContext(), listCategory);

//        createStatusList();
        recyclerCategoryView.setHasFixedSize(true);
        recyclerCategoryView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerCategoryView.setAdapter(categoryAdapter);
        return view;
    }
}
