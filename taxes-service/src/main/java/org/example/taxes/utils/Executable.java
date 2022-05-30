package org.example.taxes.utils;

public interface Executable<P, R>{

    R execute(P parameter);
}
