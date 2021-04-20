package Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class AppSession {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    public static final String PREF_APP = "DrawingApp_pref";

    public static final String PencilSize="pencli_size";
    public static final String HighLighterSize="high_lighter_size";
    public static final String pensize="pen_size";
    public static final String ereasedSize="ereased_size";

    // Constructor
    @SuppressLint("CommitPrefEdits")
    public AppSession(Context context, String pref_fname) {
        this._context = context;
        pref = _context.getSharedPreferences(pref_fname, Context.MODE_PRIVATE);
        editor = pref.edit();

    }

    public void setPencilSize(String size){
        editor.putString(PencilSize,size);
        editor.apply();
    }
    public void setHighLighterSize(String size){
        editor.putString(HighLighterSize,size);
        editor.apply();
    }
    public void setpensize(String size){
        editor.putString(pensize,size);
        editor.apply();
    }
    public void setEreasedSize(String size){
        editor.putString(ereasedSize,size);
        editor.apply();
    }
    public String getPencilSize(){
        return pref.getString(PencilSize,"5");
    }

    public String getHighLighterSize(){
        return pref.getString(HighLighterSize,"30");
    }

    public String getpensize(){
        return pref.getString(pensize,"20");
    }

    public String getereasedSize(){
        return pref.getString(ereasedSize,"20");
    }

}
