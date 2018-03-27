package com.micck.recyclerviewsticky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.addItemDecoration(new StickyItemDecoration());

        recycler_view.setAdapter(new NameAdapter(getData()));
    }

    private List<Name> getData() {
        List<Name> list = new ArrayList<>();

        Name name = new Name("A");
        list.add(name);
        name = new Name("啊1",1);
        list.add(name);
        name = new Name("啊2",1);
        list.add(name);
        name = new Name("啊3",1);
        list.add(name);
        name = new Name("啊4",1);
        list.add(name);
        name = new Name("啊5",1);
        list.add(name);
        name = new Name("啊6",1);
        list.add(name);
        name = new Name("啊7",1);
        list.add(name);
        name = new Name("啊11",1);
        list.add(name);
        name = new Name("啊12",1);
        list.add(name);
        name = new Name("啊13",1);
        list.add(name);
        name = new Name("啊14",1);
        list.add(name);
        name = new Name("啊15",1);
        list.add(name);
        name = new Name("啊16",1);
        list.add(name);
        name = new Name("啊17",1);
        list.add(name);

        name = new Name("B");
        list.add(name);
        name = new Name("不1",1);
        list.add(name);
        name = new Name("不2",1);
        list.add(name);
        name = new Name("不3",1);
        list.add(name);
        name = new Name("不4",1);
        list.add(name);
        name = new Name("不5",1);
        list.add(name);
        name = new Name("不6",1);
        list.add(name);
        name = new Name("不7",1);
        list.add(name);
        name = new Name("不11",1);
        list.add(name);
        name = new Name("不12",1);
        list.add(name);
        name = new Name("不13",1);
        list.add(name);
        name = new Name("不14",1);
        list.add(name);
        name = new Name("不15",1);
        list.add(name);
        name = new Name("不16",1);
        list.add(name);
        name = new Name("不17",1);
        list.add(name);

        name = new Name("C");
        list.add(name);
        name = new Name("吃1",1);
        list.add(name);
        name = new Name("吃2",1);
        list.add(name);
        name = new Name("吃3",1);
        list.add(name);
        name = new Name("吃4",1);
        list.add(name);
        name = new Name("吃5",1);
        list.add(name);
        name = new Name("吃6",1);
        list.add(name);
        name = new Name("吃7",1);
        list.add(name);

        return list;
    }
}
