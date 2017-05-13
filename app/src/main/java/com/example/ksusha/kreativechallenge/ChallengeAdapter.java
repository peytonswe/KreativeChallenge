package com.example.ksusha.kreativechallenge;


import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChallengeAdapter extends ArrayAdapter<Challenge> {

    public ChallengeAdapter(Activity context, ArrayList<Challenge> challenges) {
        super(context, 0, challenges);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Challenge currentChallenge = getItem(position);

        //Challenge rating TextView
        TextView ratingTextView = (TextView) listItemView.findViewById(R.id.challenge_rating);
        String formattedRating = formatRating(currentChallenge.getRating());
        ratingTextView.setText(formattedRating);

        GradientDrawable ratingCircle = (GradientDrawable) ratingTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int ratingColor = getMagnitudeColor(currentChallenge.getRating());

        // Set the color on the magnitude circle
        ratingCircle.setColor(ratingColor);

        //Name challenge TextView
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.challenge_name);
        String nameChallenge = currentChallenge.getName();
        nameTextView.setText(nameChallenge);

        //Challenge date TextView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.challenge_date);
        String formattedDate = formatDate(currentChallenge.getDate());
        dateTextView.setText(formattedDate);

        return listItemView;

    }

    private String formatRating(double rating) {
        DecimalFormat ratingFormat = new DecimalFormat("0.0");
        return ratingFormat.format(rating);
    }

    private String formatDate(long dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magNumber) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magNumber);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.valuation1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.valuation2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.valuation3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.valuation4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.valuation5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.valuation6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.valuation7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.valuation8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.valuation9;
                break;
            default:
                magnitudeColorResourceId = R.color.valuation10;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}


