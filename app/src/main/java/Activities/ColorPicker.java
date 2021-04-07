package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.packag.R;

public class ColorPicker extends AppCompatActivity {

    final int MIN_RGB = 0;
    final int MAX_RGB = 255;

    EditText redEditText, greenEditText, blueEditText, opacityEditText;
    int red, green, blue, opacity;
    int redFirstDigit, greenFirstDigit, blueFirstDigit, opacityFirstDigit;

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