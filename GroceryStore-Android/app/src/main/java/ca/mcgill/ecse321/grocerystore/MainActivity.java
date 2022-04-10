package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    // for error handling, add here ...
    private String error = null;

    private JSONObject newPerson = null;
    private JSONObject curPerson = null;
    private JSONObject newAccount = null;
    private JSONObject curAccount = null;
    private JSONObject newCustomer = null;
    private JSONObject curCustomer = null;

    /**
     * Creates an new customer account using an email, a phone number, a first name,
     * a last name, an address, a username and a password.
     *
     * @param v View
     */
    public void signup(View v) {
        final EditText emailInput = findViewById(R.id.email_input);
        String email = emailInput.getText().toString();
        final EditText phoneInput = findViewById(R.id.phone_input);
        String phone = phoneInput.getText().toString();
        final EditText firstNameInput = findViewById(R.id.firstname_input);
        String firstName = firstNameInput.getText().toString();
        final EditText lastNameInput = findViewById(R.id.lastname_input);
        String lastName = lastNameInput.getText().toString();
        final EditText addressInput = findViewById(R.id.address_input);
        String address = addressInput.getText().toString();
        final EditText usernameInput = findViewById(R.id.username_input);
        String username = usernameInput.getText().toString();
        final EditText passwordInput = findViewById(R.id.password_input);
        String password = passwordInput.getText().toString();
        final SwitchMaterial inTownSwitch = v.findViewById(R.id.town_switch);
        String isLocal = String.valueOf(inTownSwitch.isChecked());

        // auto-generated values on customer creation
        String totalPoints = "0";
        String tier = "Bronze";
        String ban = "false";

        HttpUtils.post("/createAccount/" + email, new RequestParams(), new JsonHttpResponseHandler() {

            @Override//signup success: login
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newCustomer = response;
                curCustomer = response;
                try {
                    error = "";
                    } catch (Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

            @Override //signup failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input. Please try again.";
                } catch (Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enter the app on the login/signup view
        setContentView(R.layout.fragment_signup);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}