package com.firebaseloginapp.AccountActivity;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;


        import com.firebaseloginapp.R;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class ProfAcc extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter ;
    ImageView img;
    TextView title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_acc);
        mRecyclerView = findViewById(R.id.recyclerViewP);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<ContenuDetail> models = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("DataBase").child("Contenue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot :  dataSnapshot.getChildren()){
                    ContenuDetail cours = snapshot.getValue(ContenuDetail.class);
                    String titre = cours.getTitle();
                    String des = cours.getDescription();
                    String nomP = cours.getNomprof();
                    String date = cours.getDate().toString();
                    int cmnts = cours.getCmts();
                    Log.d("Titre",titre);

                    ContenuDetail cnD = new ContenuDetail(titre,des,nomP,date,cmnts);
                    cnD.setImg(R.drawable.unnamed);
                    models.add(cnD);
                }
                myAdapter = new MyAdapter(ProfAcc.this,models);
                mRecyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }






}