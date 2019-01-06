package nsi.schoolplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nsi.schoolplanner.R;

public class NewExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exam);

        getSupportActionBar().setTitle(getResources().getString(R.string.newExam));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
