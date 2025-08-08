package com.apiweb.socialbooks.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    private String location;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
}
