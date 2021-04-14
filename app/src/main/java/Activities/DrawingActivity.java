
package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.packag.R;

import java.util.ArrayList;

import Dialogues.ClearConfirmationDialogue;
import Utils.AppSession;
import Views.CanvasView;


 public class DrawingActivity extends AppCompatActivity implements CanvasView.CanvasViewListener, ClearConfirmationDialogue.ClearConfirmationDialogueListener, ColorPickerFragment.ColorPickerFragmentListener {

    RelativeLayout canvasContainer;
    ImageButton undoButton, redoButton;
    ImageView penImgView, highlighterImgView, pencilImgView, eraserImgView, penColorIV, highlighterColorIV, pencilColorIV, eraserColorIV;
    Button clearButton, colorButton;
    CanvasView canvasView;
    ArrayList<ImageView> brushImgViews = new ArrayList<>();
    ArrayList<ImageView> colorImgViews = new ArrayList<>();

     int selectedBrushIndex;
     final int PEN_INDEX = 0;
     final int HIGHLIGHTER_INDEX = 1;
     final int PENCIL_INDEX = 2;
     final int ERASER_INDEX = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        undoButton = findViewById(R.id.undoButton);
        redoButton = findViewById(R.id.redoButton);
        clearButton = findViewById(R.id.clearButton);
        colorButton = findViewById(R.id.colorButton);

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
        colorButton.setEnabled(true);
        canvasView.setSelectedBrushToPen();
        penColorIV.setBackgroundColor(canvasView.getSelectedBrushColor());

        BrushColor test5 = new BrushColor(canvasView.getSelectedBrushColor());
        Log.i("pen color ", test5.toString());

        Drawable drawable = penImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.black));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        selectedBrushIndex = PEN_INDEX;
        deselectAllOtherBrushes();
    }

    public void highlighterImgViewClicked(View view) {
        colorButton.setEnabled(true);
        canvasView.setSelectedBrushToHighlighter();
        highlighterColorIV.setBackgroundColor(canvasView.getSelectedBrushColor());

        BrushColor test5 = new BrushColor(canvasView.getSelectedBrushColor());
        Log.i("HL color: ", test5.toString());

        Drawable drawable = highlighterImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.selected_brush));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        selectedBrushIndex = HIGHLIGHTER_INDEX;
        deselectAllOtherBrushes();
    }

    public void pencilImgViewClicked(View view) {
        colorButton.setEnabled(true);
        canvasView.setSelectedBrushToPencil();
        pencilColorIV.setBackgroundColor(canvasView.getSelectedBrushColor());

        BrushColor test5 = new BrushColor(canvasView.getSelectedBrushColor());
        Log.i("pencil color: ", test5.toString());

        Drawable drawable = pencilImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.selected_brush));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        selectedBrushIndex = PENCIL_INDEX;
        deselectAllOtherBrushes();
    }

    public void eraserImgViewClicked(View view) {
        colorButton.setEnabled(false);
        canvasView.setSelectedBrushToEraser();
        eraserColorIV.setBackgroundColor(Color.BLACK);
        Drawable drawable = eraserImgView.getDrawable();
        if(drawable!=null){
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.selected_brush));
        }
        else{
            Log.i("Drawable is null!", "null");
        }
        selectedBrushIndex = ERASER_INDEX;
        deselectAllOtherBrushes();
    }

    public void deselectAllOtherBrushes(){
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


     public void colorButtonClicked(View view) {
         ColorPickerFragment colorPickerFragment = new ColorPickerFragment(canvasView.getSelectedBrushColor());
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.fragmentContainer, colorPickerFragment)
                 .commit();
     }

     public void sizeButtonClicked(View view) {
         ChangePenSize penSizeFragment =  ChangePenSize.newInstance("","");
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.fragmentContainer, penSizeFragment)
                 .commit();
     }


     @Override
     public void onColorSelectedFromPicker(int color) {
         canvasView.setBrushColor(color);
         colorImgViews.get(selectedBrushIndex).setBackgroundColor(color);
     }

     
 }




