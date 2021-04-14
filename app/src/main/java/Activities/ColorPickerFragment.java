package Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.packag.R;


public class ColorPickerFragment extends Fragment {

    private int currentBrushColor;
    EditText redEditText, greenEditText, blueEditText, opacityEditText;
    BrushColor brushColor;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar, opacitySeekBar;
    ImageView colorIV;
    ImageButton backImgBtn;
    private ColorPickerFragmentListener listener;

    public interface ColorPickerFragmentListener{
        void onColorSelectedFromPicker(int color);
    }

    public ColorPickerFragment(int color) {
        super();
        this.currentBrushColor = color;
    }
    public void setCurrentBrushColor(int currentBrushColor) {
        this.currentBrushColor = currentBrushColor;
    }

    public int getCurrentBrushColor() {
        return this.currentBrushColor;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_color_picker, container, false);

        backImgBtn = v.findViewById(R.id.backImgBtn);
        redEditText = v.findViewById(R.id.redEditText);
        greenEditText = v.findViewById(R.id.greenEditText);
        blueEditText = v.findViewById(R.id.blueEditText);
        opacityEditText = v.findViewById(R.id.opacityEditText);

        brushColor = new BrushColor(this.currentBrushColor);
        colorIV = v.findViewById(R.id.colorIV);
        colorIV.setBackgroundColor(brushColor.getBrushColor());


        redEditText.setText(String.valueOf(brushColor.getRed()));
        greenEditText.setText(String.valueOf(brushColor.getGreen()));
        blueEditText.setText(String.valueOf(brushColor.getBlue()));
        opacityEditText.setText(String.valueOf(brushColor.getOpacity()));

        redSeekBar = v.findViewById(R.id.redSeekBar);
        greenSeekBar = v.findViewById(R.id.greenSeekBar);
        blueSeekBar = v.findViewById(R.id.blueSeekBar);
        opacitySeekBar = v.findViewById(R.id.opacitySeekBar);

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
                greenEditText.setText(String.valueOf(brushColor.getGreen()));
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
        backImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(ColorPickerFragment.this).commit();
                listener.onColorSelectedFromPicker(brushColor.getBrushColor());
            }
        });

        return v;
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ColorPickerFragmentListener){
            listener = (ColorPickerFragmentListener) context;
        }
        else{
            throw new RuntimeException((context.toString()
                    + " must implement ColorPickerFragmentListener"));
        }
    }



}