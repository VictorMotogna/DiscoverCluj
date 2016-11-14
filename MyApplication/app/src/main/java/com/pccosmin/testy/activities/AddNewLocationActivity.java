package com.pccosmin.testy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.pccosmin.inmemory.InMemoryLocationService;
import com.pccosmin.testy.R;
import com.pccosmin.testy.model.Location;
import com.pccosmin.testy.services.LocationServices;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewLocationActivity extends BaseActivity {
    public  Location currentLocation;
//
    @BindView(R.id.editText)
    EditText name;

    @BindView(R.id.editText2)
    EditText address;

    @BindView(R.id.editText3)
    EditText description;

    @BindView(R.id.editText4)
    EditText funFact;

    @BindView(R.id.editText5)
    EditText inauguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_location);
        ButterKnife.bind(this);
    }

    public Location addCurrentLocation() {
        currentLocation = new Location(1, name.getText().toString(), description.getText().toString(), "http://i.imgur.com/ZkDcclQ.jpg", address.getText().toString(), inauguration.getText().toString(), funFact.getText().toString(), 0, 0);
        finish();
        return currentLocation;
    }
}
