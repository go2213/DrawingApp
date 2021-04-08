package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.packag.R;

public class ColorPicker extends AppCompatActivity {

    EditText redEditText, greenEditText, blueEditText, opacityEditText;
    BrushColor brushColor;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar, opacitySeekBar;
    ImageView colorIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        redEditText = findViewById(R.id.redEditText);
        greenEditText = findViewById(R.id.greenEditText);
        blueEditText = findViewById(R.id.blueEditText);
        opacityEditText = findViewById(R.id.opacityEditText);

        brushColor = new BrushColor(50, 50, 50, 50);

        colorIV = findViewById(R.id.colorIV);
        colorIV.setBackgroundColor(brushColor.getBrushColor());

        redEditText.setText(String.valueOf(brushColor.getRed()));
        greenEditText.setText(String.valueOf(brushColor.getGreen()));
        blueEditText.setText(String.valueOf(brushColor.getBlue()));
        opacityEditText.setText(String.valueOf(brushColor.getOpacity()));

        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        opacitySeekBar = findViewById(R.id.opacitySeekBar);

        redSeekBar.setProgress(brushColor.getRed());
        greenSeekBar.setProgress(brushColor.getGreen());
        blueSeekBar.setProgress(brushColor.getBlue());
        opacitySeekBar.setProgress(brushColor.getOpacity());

        redEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String rgbInput = redEditText.getText().toString();
                if(!rgbInput.isEmpty()) {
                    brushColor.setRed(rgbInput);
                    redEditText.setText(String.valueOf(brushColor.getRed()));
                    redSeekBar.setProgress(brushColor.getRed());
                    colorIV.setBackgroundColor(brushColor.getBrushColor());
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
                        brushColor.setRed(brushColor.getRedFirstDigit());
                        redEditText.setText(String.valueOf(brushColor.getRed()));
                        colorIV.setBackgroundColor(brushColor.getBrushColor());
                    }
                }
            }
        });

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setRed(progress);
                redEditText.setText(String.valueOf(brushColor.getRed()));
                colorIV.setBackgroundColor(brushColor.getBrushColor());
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
                if(!rgbInput.isEmpty()){
                    brushColor.setGreen(rgbInput);
                    greenEditText.setText(String.valueOf(brushColor.getGreen()));
                    greenSeekBar.setProgress(brushColor.getGreen());
                    colorIV.setBackgroundColor(brushColor.getBrushColor());
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
                        brushColor.setGreen(brushColor.getGreenFirstDigit());
                        greenEditText.setText(String.valueOf(brushColor.getGreen()));
                        colorIV.setBackgroundColor(brushColor.getBrushColor());
                    }
                }
            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setGreen(progress);
                greenEditText.setText(String.valueOf(brushColor.getBlue()));
                colorIV.setBackgroundColor(brushColor.getBrushColor());

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
                if(! rgbInput.isEmpty()) {
                    brushColor.setBlue(rgbInput);
                    blueEditText.setText(String.valueOf(brushColor.getBlue()));
                    blueSeekBar.setProgress(brushColor.getBlue());
                    colorIV.setBackgroundColor(brushColor.getBrushColor());
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
                        brushColor.setBlue(brushColor.getBlueFirstDigit());
                        blueEditText.setText(String.valueOf(brushColor.getBlue()));
                        colorIV.setBackgroundColor(brushColor.getBrushColor());
                    }
                }
            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setBlue(progress);
                blueEditText.setText(String.valueOf(brushColor.getBlue()));
                colorIV.setBackgroundColor(brushColor.getBrushColor());
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
                if(!rgbInput.isEmpty()) {
                    brushColor.setOpacity(rgbInput);
                    opacityEditText.setText(String.valueOf(brushColor.getOpacity()));
                    opacitySeekBar.setProgress(brushColor.getOpacity());
                    colorIV.setBackgroundColor(brushColor.getBrushColor());
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
                        brushColor.setOpacity(brushColor.getOpacityFirstDigit());
                        opacityEditText.setText(String.valueOf(brushColor.getOpacity()));
                        colorIV.setBackgroundColor(brushColor.getBrushColor());
                    }
                }
            }
        });

        opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setOpacity(progress);
                opacityEditText.setText(String.valueOf(brushColor.getOpacity()));
                colorIV.setBackgroundColor(brushColor.getBrushColor());
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

}