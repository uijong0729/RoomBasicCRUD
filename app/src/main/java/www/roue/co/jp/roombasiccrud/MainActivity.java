package www.roue.co.jp.roombasiccrud;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import www.roue.co.jp.database.entity.Person;
import www.roue.co.jp.database.modelview.PersonModelView;

public class MainActivity extends AppCompatActivity {

    Button btCreate, btRead, btUpdate, btDelete;
    EditText txId, txName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //don't do it -> "new PersonModelView()"
        final PersonModelView pmv = ViewModelProviders.of(this).get(PersonModelView.class);

        txId = findViewById(R.id.id);
        txName = findViewById(R.id.name);
        btCreate = findViewById(R.id.create);
        btRead = findViewById(R.id.read);
        btUpdate = findViewById(R.id.update);
        btDelete = findViewById(R.id.delete);

        //create
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pmv.create(new Person("name"));
                Toast.makeText(MainActivity.this, "create", Toast.LENGTH_SHORT).show();
            }
        });

        //read
        btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveData<List<Person>> result = pmv.read();
                result.observe(MainActivity.this, new Observer<List<Person>>() {
                    @Override
                    public void onChanged(@Nullable List<Person> people) {
                        String str = Arrays.toString(people.toArray());
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //update
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id;
                try{
                    id = Integer.parseInt(txId.getText().toString());
                }catch (NumberFormatException e){
                    id = 1;
                }
                String name = txName.getText().toString();
                pmv.update(new Person(id, name));
                Toast.makeText(MainActivity.this, "update", Toast.LENGTH_SHORT).show();
            }
        });

        //delete
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pmv.delete();
                Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
