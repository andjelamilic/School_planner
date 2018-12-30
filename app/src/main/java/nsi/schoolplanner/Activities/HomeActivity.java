package nsi.schoolplanner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import nsi.schoolplanner.Model.DBManagerSingletone;
import nsi.schoolplanner.Model.Exam;
import nsi.schoolplanner.Model.Person;
import nsi.schoolplanner.Model.PersonDao;
import nsi.schoolplanner.Model.Subject;
import nsi.schoolplanner.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManagerSingletone singletone=new DBManagerSingletone();
        singletone.getInstance(this);

        Person person=new Person();
        person.setName("Andjela");
        singletone.getInstance(this).createPerson(person);
        Person person2=singletone.getInstance(this).getPerson("Andjela");

        Subject subject=new Subject();
        subject.setName("Matematika");
        subject.__setDaoSession(singletone.getInstance(this).daoSession);
        singletone.getInstance(this).createSubject(subject);
        Exam exam =new Exam();
        exam.setTitle("Test");
        exam.setSubject(subject);
        exam.setSubjectId(subject.getId());
        singletone.getInstance(this).createExam(exam);
        Exam exam1=singletone.getInstance(this).getExam("Test");
        int a=3;
    }
}
