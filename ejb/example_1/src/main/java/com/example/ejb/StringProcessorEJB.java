package com.example.ejb;

import javax.ejb.Stateless;
import java.util.Locale;

@Stateless
public class StringProcessorEJB {

    public String processFullName(String fullName) {
        if (fullName == null) {
            fullName = "";
        }

        String upper = fullName.toUpperCase();
        int count = fullName.replaceAll("\\s+", "").length();

        return "Hello " + upper + ", your name has " + count + " characters!";
    }
}
