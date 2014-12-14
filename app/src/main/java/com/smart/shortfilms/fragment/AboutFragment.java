package com.smart.shortfilms.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.smart.shortfilms.R;

import java.util.Calendar;

/**
 * Created by Purushotham on 08-11-2014.
 */
public class AboutFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        PackageInfo pinfo = null;
        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = pinfo.versionName;

        return new AlertDialog.Builder(getActivity())
                .setTitle(getResources().getString(R.string.about))
                .setMessage("All videos are copy rights of respective owners." +
                        "\nApp Version : "+versionName+"\nFeedback : TechSabha.Com@gmail.com").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
    }
}