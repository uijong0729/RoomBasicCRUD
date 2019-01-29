package www.roue.co.jp.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import java.util.List;

import www.roue.co.jp.database.dao.PersonDao;
import www.roue.co.jp.database.entity.Person;

public class ExamRepository {
    private PersonDao pDao;
    private static volatile ExamRepository INSTANCE;

    private ExamRepository(Application app){
        ExamDatabase db = ExamDatabase.getInstance(app);
        pDao = db.personDao();
    }

    public static ExamRepository getInstance(Application app){
        if (INSTANCE == null) {
            INSTANCE = new ExamRepository(app);
        }
        return INSTANCE;
    }

    public void create(Person p){
        new createTask(pDao).execute(p);
    }
    public void update(Person p){
        new updateTask(pDao).execute(p);
    }
    public void delete(){
        new deleteTask(pDao).execute();
    }
    public LiveData<List<Person>> read(){
        return pDao.read();
    }
    public LiveData<Person> read(int id){
        return pDao.read(id);
    }

    //create
    private static class createTask extends AsyncTask<Person, Void, Void>{
        private PersonDao dao;

        createTask(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Person... person) {
            dao.create(person[0]);
            return null;
        }
    }

    //update
    private static class updateTask extends AsyncTask<Person, Void, Void>{
        private PersonDao dao;

        updateTask(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Person... person) {
            dao.update(person[0]);
            return null;
        }
    }

    //delete
    private static class deleteTask extends AsyncTask<Void, Void, Void>{
        private PersonDao dao;

        deleteTask(PersonDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... nodata) {
            dao.delete();
            return null;
        }
    }

}
