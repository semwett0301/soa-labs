package com.example.isusevicemaven;

import io.spring.guides.gs_producing_web_service.Person;
import io.spring.guides.gs_producing_web_service.StudyGroup;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

public class BestMapperEver {

    public static StudyGroup from(entity.StudyGroup studyGroupEntity) throws DatatypeConfigurationException {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(studyGroupEntity.getId());
        studyGroup.setStudentsCount(studyGroupEntity.getStudentsCount());
        studyGroup.setName(studyGroupEntity.getName());
        studyGroup.setCreationDate(localDateTimeto(studyGroupEntity.getCreationDate()));

        return studyGroup;
    }

    private static XMLGregorianCalendar localDateTimeto(LocalDate localDate) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

    private static Person entityToSoap(entity.Person entity) {
        Person person = new Person();
        person.setId(entity.getId());
        person.setName(entity.getName());
        return person;
    }
}
