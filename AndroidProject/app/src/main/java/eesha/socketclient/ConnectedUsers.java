package eesha.socketclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ConnectedUsers extends AppCompatActivity {

    private ListView listView;
    public static final String TAG1  = "Connected";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_users);

        listView = findViewById(R.id.usersView);
        setTitle("Connected users");
        Bundle bundle = getIntent().getExtras();

        ArrayList<String> userss = bundle.getStringArrayList("users");

        ArrayAdapter<String> items = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userss);
        listView.setAdapter(items);

    }
}
