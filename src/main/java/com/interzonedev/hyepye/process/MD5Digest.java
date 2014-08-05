package com.interzonedev.hyepye.process;

import org.eclipse.jetty.util.security.Credential.MD5;

/**
 * Outputs the MD5 digest of the plain text password passed in from the command line.
 * 
 * @author mmarkarian
 */
public class MD5Digest {

    /**
     * Outputs to {@link System#out} the MD5 digest (using the {@link MD5#digest(String)} method) of the plain text
     * password passed in from the command line.
     * 
     * @param args The array of arguments passed in from the command line.
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: plain text password");
            System.exit(1);
        }

        String digest = org.eclipse.jetty.util.security.Credential.MD5.digest(args[0]);
        System.out.println("digest = " + digest);

    }

}
