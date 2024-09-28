package com.example.vetvisit;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nameTextField;
    ListView l;
    SeekBar slider;
    TextView ageNumber;
    EditText aboutTextField;
    EditText timeInput;
    Button okButton;
    String[] animals = {"Pies", "Kot", "Świnka morska"};

    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        nameTextField = findViewById(R.id.nameTextField);
        l = findViewById(R.id.animalsListView);
        slider = findViewById(R.id.slider);
        ageNumber = findViewById(R.id.ageNumber);
        aboutTextField = findViewById(R.id.aboutTextField);
        timeInput = findViewById(R.id.timeInput);
        okButton = findViewById(R.id.okButton);
        ArrayAdapter<String> arr;

        arr = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, animals);
        l.setAdapter(arr);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        l.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedItem = (String)adapterView.getItemAtPosition(i);
            switch (selectedItem){
                case "Pies":
                    slider.setMax(18);
                    break;
                case "Kot":
                    slider.setMax(20);
                    break;
                case "Świnka morska":
                    slider.setMax(9);
                    break;
                default:
                    break;
            }
        });

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ageNumber.setText(String.valueOf(slider.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        okButton.setOnClickListener(view -> {
            Toast.makeText(this, String.format("%s, %s, %s, %s, %s", nameTextField.getText(), selectedItem, slider.getProgress(), aboutTextField.getText(), timeInput.getText()), Toast.LENGTH_SHORT).show();
        });
    }
}