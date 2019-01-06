package nsi.schoolplanner.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nsi.schoolplanner.Model.DBManagerSingletone;
import nsi.schoolplanner.Model.Exam;
import nsi.schoolplanner.R;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener{

    private Exam exam;
    private TextView txtSubject;
    private TextView txtDate;
    private TextView txtTime;
    private TextView txtNote;
    private Button btnEdit;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Intent intent = getIntent();
        Bundle idBundle = intent.getExtras();
        Long id = idBundle.getLong("id");
        exam = DBManagerSingletone.getInstance(this).getExam(id);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(exam.getTitle());

        findItemsById();
        setOnClickListeners();
        setData();
    }

    private void findItemsById(){
        txtSubject = findViewById(R.id.txtSubject);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        txtNote = findViewById(R.id.txtNote);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setBackgroundColor(getResources().getColor(R.color.red));
    }

    private void setOnClickListeners(){
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void setData(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEdit:{

                break;
            }

            case R.id.btnDelete:{

                break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
