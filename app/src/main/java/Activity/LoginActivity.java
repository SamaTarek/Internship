package Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.orange.MapsActivity;
import com.example.orange.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import App.AppConfig;
import App.AppController;
import Helper.SQLiteHandler;
import Helper.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button sign_up;
    private EditText Phone;
    private EditText Password;
    private SessionManager session;
    private SQLiteHandler db;
    private ProgressDialog pDialog;
    private static final String TAG = SignUpActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Phone = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button);
        sign_up = (Button) findViewById(R.id.button2);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(intent);
            finish();
        }





        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String phone = Phone.getText().toString().trim();
                String password = Password.getText().toString().trim();

                // Check for empty data in the form
                if (!phone.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(phone, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        sign_up.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


        private void checkLogin(final String phone, final String password) {
            // Tag used to cancel the request
            String tag_string_req = "req_login";

            pDialog.setMessage("Logging in ...");
            showDialog();

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    AppConfig.URL_LOGIN, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Login Response: " + response.toString());
                    hideDialog();

                    try {

                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");

                        // Check for error node in json
                        if (!error) {
                            // user successfully logged in
                            // Create login session
                            session.setLogin(true);

                            // Now store the user in SQLite
                            String uid = jObj.getString("uid");


                            JSONObject user = jObj.getJSONObject("user");
                            String name = user.getString("name");
                            String phone = user.getString("phone");
                            String created_at = user.getString("created_at");
                            // String id =user.getString("id");

                            // Inserting row in users table
                            //db.addUser(name, email, uid, created_at);
                            //  Log.d("user_id",">>"+id);

                            // Launch main activity
                            Intent intent = new Intent(LoginActivity.this,
                                    MapsActivity.class);
                            startActivity(intent);
                            //intent.putExtra("user_id",uid);
                            // Log.d("user_id1",">>"+uid);
                            finish();
                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Login Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("phone", phone);
                    params.put("password", password);

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }

        private void showDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }




/*
    public void openLoginActiv() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void openSignActiv()
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }




}*/
