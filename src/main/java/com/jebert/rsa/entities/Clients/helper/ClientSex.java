package com.jebert.rsa.entities.Clients.helper;

public enum ClientSex {
    PJ("Masculino", "M"),
    PF("Feminino", "F"),
    NI("Não Informado", "NI");

    private final String name;
    private final String acronym;

    private ClientSex(final String name, final String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public static ClientSex fromName(final String typeName) {
        for (final ClientSex tp : ClientSex.values()) {
            if (tp.name.equalsIgnoreCase(typeName)) {
                return tp;
            }
        }

        throw new IllegalArgumentException(typeName);
    }

    public static ClientSex fromAcronym(final String acronym) {
        for (final ClientSex uf : ClientSex.values()) {
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
