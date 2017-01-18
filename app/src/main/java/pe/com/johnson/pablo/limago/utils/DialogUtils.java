package pe.com.johnson.pablo.limago.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import pe.com.johnson.pablo.limago.R;


/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */

public final class DialogUtils {

    private DialogUtils() {
    }

    public static AlertDialog createErrorDialog(Activity activity, String message) {
        return createSimpleDialog(activity, activity.getString(R.string.dialog_title_error), message);
    }

    public static AlertDialog createSimpleDialog(Activity activity, String title, String message) {
        return new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .create();
    }

    public static ProgressDialog createProgressDialog(Activity activity, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
