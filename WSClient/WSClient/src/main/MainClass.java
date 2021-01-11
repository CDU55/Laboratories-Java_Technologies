package main;

import java.util.List;

import javax.xml.ws.handler.HandlerResolver;

import webservices.Documents;
import webservices.GetDocumentsService;
import webservices.UserFile;

public class MainClass {
	public static void main(String[] args) {
		Documents service=new Documents();
		GetDocumentsService test=service.getGetDocumentsServicePort();
		System.out.println("Without parameter");
		List<UserFile> files=test.getAll(null);
		for(UserFile file:files)
		{
			System.out.println(file.getNewName());
		}
		System.out.println();
		System.out.println("With parameter 1");
		List<UserFile> files1=test.getAll(1);
		for(UserFile file:files1)
		{
			System.out.println(file.getNewName());
		}
	}

}
