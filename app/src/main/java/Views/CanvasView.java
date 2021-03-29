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


public class CanvasView extends View {
    public WindowManager.LayoutParams layoutParams;


    private Bitmap previousBitmap;
    private Canvas previousCanvas;

    private Bitmap bitmap;
    private Canvas canvas;

    private Path path = new Path();
    private ArrayList<Path> pathList;
    private ArrayList<Path> currentPathList;
    private ArrayList<Path> undoList;
    private Paint selectedBrush;

    private final Paint pen = new Paint();
    private final Paint highlighter = new Paint();
    private final Paint pencil = new Paint();


    
    public CanvasView(Context context){
        super(context);

        this.pathList = new ArrayList<>();
        this.currentPathList = new ArrayList<>();
        this.undoList = new ArrayList<>();

        this.setDefaultPenSettings();
        this.setDefaultHighlighterSettings();
        this.setDefaultPencilSettings();

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
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);  // A change has happened during a press gesture (between ACTION_DOWN and ACTION_UP)
                currentPathList.add(path);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(pointX, pointY);
                canvas.drawPath(path, selectedBrush);
                pathList.add(path);
                path = new Path();
                currentPathList.clear();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (Path path : pathList) {
            canvas.drawPath(path, selectedBrush);
        }
        for (Path path : currentPathList) {
            canvas.drawPath(path, selectedBrush);
        }

    }

    public void undoLastStroke(){
        if (pathList.size() > 0) {
            undoList.add(pathList.remove(pathList.size() - 1));
            invalidate();
        }
    }

    public void redoLastStroke() {
        if (undoList.size() > 0) {
            pathList.add(undoList.remove(undoList.size()-1));
            invalidate();
        }
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



    private void setDefaultPenSettings(){
        pen.setAntiAlias(true);
        pen.setColor(Color.BLACK);
        pen.setStyle(Paint.Style.STROKE);
        pen.setStrokeJoin(Paint.Join.ROUND);
        pen.setStrokeWidth(5f);
        pen.setStrokeCap(Paint.Cap.ROUND);
    }

    private void setDefaultHighlighterSettings(){
        highlighter.setAntiAlias(true);
        highlighter.setARGB(23,23, 23, 10);
        highlighter.setStyle(Paint.Style.STROKE);
        highlighter.setStrokeJoin(Paint.Join.ROUND);
        highlighter.setStrokeWidth(10f);
        highlighter.setStrokeCap(Paint.Cap.SQUARE);
    }

    private void setDefaultPencilSettings(){
        pencil.setAntiAlias(true);
        pencil.setColor(Color.MAGENTA);
        pencil.setARGB(23,23, 23, 10);
        pencil.setStyle(Paint.Style.STROKE);
        pencil.setStrokeJoin(Paint.Join.ROUND);
        pencil.setStrokeWidth(5f);
        pencil.setStrokeCap(Paint.Cap.SQUARE);
    }


}
