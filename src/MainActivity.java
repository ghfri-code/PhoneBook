package com.example.sara.ghafarian;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    private static List<Contact> contacts = new ArrayList<Contact>();
     private ListView contactList;
    private MyAdapter adapter;
    private MyAdapterNote adapternote;
    public  static DataModel dm;
    public  static DataModelNote dmn;
   
//onCreate*************************** 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button formButton = (Button) findViewById(R.id.formButton);
        contactList = (ListView) findViewById(R.id.contactListView);
        dm = new DataModel(this);
        dmn = new DataModelNote(this);
        adapter = new MyAdapter(MainActivity.this,dm.getAllContacts());
        contactList.setAdapter(adapter);
        registerForContextMenu(contactList);
        if(getIntent().getExtras() != null){
            contacts = dm.getAllContacts();
            Contact contact = (Contact) getIntent().getExtras().get("newContact");
            dm.addContact(contact);
            contacts = dm.getAllContacts();
           // adapter = new MyAdapter(MainActivity.this, contacts);
            
           // contactList.setAdapter(adapter);
            sortlistview();}
        
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this , SecondActivity.class);
                intent.putExtra("contactsNumber",dm.getContactsCount());
                startActivity(intent); }//end onClick
        });//end setOnClickListener
 }//onCreate
    protected void onResume() {
        super.onResume();
        contacts = new DataModel(this).getAllContacts();
        sortList();
        sortlistview();

    }
//onCreateOptionsMenu*************************************************
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.option1_menu , menu);
      return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.clearList  :
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Are you sure to Delete list?");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	dm.removeAll();
                        contacts=dm.getAllContacts();
                        adapter = new MyAdapter(MainActivity.this, contacts);
                        contactList.setAdapter(adapter); 
                        } });
                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder1.create().show();
                break;
            case R.id.settings:
                Intent intent= new Intent(MainActivity.this,MyPreferences.class);
                startActivity(intent);
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
               String choice = settings.getString("orders" , "0" );
               sortList();
                       
                         /*   if(choice.equals("1")){
                            	//contacts=dm.getAllContacts();
                            	//adapter = new MyAdapter(MainActivity.this, contacts);
                            	Collections.sort(contacts, new sortingnameA());
                               // contactList.setAdapter(adapter = new MyAdapter(MainActivity.this, contacts));
                            	}
                            
                            if(choice.equals("2")){
                               Collections.sort(contacts, new sortingnameB());
                              //  adapter.notifyDataSetChanged();
                             //   contactList.setAdapter(adapter);
                               }
                            
                             if(choice.equals("3")){
                            //	 contacts=dm.getAllContacts();
                         	//adapter = new MyAdapter(MainActivity.this, contacts);
                         	Collections.sort(contacts, new sortingphoneA());
                            // contactList.setAdapter(adapter = new MyAdapter(MainActivity.this, contacts));
                         	}
                             
                             if(choice.equals("4")){
                          //   contacts=dm.getAllContacts();
                         //	adapter = new MyAdapter(MainActivity.this, contacts);
                         	Collections.sort(contacts, new sorting());
                          //   contactList.setAdapter(adapter = new MyAdapter(MainActivity.this, contacts));
                         	}
                             adapter = new MyAdapter(MainActivity.this, contacts);
                             contactList.setAdapter(adapter = new MyAdapter(MainActivity.this, contacts));
                             */  break;
            case R.id.exit:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setMessage("Are you sure to exit?");
                builder2.setCancelable(false);
                builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // MainActivity.this.finish();
                        moveTaskToBack(true);
                        
                    }
                });
                builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder2.create().show();
                break;}//end switch
        return super.onOptionsItemSelected(item);
    }

//ContextMenu******************************
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
        }

