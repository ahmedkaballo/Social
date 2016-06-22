package com.example.ahmed.social;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ahmed on 5/22/2016.
 */
public class BackgroundWorker extends AsyncTask <String,Void,String  >{
    String result="" ;
    String line="";
    AlertDialog alertDialog;
    Intent intent;
    Context context;
    BackgroundWorker(Context ctx){

        context= ctx;

    }



    @Override
  public String doInBackground(String... params) {
        String type= params[0];
        String regestration_url ="http://192.168.1.8/creategroup.php";

        if(type.equals("create")){
            try {
                String group_name= params[1];
                String group_type= params[2];
                String description= params[3];
                String image= params[4];
                String imagename= params[5];
                URL url = new   URL(regestration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("group_name", "UTF-8")+"="+URLEncoder.encode(group_name ,"UTF-8")+"&"
                        +URLEncoder.encode("group_type", "UTF-8")+"="+URLEncoder.encode(group_type ,"UTF-8")+"&"+URLEncoder.encode("description", "UTF-8")+"="+URLEncoder.encode(description ,"UTF-8")+"&"+URLEncoder.encode("image", "UTF-8")+"="+URLEncoder.encode(image ,"UTF-8")+"&"+URLEncoder.encode("imagename", "UTF-8")+"="+URLEncoder.encode(imagename ,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));



                while((line = bufferedReader.readLine())!=null){
                    result += line;


                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPreExecute() {
       // alertDialog = new AlertDialog.Builder(context).create();
       // alertDialog.setTitle("creation status");



    }

    @Override
    protected void onPostExecute(String result) {
       // alertDialog.setMessage(result);
       // alertDialog.show();








    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

   public void setValue(String val){

       val = result;

   }
}
