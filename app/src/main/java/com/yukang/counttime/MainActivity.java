package com.yukang.counttime;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etInput;
    private Button getTime, startTime, stopTime;
    private TextView tvTime;
    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etInput = (EditText) findViewById(R.id.input_time);
        tvTime = (TextView) findViewById(R.id.time);
        getTime = (Button) findViewById(R.id.get_time);
        startTime = (Button) findViewById(R.id.start_time);
        stopTime = (Button) findViewById(R.id.stop_time);
        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_time:
                tvTime.setText(etInput.getText().toString());
                i = Integer.parseInt(etInput.getText().toString());
                break;

            case R.id.start_time:
                startTime();
                break;

            case R.id.stop_time:
                stopTime();
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvTime.setText(msg.arg1 + "");
            startTime();
        }
    };

    public void startTime() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message = mHandler.obtainMessage();
                message.arg1 = i;
                mHandler.sendMessage(message);
            }
        };
        timer.schedule(task, 1000);
    }

    public void stopTime() {
        timer.cancel();
    }
}
