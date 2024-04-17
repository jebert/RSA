package com.jebert.rsa.entities.Clients.model;
import com.jebert.rsa.entities.Clients.helper.ClientType;
import com.jebert.rsa.entities.address.model.Address;

import java.util.*;

import org.apache.commons.lang3.ObjectUtils.Null;

import com.jebert.rsa.entities.Clients.helper.ClientSex;
import com.jebert.rsa.entities.user.model.User;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Client  extends User{

    private ClientType type;

    private ClientSex sex;

    private String cpf;

    private Date birthday;

    private boolean mailoffers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_addresses", joinColumns = {@JoinColumn (name = "id_user")},
            inverseJoinColumns = {@JoinColumn (name = "id_address")}
    )
    private List<Address> addresses = new ArrayList<>();

    public ClientType getType() {
        return type;
    }

    public Client(String userName, String fullName, String password, String email, Boolean accountNonExpired,
            Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, ClientType type, ClientSex sex,
            String cpf, Date birthday, boolean mailoffers) {
        super(null  ,userName, fullName, password, email, accountNonExpired, accountNonLocked, credentialsNonExpired,
                enabled);
        this.type = type;
        this.sex = sex;
        this.cpf = cpf;
        this.birthday = birthday;
        this.mailoffers = mailoffers;
    }

    public Client (){}

    public void setType(ClientType type) {
        this.type = type;
    }

    public ClientSex getSex() {
        return sex;
    }

    public void setSex(ClientSex sex) {
        this.sex = sex;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isMailoffers() {
        return mailoffers;
    }

    public void setMailoffers(boolean mailoffers) {
        this.mailoffers = mailoffers;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(Address addresses) {
        this.addresses.add(addresses);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }


    
    
}
