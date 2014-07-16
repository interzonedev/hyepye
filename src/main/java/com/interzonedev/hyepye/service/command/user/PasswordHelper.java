package com.interzonedev.hyepye.service.command.user;

import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Password generation utilities.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.passwordHelper")
public class PasswordHelper {

    /**
     * Gets a random password seed 10 characters long.
     * 
     * @return Returns a random password seed 10 characters long.
     */
    public String generatePasswordSeed() {

        Long rand = Math.round(1000000000L * Math.random()) + 1000000000L;
        return rand.toString();

    }

    /**
     * Generates a SHA-256 hash from the specified password and password seed by combining with a prefix and suffix.
     * 
     * @param password The password to hash.
     * @param passwordSeed The seed to combine with the password in the hash.
     * 
     * @return Returns a SHA-256 hash from the specified password and password seed.
     */
    public String generatePasswordHash(String password, String passwordSeed) {

        StringBuilder passwordSb = new StringBuilder();
        passwordSb.append("hyepyePrefix");
        passwordSb.append(password);
        passwordSb.append(passwordSeed);
        passwordSb.append("hyepyeSuffix");

        return DigestUtils.sha256Hex(passwordSb.toString());

    }

    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        String passwordHash1 = passwordHelper.generatePasswordHash("testpass", "1234567890");
        String passwordHash2 = passwordHelper.generatePasswordHash("newtestpass", "1234567890");
        System.out.println("passwordHash1 = " + passwordHash1);
        System.out.println("passwordHash2 = " + passwordHash2);
    }

}
