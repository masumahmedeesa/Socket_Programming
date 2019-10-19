package eesha.socketclient;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageFormat> {



    public MessageAdapter(Context context, int resource, List<MessageFormat> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(MainActivity.TAG, "getView:");

        MessageFormat message = getItem(position);


//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        String currentTime = sdf.format(new Date());

        if(TextUtils.isEmpty(message.getMessage())){

            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.user_connected, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);

            Log.i(MainActivity.TAG, "getView: is empty ");
            String userConnected = message.getUsername();
            messageText.setText(userConnected);

//            editTime.setText(currentTime);

        }else if(message.getUniqueId().equals(MainActivity.uniqueId)){
            Log.i(MainActivity.TAG, "getView: jodi mile jai" + message.getUniqueId() + " " + MainActivity.uniqueId);


            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.my_message, parent, false);
            TextView messageText = convertView.findViewById(R.id.message_body);
            messageText.setText(message.getMessage());

//            EditText editTime = convertView.findViewById(R.id.message_time);
//            editTime.setText(currentTime);

        }else {
            Log.i(MainActivity.TAG, "getView: is not empty");

            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.their_message, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);
            TextView usernameText = (TextView) convertView.findViewById(R.id.name);

            messageText.setVisibility(View.VISIBLE);
            usernameText.setVisibility(View.VISIBLE);


            messageText.setText(message.getMessage());
            usernameText.setText(message.getUsername());

//            EditText editTime = convertView.findViewById(R.id.message_time);
//            String t = currentTime.toString();
//            editTime.setText(t);
        }

        return convertView;
    }
}
