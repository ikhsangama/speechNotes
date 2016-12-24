package ibmmobileappbuilder.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import ibmmobileappbuilder.core.R;

/**
 * Dialog to confirm or discard the delete action
 */
public class DeleteItemDialog extends DialogFragment {
    private DeleteItemListener listener;

    public DeleteItemDialog() {}

    public DeleteItemDialog(DeleteItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDeleteItemDialog();
    }

    public AlertDialog createDeleteItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity(),
                Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                        R.style.SelectionDialog :
                        R.style.SelectionDialog_PreL
        );

        builder.setTitle(getString(R.string.dialog_delete_title))
                .setMessage(getString(R.string.dialog_delete_message))
                .setPositiveButton(getString(R.string.button_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.deleteItem(true);
                            }
                        })
                .setNegativeButton(getString(R.string.button_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.deleteItem(false);
                            }
                        });

        return builder.create();
    }

    public interface DeleteItemListener{
        void deleteItem(boolean isDeleted);
    }
}
