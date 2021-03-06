package com.example.fitbros.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitbros.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    Button emailButton;
    Button smsButton;
    Button phoneButton;

    public static String[] emailList = {"w0721766@myscc.ca"};
    public static final int PERMISSION_SEND_SMS = 1;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        emailButton = view.findViewById(R.id.emailButton);
        smsButton = view.findViewById(R.id.smsButton);
        phoneButton = view.findViewById(R.id.phoneButton);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, emailList);
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.tech_support_email_subject));
                intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.tech_support_email_body_text));
                startActivity(intent);

                // ensure that there is email functionality
                if(intent.resolveActivity(getActivity().getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_web, Toast.LENGTH_SHORT).show();
                }

            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                    if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS)) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("SEND SMS").setMessage(getResources().getString(R.string.permission_sms)).create();
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS);
                            }
                        });
                        alertDialog.show();
                    }
                    else {
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS);
                    }
                }
                else {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:5191234567"));
                    intent.putExtra("sms_body", getResources().getString(R.string.tech_support_sms_message));
                    startActivity(intent);

                    // ensure that there is SMS functionality
                    if(intent.resolveActivity(getActivity().getPackageManager()) !=null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), R.string.error_web, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:5191234567"));
                startActivity(intent);

                // ensure that there is phone functionality
                if(intent.resolveActivity(getActivity().getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_web, Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}
