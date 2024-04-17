package com.jebert.rsa.entities.Clients.model;
import static com.ea.async.Async.await;
import com.jebert.rsa.entities.Clients.helper.ClientType;
import com.jebert.rsa.entities.address.model.Address;
import com.jebert.rsa.entities.address.model.vo.CEPVo;
import com.jebert.rsa.entities.address.service.AddressService;

import java.util.Date;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.Async;

import com.jebert.rsa.entities.Clients.helper.ClientSex;
import com.jebert.rsa.entities.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client  extends User{

    private ClientType type;

    private ClientSex sex;

    private String cpf;

    private Date birthday;

    private boolean mailoffers;

    private Address address;

    public ClientType getType() {
        return type;
    }

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

    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address addres) {
        this.address = addres;
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
