package com.example.alifian.if_vote;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alifian.if_vote.Common.Common;
import com.example.alifian.if_vote.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText nim,sandi;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nim = (EditText)findViewById(R.id.nim);
        sandi = (EditText)findViewById(R.id.sandi);
        btn_login = (Button)findViewById(R.id.btn_login);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_users = database.getReference("users");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please waiting ...");
                mDialog.show();

                table_users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user doesn't exist in database
                        if (dataSnapshot.child(nim.getText().toString()).exists()) {

                            //Get Users Information
                            mDialog.dismiss();
                            String txtpass = nim.getText().toString();
                            Users users = dataSnapshot.child(nim.getText().toString()).getValue(Users.class);
                            if (users.getPassword().equals(sandi.getText().toString())) {
                                Toast.makeText(LoginActivity.this, "Log In Success...!", Toast.LENGTH_SHORT).show();
                                Intent main = new Intent(LoginActivity.this,Main2Activity.class);
                                main.putExtra(Intent.EXTRA_TEXT, txtpass);
//                                Common.currentusers = users;
                                startActivity(main);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Log In Failed !!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "User not Exist..", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
