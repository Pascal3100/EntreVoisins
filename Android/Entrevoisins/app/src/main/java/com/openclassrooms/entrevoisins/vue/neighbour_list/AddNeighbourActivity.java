package com.openclassrooms.entrevoisins.vue.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.controler.interfaces.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @BindView(R.id.phoneNumberLyt)
    TextInputLayout phoneInput;
    @BindView(R.id.addressLyt)
    TextInputLayout addressInput;
    @BindView(R.id.aboutMeLyt)
    TextInputLayout aboutMeInput;
    @BindView(R.id.create)
    MaterialButton addButton;

    private NeighbourApiService mApiService;
    private String mNeighbourImage;
    private static final String IS_FAVORITE_VIEW = "IS_FAVORITE_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_neighbour);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick(R.id.create)
    void addNeighbour() {
        // Retrieve the boolean info of the view
        Intent intent = getIntent();
        Boolean isFavoriteView = intent.getBooleanExtra(IS_FAVORITE_VIEW, false);

        Neighbour neighbour = new Neighbour(
                System.currentTimeMillis(),
                nameInput.getEditText().getText().toString(),
                mNeighbourImage,
                addressInput.getEditText().getText().toString(),
                phoneInput.getEditText().getText().toString(),
                aboutMeInput.getEditText().getText().toString(),
                isFavoriteView
        );
        mApiService.createNeighbour(neighbour);
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param context
     */
    public static Intent navigate(Context context, Boolean isFavoriteView) {
        Intent intent = new Intent(context, AddNeighbourActivity.class);
        intent.putExtra(IS_FAVORITE_VIEW, isFavoriteView);

        return intent;

    }
}
