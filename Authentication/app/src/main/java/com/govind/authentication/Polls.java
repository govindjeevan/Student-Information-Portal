package com.govind.authentication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static java.lang.Boolean.TRUE;

public class Polls extends AppCompatActivity {


    private WebView webview;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
    private FirebaseAuth auth;
    private String argumentPassingIn;
    private String argumentPassingIn2;
    /** Called when the activity is first created. */@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        argumentPassingIn= auth.getCurrentUser().getDisplayName();
        argumentPassingIn2=LoginActivity.currentuser;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG,"HELLOOO"+argumentPassingIn2);
        setContentView(R.layout.activity_forum);

        this.webview = (WebView) findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(Polls.this, "Poll Pool", "Loading...");

        webview.clearCache(TRUE);
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                webview.loadUrl("javascript:myJavaScriptFunc('" + argumentPassingIn + "')"); //if passing in an object. Mapping may need to take place

            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(Polls.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }



        });
        webview.loadUrl("https://authentication-140be.firebaseapp.com/poll.html");
    }
}
