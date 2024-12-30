package com.testrigor;

import java.io.IOException;
import java.util.Collection;

public class Main {
	/**
	 * Please fix the code. Check the comments on the methods called. Sometimes making the code runnable doesn't fix all the issues.
	 */
	public static void main(String[] args) throws IOException {
		Collection<String> peopleNames = Result.collectUniquePeopleNamesFromApiResponse();
		System.out.println(peopleNames);
	}
}