package com.jpatestpratics.jpatestpratics.form;

import com.jpatestpratics.jpatestpratics.domain.Order;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Lazy;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MemberForm {

    private String name;

    private String city;
    private String street;
    private String zipcode;

}
