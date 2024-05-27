package com.project.bulmaze.payment;

public class ChargeRequest {
    public enum Currency {
        EUR, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;

    public ChargeRequest() {
    }

    public ChargeRequest(String description, int amount, Currency currency, String stripeEmail, String stripeToken) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.stripeEmail = stripeEmail;
        this.stripeToken = stripeToken;
    }

    public String getDescription() {
        return description;
    }

    public ChargeRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ChargeRequest setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public ChargeRequest setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public ChargeRequest setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
        return this;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public ChargeRequest setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
        return this;
    }
}
