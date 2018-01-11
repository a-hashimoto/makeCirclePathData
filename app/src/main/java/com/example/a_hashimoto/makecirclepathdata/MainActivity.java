package com.example.a_hashimoto.makecirclepathdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double x;
    double y;
    double r;
    double rr;
    double controlR;
    double controlRr;
    double zero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText radius = findViewById(R.id.radius);
        final EditText coordinateX = findViewById(R.id.coordinateX);
        final EditText coordinateY = findViewById(R.id.coordinateY);
        final TextView pathData = findViewById(R.id.pathData);
        final Button calculateButton = findViewById(R.id.button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r = Double.parseDouble(String.valueOf(radius.getText()));
                rr = 2 * r;
                controlR = 0.45 * r;
                controlRr = rr - controlR;
                x = Double.parseDouble(String.valueOf(coordinateX.getText()));
                y = Double.parseDouble(String.valueOf(coordinateY.getText()));
                pathData.setText(calculatePathDataFromTop());
                Log.d("calculate", calculatePathDataFromTop());
            }
        });
    }

    private String calculatePathDataFromSide() { // 開始地点左
        String command1 = createCommandM(zero, r);
        String command2 = createCommandC(0, 0.45 * r, 0.45 * r, 0, r, 0);
        String command3 = createCommandC(1.55 * r, 0, 2 * r, 0.45 * r, 2 * r, r);
        String command4 = createCommandC(2 * r, 1.55 * r, 1.55 * r, 2 * r, r, 2 * r);
        String command5 = createCommandC(0.45 * r, 2 * r, 0, 1.55 * r, 0, r);
        return command1 + command2 + command3 + command4 + command5;
    }

    private String calculatePathDataFromTop() { // 開始地点上
        String command1 = createCommandM(r, zero);
        String command2 = createCommandC(controlRr, zero, rr, controlR, rr, r);
        String command3 = createCommandC(rr, controlRr, controlRr, rr, r, rr);
        String command4 = createCommandC(controlR, rr, zero, controlRr, zero, r);
        String command5 = createCommandC(zero, controlR, controlR, zero, r, zero);
        return command1 + command2 + command3 + command4 + command5;
    }

    private String createCommandM(double x, double y) {
        return "M " + setCoordinate(x, y);
    }

    private String createCommandC(double x1, double y1, double x2, double y2, double x3, double y3) {
        return ("C " + setCoordinate(x1, y1) + setCoordinate(x2, y2) + setCoordinate(x3, y3));
    }

    private String setCoordinate(double x, double y) {
        return ((x + this.x) + "," + (y + this.y) + " ");
    }
}
