package org.hupo.psi.mi.psicquic.example;

import org.hupo.psi.mi.psicquic.wsclient.XmlPsicquicClient;
import org.hupo.psi.mi.psicquic.wsclient.result.XmlSearchResult;
import psidev.psi.mi.xml.model.Entry;
import psidev.psi.mi.xml.model.Interaction;

public class PsicquicClientXMLExample {

    public static void main(String[] args) throws Exception {

        XmlPsicquicClient xmlclient = new XmlPsicquicClient("http://www.ebi.ac.uk/Tools/webservices/psicquic/intact/webservices/psicquic");

        XmlSearchResult result = xmlclient.getByQuery("brca2", 0, 200);

        Entry entry = result.getEntrySet().getEntries().iterator().next();

        System.out.println("Total Number of Interactions for brca2: " + entry.getInteractions().size() + "\n");

        for (Interaction interaction : entry.getInteractions()) {
            System.out.println("Interaction is negative: " + interaction.isNegative());
        }

    }
}
