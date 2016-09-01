package pe.com.johnson.pablo.limago.ui.common;

/**
 * @author Pablo Johnsonn (pablo.88j@gmail.com)
 */
public abstract class LimaGoPresenter<T extends LimaGoView> {

    protected T view;

    protected LimaGoPresenter(T view) {
        this.view = view;
    }

    protected String getString(int resId) {
        return view.getContext().getString(resId);
    }

    protected void showError(int resId) {
        showError(getString(resId));
    }

    protected void showError(String message) {
        view.showError(message);
    }
}
