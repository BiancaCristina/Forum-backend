package com.github.biancacristina.Forum.domain.enums;

public enum Profile {
    ADMIN(1,"ROLE_ADMIN"),
    USER(2,"ROLE_USER");

    private int cod;
    private String name;

    Profile(int cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public static Profile toEnum(Integer cod) {
        // Convert a code to a Profile

        if (cod == null) return null;

        for(Profile i: Profile.values())
        {
            if (cod.equals(i.getCod())) return i;
        }

        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
