package com.example.sara.ghafarian;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends Activity {


    private static Contact contact = null;
    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;
    private RadioGroup typeText;
    private  Button addButton;
    private int size;
   
//onCreate******************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameText = (EditText) findViewById(R.id.nameEditText);
        phoneText = (EditText) findViewById(R.id.numberEditText);
        emailText = (EditText) findViewById(R.id.emailEditText);
        typeText = (RadioGroup) findViewById(R.id.typeGroup);
       addButton = (Button) findViewById(R.id.addButton);
        size = getIntent().getExtras().getInt("contactsNumber") + 1;

//setOnClickListener***************************
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!nameText.getText().toString().equals("")) && (!phoneText.getText().toString().equals(""))) {
                    if (typeText.getCheckedRadioButtonId() == R.id.friendRadioButton) {
                        contact = new Contact(nameText.getText().toString(),  phoneText.getText().toString(),
                                emailText.getText().toString(), R.drawable.friend);
                        }
                    
                     else if (typeText.getCheckedRadioButtonId() == R.id.familyRadioButton) {
                    	 contact = new Contact(nameText.getText().toString(),  phoneText.getText().toString(),
                                 emailText.getText().toString(), R.drawable.family);
                    	 }
                      
                    else if (typeText.getCheckedRadioButtonId() == R.id.coworkerRadioButton) {
                    	contact = new Contact(nameText.getText().toString(),  phoneText.getText().toString(),
                                emailText.getText().toString(), R.drawable.coworker);
                    	}
                  
                    String toast = String.format("New contact add Successfully\nCount is: %d" , size);
                    Toast.makeText(SecondActivity.this , toast , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    intent.putExtra("newContact", contact);
                    startActivity(intent);}//end if
              //form validation*********************************
                else if ((nameText.getText().toString().equals("")) || (phoneText.getText().toString().equals(""))) {
                    Animation animation = AnimationUtils.loadAnimation(SecondActivity.this, R.anim.animwarning);
                    if ((nameText.getText().toString().equals("")) && (phoneText.getText().toString().equals(""))) {
                        addButton.startAnimation(animation);
                    } else if (nameText.getText().toString().equals("")) {
                        nameText.startAnimation(animation);
                        Toast.makeText(SecondActivity.this, "Name Is Empty!", Toast.LENGTH_SHORT).show();}
                     else {
                        phoneText.startAnimation(animation);
                        Toast.makeText(SecondActivity.this, "Number Is Empty!", Toast.LENGTH_SHORT).show();}
                   
                }//end else if
            }//end onClick
        });//end setOnClickListener

    }//end onCreate

//OptionsMenu**************************************************
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option2_menu , menu);
        return super.onCreateOptionsMenu(menu);
      }

      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          
          switch (item.getItemId()){
              case R.id.clearForm :
                          nameText.setText(" ");
                          phoneText.setText(" ");
                          emailText.setText(" ");
             break;
              }//end switch
          return super.onOptionsItemSelected(item);
      }
}//end class body
