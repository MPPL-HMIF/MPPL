package com.example.alifian.if_vote;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alifian.if_vote.Interface.ItemClickListener;
import com.example.alifian.if_vote.Model.Kandidat;
import com.example.alifian.if_vote.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Vote extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference kandidat;

    TextView nim;

    RecyclerView recycler_view;
    RecyclerView.LayoutManager layoutManager;

    private TextView mTextMessage;
    android.support.v7.widget.Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment selectedFragment = null;
//            BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_notifications);
//            Menu menu = bottomNavigationView.getMenu();
//            MenuItem menuItem = menu.getItem(2);
//            menuItem.setChecked(true);

            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    //transaction.replace(R.id.content, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    Intent intent = new Intent(Vote.this, Vote.class);
//                    startActivity(intent);
                    return true;
//                    break;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    //transaction.replace(R.id.content, new VoteFragment()).commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        database = FirebaseDatabase.getInstance();
        kandidat = database.getReference("kandidat");

        //load data
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);

        loadData();
    }

    private void loadData(){
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("HMIF - UNIKOM");
        setSupportActionBar(toolbar);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            toolbar.setElevation(10.f);
        }
        //slider foto
//        viewpager = (ViewPager)findViewById(R.id.view);
//        adapter = new CustomAdapter(this);
//        viewpager.setAdapter(adapter);

        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FirebaseRecyclerAdapter<Kandidat,MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Kandidat, MenuViewHolder>(Kandidat.class,R.layout.menu_item,MenuViewHolder.class,kandidat) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Kandidat model, int position) {
                viewHolder.txtKetua.setText(model.getKetua());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Kandidat clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Vote.this,""+clickItem.getKetua(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recycler_view.setAdapter(adapter);
    }
}
