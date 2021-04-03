package Views;

import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

class Stroke {
        private final Path path;
        private final Paint paint;
        public Stroke(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
        public Path getPath() { return this.path; }
        public Paint getPaint() { return this.paint; }
}
