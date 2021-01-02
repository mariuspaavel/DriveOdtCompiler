package com.mariuspaavel.driveodtcompiler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jdom.JDOMException;
import org.jopendocument.dom.ODSingleXMLDocument;

public class Cat {
	
	/*
	private static byte[] template;
	
	private static InputStream getTemplate() throws IOException {
		if (template == null) {
			
			File file = new File(Documents.getPath()+"WEB-INF/template.odt");
			template = Files.readAllBytes(file.toPath());
			System.out.println(String.format("Reading template file, length: %d bytes", template.length));
		}
		return new ByteArrayInputStream(template);
	}
	*/
	
	public static byte[] cat(InputStream... files) throws JDOMException, IOException{

		ODSingleXMLDocument output = ODSingleXMLDocument.createFromPackage(files[0]);
		for(int i = 1; i < files.length; i++) {
			output.add(ODSingleXMLDocument.createFromPackage(files[i]), false);
		}
	
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		output.saveToPackage(ostream);
		byte[] result = ostream.toByteArray();
		return result;
	}
}
