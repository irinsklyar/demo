package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class WordPoiService {

    private final PersonRepository personRepository;

    public WordPoiService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public byte[] handleSimpleDoc() throws Exception {

        try (XWPFDocument document = new XWPFDocument();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        ) {

            createTitle(document);

            createSubtitle(document);

            createImage(document);

            createSectionTitle(document);

            createParagraph(document);

            document.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public void createParagraph(XWPFDocument document) {
        for (Person person : personRepository.findAll()) {
            XWPFParagraph para1 = document.createParagraph();
            para1.setAlignment(ParagraphAlignment.BOTH);
            XWPFRun para1Run = para1.createRun();
            para1Run.setText(person.getId() + " " + person.getFamilyName() + " " + person.getName());

        }
    }

    public void createSectionTitle(XWPFDocument document) {
        XWPFParagraph sectionTitle = document.createParagraph();
        XWPFRun sectionTRun = sectionTitle.createRun();
        sectionTRun.setText("From DB to Word");
        sectionTRun.setColor("00CC44");
        sectionTRun.setBold(true);
        sectionTRun.setFontFamily("Courier");
    }

    public void createImage(XWPFDocument document) throws URISyntaxException, InvalidFormatException, IOException {
        XWPFParagraph image = document.createParagraph();
        image.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun imageRun = image.createRun();
        imageRun.setTextPosition(20);
        File logo = new File(WordPoiService.class.getClassLoader().getResource("1.png").toURI());
        imageRun.addPicture(new FileInputStream(logo), XWPFDocument.PICTURE_TYPE_PNG, logo.getName(), Units.toEMU(50), Units.toEMU(50));
    }

    public void createSubtitle(XWPFDocument document) {
        XWPFParagraph subTitle = document.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("Family and Name");
        subTitleRun.setColor("00CC44");
        subTitleRun.setFontFamily("Courier");
        subTitleRun.setFontSize(16);
        subTitleRun.setTextPosition(20);
        subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
    }

    public void createTitle(XWPFDocument document) {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Persons");
        titleRun.setColor("009933");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);
    }

}
