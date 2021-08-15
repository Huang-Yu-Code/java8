package com.github.codingob.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

/**
 * Xml
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class XmlDemo {
    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(XmlDemo.class.getClassLoader().getResource("xml.xml"));
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()){
                Element element = iterator.next();
                List<Attribute> attributes = element.attributes();
                for (Attribute attribute : attributes) {
                    System.out.println(attribute.getValue());
                }
                Iterator<Element> iterator1 = element.elementIterator();
                while (iterator1.hasNext()){
                    Element entityChild = iterator1.next();
                    System.out.println("节点名："+entityChild.getName()+"---节点值："+entityChild.getStringValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
