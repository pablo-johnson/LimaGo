package pe.com.johnson.pablo.limago.ui.common;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.utils.DialogUtils;


/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public abstract class LimaGoActivity extends AppCompatActivity implements LimaGoFragmentListener {

    private AlertDialog errorAlertDialog;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        bindViews();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutResID();

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        replaceFragment(R.id.fragmentContainer, fragment, addToBackStack);
    }

    @Override
    public void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String tag = fragment.getClass().getSimpleName();
        transaction.replace(containerId, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void showError(String message) {
        if (errorAlertDialog == null) {
            errorAlertDialog = DialogUtils.createErrorDialog(this, null);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (!errorAlertDialog.isShowing()) {
            errorAlertDialog.setMessage(message);
            errorAlertDialog.show();
        }
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(getString(R.string.loadingMessage));
    }

    @Override
    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = DialogUtils.createProgressDialog(this, null);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void setActivityResult(int resultCode) {
        setResult(resultCode);
    }

    @Override
    public void setActivityResult(int resultCode, Intent resultIntent) {
        setResult(resultCode, resultIntent);
    }

    @Override
    public void setTitle(String title) {
        if (!activityHandleTitle() && getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

    }

    private boolean activityHandleTitle() {
        return false;
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
