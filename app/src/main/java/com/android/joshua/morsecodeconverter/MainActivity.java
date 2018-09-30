package com.android.joshua.morsecodeconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String mMorseCode,mConverterWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String,String> mkeyMap=new HashMap<>();
        setAplhaToCode(mkeyMap);
    }

    private void setAplhaToCode(HashMap<String, String> keyMap) {
        keyMap.put("A",".-");
        keyMap.put("B","-...");
        keyMap.put("C","-.-.");
        keyMap.put("D","-..");
        keyMap.put("E",".");
        keyMap.put("F","..-.");
        keyMap.put("G","--.");
        keyMap.put("H","....");
        keyMap.put("I","..");
        keyMap.put("J",".---");
        keyMap.put("K","-.-");
        keyMap.put("L",".-..");
        keyMap.put("M","--");
        keyMap.put("N","-.");
        keyMap.put("O","---");
        keyMap.put("P",".--.");
        keyMap.put("Q","--.-");
        keyMap.put("R",".-.");
        keyMap.put("S","...");
        keyMap.put("T","-");
        keyMap.put("U","..-");
        keyMap.put("V","...-");
        keyMap.put("W",".--");
        keyMap.put("X","-..-");
        keyMap.put("Y","-.--");
        keyMap.put("Z","--..");
        keyMap.put("1",".----");
        keyMap.put("2","..---");
        keyMap.put("3","...--");
        keyMap.put("4","....-");
        keyMap.put("5",".....");
        keyMap.put("6","-....");
        keyMap.put("7","--...");
        keyMap.put("8","---..");
        keyMap.put("9","----.");
        keyMap.put("0","-----");
    }
}
