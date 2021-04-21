package Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.packag.R;


public class ColorPickerFragment extends Fragment {

    private int currentBrushColor;
    TextView redTextView, greenTextView, blueTextView, opacityTextView;
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
        redTextView = v.findViewById(R.id.redTextView);
        greenTextView = v.findViewById(R.id.greenTextView);
        blueTextView = v.findViewById(R.id.blueTextView);
        opacityTextView = v.findViewById(R.id.opacityTextView);

        brushColor = new BrushColor(this.currentBrushColor);
        colorIV = v.findViewById(R.id.colorIV);
        colorIV.setBackgroundColor(brushColor.getBrushColor());


        redTextView.setText(String.valueOf(brushColor.getRed()));
        greenTextView.setText(String.valueOf(brushColor.getGreen()));
        blueTextView.setText(String.valueOf(brushColor.getBlue()));
        opacityTextView.setText(String.valueOf(brushColor.getOpacity()));

        redSeekBar = v.findViewById(R.id.redSeekBar);
        greenSeekBar = v.findViewById(R.id.greenSeekBar);
        blueSeekBar = v.findViewById(R.id.blueSeekBar);
        opacitySeekBar = v.findViewById(R.id.opacitySeekBar);

        redSeekBar.setProgress(brushColor.getRed());
        greenSeekBar.setProgress(brushColor.getGreen());
        blueSeekBar.setProgress(brushColor.getBlue());
        opacitySeekBar.setProgress(brushColor.getOpacity());




        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setRed(progress);
                redTextView.setText(String.valueOf(brushColor.getRed()));
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




        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setGreen(progress);
                greenTextView.setText(String.valueOf(brushColor.getGreen()));
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



        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setBlue(progress);
                blueTextView.setText(String.valueOf(brushColor.getBlue()));
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



        opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brushColor.setOpacity(progress);
                opacityTextView.setText(String.valueOf(brushColor.getOpacity()));
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