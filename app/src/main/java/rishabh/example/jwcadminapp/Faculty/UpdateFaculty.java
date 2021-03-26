package rishabh.example.jwcadminapp.Faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rishabh.example.jwcadminapp.R;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView scienceDepartment, commerceDepartment, artsDepartment, othersDepartment;
    private LinearLayout scienceNoData, commerceNoData, artsNoData, othersNoData;
    private List<TeacherData> list1, list2, list3, list4;
    private ProgressBar pbsc, pbco, pbar, pbot;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        scienceDepartment = findViewById(R.id.scienceDepartment);
        commerceDepartment = findViewById(R.id.commerceDepartment);
        artsDepartment = findViewById(R.id.artsDepartment);
        othersDepartment = findViewById(R.id.othersDepartment);

        pbsc = findViewById(R.id.pbsc);
        pbco = findViewById(R.id.pbco);
        pbar = findViewById(R.id.pbar);
        pbot = findViewById(R.id.pbot);

        scienceNoData = findViewById(R.id.scienceNoData);
        commerceNoData = findViewById(R.id.commerceNoData);
        artsNoData = findViewById(R.id.artsNoData);
        othersNoData = findViewById(R.id.othersNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");

        scienceDepartment();
        commerceDepartment();
        artsDepartment();
        othersDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this, AddTeacher.class));
            }
        });
    }

    private void scienceDepartment() {
        dbRef = reference.child("Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    scienceNoData.setVisibility(View.VISIBLE);
                    scienceDepartment.setVisibility(View.GONE);
                }
                else {
                    scienceNoData.setVisibility(View.GONE);
                    scienceDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    scienceDepartment.setHasFixedSize(true);
                    scienceDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1, UpdateFaculty.this, "Science");
                    scienceDepartment.setAdapter(adapter);
                }
                pbsc.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbsc.setVisibility(View.GONE);
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void commerceDepartment() {
        dbRef = reference.child("Commerce");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    commerceNoData.setVisibility(View.VISIBLE);
                    commerceDepartment.setVisibility(View.GONE);
                }
                else {
                    commerceNoData.setVisibility(View.GONE);
                    commerceDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    commerceDepartment.setHasFixedSize(true);
                    commerceDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2, UpdateFaculty.this, "Commerce");
                    commerceDepartment.setAdapter(adapter);
                }
                pbco.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbco.setVisibility(View.GONE);
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void artsDepartment() {
        dbRef = reference.child("Arts");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    artsNoData.setVisibility(View.VISIBLE);
                    artsDepartment.setVisibility(View.GONE);
                }
                else {
                    artsNoData.setVisibility(View.GONE);
                    artsDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    artsDepartment.setHasFixedSize(true);
                    artsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3, UpdateFaculty.this, "Arts");
                    artsDepartment.setAdapter(adapter);
                }
                pbar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbar.setVisibility(View.GONE);
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void othersDepartment() {
        dbRef = reference.child("Others");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    othersNoData.setVisibility(View.VISIBLE);
                    othersDepartment.setVisibility(View.GONE);
                }
                else {
                    othersNoData.setVisibility(View.GONE);
                    othersDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    othersDepartment.setHasFixedSize(true);
                    othersDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4, UpdateFaculty.this, "Others");
                    othersDepartment.setAdapter(adapter);
                }
                pbot.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbot.setVisibility(View.GONE);
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}