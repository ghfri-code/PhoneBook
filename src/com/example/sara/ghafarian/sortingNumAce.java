package com.example.sara.ghafarian;

import java.util.Comparator;

public class sortingNumAce implements Comparator<Contact> {

    public int compare(Contact contact1, Contact contact2) {
            return contact1.getPhoneNumber().compareTo(contact2.getPhoneNumber());
    }

}
