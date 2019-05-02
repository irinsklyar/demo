package com.example.demo.controller;


import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.service.ExcelPoiService;
import com.example.demo.service.PersonService;
import com.example.demo.service.WordPoiService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final ExcelPoiService excelPoiService;
    private final WordPoiService wordPoiService;


    public PersonController(PersonService personService, ExcelPoiService excelPoiService, WordPoiService wordPoiService) {
        this.personService = personService;
        this.excelPoiService = excelPoiService;
        this.wordPoiService = wordPoiService;
    }

    @GetMapping(value = "/{id}")
    public PersonDto get(@PathVariable("id") Long id) {
        return personService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }

    @PostMapping(value = "/{id}")
    public PersonDto update(@RequestBody PersonDto personDto, @PathVariable("id") Long id) {
        return personService.update(id, personDto);
    }

    @PostMapping
    public PersonDto save(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @GetMapping
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/excelPoi")
    public ResponseEntity<Resource> download(String param) throws IOException {
        byte[] bytes = excelPoiService.writeAllToExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=excelPoi.xlsx")
                .contentLength(bytes.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(bytes));

    }

    @PostMapping(value = "/excelPoi")
    public List<Person> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return excelPoiService.readExcel(file);
    }

    @GetMapping(value = "/wordPoi")
    public ResponseEntity<Resource> downloadAndWrite(String param) throws Exception {
        byte[] bytes = wordPoiService.handleSimpleDoc();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=wordPoi.docx")
                .contentLength(bytes.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(bytes));
    }

}
