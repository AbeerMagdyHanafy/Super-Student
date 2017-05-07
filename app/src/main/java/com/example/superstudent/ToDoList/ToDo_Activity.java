package com.example.superstudent.ToDoList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.superstudent.R;

public class ToDo_Activity extends Fragment {

    ListView list_view;
    SimpleCursorAdapter list_Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_to_do, container, false);
        return  rootView;

    }


}
