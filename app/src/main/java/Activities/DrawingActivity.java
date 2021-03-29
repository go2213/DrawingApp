package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.packag.R;
import Views.CanvasView;


public class DrawingActivity extends AppCompatActivity {

    Button undoButton, redoButton;
    RelativeLayout canvasContainer;
    CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        undoButton = findViewById(R.id.undoButton);
        redoButton = findViewById(R.id.redoButton);




        canvasView = new CanvasView(this);
        canvasView.setBrushColor(Color.MAGENTA);
        canvasView.setBrushStrokeWidth(20f);

        canvasContainer = findViewById(R.id.canvasContainer);
        canvasContainer.addView(canvasView);
    }

    public void undoButtonClicked(View view) {
         canvasView.undoLastStroke();
    }

    public void redoButtonClicked(View view) {
        canvasView.redoLastStroke();
    }
}