package com.otlb.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raaleat.R;

public class Order_Success extends AppCompatActivity {

    String Id;
    TextView Orderid;
    Button Btn_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__success);
        Id=getIntent().getStringExtra("id");
        Orderid=findViewById(R.id.orderid);
        Btn_finish=findViewById(R.id.Btn_finish);

        Orderid.setText(getResources().getString(R.string.orderid)+" :  "+Id);

        Btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Order_Success.this,Navigation.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Order_Success.this,Navigation.class));
        finish();
    }
}
