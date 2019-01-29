package www.roue.co.jp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import www.roue.co.jp.database.entity.Person;

@Dao
public interface PersonDao {
    @Insert
    void create(Person person);

    @Query("SELECT * FROM person")
    LiveData<List<Person>> read();

    @Query("SELECT * FROM person WHERE id = :id")
    LiveData<Person> read(int id);

    @Update
    void update(Person person);

    @Query("DELETE FROM person")
    void delete();

    /*
        your another options


        @Delete
        void delete(Person person);

        @Query("DELETE FROM person WHERE id=:id")
        void delete(int id);
    */
}
