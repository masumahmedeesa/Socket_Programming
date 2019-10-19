package eesha.socketclient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {


    private Button joinButton;
    private EditText userNickName, portAddress;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        userNickName = findViewById(R.id.userNickName);
        joinButton = findViewById(R.id.setNickName);
        portAddress = findViewById(R.id.ipAddress);
        imageView = findViewById(R.id.imageId);

        joinButton.setEnabled(false);

        userNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    joinButton.setEnabled(true);
                    Log.i(MainActivity.TAG, "onTextChanged: Abled");
                } else {
                    Log.i(MainActivity.TAG, "onTextChanged: Disabled");
                    joinButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String port = portAddress.getText().toString();
                String name = userNickName.getText().toString();
                int x = 0;
                Character z = null;
                for(int i=0; i<name.length(); i++){
                    Character y = name.charAt(i);
                    if(y == ' '){
                        userNickName.setError("It should be only one word.");
                        userNickName.requestFocus();
                        return;
                    }
                }

                if(port.isEmpty()){
                    portAddress.setError("Port is Required.");
                    portAddress.requestFocus();
                    return;
                }
                else if(port.equals("5000")){
                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                    intent.putExtra("username", userNickName.getText().toString());
                    Toast.makeText(AddUserActivity.this,"Happy Chatting",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    portAddress.setError("Please Enter Correct Port.");
                    portAddress.requestFocus();
                    return;
                }
            }
        });
    }
}
