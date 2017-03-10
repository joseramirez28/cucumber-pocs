package com.pepe.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.pepe.exception.EncryptionException;

public class HashCreator {

	private static final Logger LOG = Logger.getLogger(HashCreator.class);

	public byte[] createHash(final String text, final String algorithm) throws EncryptionException {
		MessageDigest md;
		byte[] digest = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes("UTF-8"));
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Unknown Algorithm", e);
			throw new EncryptionException();
		} catch (UnsupportedEncodingException e) {
			LOG.error("Encoding not supported", e);
			throw new EncryptionException();
		}
		return digest;
	}

	public static void main(String[] args) {
		try {
			byte[] hash = new HashCreator().createHash("Test message", "SHA-256");
			LOG.debug("HASH: " + String.valueOf(hash));
		} catch (EncryptionException e) {
			LOG.error("Something went wrong with encryption", e);
		}

	}

}
