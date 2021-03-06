package com.demos.volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestActivity extends Activity {
    protected RequestQueue mRequestQueue;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRequestQueue = Volley.newRequestQueue(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.searching));
    }
}
