package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.example.packag.R;
import Views.CanvasView;


public class DrawingActivity extends AppCompatActivity {

    RelativeLayout canvasContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        CanvasView canvasView = new CanvasView(this);
        canvasView.setBrushColor(Color.MAGENTA);
        canvasView.setBrushStrokeWidth(20f);

        canvasContainer = findViewById(R.id.canvasContainer);
        canvasContainer.addView(canvasView);
    }

}