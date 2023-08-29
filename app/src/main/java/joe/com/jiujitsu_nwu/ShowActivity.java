package joe.com.jiujitsu_nwu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://jiujitsu-nwu-default-rtdb.firebaseio.com/");
    private DatabaseReference reference = rootNode.getReference().child("users");

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<UserHelperClass> list;
    SearchView searchView;
    FloatingActionButton fab;
    int count = 0;
    ProgressBar progressBar;

    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        fab = findViewById(R.id.fab);
        searchView = findViewById(R.id.search);
        progressBar = findViewById(R.id.progress);
        searchView.clearFocus();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this, list);

        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String string) {
                progressBar.clearFocus();
                searchList(string);
                return true;
            }

        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserHelperClass userHelperClass = dataSnapshot.getValue(UserHelperClass.class);
                    list.add(userHelperClass);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void searchList(String text) {
        ArrayList<UserHelperClass> searchList = new ArrayList<>();
        for (UserHelperClass userHelperClass : list) {
           if(userHelperClass.getSTUDENT_NUMBER().contains(text)){
                searchList.add(userHelperClass);
            }
        }
        adapter.searchDataList(searchList);
        count = adapter.getItemCount() * 20;
        progressBar.clearFocus();
        progressBar.setProgress(count);
        if(count<=40){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
                progressBar.clearFocus();
            }else if(count>=41) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
                    progressBar.clearFocus();
                } else if (count >= 80) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
                        progressBar.clearFocus();
                    }
                }
            }
        }

       }

    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {

                //Showing the picked value in the textView
                //textView.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 01, 20);

        datePickerDialog.show();
    }


}
