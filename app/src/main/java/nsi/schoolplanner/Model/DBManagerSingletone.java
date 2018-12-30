package nsi.schoolplanner.Model;

import android.content.Context;


import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import java.util.List;

public class DBManagerSingletone {
    private static DBManager instance = null;

    public DBManagerSingletone(){

    }

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
        public void createPerson(Person person){

            DBManagerSingletone.getInstance(this.context).daoSession.insert(person);
        }
        public Person getPerson(String name){

            PersonDao personDao=daoSession.getPersonDao();
            Person person = personDao.queryBuilder()
                    .where(PersonDao.Properties.Name.eq(name))
                    .list().get(0);
            return person;
        }
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

        public void createExam(Exam exam){

            DBManagerSingletone.getInstance(this.context).daoSession.insert(exam);
        }

        public Exam getExam(String title){

            ExamDao examDao=daoSession.getExamDao();
            Exam exam = examDao.queryBuilder()
                    .where(ExamDao.Properties.Title.eq(title))
                    .list().get(0);
            return exam;
        }
    }
}
