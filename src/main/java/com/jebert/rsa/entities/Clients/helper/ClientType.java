package com.jebert.rsa.entities.Clients.helper;

public enum ClientType {
    PJ("Pessoa Jurídica", "pj"),
    PF("Pessoa Física", "pf"),

    private final String name;
    private final String acronym;

    private ClientType(final String name, final String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public static ClientType fromName(final String typeName) {
        for (final ClientType tp : ClientType.values()) {
            if (tp.name.equalsIgnoreCase(typeName)) {
                return tp;
            }
        }

        throw new IllegalArgumentException(typeName);
    }

    public static ClientType fromAcronym(final String acronym) {
        for (final ClientType uf : ClientType.values()) {
            if (uf.acronym.equalsIgnoreCase(acronym)) {
                return uf;
            }
        }
        throw new IllegalArgumentException(acronym);
    }


    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

}
