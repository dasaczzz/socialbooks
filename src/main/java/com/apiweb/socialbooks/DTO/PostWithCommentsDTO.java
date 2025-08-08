package com.apiweb.socialbooks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithCommentsDTO {
    private String post;
    private String user;
    private String text;
    private Date date;
}
