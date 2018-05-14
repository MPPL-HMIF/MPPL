package com.example.alifian.if_vote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alifian.if_vote.Interface.ItemClickListener;
import com.example.alifian.if_vote.Model.Kandidat;
import com.example.alifian.if_vote.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference mDatabase;
    FirebaseDatabase database;
    DatabaseReference kandidat;
    TextView txtnim;
    EditText nim;

    RecyclerView recycler_view;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("HMIF - UNIKOM");
        setSupportActionBar(toolbar);

        //init firebase
        database = FirebaseDatabase.getInstance();
        kandidat = database.getReference("kandidat");



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent passing = getIntent();
        String txtpas = passing.getStringExtra(Intent.EXTRA_TEXT);

        //user
        View viewHeader = navigationView.getHeaderView(0);
        txtnim = (TextView)viewHeader.findViewById(R.id.txtnim);
        txtnim.setText(txtpas);

        //load data
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);

        loadData();
    }


    private void loadData(){
        FirebaseRecyclerAdapter<Kandidat,MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Kandidat, MenuViewHolder>(Kandidat.class,R.layout.menu_item,MenuViewHolder.class,kandidat) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final Kandidat model, int position) {
                viewHolder.txtnopas.setText(model.getNopas());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Kandidat clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Main2Activity.this,"Pasangan No: "+clickItem.getNopas(),Toast.LENGTH_SHORT).show();
                        mDatabase = FirebaseDatabase.getInstance().getReference("vote");

                        String ids = mDatabase.push().getKey();
                        Artist artist = new Artist(clickItem.getNopas());

                        Intent passing = getIntent();
                        String txtpas = passing.getStringExtra(Intent.EXTRA_TEXT);

                        mDatabase.child(txtpas).setValue(artist);
                        Toast.makeText(Main2Activity.this,""+txtpas,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recycler_view.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kandidat) {
            Intent intent = new Intent(Main2Activity.this, Vote.class);
            startActivity(intent);

        } else if (id == R.id.nav_video) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
