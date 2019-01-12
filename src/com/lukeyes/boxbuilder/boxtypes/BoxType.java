package com.lukeyes.boxbuilder.boxtypes;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;

public class BoxType {

    public enum Type {
        ARDUINO,
        ARTIE_1,
        ARTIE_2,
        ARTIE_3,
        ELECTRONICS,
        FRITA,
        SABERTOOTH
    };

    public static String toString(Type boxType) {
        switch(boxType) {
            case ARDUINO:
                return "Arduino";
            case ARTIE_1:
                return "Artie 1";
            case ARTIE_2:
                return "Artie 2";
            case ARTIE_3:
                return "Artie 3";
            case ELECTRONICS:
                return "Electronics";
            case FRITA:
                return "Frita";
            case SABERTOOTH:
                return "Sabertooth";
        }

        // TODO - THROW EXCEPTION
        return null;
    }

    public static Type fromString(String s) {
        for (Type t:Type.values()) {
            if(s.equals(toString(t))) {
                return t;
            }
        }

        return null;
    }

    public static String[] stringList() {

        Type[] typeList = Type.values();

        String[] strings = new String[typeList.length];

        int i = 0;
        for (Type t:Type.values()) {
            strings[i] = toString(t);
            i++;
        }

        return strings;
    }

    public static int ordinal(Type t) {
        int i = 0;
        for(Type mainType : Type.values()) {
            if( t == mainType) {
                return i;
            }

            i++;
        }

        //TODO - throw
        return i;
    }

}
