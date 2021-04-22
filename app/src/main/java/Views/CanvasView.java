package Views;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import Utils.AppSession;


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
    private final Paint previousBrush = new Paint();

    private final Paint pen = new Paint();
    private final Paint highlighter = new Paint();
    private final Paint pencil = new Paint();
    private final Paint eraser = new Paint();
    private final CanvasViewListener canvasViewListener;
    private final AppSession appSession;
    private  boolean isRedoEnabled;


    public CanvasView(Context context, CanvasViewListener canvasViewListener){
        super(context);
        appSession = new AppSession(context, AppSession.PREF_APP);

        this.canvasViewListener = canvasViewListener;
        this.pathList = new ArrayList<>();

        this.currentPathList = new ArrayList<>();
        this.undoList = new ArrayList<>();

        this.setDefaultPenSettings(pen);
        this.setDefaultHighlighterSettings(highlighter);
        this.setDefaultPencilSettings(pencil);
        this.setDefaultEraserSettings(eraser);

        this.setSelectedBrushToPen();
        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        this.isRedoEnabled = false;
        this.setDefaultPenSettings(previousBrush);
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

                path.lineTo(pointX, pointY);
                path.lineTo(pointX, pointY);  // A change has happened during a press gesture (between ACTION_DOWN and ACTION_UP)
                currentPathList.clear();
                currentPathList.add(new Stroke(path, selectedBrush));
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(pointX, pointY);
                pathList.add(new Stroke(path, selectedBrush));
                currentPathList.clear();
                canvasViewListener.enableUndoButton(true);
                if(isRedoEnabled){
                    undoList.clear();
                }
                isRedoEnabled = false;
                canvasViewListener.enableRedoButton(false);
                break;
            default:
                return false;
        }

        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Stroke stroke : pathList) {
            previousBrush.setColor(stroke.getColor());
            previousBrush.setStrokeWidth(stroke.getSize());
            previousBrush.setStrokeCap(stroke.getStrokeCap());
            canvas.drawPath(stroke.getPath(), previousBrush);
        }
        for (Stroke stroke : currentPathList) { // last path drawn that is currently being drawn
            previousBrush.setColor(stroke.getColor());
            previousBrush.setStrokeWidth(stroke.getSize());
            previousBrush.setStrokeCap(stroke.getStrokeCap());
            canvas.drawPath(stroke.getPath(), previousBrush);
        }

    }

    public void undoLastStroke(){
        if (pathList.size() > 0) {
            undoList.add(pathList.remove(pathList.size() - 1));
            isRedoEnabled = true;
            canvasViewListener.enableRedoButton(true);
            invalidate();
        }
        if(pathList.isEmpty()){
            canvasViewListener.enableUndoButton(false);
        }
    }

    public void redoLastStroke() {
        if (undoList.size() > 0) {
            pathList.add(undoList.remove(undoList.size()-1));
            canvasViewListener.enableUndoButton(true);
            invalidate();
        }
        if(undoList.isEmpty()){
            isRedoEnabled = false;
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
        this.pen.setStrokeWidth(Float.parseFloat(appSession.getpensize()));
        this.selectedBrush = this.pen;
    }

    public void setSelectedBrushToHighlighter(){
        this.highlighter.setStrokeWidth(Float.parseFloat(appSession.getHighLighterSize()));
        this.selectedBrush = this.highlighter;
    }

    public void setSelectedBrushToPencil(){
        this.pencil.setStrokeWidth(Float.parseFloat(appSession.getPencilSize()));
        this.selectedBrush = this.pencil;
    }

    
    public void setSelectedBrushToEraser() {
        this.eraser.setStrokeWidth(Float.parseFloat(appSession.getereasedSize()));
        this.selectedBrush = this.eraser;
    }

    public int getSelectedBrushColor(){
        int color = selectedBrush.getColor();
        return Color.argb(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
    }


    private void setDefaultPenSettings(Paint paint){
        paint.setAntiAlias(true);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(20f);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void setDefaultHighlighterSettings(Paint paint){
        paint.setAntiAlias(true);
        paint.setARGB(150,238, 255, 0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(30f);
        paint.setStrokeCap(Paint.Cap.SQUARE);
    }

    private void setDefaultPencilSettings(Paint paint){
        paint.setAntiAlias(true);
        paint.setARGB(255,112, 112, 112);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(3f);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void setDefaultEraserSettings(Paint paint){
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(20f);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }



    public interface CanvasViewListener {
        void enableUndoButton(boolean isEnabled);

        void enableRedoButton(boolean isEnabled);
    }

    public void selectStroke(){
        this.pen.setStrokeWidth(Float.parseFloat(appSession.getpensize()));
        this.highlighter.setStrokeWidth(Float.parseFloat(appSession.getHighLighterSize()));
        this.pencil.setStrokeWidth(Float.parseFloat(appSession.getPencilSize()));
        this.eraser.setStrokeWidth(Float.parseFloat(appSession.getereasedSize()));
    }

}

