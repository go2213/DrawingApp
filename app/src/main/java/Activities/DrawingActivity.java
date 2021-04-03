package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.packag.R;

import java.util.ArrayList;

import Dialogues.ClearConfirmationDialogue;
import Views.CanvasView;


 public class DrawingActivity extends AppCompatActivity implements CanvasView.CanvasViewListener, ClearConfirmationDialogue.ClearConfirmationDialogueListener{

    ImageButton undoButton, redoButton;
    ImageView penImgView, highlighterImgView, pencilImgView, eraserImgView, penColorIV, highlighterColorIV, pencilColorIV, eraserColorIV;
    Button clearButton;
    RelativeLayout canvasContainer;
    CanvasView canvasView;
    ArrayList<ImageView> brushImgViews = new ArrayList<>();
    ArrayList<ImageView> colorImgViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        undoButton = findViewById(R.id.undoButton);
        redoButton = findViewById(R.id.redoButton);
        clearButton = findViewById(R.id.clearButton);

        redoButton.setEnabled(false);
        undoButton.setEnabled(false);

        penImgView = findViewById(R.id.penImgView);
        highlighterImgView = findViewById(R.id.highlighterImgView);
        pencilImgView = findViewById(R.id.pencilImgView);
        eraserImgView = findViewById(R.id.eraserImgView);

        brushImgViews.add(penImgView);
        brushImgViews.add(highlighterImgView);
        brushImgViews.add(pencilImgView);
        brushImgViews.add(eraserImgView);


        penColorIV = findViewById(R.id.penColorIV);
        highlighterColorIV = findViewById(R.id.highlighterColorIV);
        pencilColorIV = findViewById(R.id.pencilColorIV);
        eraserColorIV = findViewById(R.id.eraserColorIV);

        colorImgViews.add(penColorIV);
        colorImgViews.add(highlighterColorIV);
        colorImgViews.add(pencilColorIV);
        colorImgViews.add(eraserColorIV);



        canvasView = new CanvasView(this, this);
        canvasContainer = findViewById(R.id.canvasContainer);
        canvasContainer.addView(canvasView);

        penImgView.performClick();


    }

    public void undoButtonClicked(View view) {
         canvasView.undoLastStroke();
         redoButton.setEnabled(true);
    }

    public void redoButtonClicked(View view) { // disable redo when all strokes are dispayed
        canvasView.redoLastStroke();
    }

    public void clearButtonClicked(View view) {
        ClearConfirmationDialogue clearConfirmationDialogue = new ClearConfirmationDialogue();
        clearConfirmationDialogue.show(getSupportFragmentManager(), "confirmClear");
    }

     @Override
     public void confirmationToClear(Boolean response) {
            if(response){
                canvasView.clearAllStrokes();
                redoButton.setEnabled(false);
                undoButton.setEnabled(false);
            }
     }

    @Override
    public void enableUndoButton(boolean isEnabled) {
        undoButton.setEnabled(isEnabled);
    }

    @Override
    public void enableRedoButton(boolean isEnabled) {
            redoButton.setEnabled(isEnabled);
    }


    public void penImgViewClicked(View view) {
        canvasView.setSelectedBrushToPen();
        penColorIV.setBackgroundColor(canvasView.getSelectedBrushColor());
        Log.i("pen color: ", String.valueOf(canvasView.getSelectedBrushColor()));
        Drawable drawable = penImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.black));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        deselectAllOtherBrushes(0);
    }

    public void highlighterImgViewClicked(View view) {
        canvasView.setSelectedBrushToHighlighter();
        highlighterColorIV.setBackgroundColor(canvasView.getSelectedBrushColor());
        Log.i("HL color: ", String.valueOf(canvasView.getSelectedBrushColor()));
        Drawable drawable = highlighterImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.selected_brush));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        deselectAllOtherBrushes(1);
    }

    public void pencilImgViewClicked(View view) {
        canvasView.setSelectedBrushToPencil();
        pencilColorIV.setBackgroundColor(canvasView.getSelectedBrushColor());
        Log.i("pencil color: ", String.valueOf(canvasView.getSelectedBrushColor()));
        Drawable drawable = pencilImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.selected_brush));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        deselectAllOtherBrushes(2);

    }

    public void eraserImgViewClicked(View view) {
        eraserColorIV.setBackgroundColor(Color.BLACK);
        Drawable drawable = eraserImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.selected_brush));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        deselectAllOtherBrushes(3);
    }

    public void deselectAllOtherBrushes(int selectedBrushIndex){
        for(int i =0 ; i< brushImgViews.size(); i++){
            if(i == selectedBrushIndex){
                continue;
            }
            colorImgViews.get(i).setBackgroundColor(Color.WHITE);
            Drawable drawable = brushImgViews.get(i).getDrawable();
            if(drawable!=null){
                DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.deselected_brush));
            }
            else{
                Log.i("Drawable is null!", "null");
            }
        }
    }


 }

