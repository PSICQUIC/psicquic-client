package org.hupo.psi.mi.psicquic.example;

import org.hupo.psi.mi.psicquic.wsclient.XmlPsicquicClient;
import org.hupo.psi.mi.psicquic.wsclient.result.XmlSearchResult;
import psidev.psi.mi.xml.model.Entry;
import psidev.psi.mi.xml.model.Interaction;

import java.util.Collection;

public class PsicquicClientXMLExample {

    public static void main(String[] args) throws Exception {

        XmlPsicquicClient xmlclient = new XmlPsicquicClient("http://www.ebi.ac.uk/Tools/webservices/psicquic/intact/webservices/psicquic");

        XmlSearchResult result = xmlclient.getByQuery("brca2", 0, 20);

        Collection<Interaction> interactions = result.getEntrySet().getEntries().iterator().next().getInteractions();

        System.out.println("Total Number of Interactions for brca2: " + interactions.size() + "\n");

        for (Interaction interaction : interactions) {
            System.out.println("Confidence value: " + interaction.getConfidences().iterator().next().getValue());
        }
    }
}
