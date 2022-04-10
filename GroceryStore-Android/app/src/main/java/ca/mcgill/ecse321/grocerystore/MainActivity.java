package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
        error = "";
        final CheckBox terms_checkBox = findViewById(R.id.terms_checkBox);
        if (!terms_checkBox.isChecked()){
            error = "Please check the Term of Service and Privacy Policy";
            refreshErrorMessage();
        } else {
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
            final SwitchMaterial inTownSwitch = findViewById(R.id.town_switch);
            String inTown = String.valueOf(inTownSwitch.isChecked());

            // auto-generated values on customer creation
            String img_url = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
            String totalPoints = "0";
            String tier = "Bronze";
            String ban = "false";

            RequestParams rp_person = new RequestParams();
            rp_person.add("firstName", firstName);
            rp_person.add("lastName", lastName);
            rp_person.add("image", img_url);
            rp_person.add("phoneNumber", phone);
            rp_person.add("address", address);

            HttpUtils.post("createPerson/" + email, rp_person, new JsonHttpResponseHandler() {

                @Override //signup success: login
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    newPerson = response;
                    curPerson = response;
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
                        error = "Invalid input. Please try again. 1";
                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                    refreshErrorMessage();
                }

            });

            RequestParams rp_account = new RequestParams();
            rp_account.add("password", password);
            rp_account.add("inTown", inTown);
            rp_account.add("totalPoints", totalPoints);
            rp_account.add("personEmail", email);

            HttpUtils.post("createAccount/" + username, rp_account, new JsonHttpResponseHandler() {
                @Override //signup success: login
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    newAccount = response;
                    curAccount = response;
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
                        error = "Invalid input. Please try again. 2";
                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                    refreshErrorMessage();
                }
            });

            RequestParams rp_customer = new RequestParams();
            rp_customer.add("tierClass", tier);
            rp_customer.add("ban", ban);
            rp_customer.add("personEmail", email);

            HttpUtils.post("customer/", rp_customer, new JsonHttpResponseHandler() {
                @Override //signup success: login
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    newAccount = response;
                    curAccount = response;
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
                        error = "Invalid input. Please try again. 3";
                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                    refreshErrorMessage();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enter the app on the login/signup view
        setContentView(R.layout.fragment_signup);
        TextView signin_link = findViewById(R.id.signin_link);
        signin_link.setMovementMethod(LinkMovementMethod.getInstance());
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