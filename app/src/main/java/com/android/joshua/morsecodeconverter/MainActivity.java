package com.android.joshua.morsecodeconverter;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    String mMorseCode, mMessage;
    EditText msg, morse;
    Button dot,dah,space,sound,clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer[] mdit = {MediaPlayer.create(getApplicationContext(), R.raw.dit)};
        final MediaPlayer[] mdah = {MediaPlayer.create(getApplicationContext(), R.raw.dah)};
        setItem();
        final HashMap<String, String> mkeyMap = new HashMap<>();
        setAplhaToCode(mkeyMap);
        msg.setEnabled(false);


        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morse.setText(morse.getText().toString()+"•");
                mMorseCode = getMorseCode();
                mMessage = getMessage(mMorseCode, mkeyMap);
                msg.setText("");
                msg.setText(msg.getText().toString()+mMessage);

            }
        });

        dah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morse.setText(morse.getText().toString()+"⚊");
                mMorseCode = getMorseCode();
                mMessage = getMessage(mMorseCode, mkeyMap);
                msg.setText("");
                msg.setText(msg.getText().toString()+mMessage);

            }
        });

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morse.setText(morse.getText().toString()+" ");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(morse.getText().toString().equals("")){

                }else{
                    morse.setText(morse.getText().subSequence(0,morse.length()-1));
                }
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=morse.getText().toString();
                for(int i=0;i<code.length();i++){
                    if(code.charAt(i)=='•'){
                        if (mdit[0].isPlaying()) {
                            mdit[0].stop();
                            mdit[0].release();
                            mdit[0] = MediaPlayer.create(getApplicationContext(), R.raw.dit);
                        }
                        mdit[0].start();
                    }
                    else if(code.charAt(i)=='⚊'){
                        if (mdah[0].isPlaying()) {
                            mdah[0].stop();
                            mdah[0].release();
                            mdah[0] = MediaPlayer.create(getApplicationContext(), R.raw.dah);
                        }
                        mdah[0].start();

                    }
                    else if(code.charAt(i)==' '){

                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(450);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String getMessage(String mMorseCode, HashMap<String, String> mkeyMap) {
        String message = "";
        String[] words=mMorseCode.split(" ");
        int len=words.length; int i=0;

        do {
            Iterator it = mkeyMap.entrySet().iterator();
            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry) it.next();
                if (pair.getValue().equals(words[i])) {
                    message = message + pair.getKey().toString();
                    break;
                }
            }
            i++;
        }while(i<len);
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        return message;
    }

    private String getMorseCode() {
        String mcode;
        mcode = morse.getText().toString();
        return mcode;
    }

    private void setItem() {
        msg = findViewById(R.id.messageText);
        morse = findViewById(R.id.morseText);
        dot=findViewById(R.id.dotBtn);
        dah=findViewById(R.id.dahBtn);
        space=findViewById(R.id.spaceBtn);
        sound=findViewById(R.id.playBtn);
        clear=findViewById(R.id.clearbtn);
    }

    private void setAplhaToCode(HashMap<String, String> keyMap) {
        //Alphabets
        keyMap.put("A", "•⚊");
        keyMap.put("B", "⚊•••");
        keyMap.put("C", "⚊•⚊•");
        keyMap.put("D", "⚊••");
        keyMap.put("E", "•");
        keyMap.put("F", "••⚊•");
        keyMap.put("G", "⚊⚊•");
        keyMap.put("H", "••••");
        keyMap.put("I", "••");
        keyMap.put("J", "•⚊⚊⚊");
        keyMap.put("K", "⚊•⚊");
        keyMap.put("L", "•⚊••");
        keyMap.put("M", "⚊⚊");
        keyMap.put("N", "⚊•");
        keyMap.put("O", "⚊⚊⚊");
        keyMap.put("P", "•⚊⚊•");
        keyMap.put("Q", "⚊⚊•⚊");
        keyMap.put("R", "•⚊•");
        keyMap.put("S", "•••");
        keyMap.put("T", "⚊");
        keyMap.put("U", "••⚊");
        keyMap.put("V", "•••⚊");
        keyMap.put("W", "•⚊⚊");
        keyMap.put("X", "⚊••⚊");
        keyMap.put("Y", "⚊•⚊⚊");
        keyMap.put("Z", "⚊⚊••");
        //Numbers
        keyMap.put("1", "•⚊⚊⚊⚊");
        keyMap.put("2", "••⚊⚊⚊");
        keyMap.put("3", "•••⚊⚊");
        keyMap.put("4", "••••⚊");
        keyMap.put("5", "•••••");
        keyMap.put("6", "⚊••••");
        keyMap.put("7", "⚊⚊•••");
        keyMap.put("8", "⚊⚊⚊••");
        keyMap.put("9", "⚊⚊⚊⚊•");
        keyMap.put("0", "⚊⚊⚊⚊⚊");
        //Morse code prosigns for message handling and formatting in Amateur Radio NTS nets
        keyMap.put("AA","•⚊•⚊");
        keyMap.put("AR","•⚊•⚊•");
        keyMap.put("AS","•⚊•••");
        keyMap.put("BT","⚊•••⚊");
        keyMap.put("CT","⚊•⚊•⚊");
        keyMap.put("HH","••••••••");
        keyMap.put("?","••⚊⚊••");
        keyMap.put("KN","⚊•⚊⚊•");
        keyMap.put("NJ","⚊••⚊⚊⚊");
        keyMap.put("SK","•••⚊•⚊");
        keyMap.put("SN","•••⚊•");
        keyMap.put("SOS","•••⚊⚊⚊•••");
        keyMap.put("BK","⚊•••⚊•⚊");
        keyMap.put("CL","⚊•⚊••⚊••");
    }
}
