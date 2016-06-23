package com.example.ahmed.social;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed on 6/19/2016.
 */
public class backgroundTask {
    String json_url = "http://192.168.101.14/getgroups.php";

    Context context;
    ArrayList<Contact> arrayList = new ArrayList<>();

    public backgroundTask(Context context)
    {
        this.context=context;

    }
    public ArrayList<Contact> getList()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url,(String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0 ;
                        while (count<response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Contact contact = new Contact(jsonObject.getString("Group_name"),jsonObject.getString("Group_description"),jsonObject.getString("image"));
                                arrayList.add(contact);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context , "Error" , Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }
        );
        Singleton.getInstance(context).addToRequestque(jsonArrayRequest);

        return arrayList;
    }
}
