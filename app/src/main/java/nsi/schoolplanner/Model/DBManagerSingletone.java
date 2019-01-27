package nsi.schoolplanner.Model;

import android.content.Context;


import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
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
           daoSession.insert(person);
        }

        public void updatePerson(Person person){
            daoSession.getPersonDao().update(person);
        }

        public Person getPerson(String name){
            PersonDao personDao=daoSession.getPersonDao();
            Person person = personDao.queryBuilder()
                    .where(PersonDao.Properties.Name.eq(name))
                    .list().get(0);
            return person;
        }

        public Person getPerson(){
           List<Person> personList = daoSession.getPersonDao().loadAll();
           if(personList.size() > 0)
               return personList.get(0);

           return  null;
        }

        public Long createExam(Exam exam){
            return daoSession.insert(exam); //returns id
        }

        public Exam getExam(Long id){
            return daoSession.getExamDao().load(id);
        }

        public Exam getExam(String title){
            ExamDao examDao=daoSession.getExamDao();
            Exam exam = examDao.queryBuilder()
                    .where(ExamDao.Properties.Title.eq(title))
                    .list().get(0);
            return exam;
        }

        public List<Exam> getFutureExams(){
            Date startDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.YEAR, 1);
            Date endDate = calendar.getTime();

            ExamDao examDao = daoSession.getExamDao();
            List<Exam> exams = examDao.queryBuilder()
                    .where(ExamDao.Properties.Date.between(startDate, endDate))
                    .orderAsc(ExamDao.Properties.Date)
                    .list();

            return exams;
        }

        public void updateExam(Exam exam){
            daoSession.getExamDao().update(exam);
        }

        public void deleteExam(Exam exam){
            if(exam.getGrade() != null)
                daoSession.getGradeDao().delete(exam.getGrade());

            daoSession.getExamDao().delete(exam);
        }

        public List<Exam> getExamsForDate(Date date){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            Date startDate = calendar.getTime();

            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            Date endDate = calendar.getTime();

            ExamDao examDao = daoSession.getExamDao();
            List<Exam> exams = examDao.queryBuilder()
                    .where(ExamDao.Properties.Date.between(startDate, endDate))
                    .list();

            return exams;
        }

        public void deleteGradesAndTimeTable(){
            daoSession.getGradeDao().deleteAll();
            daoSession.getExamDao().deleteAll();
            daoSession.getJoinTimetableWithSubjectDao().deleteAll();
            daoSession.getTimetableDao().deleteAll();
        }

        //---------------------------------------------------------------------------------------------------------------------------------------



        public Long createSubject(Subject subject){

           return DBManagerSingletone.getInstance(this.context).daoSession.insert(subject);
        }

        public Subject getSubjectByName(String name){

            SubjectDao subjectDao=daoSession.getSubjectDao();
            List<Subject> subjects = subjectDao.queryBuilder()
                    .where(SubjectDao.Properties.Name.eq(name))
                    .list();

            if(subjects.size()>0)
                return subjects.get(0);
            else
                return null;

        }

        public Subject getSubjectById(Long id){
            SubjectDao subjectDao = daoSession.getSubjectDao();
            Subject subject = subjectDao.queryBuilder().where(SubjectDao.Properties.Id.eq(id)).list().get(0);
            return subject;
        }

        public List<Subject> getAllSubjects(){

            SubjectDao subjectDao=daoSession.getSubjectDao();
            List<Subject>subjects=subjectDao.queryBuilder().list();
            return  subjects;
        }

        public void deleteSubject(String name){

            Subject subject=getSubjectByName(name);
            SubjectDao subjectDao=daoSession.getSubjectDao();
            subjectDao.delete(subject);
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
