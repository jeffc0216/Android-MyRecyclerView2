package com.cax.jeffchen.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SpotDetailActivity extends AppCompatActivity {
    private TextView tvName, tvPhone, tvAddress;
    private ImageView ivSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        findViews();
        Spot spot = (Spot) getIntent().getExtras().getSerializable("spot");
        if(spot != null) {
            showSpot(spot);
        }
    }

    private void showSpot(Spot spot) {
        tvName.setText(spot.getName());
        tvPhone.setText(spot.getPhone());
        tvAddress.setText(spot.getAddress());
        ivSpot.setImageResource(spot.getImageId());
    }

    private void findViews() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        ivSpot = (ImageView) findViewById(R.id.ivSpot);

    }
}
