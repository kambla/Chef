package com.example.chef.user;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chef.R;
import com.example.chef.Recipe;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        TextView userEmail = findViewById(R.id.user_email);
        userEmail.setText(currentUser.getEmail() + "\n" + currentUser.getDisplayName() + "\n" +
                currentUser.getPhoneNumber());

    }

    public void sendData(){

    }

    public void receiveData(View v){

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document("testDoc");
        docRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                FieldPath fp = FieldPath.of("recipes");
                DocumentSnapshot ds = task.getResult();
                if(ds != null) {
                    Object ob = ds.get(fp);
                    Log.d("App-com.example.chef",ob.toString());
                    String message = ds.get(fp).toString();
                    Toast.makeText(UserInfoActivity.this, message, Toast.LENGTH_SHORT);
                }
                else
                    Toast.makeText(UserInfoActivity.this, "Null recipe ref",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(UserInfoActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
