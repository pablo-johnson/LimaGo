package pe.com.johnson.pablo.limago.view.ui.common;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */

public interface LimaGoFragmentListener {

    void replaceFragment(Fragment fragment, boolean addToBackStack);

    void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack);

    void showError(String message);

    void showProgressDialog();

    void showProgressDialog(String message);

    void dismissProgressDialog();

    void closeActivity();

    void setActivityResult(int resultCode);

    void setActivityResult(int resultCode, Intent resultIntent);

    void setTitle(String title);

    void setToolbar(Toolbar toolbar);

    void removeFragment(Fragment registerFragment);
}
