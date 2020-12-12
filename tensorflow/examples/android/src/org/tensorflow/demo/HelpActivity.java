package org.tensorflow.demo;
// HelpActivity.java
// HelpActivity.java

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Locale;

public class HelpActivity extends Activity {
    TextView textView_tutorial;
    TextView textView_info_func;
    TextView textView_menu_tutorial;
    Context mContext;

    TextToSpeech tts;
    String text;

    private long btnPressTime = 0;
    RelativeLayout relativehelp;
    RelativeLayout relativetutorial;
    LinearLayout linear_menu_map;
    LinearLayout linear_menu_camera;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        textView_tutorial = (TextView)findViewById(R.id.textView_tutorial_simple);

        relativehelp = (RelativeLayout) findViewById(R.id.RelativeLayout_info_func_simple);
        relativetutorial = (RelativeLayout) findViewById(R.id.RelativeLayout_tutorial_simple);
        linear_menu_map = (LinearLayout) findViewById(R.id.linear_menu_map);
        linear_menu_camera = (LinearLayout) findViewById(R.id.linear_menu_camera);


        // tts 설정
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                    tts.setSpeechRate(0.9f);
                }
            }
        });

        // 튜토리얼에 관한 설명
        textView_tutorial = (TextView)findViewById(R.id.textView_tutorial_simple);
        relativetutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text1 = textView_tutorial.getText().toString();
                    Toast.makeText(HelpActivity.this, text1, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text1);
                    } else {
                        ttsUnder20(text1);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    //((MainActivity)getActivity()).replaceFragment(TutorialActivity.newInstance());
                    Intent intent = new Intent(HelpActivity.this,TutorialActivity.class);

                    startActivity(intent);
                }
            }
        });

        // 기능에 관한 설명
        textView_info_func = (TextView)findViewById(R.id.textView_info_func_simple);
        relativehelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text1 = textView_info_func.getText().toString();
                    Toast.makeText(HelpActivity.this, text1, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text1);
                    } else {
                        ttsUnder20(text1);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    Intent it = new Intent(HelpActivity.this,FuncActivity.class);

                    startActivity(it);
                }
            }
        });


        //하단바
        // 메뉴 - 지도 클릭
        linear_menu_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text2 = "지도";
                    Toast.makeText(HelpActivity.this, text2, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text2);
                    } else {
                        ttsUnder20(text2);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    Intent it = new Intent(HelpActivity.this, MapActivity.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


                    startActivity(it);
                }
            }
        });

        // 메뉴 - 카메라  클릭
        linear_menu_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text2 = "카메라";
                    Toast.makeText(HelpActivity.this, text2, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text2);
                    } else {
                        ttsUnder20(text2);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    Intent it = new Intent(HelpActivity.this,DetectorActivity.class);

                    startActivity(it);
                }
            }
        });

        //super.onCreate(savedInstanceState, persistentState);
    }

    // TTS 관련
    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
}