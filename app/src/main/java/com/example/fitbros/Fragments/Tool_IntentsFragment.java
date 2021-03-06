package com.example.fitbros.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitbros.R;

public class Tool_IntentsFragment extends Fragment {

    Button alarmButton;
    Button twitterButton;
    Button mapButton;
    Button webButton;

    public Tool_IntentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tool_intents, container, false);

        alarmButton = view.findViewById(R.id.buttonGymAlarm);
        twitterButton = view.findViewById(R.id.buttonGymTwitter);
        mapButton = view.findViewById(R.id.buttonGymFinder);
        webButton = view.findViewById(R.id.buttonGymFitTips);



        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // CODE CHECKPOINT
                // Toast.makeText(getContext(), R.string.error_alarm, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, getResources().getString(R.string.tool_alarm_message))
                        .putExtra(AlarmClock.EXTRA_HOUR, 17)
                        .putExtra(AlarmClock.EXTRA_MINUTES,30);

                // ensure that there is alarm functionality
                if(intent.resolveActivity(getActivity().getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_alarm, Toast.LENGTH_SHORT).show();
                }

            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tweet = String.format("https://twitter.com/intent/tweet?text=%s", getResources().getString(R.string.tool_twitter_message));

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet));

                // ensure that there is twitter functionality
                if(intent.resolveActivity(getActivity().getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_twitter, Toast.LENGTH_SHORT).show();
                }

            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri geoLocation = Uri.parse("geo:0.0?q=fitness%20or%20gym");

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(geoLocation);

                // ensure that there is map functionality
                if(intent.resolveActivity(getActivity().getPackageManager()) !=null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.error_maps, Toast.LENGTH_SHORT).show();
                }
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri webpage = Uri.parse("https://www.instagram.com/bespoketreatments/");

                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

                // ensure that there is web functionality
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
