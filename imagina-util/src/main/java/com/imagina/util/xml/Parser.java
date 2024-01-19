package com.imagina.util.xml;

import java.io.InputStream;
import java.io.OutputStream;

public interface Parser<E> {

	E unmarshall(Class<E> clazz, InputStream in);

	void marshall(E marshall, OutputStream out);

	String toString(E marshall);

}
