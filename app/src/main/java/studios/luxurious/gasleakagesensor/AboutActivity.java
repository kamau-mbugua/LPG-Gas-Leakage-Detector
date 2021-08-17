package studios.luxurious.gasleakagesensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import studios.luxurious.gasleakagesensor.databinding.ActivityAboutBinding;
import studios.luxurious.gasleakagesensor.databinding.ActivityLoginBinding;

public class AboutActivity extends AppCompatActivity {

    ActivityAboutBinding aboutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        View view = aboutBinding.getRoot();
        setContentView(view);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();


        aboutBinding.backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        Element adsElement = new Element();
        adsElement.setTitle("Like our Idea?");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Gas Leakage Detector")
                .setImage(R.drawable.preview)
                .addItem(new Element().setTitle("Version 0.1"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("kelvinkamaumbugua@gmail.com")
                .addWebsite("https://kamau-mbugua.github.io/")
                .addFacebook("kKamau")
                .addTwitter("@mbuguakamau_")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addInstagram("medyo80")
                .addGitHub("kamau-mbugua")
                .addItem(getCopyRightsElement())
                .create();

        setContentView(aboutPage);
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.preview);
        copyRightsElement.setAutoApplyIconTint(true);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}