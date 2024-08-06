package com.example.sara.ghafarian;


import java.io.Serializable;

public class Contact implements Serializable {
	private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private  int image;
    private String Note;

    public Contact(int id,String name, String phoneNumber, String email, int image) {
    	setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setImage(image);
        
    }
	public Contact(String name, String phoneNumber, String email ,  int image) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setImage(image);
        
    }

    public Contact( ) {
    
    }
    public Contact( int id, String note) {
        this.id = id;
        Note = note;
    }
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
	public String toString() {
		return "[" + email + "]";
	}

	public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
   
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
}

