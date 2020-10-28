/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.helper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.persistence.Table;

public class BaseUUIDGenerator {
	
	public static void main(String[] args) throws Exception {
		List<Class<?>> classes = BaseUUIDGenerator.getClasses("it.csi.cpass.cpassbe.ejb.entity");
		
		String sql = classes.stream()
		.filter(c -> !Modifier.isAbstract(c.getModifiers()) && !Modifier.isInterface(c.getModifiers()) && c.isAnnotationPresent(Table.class))
		.filter(c -> Arrays.stream(c.getMethods()) .anyMatch(m -> m.getName() == "getId" && UUID.class.equals(m.getReturnType())))
		.map(c -> {
			Table table = c.getAnnotation(Table.class);
			return "('" + table.name() + "', '" + generateUUIDv5FromNamespaceAndString(UUID.fromString("6ba7b812-9dad-11d1-80b4-00c04fd430c8"), table.name()).toString() + "')";
		})
		.collect(Collectors.joining(",\n\t"));
		System.out.println("INSERT INTO cpass.cpass_t_uuid_namespace (uuid_namespace_table, uuid_namespace_value)\nSELECT tmp.tab, tmp.val::uuid\nFROM (VALUES\n\t" + sql + "\n) AS tmp(tab, val)\nWHERE NOT EXISTS (\n\tSELECT 1\n\tFROM cpass.cpass_t_uuid_namespace current\n\tWHERE current.uuid_namespace_table = tmp.tab\n);");
	}

	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    return enumerationAsStream(classLoader.getResources(path))
	    	.map(URL::getFile)
	    	.map(File::new)
	    	.flatMap(directory -> findClasses(directory, packageName).stream())
	    	.collect(Collectors.toList());
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> findClasses(File directory, String packageName) {
	    if (!directory.exists()) {
	        return new ArrayList<>();
	    }
	    File[] files = directory.listFiles();
	    List<Class<?>> classes = new ArrayList<>();
		for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            try {
					classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Converted to Runtime", e);
				}
	        }
	    }
	    return classes;
	}
	
	private static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<T>() {
			@Override
			public T next() {
				return e.nextElement();
			}

			@Override
			public boolean hasNext() {
				return e.hasMoreElements();
			}
		}, Spliterator.ORDERED), false);
	}
	 
	 /**
	 * Generates an UUID from a namespace and a string
	 * @param namespace the namespace
	 * @param name the string
	 * @return the UUID
	 */
	private static UUID generateUUIDv5FromNamespaceAndString(UUID namespace, String name) {
		return generateUUIDv5FromNamespaceAndBytes(namespace, Objects.requireNonNull(name, "name == null").getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Generates an UUID from a namespace and the bytes of a string
	 * @param namespace the namespace
	 * @param name the bytes
	 * @return the UUID
	 */
	private static UUID generateUUIDv5FromNamespaceAndBytes(UUID namespace, byte[] name) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException nsae) {
			throw new InternalError("SHA-1 not supported");
		}
		md.update(toBytes(Objects.requireNonNull(namespace, "namespace is null")));
		md.update(Objects.requireNonNull(name, "name is null"));
		byte[] sha1Bytes = md.digest();
		// Clear version
		sha1Bytes[6] &= 0x0f;
		// Set to version 5
		sha1Bytes[6] |= 0x50;
		// Clear variant
		sha1Bytes[8] &= 0x3f;
		// Set to IETF variant
		sha1Bytes[8] |= 0x80;
		return fromBytes(sha1Bytes);
	}
	
	/**
	 * Transforms an UUID to its bytes
	 *
	 * @param uuid the UUID
	 * @return the bytes
	 */
	private static byte[] toBytes(UUID uuid) {
		// inverted logic of fromBytes()
		byte[] out = new byte[16];
		long msb = uuid.getMostSignificantBits();
		long lsb = uuid.getLeastSignificantBits();
		for (int i = 0; i < 8; i++) {
			out[i] = (byte) ((msb >> ((7 - i) * 8)) & 0xff);
		}
		for (int i = 8; i < 16; i++) {
			out[i] = (byte) ((lsb >> ((15 - i) * 8)) & 0xff);
		}
		return out;
	}
	
	/**
	 * Generates an UUID from bytes
	 *
	 * @param data the bytes of the UUID
	 * @return the UUID
	 */
	private static UUID fromBytes(byte[] data) {
		// Based on the private UUID(bytes[]) constructor
		long msb = 0;
		long lsb = 0;
		assert data.length >= 16;
		for (int i = 0; i < 8; i++) {
			msb = (msb << 8) | (data[i] & 0xff);
		}
		for (int i = 8; i < 16; i++) {
			lsb = (lsb << 8) | (data[i] & 0xff);
		}
		return new UUID(msb, lsb);
	}
}
