package com.stackroute.muzix.exceptions;

public class TrackNotFoundException extends Exception{
    private String message;

    //default constructor
    public TrackNotFoundException()
    {}
    public TrackNotFoundException(String message)
    {
        super(message);
        this.message=message;
    }
}
