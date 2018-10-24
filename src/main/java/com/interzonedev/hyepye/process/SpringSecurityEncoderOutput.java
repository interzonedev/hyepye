package com.interzonedev.hyepye.process;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Outputs the encoded values of the plain text password passed in from the command line from the Spring Security
 * {@link BCryptPasswordEncoder} and {@link StandardPasswordEncoder} (SHA-256 by default) encoders.
 * 
 * @author mmarkarian
 */
public class SpringSecurityEncoderOutput {

    /**
     * Outputs to {@link System#out} the encoded values (using the Spring Security {@link BCryptPasswordEncoder} and
     * {@link StandardPasswordEncoder} (SHA-256 by default) encoders) of the plain text password passed in from the
     * command line. Allows an optional strength parameter for the {@link BCryptPasswordEncoder} encoder.
     *
     * @param args The array of arguments passed in from the command line. Only a single input is allowed.
     */
    public static void main(String[] args) {

        if ((args.length < 1) || (args.length > 2)) {
            System.err.println("Usage: plain text password [BCrypt strength]");
            System.exit(1);
        }

        String plainTextPassowrd = args[0];
        int strength = -1;
        if (2 == args.length) {
            try {
                strength = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                System.err.println("Usage: BCrypt strength must be an integer");
                System.exit(1);
            }
        }

        Long start = System.currentTimeMillis();

        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(strength);
        String bcryptEncoded = bcryptEncoder.encode(plainTextPassowrd);
        System.out.println("bcryptEncoded = " + bcryptEncoded);

        Long afterBCrypt = System.currentTimeMillis();

        @SuppressWarnings("deprecation")
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        String standardPasswordEncoded = standardPasswordEncoder.encode(plainTextPassowrd);
        System.out.println("standardPasswordEncoded = " + standardPasswordEncoded);

        Long afterStandard = System.currentTimeMillis();

        System.out.println("BCrypt time = " + (afterBCrypt - start) + " ms");

        System.out.println("Standard time = " + (afterStandard - afterBCrypt) + " ms");

    }

}
