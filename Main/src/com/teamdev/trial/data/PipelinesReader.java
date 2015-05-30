package com.teamdev.trial.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladimir Ikryanov
 */
public class PipelinesReader {

    public static Map<Integer, Pipeline> read(File dataFile) throws Exception {
        Map<Integer, Pipeline> result = new HashMap<Integer, Pipeline>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(dataFile);

        // pipelines
        NodeList pipelines = document.getElementsByTagName("pipeline");
        for (int i = 0; i < pipelines.getLength(); i++) {
            Element pipelineElement = (Element) pipelines.item(i);
            String id = pipelineElement.getAttribute("id");
            String name = pipelineElement.getAttribute("name");
            List<Phase> phases = new ArrayList<Phase>();
            NodeList phasesNodeList = pipelineElement.getElementsByTagName("phase");
            for (int j = 0; j < phasesNodeList.getLength(); j++) {
                Element phaseElement = (Element) phasesNodeList.item(j);
                String phaseName = phaseElement.getAttribute("name");
                String offsetInDays = phaseElement.getAttribute("offsetInDays");
                Action action = null;
                NodeList actionsNodeList = phaseElement.getElementsByTagName("action");
                if (actionsNodeList.getLength() != 0) {
                    Element actionElement = (Element) actionsNodeList.item(0);
                    String actionType = actionElement.getAttribute("type");
                    if ("email".equals(actionType)) {
                        String subject = actionElement.getElementsByTagName("subject").item(0).getTextContent();
                        String body = actionElement.getElementsByTagName("body").item(0).getTextContent();
                        action = new EmailAction(subject, body);
                    }
                    if ("decision".equals(actionType)) {
                        action = new DecisionAction();
                    }
                }
                phases.add(new Phase(phaseName, Integer.valueOf(offsetInDays), action));
            }
            result.put(Integer.valueOf(id), new Pipeline(name, phases));
        }
        return result;
    }
}
