package Dialogues;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import Activities.DrawingActivity;

public class ClearConfirmationDialogue extends AppCompatDialogFragment {

    private ClearConfirmationDialogueListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("CLEAR CANVAS");
        builder.setMessage("Are you sure you want to clear the canvas? There is no going back.");
        builder .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.confirmationToClear(false);
            }
        });

        builder.setPositiveButton("Clear Canvas", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.confirmationToClear(true);
            }
        });
        return  builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ClearConfirmationDialogueListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString() + "must implement RemoveHousemateDialogueListener"));
        }
    }

    public interface ClearConfirmationDialogueListener{
        void confirmationToClear(Boolean response);
    }
}