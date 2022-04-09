package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.grocerystore.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String error = null;
    private JSONObject currentCustomer = null;
    private JSONObject newCustomer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void login(View v) {
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        RequestParams requestParams = new RequestParams();
        requestParams.add("username", username.getText().toString());
        requestParams.add("password", password.getText().toString());

        //http resquest to login Customer
        HttpUtils.get("/loginAccount/", requestParams, new JsonHttpResponseHandler() {

            @Override // login successful : display account info
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                currentCustomer = response;
                try {
                    error = "";
                   // setContentView(R.layout.profile);
                   // ((TextView) findViewById(R.id.showUsername)).setText(currentCustomer.getString("username"));
                   // ((TextView) findViewById(R.id.showPassword)).setText(currentCustomer.getString("password"));
                   // ((TextView) findViewById(R.id.showInTown)).setText(currentCustomer.getString("inTown"));
                   // ((TextView) findViewById(R.id.showTotalPoints)).setText(currentCustomer.getString("totalPoints"));
                } catch(Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

            @Override //login failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += "Error: Account does not exist.\nPlease try again.";
                } catch(Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }



}