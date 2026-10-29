package com.example.animalcompanymenu;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private final int BG = 0xFF101820;
    private final int CARD = 0xFF1C2936;
    private final int ACCENT = 0xFF63D6B5;
    private final int MUTED = 0xFF9BAABA;

    private final List<Switch> switches = new ArrayList<>();
    private TextView status;

    private int dp(float value) {
        return (int) (value *
            getResources().getDisplayMetrics().density + 0.5f);
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        return t;
    }

    private void addFeature(
            LinearLayout root,
            String title,
            String description) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(12), dp(16), dp(12));
        card.setBackground(shape(CARD, 14));

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout words = new LinearLayout(this);
        words.setOrientation(LinearLayout.VERTICAL);

        TextView name = text(title, 17, Color.WHITE);
        name.setTypeface(null, Typeface.BOLD);

        TextView detail = text(description, 12, MUTED);
        detail.setPadding(0, dp(5), dp(6), 0);

        words.addView(name);
        words.addView(detail);

        LinearLayout.LayoutParams wordParams =
            new LinearLayout.LayoutParams(0, -2, 1f);

        row.addView(words, wordParams);

        Switch sw = new Switch(this);
        sw.setContentDescription(title);
        switches.add(sw);

        sw.setOnCheckedChangeListener((button, enabled) -> {
            status.setText(
                title + (enabled
                    ? " — enabled in demo"
                    : " — disabled")
            );
        });

        row.addView(sw);
        card.addView(row);

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(10);

        root.addView(card, params);
    }

    private void addHeading(LinearLayout root, String value) {
        TextView heading = text(value, 14, ACCENT);
        heading.setTypeface(null, Typeface.BOLD);
        heading.setPadding(dp(2), dp(18), 0, dp(10));
        root.addView(heading);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switches.clear();

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BG);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(20), dp(18), dp(24));

        scroll.addView(root);

        TextView eyebrow = text(
            "ANIMAL COMPANY  /  QUEST",
            12, ACCENT);
        root.addView(eyebrow);

        TextView title = text(
            "Developer Panel",
            29, Color.WHITE);
        title.setTypeface(null, Typeface.BOLD);
        title.setPadding(0, dp(8), 0, dp(4));
        root.addView(title);

        TextView subtitle = text(
            "Standalone interface prototype",
            14, MUTED);
        root.addView(subtitle);

        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);
        info.setPadding(dp(15), dp(14), dp(15), dp(14));
        info.setBackground(shape(CARD, 14));

        TextView infoTitle = text(
            "LOCAL DEMO MODE",
            13, ACCENT);
        infoTitle.setTypeface(null, Typeface.BOLD);
        info.addView(infoTitle);

        TextView infoText = text(
            "Controls change this demo's interface only. " +
            "They do not modify Animal Company's game data.",
            13, MUTED);
        infoText.setPadding(0, dp(7), 0, 0);
        info.addView(infoText);

        LinearLayout.LayoutParams infoParams =
            new LinearLayout.LayoutParams(-1, -2);
        infoParams.topMargin = dp(20);
        root.addView(info, infoParams);

        addHeading(root, "DEVELOPER CONTROLS");

        addFeature(root,
            "Developer Mode",
            "Demo toggle; does not enable game developer access.");

        addHeading(root, "CURRENCY");

        addFeature(root,
            "Infinite Nuts",
            "Visual demonstration only.");

        addFeature(root,
            "Infinite Coins",
            "Visual demonstration only.");

        addHeading(root, "PROGRESSION");

        addFeature(root,
            "Research Points",
            "Visual demonstration only.");

        addHeading(root, "STATUS");

        status = text("All demo controls are disabled.",
            13, MUTED);
        status.setPadding(dp(4), dp(8), dp(4), dp(12));
        root.addView(status);

        Button reset = new Button(this);
        reset.setText("RESET ALL CONTROLS");
        reset.setTextColor(BG);
        reset.setAllCaps(false);
        reset.setBackground(shape(ACCENT, 12));

        reset.setOnClickListener(v -> {
            for (Switch sw : switches) {
                sw.setChecked(false);
            }
            status.setText("All demo controls are disabled.");
            Toast.makeText(
                this, "Demo reset",
                Toast.LENGTH_SHORT).show();
        });

        root.addView(reset,
            new LinearLayout.LayoutParams(-1, dp(52)));

        TextView footer = text(
            "Developer Panel • Prototype v2",
            12, MUTED);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, dp(20), 0, dp(4));
        root.addView(footer);

        setContentView(scroll);
    }
}
