package com.gpxdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gpxdemo.util.GPXUtil;

import org.alternativevision.gpx.beans.Waypoint;

import java.util.List;

public class MainActivity extends Activity {
    private TextView tv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);
        tv = (TextView) findViewById(R.id.tv);
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/gpxdemo.gpx";
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Waypoint> list = GPXUtil.getGPX(path);
                tv.setText(String.valueOf(list.size()));
            }
        });
    }
}
