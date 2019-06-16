package com.otlb.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.otlb.Model.Profile;
import com.otlb.Model.UserRegister;
import com.otlb.Presenter.GetProfile_Presenter;
import com.raaleat.R;
import com.otlb.View.GetProfile_View;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

public class PayMent extends AppCompatActivity implements GetProfile_View {
    EditText login_Address, city, state, country;
    ProgressBar Progrossregister;
    GetProfile_Presenter getProfile_presenter;
    SharedPreferences Shared;
    String userr;
    String Email,Phone;
    String Price;
    double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ment);
        Price=getIntent().getStringExtra("price");
        init();
        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FUtilsValidation.isEmpty(login_Address,"");
                FUtilsValidation.isEmpty(city, "");
                FUtilsValidation.isEmpty(state, "");
//                FUtilsValidation.isEmpty(country, "");
                    if (!login_Address.getText().toString().equals("") &&
                            !city.getText().toString().equals("")
                            && !state.getText().toString().equals("")) {

                        goPayment();

                    }

            }
        });
    }
    public void init(){
        login_Address=findViewById(R.id.login_Address);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);

        Shared=getSharedPreferences("login",MODE_PRIVATE);
        userr=Shared.getString("logggin",null);
       getProfile_presenter=new GetProfile_Presenter(this,this);
       getProfile_presenter.Login(userr);
    }

    private void goPayment() {
        Intent in = new Intent(getApplicationContext(), PayTabActivity.class);
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "ahmedpop2194@gmail.com"); //this a demo account for testing the sdk
        in.putExtra(PaymentParams.SECRET_KEY,"TN7Knq5GEWnAx87l0khZyNeKR9PZEyyxmGhJ6mqLjNlRWtcWNuWmhb69kV8YuHvQnNzuaZftNBkJ4xGS1DejOks8gB7KiCAGqaaA");//Add your Secret Key Here
        in.putExtra(PaymentParams.LANGUAGE,PaymentParams.ENGLISH);
        in.putExtra(PaymentParams.TRANSACTION_TITLE, "Raaleat");
        in.putExtra(PaymentParams.AMOUNT, Double.parseDouble(Price));

        in.putExtra(PaymentParams.CURRENCY_CODE, "SAR");
        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, Phone);
        in.putExtra(PaymentParams.CUSTOMER_EMAIL, Email);
        in.putExtra(PaymentParams.ORDER_ID, "123456");
        in.putExtra(PaymentParams.PRODUCT_NAME, "Product 1, Product 2");

        in.putExtra(PaymentParams.ADDRESS_BILLING, login_Address.getText().toString());
        in.putExtra(PaymentParams.CITY_BILLING, city.getText().toString());
        in.putExtra(PaymentParams.STATE_BILLING, state.getText().toString());
        in.putExtra(PaymentParams.COUNTRY_BILLING, "SAU");
        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "682"); //Put Country Phone code if Postal code not available '00973'

//Shipping Address
        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
        in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "SAR");
        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "682"); //Put Country Phone code if Postal code not available '00973'

//Payment Page Style
        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#2474bc");
        in.putExtra(PaymentParams.THEME, PaymentParams.THEME_LIGHT);
        in.putExtra(PaymentParams.IS_TOKENIZATION, true);
        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);

    }

    @Override
    public void profile(Profile profile) {
        Email=profile.getEmail();
        Phone=profile.getPhone();

    }

    @Override
    public void Error() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {
            Log.e("Tag", data.getStringExtra(PaymentParams.RESPONSE_CODE));
            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
            }
        }
    }
}
