package com.example.ahmed.social;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Group_gui extends AppCompatActivity {
    String G_name ,G_type , G_dis , G_image;
    private static final int Result_load_image=1;
    Button button;
    EditText name , type , des;
    ImageView theGroupImage;
    String json_url="http://192.168.1.8/jsondata.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_gui);

       // button = (Button)findViewById(R.id.bn);
        name=(EditText) findViewById(R.id.group_name);
        type=(EditText) findViewById(R.id.group_type);
        des=(EditText) findViewById(R.id.description);
        theGroupImage = (ImageView)findViewById(R.id.group_image);
        theGroupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galaryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galaryintent,Result_load_image);
            }
        });
       /* button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {*/
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,json_url,(String)null,
                         new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    G_name =response.getString("Group_name");
                                    G_type =response.getString("Group_type");
                                    G_dis =response.getString("Group_description");
                                    G_image =response.getString("image");
                                    init();
                                   // name.setText(response.getString("Group_name"));
                                    //type.setText(response.getString("Group_type"));
                                    //des.setText(response.getString("Group_description"));



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( Group_gui.this,"error",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();



                    }
                });

               Singleton.getInstance(Group_gui.this).addToRequestque(jsonObjectRequest);




           /* }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==Result_load_image && resultCode==RESULT_OK &&data!=null){

           Uri selected_image = data.getData();
            theGroupImage.setImageURI(selected_image);

        }
    }
    public void init()
    {
        name.setText(G_name);
        type.setText(G_type);
        des.setText(G_dis);

        Picasso.with(this).load(G_image).resize(100,100).into(theGroupImage);
    }
}

