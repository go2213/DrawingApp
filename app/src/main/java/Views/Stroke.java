package Views;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

class Stroke {
        private final Path path;
        private final int color;
        private final float size;

        public Stroke(Path path, Paint paint) {
            this.path = path;
            this.color = paint.getColor();
            this.size = paint.getStrokeWidth();
        }

        public Path getPath() { return this.path; }

        public int getColor() {
            return color;
        }

        public float getSize() {
            return size;
        }
}
