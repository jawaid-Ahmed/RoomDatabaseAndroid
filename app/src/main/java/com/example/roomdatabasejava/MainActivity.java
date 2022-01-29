package com.example.roomdatabasejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabasejava.db.AppDatabase;
import com.example.roomdatabasejava.db.User;
import com.example.roomdatabasejava.db.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addUserBtn,getUserBtn;
    TextView infoText;
    Button deleteUserBtn,updateUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addUserBtn=findViewById(R.id.addUserBtn);
        getUserBtn=findViewById(R.id.getUserBtn);
        infoText=findViewById(R.id.infoText);
        deleteUserBtn=findViewById(R.id.deleteUserBtn);
        updateUserBtn=findViewById(R.id.updateUserBtn);


        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser("Jawaid Ali","Mangi");
            }
        });

        getUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsers();
            }
        });

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });


    }

    private void deleteUser() {
        AppDatabase database=AppDatabase.getiNSTANCE(this.getApplicationContext());

        List<User> userList=database.userDao().getAllUsers();
        if(userList.size()>0){
            database.userDao().deleteUser(userList.get(0));
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "No Users To Delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser(){



        AppDatabase database=AppDatabase.getiNSTANCE(this.getApplicationContext());

        User user=new User();
        user.firstname="Sajid";
        user.lastname="Ahmed";

        database.userDao().update(user);
    }

    private void getUsers(){
        AppDatabase database=AppDatabase.getiNSTANCE(this.getApplicationContext());

        List<User> userList=database.userDao().getAllUsers();


        if(userList.size()>0){
            infoText.setText(userList.get(0).firstname);
        }else {
            Toast.makeText(this, "No Users Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUser(String fname, String lname) {
        AppDatabase database=AppDatabase.getiNSTANCE(this.getApplicationContext());

        User user=new User();
        user.firstname=fname;
        user.lastname=lname;
        database.userDao().insertUser(user);

        Toast.makeText(this, "User Inserted", Toast.LENGTH_SHORT).show();

    }
}