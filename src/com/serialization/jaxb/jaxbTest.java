package com.serialization.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Bogomolov Vladimir on 03.08.17.
 */
public class jaxbTest {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        String fileName = "/u/Prj/testing/StepicHomeWork/resources/xmlSamples/v7.0.contract_1773304672117000042_32082211.xml";
        String instancePath = "com.serialization.jaxb.model";
        JAXBContext jc = JAXBContext.newInstance(instancePath);
        Unmarshaller u = jc.createUnmarshaller();
        Object contract = u.unmarshal(new FileInputStream(fileName));
        System.out.println(contract);
    }
}
