package com.example.sara.ghafarian;
import java.util.Comparator;

public class sortingNameAce implements Comparator<Contact> {

    public int compare(Contact contact1, Contact contact2) {
            if (contact1.getName().compareTo(contact2.getName())!=0){
                return contact1.getName().compareTo(contact2.getName());
            } else{
                return contact1.getPhoneNumber().compareTo(contact2.getPhoneNumber());
            }
    }

}