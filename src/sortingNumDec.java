package com.example.sara.ghafarian;

import java.util.Comparator;

public class sortingNumDec implements Comparator<Contact> {

    public int compare(Contact contact1, Contact contact2) {
        return contact2.getPhoneNumber().compareTo(contact1.getPhoneNumber());
    }

}