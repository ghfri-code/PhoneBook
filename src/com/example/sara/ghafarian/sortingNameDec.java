package com.example.sara.ghafarian;

import java.util.Comparator;

public class sortingNameDec implements Comparator<Contact> {

    public int compare(Contact contact1, Contact contact2) {
        if (contact2.getName().compareTo(contact1.getName())!=0){
            return contact2.getName().compareTo(contact1.getName());
        } else{
            return contact2.getPhoneNumber().compareTo(contact1.getPhoneNumber());
        }
    }

}
