package com.example.msnihur_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_screen extends AppCompatActivity {

    String Name;
    String Comment;
    double Charge;
    Date date;
    private String newName;
    private String newComment;
    private double newCharge;
    private String newDateString;
    private Date newDate;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        Intent subscription = getIntent();
        final Subscriptions editSub = (Subscriptions) subscription.getSerializableExtra("toEdit");
        subscription.getIntExtra("position", pos);

        Name = editSub.getName();
        Comment = editSub.getComment();
        Charge = editSub.getCharge();
        date = editSub.getDate();
        TextView nameText = (TextView) findViewById(R.id.Name);
        TextView CommentText = (TextView) findViewById(R.id.Comment);
        TextView DateText = (TextView) findViewById(R.id.Date);
        TextView ChargeText = (TextView) findViewById(R.id.Charge);

        nameText.setText(Name);
        CommentText.setText(Comment);
        ChargeText.setText(Double.toString(Charge));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = format.format(date);
        //DateText.setText(Date.toString(Date));
        DateText.setText(dateText);

        Button EditButton = (Button) findViewById(R.id.editbutton);
        Button BackButton = (Button) findViewById(R.id.exit_button);

        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText NameValue = (EditText) findViewById(R.id.editName);
                EditText DateValue = (EditText) findViewById(R.id.editDate);
                EditText ChargeValue = (EditText) findViewById(R.id.editCharge);
                EditText CommentValue = (EditText) findViewById(R.id.editComment);

                newName = NameValue.getText().toString();
                newComment = CommentValue.getText().toString();
                try {
                    newCharge = Double.parseDouble(ChargeValue.getText().toString());
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }
                newDateString = DateValue.getText().toString();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd/mm/yy");
                newDate = new Date();
                try {
                    newDate = formattedDate.parse(newDateString);
                }catch(java.text.ParseException e ){
                    e.printStackTrace();
                }
                editSub.setName(newName);
                editSub.setCharge(newCharge);
                editSub.setDate(newDate);
                editSub.setComment(newComment);

                Log.i(editSub.toString(), "here");
                Log.i(Integer.toString(pos),"YESSIRY");

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("position",pos);
                intent.putExtra("edit",editSub);
                setResult(1, intent);
                finish();

            }

        });
        /*BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("position", pos);
                Subscriptions sub = (Subscriptions) editSub;
                intent.putExtra("toEdit", sub);
                setResult(1, intent);
                finish();
            }
        });*/



    }
}