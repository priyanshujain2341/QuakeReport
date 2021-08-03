package com.example.android.quakereport;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;


import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes)
    {
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //Displays magnitude and converts it into specified 0.0 format
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        double magnitude = currentEarthquake.getMagnitude();
        String formattedMagnitude = decimalFormat.format(magnitude);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        magnitudeView.setText(formattedMagnitude);
        //End

        //String manipulation for location:
        String offset="", primary;

        String location = currentEarthquake.getLocation();
        if(location.contains("of"))
        {
            int length = location.length();
            int temp = location.indexOf("of");
            System.out.println(length);
            offset = location.substring(0,temp+2);
            primary = location.substring(temp+3, location.length());
        }
        else
        {
            offset = "Near the";
            primary = location;
        }

        TextView offsetView = (TextView) listItemView.findViewById(R.id.offset);
        offsetView.setText(offset);

        TextView locationView  = (TextView) listItemView.findViewById(R.id.location);
        locationView.setText(primary);

        //End

        Date date = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(date);

        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(date);
        timeView.setText(formattedTime);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private int getMagnitudeColor (double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
