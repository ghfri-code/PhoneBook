package com.example.sara.ghafarian;
import java.util.ArrayList;
import java.util.List;
import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.OpenableColumns;
import android.util.Log;

public class DataModel extends SQLiteOpenHelper {
    
	protected static final int DATABASE_VERSION = 1;
	protected static final String DATABASE_NAME = "mydb";
	protected static final String TABALE_NAME = "contacts";
	protected static final String KEY_ID = "id";
	protected static final String KEY_NAME = "name";
	protected static final String KEY_PHONE_NO = "phone_number";	
	protected static final String KEY_EMAIL = "email";
	protected static final String KEY_IMAGE = "image";
	
	
//*********************************************
	public DataModel(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
		}
//************************************************
	public void onCreate(SQLiteDatabase db) {
		Log.d("d","d");
		db.execSQL("CREATE TABLE " + TABALE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_NAME + " TEXT, " + KEY_PHONE_NO + " TEXT, "+ KEY_EMAIL + " TEXT, "+ KEY_IMAGE + " INTEGER);");        
	}
//*************************************************
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
		 db.execSQL("DROP TABLE " + TABALE_NAME);
	        this.onCreate(db);

	}
//***********************************************
	public void addContact(Contact contact){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PHONE_NO, contact.getPhoneNumber());
		values.put(KEY_EMAIL, contact.getEmail());
		values.put(KEY_IMAGE, contact.getImage());
		db.insert(TABALE_NAME, null, values);
		db.close();
	}
//******************************************************	
	public Contact getContact(int id){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABALE_NAME + " WHERE " + KEY_ID + " = ?", new String[] {String.valueOf(id)});
		Contact contact = null;
		if(cursor != null){
			cursor.moveToFirst();
			contact = new Contact(cursor.getInt(cursor.getColumnIndex(KEY_ID)), cursor.getString(cursor.getColumnIndex(KEY_NAME)),
					cursor.getString(cursor.getColumnIndex(KEY_PHONE_NO)),cursor.getString(cursor.getColumnIndex(KEY_EMAIL)),
					cursor.getInt(cursor.getColumnIndex(KEY_IMAGE)));
			return contact;			
		}else
			return contact; }	

//*************************************************
	public List<Contact> getAllContacts(){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABALE_NAME, null);
		List<Contact> contacts = new ArrayList<Contact>();
		
		if(cursor.moveToFirst()){
			do{
				Contact c = new Contact();
				c.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				c.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
				c.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NO)));
				c.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
				c.setImage(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE)));
				
				contacts.add(c);
			}while(cursor.moveToNext());
		}
		return contacts;
	}
//**************************************
	public int getContactsCount(){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABALE_NAME, null);
		return cursor.getCount();
	}
//*****************************************	
	public void editContact(int id, String name, String phoneNo,String email,int img){
			
			ContentValues cv=new ContentValues();
			cv.put(KEY_NAME,name);
			cv.put(KEY_PHONE_NO,phoneNo);
			cv.put( KEY_EMAIL,email);
			cv.put(KEY_IMAGE,img);
			SQLiteDatabase db=getWritableDatabase();
			int count=db.update(TABALE_NAME,cv,"id = "+id,null);
			 Log.i("update_posts", "" + count);
			db.close();}
		
//*******************************************
	public void removeContact(int id){
		SQLiteDatabase db = this.getWritableDatabase();
			        db.execSQL("DELETE FROM "+ TABALE_NAME + " WHERE id =  "+id);
			        db.close();
			    }

//****************************************************
public void removeAll( ){
	   SQLiteDatabase db =getWritableDatabase();
	    db.execSQL("DELETE FROM " + TABALE_NAME);
	   db.close();
	   }
//*****************************

}// end class body