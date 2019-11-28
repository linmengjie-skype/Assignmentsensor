package com.example.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textmsg,textmag1;
    TextView mainView;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg = (EditText)findViewById(R.id.editText);
        textmag1=(EditText)findViewById(R.id.editText1);
        mainView = (TextView) findViewById(R.id.mainView);



        //与DatabaseHelper.java
        SQLiteOpenHelper dbHelper = new DatabaseHelper(getApplicationContext());

        try{
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //选中 相当于select
            Cursor cursor = db.query ("DRINK",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "name = ?",
                    new String[] {"Latte"},
                    null, null,null);
            //选中第一行，如果有第一行就打出，如果没有就报错
            if (cursor.moveToFirst()) {
                mainView.setText("Latte's description is " + cursor.getString(1));
            }
        } catch(SQLiteException e) {
            mainView.setText("SQL error happened:\n" + e.toString());
        }
    }

    public void WriteBtn(View v) {
        // add-write text into file
        try {
            textmag1.getText().toString();
            //要写这个文件
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            //用这个方法写文件
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            //开始写文件
            outputWriter.write(textmsg.getText().toString());
            //关闭wf

            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadBtn(View v) {
        //reading text from file
        try {
            textmag1.getText().toString();
            //要读这个文件
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            //用这个方法读
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = ""; int charRead;

            while ((charRead = InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            mainView.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteBtn(View v) {

        // delete file
        try {
            textmag1.getText().toString();
            String dir = getFilesDir().getAbsolutePath();
            mainView.setText(dir);
            File file = new File(dir, "mytextfile.txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void WriteBtn1(View v) {
        // add-write text into file
        try {
            textmag1.getText().toString();
            File sdCard= Environment.getExternalStorageDirectory();
            File directory= new File(sdCard.getAbsolutePath()+"MyFiles");
            File file=new File(directory,"textfile.txt");
            FileOutputStream Fln = new FileOutputStream(file);
            OutputStreamWriter oWriter = new OutputStreamWriter(Fln);
            oWriter.write(textmsg.getText().toString());
            oWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadBtn1(View v) {
        //reading text from file
        try {
            textmag1.getText().toString();
            File sdCard= Environment.getExternalStorageDirectory();
            File directory= new File(sdCard.getAbsolutePath()+"MyFiles");
            File file=new File(directory,"textfile.txt");
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader isr=new InputStreamReader(fIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = ""; int charRead;

            while ((charRead = isr.read(inputBuffer))>0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            isr.close();
            mainView.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteBtn1(View v) {
        // delete file
        try {
            textmag1.getText().toString();

            File sdCard= Environment.getExternalStorageDirectory();
            File directory= new File(sdCard.getAbsolutePath()+"MyFiles");
            File file=new File(directory,"textfile.txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
