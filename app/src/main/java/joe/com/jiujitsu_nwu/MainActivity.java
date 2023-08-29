package joe.com.jiujitsu_nwu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button doneButton;
    EditText studentNumberEdt;
    private FirebaseAuth mAuth;
    User user;
    DatabaseReference databaseReference;
    long maxid = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doneButton = findViewById(R.id.doneBtn);
        studentNumberEdt = findViewById(R.id.studentNumberEdtTxt);
        doneButton.setOnClickListener(this::onClick);
        user = new User();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    // declaration

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH: mm : ss");

    Calendar calendar = Calendar.getInstance();

    //start of onclick listener
    public void onClick(View view){
        switch (view.getId()){
            case R.id.doneBtn:
                signIn();
                databaseReference.child(String.valueOf(maxid+1)).setValue(user);
                break;
        }
    }

    // [START signin]
    private void signIn() {
        String studentNumber = studentNumberEdt.getText().toString();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String currentTime = dateFormat.format(calendar.getTime());

        HashMap<String , String> userMap = new HashMap<>();

        userMap.put("STUDENT_NUMBER" , studentNumber);
        userMap.put("DATE" , currentDate);
        userMap.put("TIME" , currentTime);

        databaseReference.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

    }
    // [END signin]

    private void updateUI(FirebaseUser user) {

    }
}