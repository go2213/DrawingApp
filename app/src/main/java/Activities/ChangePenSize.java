package Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.example.packag.R;

import Utils.AppSession;
import Views.CanvasView;
import interfaces.changeStrokeInterface;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePenSize#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePenSize extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SeekBar penSeekBar, pencilSeekBar, highLighterSeekBar, ereasedSeekBar;
    ImageButton backImgBtn;

    public AppSession appSession;
    changeStrokeInterface changeStrokeInterface1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePenSize.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePenSize newInstance(String param1, String param2) {
        ChangePenSize fragment = new ChangePenSize();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appSession = new AppSession(getContext(), AppSession.PREF_APP);
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_change_pen_size, container, false);
        backImgBtn = v.findViewById(R.id.backImgBtn);

        penSeekBar = v.findViewById(R.id.penSeekBar);
        pencilSeekBar = v.findViewById(R.id.pencliSeekBar);
        highLighterSeekBar = v.findViewById(R.id.highLighterSeekBar);
        ereasedSeekBar = v.findViewById(R.id.ereasedSeekBar);
        pencilSeekBar.setProgress(Integer.parseInt(appSession.getPencilSize()));
        penSeekBar.setProgress(Integer.parseInt(appSession.getpensize()));
        ereasedSeekBar.setProgress(Integer.parseInt(appSession.getereasedSize()));
        highLighterSeekBar.setProgress(Integer.parseInt(appSession.getHighLighterSize()));

        penSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                appSession.setpensize(String.valueOf(penSeekBar.getProgress()));
                changeStrokeInterface1.changeStroke();
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
        pencilSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                appSession.setPencilSize(String.valueOf(pencilSeekBar.getProgress()));
                changeStrokeInterface1.changeStroke();
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
        highLighterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                appSession.setHighLighterSize(String.valueOf(highLighterSeekBar.getProgress()));
                changeStrokeInterface1.changeStroke();
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

        ereasedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                appSession.setEreasedSize(String.valueOf(ereasedSeekBar.getProgress()));
                changeStrokeInterface1.changeStroke();
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
                getFragmentManager().beginTransaction().remove(ChangePenSize.this).commit();
            }
        });
        return v;
    }

    public void changeStrokeInterface(changeStrokeInterface changeStrokeInterface1) {
        this.changeStrokeInterface1 = changeStrokeInterface1;
    }
}