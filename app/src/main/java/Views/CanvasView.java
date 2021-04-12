package Views;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.HashMap;


public class CanvasView extends View {
    public WindowManager.LayoutParams layoutParams;


    private Bitmap previousBitmap;
    private Canvas previousCanvas;

    private Bitmap bitmap;
    private Canvas canvas;

    private Path path = new Path();
    private final ArrayList<Stroke> pathList;
    private final ArrayList<Stroke> currentPathList;
    private final ArrayList<Stroke> undoList;

    private Paint selectedBrush;

    private final Paint pen = new Paint();
    private final Paint highlighter = new Paint();
    private final Paint pencil = new Paint();
    private final Paint eraser = new Paint();

    private final CanvasViewListener canvasViewListener;
    
    public CanvasView(Context context, CanvasViewListener canvasViewListener){
        super(context);

        this.canvasViewListener = canvasViewListener;
        this.pathList = new ArrayList<>();

        this.currentPathList = new ArrayList<>();
        this.undoList = new ArrayList<>();

        this.setDefaultPenSettings();
        this.setDefaultHighlighterSettings();
        this.setDefaultPencilSettings();
        this.setDefaultEraserSettings();

        this.setSelectedBrushToPen();
        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        this.bitmap = Bitmap.createBitmap(xNew, yNew, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(bitmap);


        this.previousBitmap = Bitmap.createBitmap(xNew, yNew, Bitmap.Config.ARGB_8888);
        this.previousCanvas = new Canvas(bitmap);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) { // gets position of cursor, moves path accordingly
        float pointX = event.getX();
        float pointY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: // when the user first touches the screen
                path = new Path();
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);  // A change has happened during a press gesture (between ACTION_DOWN and ACTION_UP)
                currentPathList.clear();
                currentPathList.add(new Stroke(path, selectedBrush));
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(pointX, pointY);
                pathList.add(new Stroke(path, selectedBrush));
                currentPathList.clear();
                canvasViewListener.enableUndoButton(true);
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Stroke stroke : pathList) {
            selectedBrush.setColor(stroke.getColor());
            selectedBrush.setStrokeWidth(stroke.getSize());
            canvas.drawPath(stroke.getPath(), selectedBrush);
        }
        for (Stroke stroke : currentPathList) { // last path drawn that is currently being drawn
            selectedBrush.setColor(stroke.getColor());
            selectedBrush.setStrokeWidth(stroke.getSize());
            canvas.drawPath(stroke.getPath(), selectedBrush);
        }

    }

    public void undoLastStroke(){

        if (pathList.size() > 0) {
            undoList.add(pathList.remove(pathList.size() - 1));
            invalidate();
        }
        else{
            canvasViewListener.enableUndoButton(false);
        }
    }

    public void redoLastStroke() {
        if (undoList.size() > 0) {
            pathList.add(undoList.remove(undoList.size()-1));
            invalidate();
        }
        else{
            canvasViewListener.enableRedoButton(false);
        }
    }



    public void clearAllStrokes(){
        undoList.clear();
        pathList.clear();
        invalidate();
    }



    public void setBrushColor(int color){
        this.selectedBrush.setColor(color);

    }

    public void setBrushStrokeWidth(float width) {
        this.selectedBrush.setStrokeWidth(width);
    }

    public void setSelectedBrushToPen(){
        this.selectedBrush = this.pen;
    }

    public void setSelectedBrushToHighlighter(){
        this.selectedBrush = this.highlighter;
    }

    public void setSelectedBrushToPencil(){
        this.selectedBrush = this.pencil;
    }

    public void setSelectedBrushToEraser() {
        this.selectedBrush = this.eraser;
    }

    public int getSelectedBrushColor(){
        int color = selectedBrush.getColor();
        return Color.argb(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
    }



    private void setDefaultPenSettings(){
        pen.setAntiAlias(true);
        pen.setColor(Color.MAGENTA);
        pen.setStyle(Paint.Style.STROKE);
        pen.setStrokeJoin(Paint.Join.ROUND);
        pen.setStrokeWidth(20f);
        pen.setStrokeCap(Paint.Cap.ROUND);
    }

    private void setDefaultHighlighterSettings(){
        highlighter.setAntiAlias(true);
        highlighter.setARGB(200,238, 255, 0);
        highlighter.setStyle(Paint.Style.STROKE);
//        highlighter.setColor(Color.YELLOW);
//        highlighter.setAlpha(200);
        highlighter.setStrokeJoin(Paint.Join.ROUND);
        highlighter.setStrokeWidth(30f);
        highlighter.setStrokeCap(Paint.Cap.SQUARE);
    }

    private void setDefaultPencilSettings(){
        pencil.setAntiAlias(true);
        pencil.setARGB(23,23, 23, 10);
        pencil.setStyle(Paint.Style.STROKE);
        pencil.setStrokeJoin(Paint.Join.ROUND);
        pencil.setStrokeWidth(5f);
        pencil.setStrokeCap(Paint.Cap.SQUARE);
    }

    private void setDefaultEraserSettings(){
        eraser.setAntiAlias(true);
        eraser.setColor(Color.WHITE);
        eraser.setStyle(Paint.Style.STROKE);
        eraser.setStrokeJoin(Paint.Join.ROUND);
        eraser.setStrokeWidth(20f);
    }



    public interface CanvasViewListener {
        void enableUndoButton(boolean isEnabled);

        void enableRedoButton(boolean isEnabled);
    }


}

