package org.example.data;

import org.example.money.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Bank {
    private String name;
    private String hashName;
    private Loan[] offers;

    public Bank(String name, String hashName, Loan[] offers) {
        this.name = name;
        this.hashName = hashName;
        this.offers = offers;
    }

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

    @Override
    public boolean equals(Object o) {
        // OFFERS OR CREDITS OF BANKS ARE NOT EVALUATED IT MAY CAUSE FUTURE PROBLEMS< BE AWARE
        if (this == o) return true;
        if (!(o instanceof Bank bank)) return false;
        return Objects.equals(getName(), bank.getName()) && Objects.equals(hashName, bank.hashName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), hashName);
        result = 31 * result + Arrays.hashCode(offers);
        return result;
    }
}
