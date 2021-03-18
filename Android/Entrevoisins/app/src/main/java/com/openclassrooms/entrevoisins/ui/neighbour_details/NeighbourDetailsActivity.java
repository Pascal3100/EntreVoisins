package com.openclassrooms.entrevoisins.ui.neighbour_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

public class NeighbourDetailsActivity extends AppCompatActivity {

    ImageView appbarBackgroundImage = findViewById(R.id.appbar_background_image_neighbour_details_activity);
    TextView bigTitleNeighbour = findViewById(R.id.big_title_neighbour_details_activity);
    TextView cardCoordinatesTitle = findViewById(R.id.card_coordinates_title_neighbour_details_activity);
    TextView cardCoordinatesAdress = findViewById(R.id.card_coordinates_adress_neighbour_details_activity);
    TextView cardCoordinatesPhone = findViewById(R.id.card_coordinates_phone_neighbour_details_activity);
    TextView cardCoordinatesSocial = findViewById(R.id.card_coordinates_social_neighbour_details_activity);
    TextView cardAboutTitle = findViewById(R.id.card_about_title_neighbour_details_activity);
    TextView cardAboutDescription = findViewById(R.id.card_about_description_neighbour_details_activity);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

    }
}