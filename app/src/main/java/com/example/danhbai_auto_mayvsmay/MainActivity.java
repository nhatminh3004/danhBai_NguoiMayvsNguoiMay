package com.example.danhbai_auto_mayvsmay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {

    int Source_nguoi=0;
    int  Source_may=0;
    //
    EditText editTextNhapThoiGianDanhBai;
    TextView countdownTimerText;
    Button startTimer, resetTimer;
    private static CountDownTimer countDownTimer;
    //

    int manghinhbai[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};
    ImageView hinh1,hinh2,hinh3,hinh4,hinh5,hinh6;
    TextView textViewKetQua_nguoi,textviewKetQua_may,textViewLanThang_nguoi,textViewLanThang_may;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        hinh1=(ImageView) findViewById(R.id.imageView1_nguoi);
        hinh2=(ImageView) findViewById(R.id.imageView2_nguoi);
        hinh3=(ImageView) findViewById(R.id.imageView3_nguoi);
        hinh4 = (ImageView) findViewById(R.id.imageView1_may);
        hinh5 =(ImageView) findViewById(R.id.imageView2_may);
        hinh6 =(ImageView) findViewById(R.id.imageView3_may);

        //**
        editTextNhapThoiGianDanhBai= (EditText) findViewById(R.id.enterMinutes);
        countdownTimerText = (TextView) findViewById(R.id.countdownText);
        startTimer=(Button) findViewById(R.id.startTimer);
        resetTimer= (Button) findViewById(R.id.resetTimer);
        //**




        textViewKetQua_nguoi=(TextView) findViewById(R.id.textViewKetQua_nguoi);
        textviewKetQua_may=(TextView) findViewById(R.id.textViewKetQua_may);
        textViewLanThang_nguoi=(TextView) findViewById(R.id.textViewLanThang_nguoi);
        textViewLanThang_may=(TextView) findViewById(R.id.textViewLanThang_may);
        textViewLanThang_may.setText(Source_may+"");
        textViewLanThang_nguoi.setText(Source_nguoi+"");


        //on click
        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.startTimer:
                        //nếu countDownTimer is null thì xử lý như sau :
                        if(countDownTimer == null){
                            String getMinutes = editTextNhapThoiGianDanhBai.getText().toString();
                            if(!getMinutes.equals("")&&getMinutes.length()>0){
                                int miligiay = Integer.parseInt(getMinutes) *60 *1000;//chuyen phut thanh miliiay
                                startTimer(miligiay);//bắt đầu đếm ngược
                                startTimer.setText(getString(R.string.stop_timer));//change Text

                            } else
                                Toast.makeText(MainActivity.this, "Vui lòng nhập số phút", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            stopCountdown();
                            startTimer.setText(getString(R.string.start_timer));
                        }
                        break;


                }
            }
        });
        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopCountdown();
                startTimer.setText(getString(R.string.start_timer));//Change text to Start Timer
                countdownTimerText.setText(getString(R.string.timer));//Change Timer text
            }
        });
        //














    }

    //Hàm tính thắng thua
    private int tinhThangThua(int [] a){
        int nut=0;
        if(tinhsoBaiTay(a) ==3)
            nut=10;
        else {
            int temp=0;
            for(int i=0;i<a.length;i++)

                if(a[i] %13 <10)
                    temp=temp+(a[i]%13 +1);
            nut=temp%10;
            if(temp %10 ==0)
                nut=0;
        }
        return nut;
    }
    //hàm tính số nút
    private String tinhSoNut(int[] a){
        String ketqua="";
        if(tinhsoBaiTay(a) == 3)
            ketqua="3 cào";
        else {
            int tongsonut=0;
            for(int i=0;i<a.length;i++)
                if(a[i] %13 <10)
                    tongsonut=tongsonut+(a[i]%13 +1);
            if(tongsonut %10 ==0 )
                ketqua ="kết quả bù, trong đó có "+tinhsoBaiTay(a)+"tây";
            else {
                ketqua=(tongsonut%10)+"\tnút , "+"trong đó có:"+tinhsoBaiTay(a)+"tây";
            }
        }
        return ketqua;
    }

    //hàm kiểm tra lá bài tây
    private int tinhsoBaiTay(int [] a){
        int k=0;
        for(int i=0;i<a.length;i++)
            if(a[i]%13 >=10 && a[i]%13 <13)
                k++;
        return k;
    }
    //hàm lấy 3 số ngẫu nhiên
    private int[] lay3songaunhien(int min, int max){
        int [] baso = new int[3];
        int i=0;
        baso[i++] = random(min, max);
        do {
            int k =random(min, max);
            if(!kiemTraTrung(k,baso)){
                baso[i++]=k;
            }
        }while (i<3);
        return baso;
    }
    //ham random
    private int random(int min, int max){

        return min+(int)(Math.random()*((max-min)+1));
    }
    //ham kiem tra trùng
    private boolean kiemTraTrung(int k, int []a){
        for(int i=0;i<a.length;i++)
            if(a[i] ==k)
                return true;
        return false;
    }
    //hàm stoup CountDown
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
    private void startTimer(int miligiay){
        countDownTimer = new CountDownTimer(miligiay,5000) {
            @Override
            public void onTick(long l) {
                long miligiay = l;
                //chuyen miligiay thành 1 tiếng , phút , giây
                String dinhdang= String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(miligiay),TimeUnit.MILLISECONDS.toMinutes(miligiay) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(miligiay)), TimeUnit.MILLISECONDS.toSeconds(miligiay) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(miligiay)));
                countdownTimerText.setText(dinhdang);

                int [] value_nguoi= lay3songaunhien(0,51);

                YoYo.with(Techniques.SlideInRight).duration(1000).repeat(0).playOn(hinh1);
                YoYo.with(Techniques.SlideInRight).duration(1200).repeat(0).playOn(hinh2);
                YoYo.with(Techniques.SlideInRight).duration(1400).repeat(0).playOn(hinh3);
                hinh1.setImageResource(manghinhbai[value_nguoi[0]]);

                hinh2.setImageResource(manghinhbai[value_nguoi[1]]);
                hinh3.setImageResource(manghinhbai[value_nguoi[2]]);
                String kq_nguoi=tinhSoNut(value_nguoi);
                textViewKetQua_nguoi.setText(kq_nguoi);

                int [] value_may= lay3songaunhien(0,51);
                YoYo.with(Techniques.SlideInRight).duration(1000).repeat(0).playOn(hinh4);
                YoYo.with(Techniques.SlideInRight).duration(1200).repeat(0).playOn(hinh5);
                YoYo.with(Techniques.SlideInRight).duration(1400).repeat(0).playOn(hinh6);
                hinh4.setImageResource(manghinhbai[value_may[0]]);
                hinh5.setImageResource(manghinhbai[value_may[1]]);
                hinh6.setImageResource(manghinhbai[value_may[2]]);
                String kq_may=tinhSoNut(value_may);
                textviewKetQua_may.setText(kq_may);
                int lanthangofnguoi=tinhThangThua(value_nguoi);
                int lanthangofmay= tinhThangThua(value_may);
                if(lanthangofnguoi>lanthangofmay){
                    Toast.makeText(MainActivity.this, "Người chơi thắng", Toast.LENGTH_SHORT).show();
                    Source_nguoi++;
                    YoYo.with(Techniques.FlipInX).duration(1000).repeat(0).playOn(textViewLanThang_nguoi);
                    textViewLanThang_nguoi.setText(Source_nguoi+"");

                }
                if(lanthangofmay>lanthangofnguoi){
                    Toast.makeText(MainActivity.this, "Máy thắng", Toast.LENGTH_SHORT).show();
                    Source_may++;
                    YoYo.with(Techniques.FlipInX).duration(1000).repeat(0).playOn(textViewLanThang_may);
                    textViewLanThang_may.setText(Source_may+"");
                }
                if(lanthangofmay==lanthangofnguoi){
                    Toast.makeText(MainActivity.this, "Hoà nhau", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFinish() {
                countdownTimerText.setText("Hết Giờ"); //On finish change timer text
                countDownTimer = null;//set CountDownTimer to null
                startTimer.setText(getString(R.string.start_timer));//Change button text
            }
        }.start();
    }

}