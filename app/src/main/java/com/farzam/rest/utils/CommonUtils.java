/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.farzam.rest.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farzam.rest.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class CommonUtils implements AppConstants {

    private static Dialog dialog;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
//        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
            return phoneNumber.matches("^[+]?[0-9]{10,14}$");
            // ^[+]?[0-9]{10,13}$
        }
    }

    public static boolean isTextContainOnlyNumber(String text) {
        return text.matches("^[+]?[0-9]+");
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static void showSingleRuleButtonAlert(Context context, String title, String msg, String buttonTitle, final IL listner) {
        try {
            TextView tvAlertTitle, tvAlertMessage;
            Button btnAlertOK;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_rule_alert, null);
            tvAlertTitle = (TextView) dialogAlert.findViewById(R.id.tv_alert_title);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnAlertOK = (Button) dialogAlert.findViewById(R.id.btnOk);
            dialogAlert.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            if (TextUtils.isEmpty(title))
                tvAlertTitle.setVisibility(View.GONE);
            else {
                tvAlertTitle.setVisibility(View.VISIBLE);
                tvAlertTitle.setText(title);
            }

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);

//            btnAlertOK.setText(TextUtils.isEmpty(buttonTitle) ? StringLanguage..btn_ok) : buttonTitle);
            btnAlertOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showSingleButtonAlert(Context context, String title, String msg, String buttonTitle, final IL listner) {
        try {
            TextView tvAlertTitle, tvAlertMessage;
            RelativeLayout btnAlertOK;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_alert, null);
            tvAlertTitle = (TextView) dialogAlert.findViewById(R.id.tv_alert_title);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnAlertOK = (RelativeLayout) dialogAlert.findViewById(R.id.btn_alert_ok);
            dialogAlert.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            if (TextUtils.isEmpty(title))
                tvAlertTitle.setVisibility(View.GONE);
            else {
                tvAlertTitle.setVisibility(View.VISIBLE);
                tvAlertTitle.setText(title);
            }

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);

//            btnAlertOK.setText(TextUtils.isEmpty(buttonTitle) ? StringLanguage..btn_ok) : buttonTitle);
            btnAlertOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMessageButtonAlert(Context context, String title, String msg, int image, final IL listner) {
        try {
            TextView tvAlertTitle, tvAlertMessage;
            RelativeLayout btnAlertOK;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
            tvAlertTitle = (TextView) dialogAlert.findViewById(R.id.tv_alert_title);
            ImageView imgg = (ImageView) dialogAlert.findViewById(R.id.img);
            imgg.setImageResource(image);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnAlertOK = (RelativeLayout) dialogAlert.findViewById(R.id.btn_alert_ok);
            dialogAlert.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            if (TextUtils.isEmpty(title))
                tvAlertTitle.setVisibility(View.GONE);
            else {
                tvAlertTitle.setVisibility(View.VISIBLE);
                tvAlertTitle.setText(title);
            }

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);

//            btnAlertOK.setText(TextUtils.isEmpty(buttonTitle) ? StringLanguage..btn_ok) : buttonTitle);
            btnAlertOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showTwoButtonAlert(Context context, String msg, String buttonOk, String btnCancel, final IL listner) {
        try {
            TextView tvAlertMessage;
            ImageView btnAlertCancel;
            Button btnOk, btCancel;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_two_alert, null);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnOk = (Button) dialogAlert.findViewById(R.id.btnOk);
            btnOk.setText(buttonOk);
            btCancel = (Button) dialogAlert.findViewById(R.id.btnCancel);
            btCancel.setText(btnCancel);
            btnAlertCancel = (ImageView) dialogAlert.findViewById(R.id.btn_alert_cancel);

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);
            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onCancel();
                }
            });
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            btnAlertCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onCancel();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface IL {
        void onSuccess();

        void onCancel();
    }


    public static void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public static boolean isDialogOpen() {
        if (dialog != null)
            return dialog.isShowing();
        else
            return false;
    }

    public static void showDialogWithView(Context activity, final View view, final IL listner) {
        try {
            dismissDialog();
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            WindowManager.LayoutParams windowLayout = dialog.getWindow().getAttributes();

            windowLayout.gravity = Gravity.CENTER;
            dialog.show();
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        if (listner != null)
                            listner.onCancel();
                    }
                    return false;
                }
            });
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showDialogWithView(Context activity, final View view, final IL listner, boolean touchOutSide) {
        try {
            dismissDialog();
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(touchOutSide);
            WindowManager.LayoutParams windowLayout = dialog.getWindow().getAttributes();
            windowLayout.gravity = Gravity.CENTER;
            dialog.show();
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        if (listner != null)
                            listner.onCancel();
                    }
                    return false;
                }
            });
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String[] HafteGhabl(String date) {

        String[] roozHayeHafteGhabl = new String[7];
        String dateShanbe = AddDate(date, -7);
        for (int i = 1; i <= 7; i++) {
            roozHayeHafteGhabl[i-1] = AddDate(dateShanbe, i - 1);
        }
        return roozHayeHafteGhabl;
    }

    public static String[] hafteJari(){

        Calendar c = Calendar.getInstance();
        //c.setTime(yourDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 7)
            dayOfWeek = 0;
        else
            dayOfWeek = -1 * dayOfWeek;
        String[] roozHayeHafteJari = new String[7];
        String date = AddDate(DateUtil.getCurrentDate(), dayOfWeek);
        for (int i = 1; i <= 7; i++) {
            roozHayeHafteJari[i-1] = AddDate(date, i - 1);
        }
        return roozHayeHafteJari;

    }

    public static String[] HafteBadi(String date ) {
        String[] roozHayeHafteBadi = new String[7];
        String dateShanbe = AddDate(date, 7);
        for (int i = 1; i <= 7; i++) {
            roozHayeHafteBadi[i-1] = AddDate(dateShanbe, i - 1);
        }

        return roozHayeHafteBadi;
    }
    public static String AddDate(String date_string, Integer n) {

        Integer y, m, d;
        String yst, mst, dst;
        Byte i;
        byte maxdays[] = new byte[13];

        maxdays[1] = 31;
        maxdays[2] = 31;
        maxdays[3] = 31;
        maxdays[4] = 31;
        maxdays[5] = 31;
        maxdays[6] = 31;
        maxdays[7] = 30;
        maxdays[8] = 30;
        maxdays[9] = 30;
        maxdays[10] = 30;
        maxdays[11] = 30;
        maxdays[12] = 29;


        y = Integer.parseInt(date_string.substring(0, 4));
        m = Integer.parseInt(date_string.substring(5, 7));
        d = Integer.parseInt(date_string.substring(8, 10));
        if (n >= 0) {
            d = d + n;
            if (y % 4 == 3)
                maxdays[12] = 30;
            while (d > maxdays[m]) {
                d = d - maxdays[m];

                m++;
                if (m > 12) {
                    m = 1;

                    y++;
                    if (y % 4 == 3)
                        maxdays[12] = 30;
                    else
                        maxdays[12] = 29;
                }
            }
        } else {
            d = d + n;
            while (d < 1) {
                m--;
                if (m < 1) {
                    m = 12;
                    y--;
                    if (y % 4 == 3)
                        maxdays[12] = 30;
                    else
                        maxdays[12] = 29;

                }
                d = d + maxdays[m];

            }
        }

        yst = String.format("%1$04d", y);
        mst = String.format("%1$02d", m);
        dst = String.format("%1$02d", d);
        date_string = yst + "/" + mst + "/" + dst;
        return date_string;
    }


}
