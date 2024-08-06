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

public class DataModelNote extends SQLiteOpenHelper {
    
	protected static final int DATABASE_VERSION = 1;
	protected static final String DATABASE_NAME = "db";
	protected static final String TABALE_NOTE = "notes";
	protected static final String KEY_ID = "id";
	protected static final String KEY_NOTE = "note";
	protected static final String KEY_FK = "contact_id";
//*********************************************
	public DataModelNote(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
		}
//************************************************
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE " + TABALE_NOTE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_NOTE + " TEXT, " + KEY_FK +  " INTEGER);");}
//*************************************************
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
		 db.execSQL("DROP TABLE " + TABALE_NOTE);
	        this.onCreate(db);
	}
//***********************************************
	public void addNote(Note note){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NOTE,note.getNotetext());
		values.put(KEY_FK, note.getIdcont());
		db.insert(TABALE_NOTE, null, values);
		Log.d("values",values.toString());
		db.close();
		
	}
//*************************************************
	public List<Note> getAllNotes(int idcontact){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABALE_NOTE+" WHERE "+KEY_FK+" = "+idcontact, null);
		List<Note> notes = new ArrayList<Note>();
		
		if(cursor.moveToFirst()){
			do{
			//	Note n = new Note();
				String Notee = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
				// int idcontactt = cursor.getInt(cursor.getColumnIndex(KEY_ID));
				/*n.setIdnote(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				n.setNotetext(cursor.getString(cursor.getColumnIndex(KEY_NOTE)));
				n.setIdcont(cursor.getInt(cursor.getColumnIndex(KEY_FK)));*/
				Note note= new Note(Notee);
				notes.add(note);
				
	             
			}while(cursor.moveToNext());
			
		}
		return notes;
	}

	           

	     

}