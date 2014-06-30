package com.interzonedev.hyepye.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for the {@link User} domain model. Makes sure all properties are accounted for in {@link User.Builder},
 * {@link User#equals(Object)} and {@link User#hashCode()}.
 * 
 * @author mmarkarian
 */
public class UserTest {

    @Test
    public void testNullNotEqual() {
        User user = User.newBuilder().build();

        Assert.assertNotEquals(user, null);
    }

    @Test
    public void testOtherClass() {
        User base = User.newBuilder().build();
        String other = "foo";

        Assert.assertNotEquals(base, other);
    }

    @Test
    public void testIdentityEqual() {
        User user = User.newBuilder().build();

        Assert.assertEquals(user, user);
    }

    @Test
    public void testDefault() {
        User base = User.newBuilder().build();
        User same = User.newBuilder().build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

    @Test
    public void testId() {
        Long baseId = 1L;
        Long otherId = 2L;

        User base = User.newBuilder().setId(baseId).build();
        User same = User.newBuilder().setId(baseId).build();
        User different = User.newBuilder().setId(otherId).build();

        Assert.assertEquals(baseId, base.getId());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testUsername() {
        String baseUsername = "foo";
        String otherUsername = "bar";

        User base = User.newBuilder().setUsername(baseUsername).build();
        User same = User.newBuilder().setUsername(baseUsername).build();
        User different = User.newBuilder().setUsername(otherUsername).build();

        Assert.assertEquals(baseUsername, base.getUsername());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testPasswordHash() {
        String basePasswordHash = "foo";
        String otherPasswordHash = "bar";

        User base = User.newBuilder().setPasswordHash(basePasswordHash).build();
        User same = User.newBuilder().setPasswordHash(basePasswordHash).build();
        User different = User.newBuilder().setPasswordHash(otherPasswordHash).build();

        Assert.assertEquals(basePasswordHash, base.getPasswordHash());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testPasswordSeed() {
        String basePasswordSeed = "foo";
        String otherPasswordSeed = "bar";

        User base = User.newBuilder().setPasswordSeed(basePasswordSeed).build();
        User same = User.newBuilder().setPasswordSeed(basePasswordSeed).build();
        User different = User.newBuilder().setPasswordSeed(otherPasswordSeed).build();

        Assert.assertEquals(basePasswordSeed, base.getPasswordSeed());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testEmail() {
        String baseEmail = "foo";
        String otherEmail = "bar";

        User base = User.newBuilder().setEmail(baseEmail).build();
        User same = User.newBuilder().setEmail(baseEmail).build();
        User different = User.newBuilder().setEmail(otherEmail).build();

        Assert.assertEquals(baseEmail, base.getEmail());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testFirstName() {
        String baseFirstName = "foo";
        String otherFirstName = "bar";

        User base = User.newBuilder().setFirstName(baseFirstName).build();
        User same = User.newBuilder().setFirstName(baseFirstName).build();
        User different = User.newBuilder().setFirstName(otherFirstName).build();

        Assert.assertEquals(baseFirstName, base.getFirstName());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testLastName() {
        String baseLastName = "foo";
        String otherLastName = "bar";

        User base = User.newBuilder().setLastName(baseLastName).build();
        User same = User.newBuilder().setLastName(baseLastName).build();
        User different = User.newBuilder().setLastName(otherLastName).build();

        Assert.assertEquals(baseLastName, base.getLastName());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testRole() {
        Role baseRole = Role.ADMIN;
        Role otherRole = Role.APPROVER;

        User base = User.newBuilder().setRole(baseRole).build();
        User same = User.newBuilder().setRole(baseRole).build();
        User different = User.newBuilder().setRole(otherRole).build();

        Assert.assertEquals(baseRole, base.getRole());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testActive() {
        boolean baseActive = true;
        boolean otherActive = false;

        User base = User.newBuilder().setActive(baseActive).build();
        User same = User.newBuilder().setActive(baseActive).build();
        User different = User.newBuilder().setActive(otherActive).build();

        Assert.assertEquals(baseActive, base.isActive());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testTimeCreated() {
        Date baseTimeCreated = new Date();
        Date otherTimeCreated = new Date(1L);

        User base = User.newBuilder().setTimeCreated(baseTimeCreated).build();
        User same = User.newBuilder().setTimeCreated(baseTimeCreated).build();
        User different = User.newBuilder().setTimeCreated(otherTimeCreated).build();

        Assert.assertEquals(baseTimeCreated, base.getTimeCreated());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testTimeUpdated() {
        Date baseTimeUpdated = new Date();
        Date otherTimeUpdated = new Date(1L);

        User base = User.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        User same = User.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        User different = User.newBuilder().setTimeUpdated(otherTimeUpdated).build();

        Assert.assertEquals(baseTimeUpdated, base.getTimeUpdated());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testBuildFromTemplate() {
        User base = User.newBuilder().setId(1L).setUsername("username").setPasswordHash("passwordHash")
                .setPasswordSeed("passwordSeed").setEmail("email").setFirstName("firstName").setLastName("lastName")
                .setRole(Role.ADMIN).setActive(true).setTimeCreated(new Date(1L)).setTimeUpdated(new Date(2L)).build();
        User same = User.newBuilder(base).build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

}
