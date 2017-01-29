package pe.com.johnson.pablo.limago.view.ui.common;

import android.content.Context;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */

public interface LimaGoView {

    void showProgressDialog();
    void showProgressDialog(String message);
    void dismissProgressDialog();
    void setTitle(String title);
    void showError(String message);
    Context getContext();
}
