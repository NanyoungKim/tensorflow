package org.tensorflow.demo;


// TutorialActivity

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class TutorialActivity extends Activity {

    TextView textView;
    TextToSpeech tts;

    LinearLayout linear_menu_tutorial;
    LinearLayout linear_menu_map;
    LinearLayout linear_menu_camera;
    private long btnPressTime = 0;

    public TutorialActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        linear_menu_tutorial = (LinearLayout)findViewById(R.id.linear_menu_tutorial);
        linear_menu_map = (LinearLayout)findViewById(R.id.linear_menu_map);
        linear_menu_camera = (LinearLayout) findViewById(R.id.linear_menu_camera);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        textView = (TextView)findViewById(R.id.textView_app_manual_contents);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tts.setSpeechRate(0.9f);
                tts.speak(textView.getText().toString(),TextToSpeech.QUEUE_FLUSH, null,null);
            }
        });
        // 메뉴 - 도움말 클릭
        linear_menu_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text2 = "도움말";
                    Toast.makeText(TutorialActivity.this, text2, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text2);
                    } else {
                        ttsUnder20(text2);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    Intent it = new Intent(TutorialActivity.this,HelpActivity.class);

                    startActivity(it);
                }
            }
        });

        // 메뉴 - 지도 클릭
        linear_menu_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text2 = "지도";
                    Toast.makeText(TutorialActivity.this, text2, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text2);
                    } else {
                        ttsUnder20(text2);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    Intent it = new Intent(TutorialActivity.this,MapActivity.class);

                    startActivity(it);
                }
            }
        });

        // 메뉴 - 카메라 클릭
        linear_menu_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()>btnPressTime+1000){
                    btnPressTime = System.currentTimeMillis();
                    String text2 = "카메라";
                    Toast.makeText(TutorialActivity.this, text2, Toast.LENGTH_SHORT).show();

                    //http://stackoverflow.com/a/29777304
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ttsGreater21(text2);
                    } else {
                        ttsUnder20(text2);
                    }
                    return;
                }
                if(System.currentTimeMillis()<=btnPressTime+1000){
                    Intent it = new Intent(TutorialActivity.this,DetectorActivity.class);

                    startActivity(it);
                }
            }
        });
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

    protected void onDestroy() {
        super.onDestroy();
        // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }
}