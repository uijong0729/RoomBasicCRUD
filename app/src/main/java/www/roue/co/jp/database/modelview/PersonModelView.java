package www.roue.co.jp.database.modelview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import www.roue.co.jp.database.ExamRepository;
import www.roue.co.jp.database.entity.Person;

public class PersonModelView extends AndroidViewModel{

    private ExamRepository eRepo;

    public PersonModelView(Application application) {
        super(application);
        eRepo = ExamRepository.getInstance(application);
    }

    public void create(Person p){
        eRepo.create(p);
    }
    public void update(Person p){
        eRepo.update(p);
    }
    public void delete(){
        eRepo.delete();
    }
    public LiveData<List<Person>> read(){
        return eRepo.read();
    }
    public LiveData<Person> read(int id){
        return eRepo.read(id);
    }
}
