package nsi.schoolplanner.Model;

import android.content.Context;


import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManagerSingletone {
    private static DBManager instance = null;

    public DBManagerSingletone(){ }

    public static DBManager getInstance(Context context){
        if(instance == null)
            instance = new DBManager(context);

        return instance;
    }

    public static class DBManager{

        private DaoMaster.DevOpenHelper helper;
        private DatabaseOpenHelper database;
        private Database dbhelper;
        private DaoMaster daoMaster;
        public DaoSession daoSession;
        private Context context;

        public DBManager(Context context){

            this.context=context;
            helper = new DaoMaster.DevOpenHelper(this.context, "notes-db");
            dbhelper = helper.getWritableDb();

            daoSession = new DaoMaster(dbhelper).newSession();
        }


        // -------------------------  IVINE FUNKCIJE  ----------------------------------------------------------------------------

        public void createPerson(Person person){

            DBManagerSingletone.getInstance(this.context).daoSession.insert(person);
        }

        public void updatePerson(Person person){

        }

        public Person getPerson(String name){
            PersonDao personDao=daoSession.getPersonDao();
            Person person = personDao.queryBuilder()
                    .where(PersonDao.Properties.Name.eq(name))
                    .list().get(0);
            return person;
        }

        public void createExam(Exam exam){
            DBManagerSingletone.getInstance(this.context).daoSession.insert(exam);
        }

        public Exam getExam(Long id){
            return new Exam();
        }

        public Exam getExam(String title){
            ExamDao examDao=daoSession.getExamDao();
            Exam exam = examDao.queryBuilder()
                    .where(ExamDao.Properties.Title.eq(title))
                    .list().get(0);
            return exam;
        }

        public ArrayList<Exam> getFutureExams(){
            ArrayList<Exam> exams = new ArrayList<>();


            return exams;
        }

        public void updateExam(Exam exam){

        }

        public void deleteExam(Exam exam){

        }

        public ArrayList<Exam> getExamsForDate(Date date){
            ArrayList<Exam> exams = new ArrayList<>();

            return exams;
        }

        public void deleteGradesAndTimeTable(){

        }

        //---------------------------------------------------------------------------------------------------------------------------------------



        public void createSubject(Subject subject){

            DBManagerSingletone.getInstance(this.context).daoSession.insert(subject);
        }

        public Subject getSubject(String name){

            SubjectDao subjectDao=daoSession.getSubjectDao();
            Subject subject = subjectDao.queryBuilder()
                    .where(SubjectDao.Properties.Name.eq(name))
                    .list().get(0);
            return subject;
        }

        public Subject getSubject(Long id){
            SubjectDao subjectDao = daoSession.getSubjectDao();
            Subject subject = subjectDao.queryBuilder().where(SubjectDao.Properties.Id.eq(id)).list().get(0);
            return subject;
        }

    }
}


//KOD IZ HOME ACTIVITY STO SMO ISPROBAVALE
        /*DBManagerSingletone.getInstance(this);

        Person person=new Person();
        person.setName("Andjela");
        DBManagerSingletone.getInstance(this).createPerson(person);
        Person person2=DBManagerSingletone.getInstance(this).getPerson("Andjela");

        try {
            Subject subject = new Subject();
            subject.setName("Fizika4");
            DBManagerSingletone.getInstance(this).createSubject(subject);

            Exam exam = new Exam();
            exam.setTitle("Pismeni");
            exam.setSubjectId(subject.getId());
            DBManagerSingletone.getInstance(this).createExam(exam);
            Exam exam1 = DBManagerSingletone.getInstance(this).getExam("Pismeni");
            Subject subject2 = DBManagerSingletone.getInstance(this).getSubject(exam1.getSubjectId());

            List<Exam> exams = subject.getExams();
            exams.add(exam);
            subject.resetExams();

            Subject subject1 = DBManagerSingletone.getInstance(this).getSubject("Fizika4");
            subject1.getExams();
            int a = 3;
        }
        catch (Exception e){
            Log.i("IVADAO", e.getMessage());
        }*/
