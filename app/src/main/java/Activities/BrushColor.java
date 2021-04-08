package Activities;

import android.graphics.Color;

public class BrushColor {

    final int MAX_RGB = 255;
    final int MIN_RGB = 0;
    private int red;
    private int green;
    private int blue;
    private int opacity;
    int redFirstDigit, greenFirstDigit, blueFirstDigit, opacityFirstDigit;

    public BrushColor(int red, int green, int blue, int opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
        this.redFirstDigit = 0;
        this.greenFirstDigit = 0;
        this.blueFirstDigit = 0;
        this.opacityFirstDigit = 0;
    }


    public void setRed(int redValue) {
        this.red = this.checkInBounds(redValue);
    }

    public void setGreen(int greenValue) {
        this.green = this.checkInBounds(greenValue);
    }

    public void setBlue(int blueValue) {
        this.blue = this.checkInBounds(blueValue);
    }

    public void setOpacity(int opacityValue) {
        this.opacity = this.checkInBounds(opacityValue);
    }


    public void setRed(String redStr) {
        try{
            this.setRed(Integer.parseInt(redStr));
            redFirstDigit = Integer.parseInt(redStr.substring(0,1));
        }
        catch(NumberFormatException e){
            this.setRed(MIN_RGB);
        }
    }
    public void setGreen(String greenStr) {
        try{
            this.setGreen(Integer.parseInt(greenStr));
            greenFirstDigit = Integer.parseInt(greenStr.substring(0,1));
        }
        catch(NumberFormatException e){
            this.setGreen(MIN_RGB);
        }
    }
    public void setBlue(String blueStr) {
        try{
            this.setBlue(Integer.parseInt(blueStr));
            blueFirstDigit = Integer.parseInt(blueStr.substring(0,1));
        }
        catch(NumberFormatException e){
            this.setBlue(MIN_RGB);
        }
    }
    public void setOpacity(String opacityStr) {
        try{
            this.setOpacity(Integer.parseInt(opacityStr));
            opacityFirstDigit = Integer.parseInt(opacityStr.substring(0,1));
        }
        catch(NumberFormatException e){
            this.setOpacity(MIN_RGB);
        }
    }


    public int getRedFirstDigit() {
        return redFirstDigit;
    }

    public int getGreenFirstDigit() {
        return greenFirstDigit;
    }

    public int getBlueFirstDigit() {
        return blueFirstDigit;
    }

    public int getOpacityFirstDigit() {
        return opacityFirstDigit;
    }

    public int getBrushColor(){
        return Color.argb(opacity, red, green, blue);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getOpacity() {
        return opacity;
    }


    public int checkInBounds(int rgbValue){
        int MIN_RGB = 0;
        if(rgbValue < MIN_RGB){
            return MIN_RGB;
        }
        if(rgbValue > MAX_RGB){
            return MAX_RGB;
        }
        return rgbValue;
    }


}
