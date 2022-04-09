package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signup extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String error_msg = "";

    public signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signup.
     */
    // TODO: Rename and change types and number of parameters
    public static signup newInstance(String param1, String param2) {
        signup fragment = new signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        TextView error = v.findViewById(R.id.error);

        CheckBox terms_checkBox = v.findViewById(R.id.terms_checkBox);
        Button signup_btn = v.findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (terms_checkBox.isChecked()) {
                    signup(v);
                    error_msg = "";
                    //refreshErrorMessage(v);

                } else {
                    error_msg += "Please check the Term of Service and Privacy Policy";
                    refreshErrorMessage(v);
                }
            }
        });


        TextView signin_link = v.findViewById(R.id.signin_link);
        signin_link.setMovementMethod(LinkMovementMethod.getInstance());

        return v;
    }

    public void signup(View v) {

        final EditText emailInput = v.findViewById(R.id.email_input);
        String email = emailInput.getText().toString();
        final EditText phoneInput = v.findViewById(R.id.phone_input);
        String phone = phoneInput.getText().toString();
        final EditText firstNameInput = v.findViewById(R.id.firstname_input);
        String firstName = firstNameInput.getText().toString();
        final EditText lastNameInput = v.findViewById(R.id.lastname_input);
        String lastName = lastNameInput.getText().toString();
        final EditText addressInput = v.findViewById(R.id.address_input);
        String address = addressInput.getText().toString();
        final EditText usernameInput = v.findViewById(R.id.username_input);
        String username = usernameInput.getText().toString();
        final EditText passwordInput = v.findViewById(R.id.password_input);
        String password = passwordInput.getText().toString();
        final SwitchMaterial inTownSwitch = v.findViewById(R.id.town_switch);
        String inTown = String.valueOf(inTownSwitch.isChecked());
        String totalPoints = "0";
        String tier = "Bronze";
        String ban = "false";
        TextView error = v.findViewById(R.id.error);

        RequestParams rp_person = new RequestParams();
        rp_person.add("firstName", firstName);
        rp_person.add("lastName", lastName);
        rp_person.add("phoneNumber", phone);
        rp_person.add("address", address);

        HttpUtils.post("createPerson/" + email, rp_person, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    error.setText("");
                    refreshErrorMessage(v);
                } catch (JSONException e) {
                    error_msg += e.getMessage();
                    refreshErrorMessage(v);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error_msg += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error_msg += e.getMessage();
                }
                //refreshErrorMessage(v);
            }
        });

        RequestParams rp_account = new RequestParams();
        rp_account.add("password", password);
        rp_account.add("inTown", inTown);
        rp_account.add("totalPoints", totalPoints);
        rp_account.add("personEmail", email);

        HttpUtils.post("createAccount/" + username, rp_account, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    error_msg += e.getMessage();
                }
                //refreshErrorMessage(v);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error_msg += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error_msg += e.getMessage();
                }
                //refreshErrorMessage(v);
            }
        });

        RequestParams rp_customer = new RequestParams();
        rp_customer.add("tierClass", tier);
        rp_customer.add("ban", ban);
        rp_customer.add("personEmail", email);

        HttpUtils.post("customer/", rp_customer, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    error_msg += e.getMessage();
                }
                //refreshErrorMessage(v);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error_msg += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error_msg += e.getMessage();
                }
                //refreshErrorMessage(v);
            }
        });
    }

    private void refreshErrorMessage(View v) {
        TextView error = (TextView) v.findViewById(R.id.error);
        error.setText(error_msg);

        if (error_msg == null || error_msg.length() == 0) {
            error.setVisibility(View.GONE);
        } else {
            error.setVisibility(View.VISIBLE);
        }
    }
}