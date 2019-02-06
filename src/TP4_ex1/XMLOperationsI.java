package TP4_ex1;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface XMLOperationsI extends Remote{
	public boolean pseudoExist(String pseudo) throws RemoteException, ParserConfigurationException, SAXException, IOException;
	public boolean userExist(String pseudo, String mdp) throws RemoteException, ParserConfigurationException, SAXException, IOException;
	public boolean addUser(String pseudo, String mdp) throws RemoteException, ParserConfigurationException, SAXException, IOException, TransformerException;
	public boolean removeUser(String pseudo) throws RemoteException, ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException;
}
