package helloworld.com.taquangtu132gmail.taquangtu.webservicejava.view;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import helloworld.com.taquangtu132gmail.taquangtu.webservicejava.R;
import helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model.HttpUrlConnection;
import helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model.JsonReader;
import helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model.User;
import helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model.UserAdapter;

public class MainActivity extends AppCompatActivity {

    private static String sApi = "http://5bbf11b772de1d0013253714.mockapi.io/api/v11";
    private HttpUrlConnection mConnection = new HttpUrlConnection();
    private ListView mListViewUser;
    private Button mButtonPost,mButtonPut,mButtonDelete;
    private ArrayList<User> mUserArrayList;
    private UserAdapter mUserAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        mapViews();
        updateListView();
        setButtonActions();
        setListViewItemClick();
    }
    private void mapViews()
    {
        mButtonPost = findViewById(R.id.btn_add_user);
        mButtonDelete = findViewById(R.id.btn_delete);
        mButtonPut    = findViewById(R.id.btn_put);
        mListViewUser = findViewById(R.id.lv_users);
    }
    ArrayList<User> getUserArrayList(String desiredUrl)
    {
        String jsonString = null;
        try
        {
           jsonString = mConnection.getUsersJsonString(desiredUrl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return JsonReader.getArrayList(jsonString);
    }
    private void setButtonActions()
    {
        mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    mConnection.sendPOST(sApi,"users");
                    updateListView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    mConnection.sendDELETE(sApi,"users/3");
                    updateListView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mButtonPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    mConnection.sendPUT(sApi,"users/5");
                    updateListView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void updateListView()
    {
        mUserArrayList = getUserArrayList(sApi+"/users");
        mUserAdapter = new UserAdapter(this,R.layout.item_user,mUserArrayList);
        mListViewUser.setAdapter(mUserAdapter);
    }
    public void setListViewItemClick()
    {
        mListViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idAtItemIth = (int)mListViewUser.getAdapter().getItemId(i);
                String id = Integer.toString(idAtItemIth);
                try {
                    String userJson = mConnection.getById(sApi,"users/"+id);
                    JSONObject object = new JSONObject(userJson);
                    String createdAt = object.getString("createdAt");
                    String avatarLink = object.getString("avatar");
                    Toast.makeText(MainActivity.this,"Created At: "+createdAt+"\nAvatar: "+avatarLink , Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
