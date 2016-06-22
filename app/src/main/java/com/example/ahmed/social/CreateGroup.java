package com.example.ahmed.social;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class CreateGroup extends AppCompatActivity {
    Bitmap image;
    String encodedimage;
    String imageName;

    private static final int Result_load_image=1;
    EditText group_name , group_type , group_description;
    ImageView create_group_image;

    String result;
    String sen="group created";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        create_group_image = (ImageView)findViewById(R.id.create_group_image);
        group_name = (EditText) findViewById(R.id.group_name);
        group_type = (EditText) findViewById(R.id.group_type);
        group_description = (EditText) findViewById(R.id.group_description);
        create_group_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galaryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galaryintent,Result_load_image);
            }
        });


    }
    public void  oncreategroup(View view){
        Intent intent;
        image=((BitmapDrawable)create_group_image.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        encodedimage= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        String g_name = group_name.getText().toString();
        String g_type = group_type.getText().toString();
        String g_description = group_description.getText().toString();
        String type ="create";
        BackgroundWorker  backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type , g_name ,  g_type, g_description , encodedimage , imageName);
        intent = new Intent(this, Group_gui.class);
        startActivity(intent);
      /*  backgroundWorker.setValue(result);
        if (result == sen){
            intent = new Intent(this, Group_gui.class);
            startActivity(intent);

        }
        else{
            Toast toast = Toast.makeText(this , "Error",Toast.LENGTH_LONG);
            toast.show();

        }*/




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==Result_load_image && resultCode==RESULT_OK &&data!=null){

            Uri selected_image = data.getData();
            create_group_image.setImageURI(selected_image);
            File f = new File(String.valueOf(selected_image));

             imageName = f.getName();



        }
    }
}
