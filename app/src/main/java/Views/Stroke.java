package Views;

import android.graphics.Paint;
import android.graphics.Path;

class Stroke {
        private final Path path;
        private final int color;
        private final float size;
        private final Paint.Cap strokeCap;

        public Stroke(Path path, Paint paint) {
            this.path = path;
            this.color = paint.getColor();
            this.size = paint.getStrokeWidth();
            this.strokeCap = paint.getStrokeCap();
        }

        public Path getPath() { return this.path; }

        public int getColor() {
            return color;
        }

        public float getSize() {
            return size;
        }

    public Paint.Cap getStrokeCap() {
        return strokeCap;
    }
}
