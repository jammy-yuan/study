package com.study.homework;

import java.io.IOException;
import java.io.InputStream;

public class XclassLoader extends ClassLoader {
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		InputStream in = null;
		byte[] classByte = null;
		try {
			in = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass");
			byte[] b = new byte[in.available()];
			try {
				in.read(b);
			} catch (IOException e) {
				System.out.println("Can't read inputStream to byte[]");
			}
			classByte = decodeClass(b);
		} catch (Exception e) {
			System.out.println("Can't find class: " + name);
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("Can't close inputStream");
				}
			}
		}
		System.out.println("Length of byte[]: " + classByte.length);
		return defineClass(name, classByte, 0, classByte.length);
	}
	
	private byte[] decodeClass(byte[] bytes) {
		byte[] codeByte = new byte[bytes.length];
		int i = 0;
		for (byte b : bytes) {
			codeByte[i] = (byte)(255 - b);
			i++;
		}
		return codeByte;
	}

}
