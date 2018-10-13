package helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonReader
{
    public static ArrayList<User> getArrayList(String jsonString)
    {
        ArrayList<User> users= new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject user = (JSONObject) jsonArray.getJSONObject(i);
                int userId = user.getInt("id");
                String name = user.getString("name");
                users.add(new User(userId,name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
