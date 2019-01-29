package www.roue.co.jp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import www.roue.co.jp.database.dao.PersonDao;
import www.roue.co.jp.database.entity.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class ExamDatabase extends RoomDatabase{

    public abstract PersonDao personDao();

    private static volatile ExamDatabase INSTANCE;
    private static String DATABASE_NAME = "Database";

    static ExamDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExamDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExamDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
