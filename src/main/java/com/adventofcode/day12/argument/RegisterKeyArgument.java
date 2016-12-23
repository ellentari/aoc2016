package com.adventofcode.day12.argument;

import com.adventofcode.day12.Register;

public class RegisterKeyArgument implements Argument {

    private Register register;
    private Register.Key key;

    public RegisterKeyArgument(Register.Key key, Register register) {
        this.key = key;
        this.register = register;
    }

    @Override
    public int getValue() {
        return register.get(key);
    }

    @Override
    public void setValue(int value) {
        register.set(key, value);
    }

    @Override
    public void increment() {
        register.increment(key);
    }

    @Override
    public void decrement() {
        register.decrement(key);
    }

    @Override
    public Argument copy() {
        return new RegisterKeyArgument(key, register);
    }

    @Override
    public String toString() {
        return "RegisterKeyArgument{" +
                "key=" + key +
                ", value=" + register.get(key) +
                '}';
    }
}
