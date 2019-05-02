package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExcelPoiService {

    private final PersonRepository personRepository;

    public ExcelPoiService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public byte[] writeAllToExcel() throws IOException {
        List<PersonDto> personDtoList = personRepository.findAll().stream()
                .map(PersonDto::new)
                .collect(Collectors.toList());
        return writeExcel(personDtoList);

    }

    private byte[] writeExcel(List<PersonDto> personDtoList) throws IOException {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {

            Sheet sheet = createSheet(workbook);

            Row header = sheet.createRow(0);

            CellStyle headerStyle = setHeaderStyle(workbook);

            fillHeader(header, headerStyle);

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

            writePerson(personDtoList, sheet, style);

            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        }
    }

    private Sheet createSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Persons");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        return sheet;
    }

    private CellStyle setHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();

        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);
        return headerStyle;
    }

    private void fillHeader(Row header, CellStyle headerStyle) {
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Family");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);
    }

    private void writePerson(List<PersonDto> personDtoList, Sheet sheet, CellStyle style) {
        Row row;
        PersonDto personDto;
        for (int i = 0; i < personDtoList.size(); i++) {
            personDto = personDtoList.get(i);
            row = sheet.createRow(i + 2);
            Cell cell = row.createCell(0);
            cell.setCellValue(personDto.getId());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(personDto.getFamilyName());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(personDto.getName());
            cell.setCellStyle(style);
        }
    }

    public List<Person> readExcel(MultipartFile file) throws IOException {

        List<Person> personList;
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            personList = StreamSupport.stream(sheet.spliterator(), false)
                    .map(this::toPersona)
                    .collect(Collectors.toList());
            personRepository.saveAll(personList);
        }
        return personList;
    }

    private Person toPersona(Row row) {
        Person person = new Person();
        person.setName(row.getCell(2).getStringCellValue());
        person.setFamilyName(row.getCell(1).getStringCellValue());
        return person;
    }
}
