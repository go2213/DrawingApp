package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.packag.R;

public class ColorPicker extends AppCompatActivity {

    final int MIN_RGB = 0;
    final int MAX_RGB = 255;

    EditText redEditText, greenEditText, blueEditText, opacityEditText;
    int red, green, blue, opacity;
    int redFirstDigit, greenFirstDigit, blueFirstDigit, opacityFirstDigit;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar, opacitySeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        redEditText = findViewById(R.id.redEditText);
        greenEditText = findViewById(R.id.greenEditText);
        blueEditText = findViewById(R.id.blueEditText);
        opacityEditText = findViewById(R.id.opacityEditText);

        red = 50;
        blue = 50;
        green = 50;
        opacity = 50;

        redFirstDigit = 5;
        greenFirstDigit = 5;
        blueFirstDigit = 5;
        opacityFirstDigit = 5;

        redEditText.setText(String.valueOf(red));
        greenEditText.setText(String.valueOf(green));
        blueEditText.setText(String.valueOf(blue));
        opacityEditText.setText(String.valueOf(opacity));

        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        opacitySeekBar = findViewById(R.id.opacitySeekBar);

        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);
        opacitySeekBar.setProgress(opacity);

        redEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String rgbInput = redEditText.getText().toString();
                if(rgbInput == null || rgbInput.isEmpty()){
                    red = MIN_RGB;
                }
                else {
                    redFirstDigit = Integer.parseInt(rgbInput.substring(0,1));
                    red = Integer.parseInt(rgbInput);
                    red = rgbCheckInBounds(red);
                    redEditText.setText(String.valueOf(red));
                    redSeekBar.setProgress(red);
                    redEditText.setSelection(redEditText.getText().length());
                }
                return false;
            }
        });

        redEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String rgbInput = redEditText.getText().toString();
                    if(rgbInput.isEmpty()){
                        red = redFirstDigit;
                        redEditText.setText(String.valueOf(red));
                    }
                }
            }
        });

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                redEditText.setText(String.valueOf(red));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });

        greenEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String rgbInput = greenEditText.getText().toString();
                if(rgbInput == null || rgbInput.isEmpty()){
                    green = MIN_RGB;
                }
                else {
                    greenFirstDigit = Integer.parseInt(rgbInput.substring(0,1));
                    green = Integer.parseInt(rgbInput);
                    green = rgbCheckInBounds(green);
                    greenEditText.setText(String.valueOf(green));
                    greenSeekBar.setProgress(green);
                    greenEditText.setSelection(greenEditText.getText().length());
                }
                return false;
            }
        });

        greenEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String rgbInput = greenEditText.getText().toString();
                    if(rgbInput.isEmpty()){
                        green = greenFirstDigit;
                        greenEditText.setText(String.valueOf(green));
                    }
                }
            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                greenEditText.setText(String.valueOf(green));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });

        blueEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String rgbInput = blueEditText.getText().toString();
                if(rgbInput == null || rgbInput.isEmpty()){
                    blue = MIN_RGB;
                }
                else {
                    blueFirstDigit = Integer.parseInt(rgbInput.substring(0,1));
                    blue = Integer.parseInt(rgbInput);
                    blue = rgbCheckInBounds(blue);
                    blueEditText.setText(String.valueOf(blue));
                    blueSeekBar.setProgress(blue);
                    blueEditText.setSelection(blueEditText.getText().length());
                }
                return false;
            }
        });

        blueEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String rgbInput = blueEditText.getText().toString();
                    if(rgbInput.isEmpty()){
                        blue = blueFirstDigit;
                        blueEditText.setText(String.valueOf(blue));
                    }
                }
            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                blueEditText.setText(String.valueOf(blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });

        opacityEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String rgbInput = opacityEditText.getText().toString();
                if(rgbInput == null || rgbInput.isEmpty()){
                    opacity = MIN_RGB;
                }
                else {
                    opacityFirstDigit = Integer.parseInt(rgbInput.substring(0,1));
                    opacity = Integer.parseInt(rgbInput);
                    opacity = rgbCheckInBounds(opacity);
                    opacityEditText.setText(String.valueOf(opacity));
                    opacitySeekBar.setProgress(opacity);
                    opacityEditText.setSelection(opacityEditText.getText().length());
                }
                return false;
            }
        });

        opacityEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String rgbInput = opacityEditText.getText().toString();
                    if(rgbInput.isEmpty()){
                        opacity = opacityFirstDigit;
                        opacityEditText.setText(String.valueOf(opacity));
                    }
                }
            }
        });

        opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                opacity = progress;
                opacityEditText.setText(String.valueOf(opacity));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });
    }


    public int rgbCheckInBounds(int rgbValue){
        if(rgbValue < MIN_RGB){
            return MIN_RGB;
        }
        if(rgbValue > MAX_RGB){
            return MAX_RGB;
        }
        return rgbValue;
    }


}