package nsi.schoolplanner.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nsi.schoolplanner.R;

public class SubjectActivity extends AppCompatActivity {

    public static String subjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        if(subjectName!=null) {
            getSupportActionBar().setTitle(subjectName);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // finish();
        startActivity(new Intent(SubjectActivity.this,AllSubjectsActivity.class));
        return true;
    }
}
