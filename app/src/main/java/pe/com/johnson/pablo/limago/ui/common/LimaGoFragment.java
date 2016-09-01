package pe.com.johnson.pablo.limago.ui.common;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class LimaGoFragment extends Fragment implements LimaGoView {

    protected LimaGoFragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentListener = (LimaGoFragmentListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentListener = (LimaGoFragmentListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Override
    public void showProgressDialog() {
        if (fragmentListener != null) {
            fragmentListener.showProgressDialog();
        }
    }

    @Override
    public void showProgressDialog(String message) {
        if (fragmentListener != null) {
            fragmentListener.showProgressDialog(message);
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (fragmentListener != null) {
            fragmentListener.dismissProgressDialog();
        }
    }

    @Override
    public void setTitle(String title) {
        if (fragmentListener != null) {
            fragmentListener.setTitle(title);
        }
    }

    @Override
    public void showError(String message) {
        if (fragmentListener != null) {
            fragmentListener.showError(message);
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
