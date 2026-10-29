package com.example.animalcompanymenu;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(30, 30, 30, 30);
        root.setBackgroundColor(Color.rgb(17, 24, 39));

        TextView title = new TextView(this);
        title.setText("🐾 ANIMAL COMPANY");
        title.setTextSize(26);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        addToggle(root, "Dev Mode");
        addToggle(root, "Infinite Nuts");
        addToggle(root, "Infinite Coins");
        addToggle(root, "Research Points");

        TextView note = new TextView(this);
        note.setText(
            "AC MENU DEMO\n\n" +
            "These switches change this menu only. " +
            "They do not modify Animal Company's game data."
        );
        note.setTextColor(Color.LTGRAY);
        note.setTextSize(16);
        note.setPadding(0, 30, 0, 0);
        root.addView(note);

        setContentView(root);
    }

    private void addToggle(LinearLayout root, String label) {
        Switch toggle = new Switch(this);
        toggle.setText(label);
        toggle.setTextSize(18);
        toggle.setTextColor(Color.WHITE);
        toggle.setPadding(0, 20, 0, 20);

        toggle.setOnCheckedChangeListener((button, enabled) -> {
            Toast.makeText(
                this,
                label + (enabled ? " ON" : " OFF"),
                Toast.LENGTH_SHORT
            ).show();
        });

        root.addView(toggle);
    }
}
