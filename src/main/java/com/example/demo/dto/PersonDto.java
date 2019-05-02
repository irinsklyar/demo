package com.example.demo.dto;


import com.example.demo.entity.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    private Long id;
    private String name;
    private String familyName;

    public PersonDto(Person person) {
        BeanUtils.copyProperties(person,this);
    }
}
