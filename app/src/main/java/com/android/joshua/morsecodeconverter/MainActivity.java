package com.android.joshua.morsecodeconverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView topMsg,swapMsg;
    String mMorseCode, mMessage;
    EditText msg, morse;
    Button dot,dah,space,sound,clear,swap,clear2,clearall;
    Animation an;
    String act="";
    ImageButton copy;
    final HashMap<String, String> mkeyMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer[] mdit = {MediaPlayer.create(getApplicationContext(), R.raw.dit)};
        final MediaPlayer[] mdah = {MediaPlayer.create(getApplicationContext(), R.raw.dah)};
        setItem();
        setAplhaToCode(mkeyMap);
        msg.setEnabled(false);


        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morse.setText(morse.getText().toString()+"•");
                mMorseCode = getMorseCode();
                mMessage = getMessage(mMorseCode, mkeyMap);
                msg.setText("");
                msg.setText(msg.getText().toString()+mMessage.toLowerCase());

            }
        });

        dah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morse.setText(morse.getText().toString()+"⚊");
                mMorseCode = getMorseCode();
                mMessage = getMessage(mMorseCode, mkeyMap);
                msg.setText("");
                msg.setText(msg.getText().toString()+mMessage.toLowerCase());

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
                clear();
            }
        });

        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
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

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap.startAnimation(an);
                if(act.equals("1")){
                    act="2";
                    topMsg.setText("Morse Code to English");
                    morse.setFocusable(false);
                    morse.setHint("Morse Code");
                    msg.setHint("Message");
                    clear2.setVisibility(View.INVISIBLE);
                    swapMsg.setText("Text to Morse Code");
                    space.setVisibility(View.VISIBLE);
                    dot.setVisibility(View.VISIBLE);
                    dah.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.VISIBLE);

                }
                else{
                    act="1";
                    topMsg.setText("English to Morse Code");
                    morse.setFocusableInTouchMode(true);
                    morse.setHint("Message");
                    msg.setHint("Morse Code");
                    clear2.setVisibility(View.VISIBLE);
                    swapMsg.setText("Morse Code to Text");
                    space.setVisibility(View.INVISIBLE);
                    dot.setVisibility(View.INVISIBLE);
                    dah.setVisibility(View.INVISIBLE);
                    clear.setVisibility(View.INVISIBLE);

                    morse.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            String Message=morse.getText().toString();
                            String ConvertedMsg="";

                            for(int i=0;i<Message.length();i++){
                                ConvertedMsg=ConvertedMsg+getConvertedMsg(Message.charAt(i));
                                msg.setText(ConvertedMsg);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Toast.makeText(getApplicationContext(), morse.getText().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Message", msg.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"Message copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morse.setText("");
                msg.setText("");
            }
        });
    }

    private String getConvertedMsg(char c) {
        String message = "";


            Iterator<Map.Entry<String, String>> it = mkeyMap.entrySet().iterator();
            while (it.hasNext()) {

                Map.Entry<String, String> pair = it.next();
                if (pair.getKey().equals(Character.toUpperCase(c))) {
                    message = message + pair.getValue();
                    break;
                }
            }

        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        return message;
    }

    private void clear() {
        if(morse.getText().toString().equals("")){

        }else{
            morse.setText(morse.getText().subSequence(0,morse.length()-1));
            mMorseCode = getMorseCode();
            mMessage = getMessage(mMorseCode, mkeyMap);
            msg.setText("");
            msg.setText(msg.getText().toString()+mMessage.toLowerCase());
        }
    }

    private String getMessage(String mMorseCode, HashMap<String, String> mkeyMap) {
        String message = "";
        String[] words=mMorseCode.split(" ");
        int len=words.length; int i=0;

        do {
            Iterator<Map.Entry<String, String>> it = mkeyMap.entrySet().iterator();
            while (it.hasNext()) {

                Map.Entry<String, String> pair = it.next();
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
        swap=findViewById(R.id.swap);
        copy=findViewById(R.id.copyBtn);
        topMsg=findViewById(R.id.title);
        swapMsg=findViewById(R.id.swapMsg);
        clear2=findViewById(R.id.clear2btn);
        clearall=findViewById(R.id.clearallbtn);
        an=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
    }

    private void setAplhaToCode(HashMap<String, String> keyMap) {
        //Alphabets
        keyMap.put("a", "•⚊");
        keyMap.put("b", "⚊•••");
        keyMap.put("c", "⚊•⚊•");
        keyMap.put("d", "⚊••");
        keyMap.put("e", "•");
        keyMap.put("f", "••⚊•");
        keyMap.put("g", "⚊⚊•");
        keyMap.put("H", "••••");
        keyMap.put("i", "••");
        keyMap.put("j", "•⚊⚊⚊");
        keyMap.put("k", "⚊•⚊");
        keyMap.put("l", "•⚊••");
        keyMap.put("m", "⚊⚊");
        keyMap.put("n", "⚊•");
        keyMap.put("o", "⚊⚊⚊");
        keyMap.put("p", "•⚊⚊•");
        keyMap.put("q", "⚊⚊•⚊");
        keyMap.put("r", "•⚊•");
        keyMap.put("s", "•••");
        keyMap.put("t", "⚊");
        keyMap.put("u", "••⚊");
        keyMap.put("v", "•••⚊");
        keyMap.put("w", "•⚊⚊");
        keyMap.put("x", "⚊••⚊");
        keyMap.put("y", "⚊•⚊⚊");
        keyMap.put("z", "⚊⚊••");
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
        //Space
        keyMap.put(" "," ");
    }
}
