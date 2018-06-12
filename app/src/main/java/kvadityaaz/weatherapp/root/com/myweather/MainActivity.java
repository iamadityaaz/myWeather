package kvadityaaz.weatherapp.root.com.myweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    EditText city;
    TextView result;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        city = (EditText) findViewById(R.id.getCity);
        result = (TextView) findViewById(R.id.result);
        button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myURL = "http://api.openweathermap.org/data/2.5/weather?q=" + city.getText().toString() + "&appid=35649579c1d1403a49d25504c76b92c7";


                Log.i("Tap", "Tapped" + myURL);

                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("JSON", "json" + response);


                                try {
                                    String weather = response.getString("weather");

                                    JSONArray ar = new JSONArray(weather);

                                    for (int i = 0; i <ar.length(); i++){
                                        JSONObject jr = ar.getJSONObject(i);

                                        String st = jr.getString("main");
                                        result.setText(st);
                                    }


//                                    result.setText(weather);
//
//                                    Log.i("coor", "coordinates" + weather);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error", "error" + error);
                            }
                        }

                );

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }
        });


    }
}
