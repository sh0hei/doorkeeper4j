package com.github.sh0hei.doorkeeper4j;

public class DoorKeeperException extends Exception {

    public DoorKeeperException(String message) {
        super(message);
    }

    public DoorKeeperException(Throwable cause) {
        super(cause);
    }

    public DoorKeeperException(String message, Throwable cause) {
    	super(message, cause);
    }
}
