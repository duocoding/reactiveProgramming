package com.reactive.function.domain;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * @ClassName PraseXMl
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/12/13 11:00
 * @Version 1.0
 */
public class PraseXMl {

    public static void main(String[] args) throws MalformedURLException, DocumentException {
        new PraseXMl().prase();
    }

    public void prase() throws MalformedURLException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("C:\\Users\\www13\\Desktop\\test.xml"));
        Element root = document.getRootElement();
        readNode(root, "");
    }

    private void readNode(Element element, String prefix) {
        if (element == null) {
            return;
        }
        List<Attribute> attributes = element.attributes();
        if (attributes != null && attributes.size() > 0) {
            System.out.println(prefix);
            for (Attribute attribute : attributes) {
                System.out.println(attribute.getValue());
            }
            System.out.println();
        }
        List<Element> childNodes = element.elements();
        prefix = "\t";
        for (Element childNode : childNodes) {
            readNode(childNode, prefix);
        }

    }
}
