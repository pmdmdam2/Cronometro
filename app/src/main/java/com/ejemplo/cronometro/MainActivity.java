package com.ejemplo.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Chronometer chronometer = (Chronometer)findViewById(R.id.chTime);

        Button btStart = (Button)findViewById(R.id.btStartChronometer);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Se pone en marcha el cronómetro
                 */
                chronometer.start();
            }
        });

        Button btStop = (Button)findViewById(R.id.btStopChronometer);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Se detiene el cronómetro
                 */
                chronometer.stop();
            }
        });

        Button btRestart = (Button)findViewById(R.id.btRestartChronometer);
        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Se inicializa la hora base del cronómetro
                 */
                long systemCurrTime = SystemClock.elapsedRealtime();
                chronometer.setBase(systemCurrTime);
            }
        });

        Button btSetChFormat = (Button)findViewById(R.id.btSetChronometerFormat);
        btSetChFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Este método de evento es llamado cada 10 segundos. Momento en que se inicializa
                el cronómetro a 0
                 */
                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long systemCurrTime = SystemClock.elapsedRealtime();
                        long chronometerBaseTime = chronometer.getBase();
                        long deltaTime = systemCurrTime - chronometerBaseTime;

                        if(deltaTime > 10000)
                        {
                            chronometer.setBase(systemCurrTime);
                        }
                    }
                });

            }
        });

        Button buttonClearChronometerFormat = (Button)findViewById(R.id.btClearChronometerFormat);
        buttonClearChronometerFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Este método de evento programa el cronómetro para que cuando el contador llegue
                a 0 en la cuenta atrás se inicialice a 10
                 */
                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    int counter = 10;
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        if(counter < 0)
                        {
                            counter = 10;
                        }
                        chronometer.setText(counter + "");
                        counter--;
                    }
                });
            }
        });
    }
}
