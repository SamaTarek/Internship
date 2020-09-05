package com.example.orange;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String LegacyData_url="http://10.0.2.2/orangeapp/recordLegacy.php";
        String RechargeData_url= "http://10.0.2.2/orangeapp/recordRecharge.php";
        String method = params[0];
        if(method.equals("legacyData"))
        {
            String code=params[1];
            String KWH = params[2];
            try {
                URL url =new URL(LegacyData_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("code","UTF-8") +"="+URLEncoder.encode(code,"UTF-8")+"&"+
                        URLEncoder.encode("KWH","UTF-8") +"="+URLEncoder.encode(KWH,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Data stored successfully!";
            } catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            if(method.equals("rechargeData"))
            {
                String code=params[1];
                String accumReading=params[2];
                String preReadingBefKWH= params[3];
                String preReadingBefEGP=params[4];
                String preReadingAftKWH = params[5];
                String preReadingAftEGP= params[6];
                try {
                    URL url =new URL(RechargeData_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                    String data = URLEncoder.encode("code","UTF-8") +"="+URLEncoder.encode(code,"UTF-8")+"&"+
                            URLEncoder.encode("accumReading","UTF-8") +"="+URLEncoder.encode(accumReading,"UTF-8")+"&"+
                            URLEncoder.encode("preReadingBefKWH","UTF-8") +"="+URLEncoder.encode(preReadingBefKWH,"UTF-8")+"&"+
                            URLEncoder.encode("preReadingBefEGP","UTF-8") +"="+URLEncoder.encode(preReadingBefEGP,"UTF-8")+"&"+
                            URLEncoder.encode("preReadingAftKWH","UTF-8") +"="+URLEncoder.encode(preReadingAftKWH,"UTF-8")+"&"+
                            URLEncoder.encode("preReadingAftEGP","UTF-8") +"="+URLEncoder.encode(preReadingAftEGP,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Data stored successfully!";
                } catch (MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException z) {
                    z.printStackTrace();
                }
            }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
