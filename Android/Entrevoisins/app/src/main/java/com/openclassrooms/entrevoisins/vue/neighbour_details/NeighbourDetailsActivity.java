package com.openclassrooms.entrevoisins.vue.neighbour_details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.vue.neighbour_list.ListNeighbourActivity;

public class NeighbourDetailsActivity extends AppCompatActivity {

    private static final String TAG = "NeighbourDetails";
/*
    private Neighbour neighbour;
    private FloatingActionButton addFavorite;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_neighbour_details);

        // Widgets declaration
        MaterialToolbar topAppBar = findViewById(R.id.material_toolbar_neighbour_details_activity);
        ImageView appbarBackgroundImage = findViewById(R.id.appbar_background_image_neighbour_details_activity);
        TextView bigTitleNeighbour = findViewById(R.id.big_title_neighbour_details_activity);
        TextView cardCoordinatesTitle = findViewById(R.id.card_coordinates_title_neighbour_details_activity);
        TextView cardCoordinatesAdress = findViewById(R.id.card_coordinates_adress_neighbour_details_activity);
        TextView cardCoordinatesPhone = findViewById(R.id.card_coordinates_phone_neighbour_details_activity);
        TextView cardCoordinatesSocial = findViewById(R.id.card_coordinates_social_neighbour_details_activity);
        TextView cardAboutDescription = findViewById(R.id.card_about_description_neighbour_details_activity);
        FloatingActionButton addFavorite = findViewById(R.id.add_favorite_fab_neighbour_details_activity);

        // Retrieve the neighbour object from the intent via parcel
        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("NEIGHBOUR_OBJECT");

        // Fill the different widgets

        Glide.with(NeighbourDetailsActivity.this)
             .load(neighbour.getAvatarUrl())
             .into(appbarBackgroundImage);

        bigTitleNeighbour.setText(neighbour.getName());
        cardCoordinatesTitle.setText(neighbour.getName());
        cardCoordinatesAdress.setText(neighbour.getAddress());
        cardCoordinatesPhone.setText(neighbour.getPhoneNumber());
        cardCoordinatesSocial.setText("www.facebook.com/" + neighbour.getName());
        cardAboutDescription.setText(neighbour.getAboutMe());
        setIconInFab(neighbour.getFavorite(), addFavorite);

        // Implement action when navigation button is pressed
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Implement action when FAB is pressed
        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                neighbour.switchFavorite();
                setIconInFab(neighbour.getFavorite(), addFavorite);
                Log.d(TAG, "onClick: fav clicked!");
            }
        });
    }

    private void setIconInFab(Boolean isFavorite, FloatingActionButton addFavorite) {
        // Managing fab icon function of neighbour favorite status
        if (isFavorite) {
            addFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_add_favorite_32dp));
        } else {
            addFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_add_favorite_no_32dp));
        }
    }
}