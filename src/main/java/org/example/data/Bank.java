package org.example.data;

import org.example.money.Loan;

public class Bank {
    private String name;
    private String hashName;
    private Loan[] offers;

    public Loan[] getPropositions() {
        return offers;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