@Override
public boolean onContextItemSelected(MenuItem item) {
	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
	final int index = (info.position);
 //edit one contact******************
 

   if(item.getItemId()==R.id.edit) {	
   final Dialog editDialog = new Dialog(MainActivity.this);
   editDialog.setTitle("Edit Form");

   final LayoutInflater inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
   View editView = inflator.inflate(R.layout.edit_layout,null,false);
   final EditText txtName = (EditText) editView.findViewById(R.id.editname);
    final EditText txtPhone = (EditText) editView.findViewById(R.id.editnum);
   final EditText txtEmail = (EditText) editView.findViewById(R.id.editemail);
   final RadioGroup rdgRelation = (RadioGroup) editView.findViewById(R.id.radioGroup1);
   Button btnEdit = (Button) editView.findViewById(R.id.editbutton);
   sortList();
   final Contact contact = sortList().get(index);

  txtEmail.setText(contact.getEmail());
  txtName.setText(contact.getName());
  txtPhone.setText(contact.getPhoneNumber());

  if (contact.getImage()==R.drawable.friend)
   rdgRelation.check(R.id.radio0);

  else if (contact.getImage()==R.drawable.family)
   rdgRelation.check(R.id.radio1);
  else if (contact.getImage()==R.drawable.coworker)
   rdgRelation.check(R.id.radio2);


  btnEdit.setOnClickListener(new View.OnClickListener() {
@Override
  public void onClick(View view) {
  int pic = 0;
  if( rdgRelation.getCheckedRadioButtonId()==R.id.radio0)
        pic = R.drawable.friend;
  else if (rdgRelation.getCheckedRadioButtonId()== R.id.radio1)
        pic = R.drawable.family;
        
  else if( rdgRelation.getCheckedRadioButtonId()==R.id.radio2)
        pic = R.drawable.coworker;
       

  dm.editContact(contact.getId(),txtName.getText().toString(),txtPhone.getText().toString() ,txtEmail.getText().toString(),pic);
  contacts = dm.getAllContacts();
//adapter = new MyAdapter(MainActivity.this,contacts);
//contactList.setAdapter(adapter);
  sortlistview();
  Toast.makeText(MainActivity.this, "Edit successfully..",
        Toast.LENGTH_LONG).show();
  editDialog.cancel();
}
});

  editDialog.setContentView(editView);
  editDialog.show();


}
				
//delete one  contact*************
   if(item.getItemId()==R.id.remove){
  	  AlertDialog.Builder builder3= new AlertDialog.Builder(MainActivity.this);
        builder3.setMessage("Are you sure to Remove this contact?");
        builder3.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
           
            	sortList();
                            Contact c = sortList().get(index);
                             dm.removeContact(c.getId());
                             Toast.makeText(MainActivity.this, "Removed successfully..",
                            	        Toast.LENGTH_LONG).show();
          	  
          	 contacts=dm.getAllContacts();
          	  // adapter = new MyAdapter(MainActivity.this, contacts);
             //  contactList.setAdapter(adapter);
          	  sortList();
          	sortlistview();}
        });
        builder3.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }});
        builder3.create().show();
}
//add a note***********************
if(item.getItemId()==R.id.addnote){

	Builder addNote = new AlertDialog.Builder(MainActivity.this);
    LayoutInflater inflater2 = MainActivity.this.getLayoutInflater();
    View dialog2View = inflater2.inflate(R.layout.addnote_layout, null);
    addNote.setView(dialog2View);
    addNote.setTitle("Add Note");
   final EditText noteEditText = (EditText) dialog2View.findViewById(R.id.txtnot);
   
    Button addButton = (Button) dialog2View.findViewById(R.id.addnot);
    Log.d("edittext", noteEditText.getText().toString());
    final AlertDialog addAlertDialog = addNote.create();
    addButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	sortList();
        	
        	Contact con =sortList().get(index);
        	Note not=new Note(con.getId(), noteEditText.getText().toString());
        	Log.d("note",not.getNotetext());
            dmn.addNote( not );
               
                
        	 Toast.makeText(MainActivity.this, "note add successfully", Toast.LENGTH_SHORT).show();
        	 addAlertDialog.cancel();
        }
    });

    addAlertDialog.show();
    
 	}//end if menu3
if(item.getItemId()==R.id.allnote){
	 final Dialog allnoteDialog = new Dialog(MainActivity.this);
	 	final LayoutInflater infl = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	 	View allnoteView = infl.inflate(R.layout.show_notes,null,false);
	 	
	 	allnoteDialog.setTitle("ALL NOTES");
	 
	 	TextView txtNote = (TextView) allnoteView.findViewById(R.id.textViewnote);
	 	
	 	ListView lv=(ListView) allnoteView.findViewById(R.id.listViewnote);
	 	Contact cnt = sortList().get(index);
	 	List<Note> notes=dmn.getAllNotes(cnt.getId());

	               
	               if(notes.isEmpty()){
	            	   txtNote.setText("NO NOTES YET...");
	            	   }
	               else{
	             adapternote= new MyAdapterNote(MainActivity.this,notes);
	             lv.setAdapter(adapternote);
	             
	               }
	        	 allnoteDialog.cancel();
	        	 
	         
	 	allnoteDialog.setContentView(allnoteView);
	    allnoteDialog.show();
}

        return super.onContextItemSelected(item);
        
 }//end oncontext
//sort list*******************
public List<Contact> sortList(){
	SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
    String choice = settings.getString("orders" , "0" );
  
    if(choice.equals("1")){
   	Collections.sort(contacts, new sortingNameAce());
   	}
   
   if(choice.equals("2")){
      Collections.sort(contacts, new sortingNameDec());
      }
   
    if(choice.equals("3")){
	Collections.sort(contacts, new sortingNumAce());
	}
   
    if(choice.equals("4")){
	Collections.sort(contacts, new sortingNumDec());}
   return contacts; }
   
public void sortlistview(){
	contacts=sortList();
    adapter = new MyAdapter(MainActivity.this, contacts);
    contactList.setAdapter(adapter);
      }
//onBackPressed1***********************************   
private int  time_space = 2000;//2sec=2000milisec
 private long lastClickTime;//zaman akharin click ke ebtda 0 ast 
  public void onBackPressed()
  {
	  if ( System.currentTimeMillis()<lastClickTime + time_space )//check mikonim ekhtlaf bishtar az 2s nabash
       {//super.onBackPressed();
       //return;
		  //finish();
		moveTaskToBack(true);
		  
           }
	  else{
		  Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
	  }
      lastClickTime = System.currentTimeMillis(); } 
  
    }//end class body