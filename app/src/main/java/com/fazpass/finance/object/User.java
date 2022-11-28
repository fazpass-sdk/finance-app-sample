package com.fazpass.finance.object;

import androidx.annotation.NonNull;

public class User {
    private final String email;
    private final String phone;
    private final String name;
    private final String idCard;
    private final String address;
    private final String pin;
    private static boolean useFinger;

    public User(@NonNull String email, @NonNull String phone, @NonNull String name, @NonNull String idCard, @NonNull String address, @NonNull String pin) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.idCard = idCard;
        this.address = address;
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getAddress() {
        return address;
    }

    public String getPin() {
        return pin;
    }

    public static boolean isUseFinger() {
        return useFinger;
    }

    public static void setUseFinger(boolean useFinger) {
        User.useFinger = useFinger;
    }
}
