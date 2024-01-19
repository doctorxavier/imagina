package com.imagina.util.xml.jaxb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.util.xml.Parser;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class JAXBParser<E> implements Parser<E> {

	private static Logger	logger	= LoggerFactory.getLogger(JAXBParser.class);
	private boolean			formatted;

	public void setFormatted(boolean formatted) {
		this.formatted = formatted;
	}

	@SuppressWarnings("unchecked")
	public E unmarshall(Class<E> clazz, InputStream in) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (E) jaxbUnmarshaller.unmarshal(in);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public void marshall(E marshall, OutputStream out) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(marshall.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, this.formatted);
			jaxbMarshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {

				@Override
				public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
					writer.write(ac, i, j);
				}
			});
			jaxbMarshaller.marshal(marshall, out);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public String toString(E marshall) {
		String document = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(marshall.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, this.formatted);
			jaxbMarshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {

				@Override
				public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
					writer.write(ac, i, j);
				}
			});
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(marshall, bufferOut);
			document = bufferOut.toString();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return document;
	}

}
