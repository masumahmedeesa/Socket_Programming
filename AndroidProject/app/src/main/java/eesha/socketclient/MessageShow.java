 package eesha.socketclient;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

 public class MessageShow extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_show);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        listView = findViewById(R.id.messageShowId);
        databaseHelper = new DatabaseHelper(this);
        loadData();

        setTitle("Saved messages");
    }

    public void loadData(){

        ArrayList<String> show = new ArrayList<>();
        Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No data is available ", Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                show.add(cursor.getString(1)+" : "+cursor.getString(2));
            }
        }
        ArrayAdapter<String> items = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,show);
        listView.setAdapter(items);
    }
}
