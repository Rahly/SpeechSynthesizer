package com.example.beaconreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    Button useSpeechSynthesizer;
    TextView buildingTitle;
    TextView aboutBuildingText;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useSpeechSynthesizer = findViewById(R.id.use_synthesizer_button);
        buildingTitle = findViewById(R.id.building_title);
        aboutBuildingText = findViewById(R.id.about_building_text);

        aboutBuildingText.setText("Karol Oleksy, Politechnika Lodzka");

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("pl", "PL"));
                }
            }
        });

        useSpeechSynthesizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = aboutBuildingText.getText().toString();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}
