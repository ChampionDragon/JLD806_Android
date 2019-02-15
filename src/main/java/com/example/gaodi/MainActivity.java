package com.example.gaodi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Gpio;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tvResult;
    CheckBox cbIsH, onOff;

    EditText editNum;

    //	EditText edBB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tvResult);

        /*初始化五个Gpio的状态*/
        GpioStatus();

        cbIsH = (CheckBox) findViewById(R.id.cbIsHigh);
        onOff = (CheckBox) findViewById(R.id.onoff);

        editNum = (EditText) findViewById(R.id.edNumInput);
//		edBB = (EditText) findViewById(R.id.edBB);
        Button btnRead = (Button) findViewById(R.id.btnRead);

        Button btnWrite = (Button) findViewById(R.id.btnWrite);

        editNum = (EditText) findViewById(R.id.edNumInput);

        findViewById(R.id.readgpio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GpioStatus();
            }
        });


        btnRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Gpio.SetGpioInput(editNum.getText().toString());//设置GPIO为输入引脚(默认初始化，初始化一般为高电平状态为1)
                int gpio_status = Gpio.GetGpioValue(editNum.getText().toString()/*,
                        Integer.valueOf(editNum.getText().toString())*/);//读取该引脚的高低电平状态
                Log.e("lcb", "状态：" + Gpio.GetGpioValue(editNum.getText().toString()));
                if (gpio_status == 1) {
                    tvResult.setText(String.valueOf(editNum.getText()) + "->读取高电平完成  状态：" + Gpio.GetGpioValue(editNum.getText().toString()));
                } else {
                    tvResult.setText(String.valueOf(editNum.getText()) + "->读取低电平完成  状态：" + Gpio.GetGpioValue(editNum.getText().toString()));
                }
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int aa = 0;
                if (cbIsH.isChecked()) {
                    aa = 1;
                }
                if (aa == 1)
                    Gpio.SetGpioOutputHigh(editNum.getText().toString());//设置GPIO引脚输出高电平
                else if (aa == 0)
                    Gpio.SetGpioOutputLow(editNum.getText().toString());//设置GPIO引脚输出低电平
                tvResult.setText(String.valueOf(editNum.getText()) + "->写入 " + String.valueOf(aa) + "完成!!!");
                Log.w("lcb", "78写入后的状态：" + Gpio.GetGpioValue(editNum.getText().toString()));
            }
        });

        // Thread mainthread = new Thread(runnable);
        // mainthread.start();


        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Gpio.SetGpioOutputLow("gpio_bl");
                    Gpio.SetGpioOutputLow("gpio_lcd_en");
                } else {
                    Gpio.SetGpioOutputHigh("gpio_bl");
                    Gpio.SetGpioOutputHigh("gpio_lcd_en");
                }
            }
        });


    }


    // Handler handler = new Handler() {
    // public void handleMessage(Message msg) {
    // switch (msg.what) {
    // case 1:
    // tx.setText("�ߵ�ƽ");
    // break;
    // case 2:
    // tx.setText("�͵�ƽ");
    // break;
    // }
    // }
    // };
    // private Runnable runnable = new Runnable() {
    // public void run() {
    // while (true) {
    //
    // int gpio_status = Gpio.readGpio('h', 9);
    // if (gpio_status == 1) {
    // Message message = new Message();
    // message.what = 1;
    // handler.sendMessage(message);
    // // Gpio.writeGpio('h', 12, 1);
    // } else {
    // Message message = new Message();
    // message.what = 2;
    // handler.sendMessage(message);
    // // Gpio.writeGpio('h', 12, 0);
    // }
    // try {
    // Thread.sleep(500);
    // } catch (InterruptedException e) {
    // }
    // }
    // }
    // };
    public void GpioStatus() {
        String status = "gpio1的状态：" + Gpio.GetGpioValue("gpio1") + "\ngpio2的状态：" + Gpio.GetGpioValue("gpio2") +
                "\ngpio3的状态：" + Gpio.GetGpioValue("gpio3") + "\ngpio4的状态："
                + Gpio.GetGpioValue("gpio4") + "\ngpio5的状态：" + Gpio.GetGpioValue("gpio5");
        tvResult.setText(status);
    }

}
