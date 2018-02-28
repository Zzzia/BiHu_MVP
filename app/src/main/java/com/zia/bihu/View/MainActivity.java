package com.zia.bihu.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.zia.bihu.Adapter.QuestionRvAdapter;
import com.zia.bihu.Bean.QuestionResult;
import com.zia.bihu.Interface.ItemController;
import com.zia.bihu.R;

public class MainActivity extends AppCompatActivity implements ItemController {

    private QuestionRvAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setItemController(this);
        recyclerView.setAdapter(adapter);
        adapter.init();
    }

    private void init() {
        recyclerView = findViewById(R.id.main_recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new QuestionRvAdapter(this);
    }

    @Override
    public void top(int position) {
        recyclerView.smoothScrollToPosition(position);
    }
}
