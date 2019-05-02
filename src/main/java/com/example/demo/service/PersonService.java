package com.example.demo.service;


import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {


    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDto save(PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        personRepository.save(person);

        return PersonDto.builder()
                .id(person.getId())
                .build();
    }

    public PersonDto get(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new PersonDto(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public PersonDto update(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(personDto, person);
        personRepository.save(person);
        return PersonDto.builder()
                .id(person.getId())
                .build();
    }

    public List<PersonDto> getAll() {
        return personRepository.findAll().stream()
                .map(PersonDto::new)
                .collect(Collectors.toList());
    }
}
