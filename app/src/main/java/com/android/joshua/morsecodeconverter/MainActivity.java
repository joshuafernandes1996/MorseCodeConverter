package com.android.joshua.morsecodeconverter;

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

public class MainActivity extends AppCompatActivity {
    String mMorseCode, mMessage;
    EditText msg, morse;
    Button convert,dot,dah,space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setItem();
        final HashMap<String, String> mkeyMap = new HashMap<>();
        setAplhaToCode(mkeyMap);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMorseCode = getMorseCode();
                mMessage = getMessage(mMorseCode, mkeyMap);
                if(mMessage.equals("")){mMessage="Invalid Morse Code";}
                msg.setText("");
                msg.setText(msg.getText().toString()+mMessage);
            }
        });

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
    }

    private String getMessage(String mMorseCode, HashMap<String, String> mkeyMap) {
        String message = "";
        Iterator it = mkeyMap.entrySet().iterator();
        while (it.hasNext()) {

                //Toast.makeText(getApplicationContext(),count+""+len,Toast.LENGTH_SHORT).show();
                Map.Entry pair = (Map.Entry) it.next();
                    if (pair.getValue().equals(mMorseCode)) {message =message+pair.getKey().toString(); break;}
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
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
        convert = findViewById(R.id.convertBtn);
        dot=findViewById(R.id.dotBtn);
        dah=findViewById(R.id.dahBtn);
        space=findViewById(R.id.spaceBtn);
    }

    private void setAplhaToCode(HashMap<String, String> keyMap) {
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
    }
}
