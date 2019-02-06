package TP4_ex1;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLOperations extends UnicastRemoteObject implements XMLOperationsI {
	
	protected XMLOperations() throws RemoteException {
		super();
	}

	public boolean pseudoExist(String pseudo) throws RemoteException, ParserConfigurationException, SAXException, IOException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("src/TP4_ex1/clients.xml"));
		final Element racine = document.getDocumentElement();
		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();
		for (int i = 0; i<nbRacineNoeuds; i++) {
		    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        Element client = (Element) racineNoeuds.item(i);
		        Element pseudoXML = (Element) client.getElementsByTagName("pseudo").item(0);
		        String pseudoXMLTxt = pseudoXML.getTextContent();
	    		//System.out.println("pseudoXML : " + pseudoXMLTxt + ", pseudo : " + pseudo);
		        if(pseudoXMLTxt.equals(pseudo)) {
		    		//System.out.println("Le pseudo " + pseudo + " est déjà utilisé");
		        	return true;
		        }
		    }
		}
		//System.out.println("Le pseudo " + pseudo + " n'est pas utilisé");
		return false;
	}
	
	public boolean userExist(String pseudo, String mdp) throws RemoteException, ParserConfigurationException, SAXException, IOException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("src/TP4_ex1/clients.xml"));
		final Element racine = document.getDocumentElement();
		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();
		for (int i = 0; i<nbRacineNoeuds; i++) {
		    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        final Element client = (Element) racineNoeuds.item(i);
		        final Element pseudoXML = (Element) client.getElementsByTagName("pseudo").item(0);
		        final Element mdpXML = (Element) client.getElementsByTagName("mpd").item(0);
		        String pseudoXMLTxt = pseudoXML.getTextContent();
		        String mdpXMLTxt = mdpXML.getTextContent();
		        if(mdpXMLTxt.equals(pseudo) && mdpXMLTxt.equals(mdp)) {
		    		//System.out.println("L'accès est refusé, le pseudo et/ou le mot de passe sont incorrects");
		        	return true;
		        }
		    }
		}
		//System.out.println("Le pseudo et le mot de passe sont corrects");
		return false;
	}
	
	public boolean addUser(String pseudo, String mdp) throws RemoteException, ParserConfigurationException, SAXException, IOException, TransformerException {
		if(userExist(pseudo, mdp) || pseudoExist(pseudo))
			return false;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("src/TP4_ex1/clients.xml"));
		final Element racine = document.getDocumentElement();
		final Element client = document.createElement("client");
		racine.appendChild(client);
		final Element newPseudo = document.createElement("pseudo");
		final Element newMdp = document.createElement("mdp");
		client.appendChild(newPseudo);
		client.appendChild(newMdp);
		newPseudo.appendChild(document.createTextNode(pseudo));
		newMdp.appendChild(document.createTextNode(mdp));
		
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    final StreamResult sortie = new StreamResult(new File("src/TP4_ex1/clients.xml"));
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "no");	
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	    transformer.transform(source, sortie);
		//System.out.println("Le client " + pseudo + " a bien été inséré");
		return true;
	}
	
	public boolean removeUser(String pseudo) throws RemoteException, ParserConfigurationException, SAXException, IOException, TransformerException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("src/TP4_ex1/clients.xml"));
		final Element racine = document.getDocumentElement();
		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();
		for (int i = 0; i<nbRacineNoeuds; i++) {
		    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        Element client = (Element) racineNoeuds.item(i);
		        Element pseudoXML = (Element) client.getElementsByTagName("pseudo").item(0);
		        String pseudoXMLTxt = pseudoXML.getTextContent();
	    		//System.out.println("pseudoXML : " + pseudoXMLTxt + ", pseudo : " + pseudo);
		        if(pseudoXMLTxt.equals(pseudo)) {
		    		racine.removeChild(client);
		    		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    	    final Transformer transformer = transformerFactory.newTransformer();
		    	    final DOMSource source = new DOMSource(document);
		    	    final StreamResult sortie = new StreamResult(new File("src/TP4_ex1/clients.xml"));
		    	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
		    	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    	    transformer.setOutputProperty(OutputKeys.STANDALONE, "no");	
		    	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    	    transformer.transform(source, sortie);
		        	return true;
		        }
		    }
		}
		return false;
	}

}