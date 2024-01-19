package com.imagina.util.xml.jibx;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;

import com.imagina.util.xml.Parser;

public class JiBXParser<E> implements Parser<E> {

	private static Logger	logger	= LoggerFactory.getLogger(JiBXParser.class);

	@SuppressWarnings("unchecked")
	public E unmarshall(Class<E> clazz, InputStream in) {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(clazz);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			return (E) uctx.unmarshalDocument(in, null);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public void marshall(E marshall, OutputStream out) {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setOutput(out, null);
			mctx.marshalDocument(marshall);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public String toString(E marshall) {
		String document = null;
		try {
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setOutput(bufferOut, null);
			mctx.marshalDocument(marshall);
			document = bufferOut.toString();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return document;
	}

}
