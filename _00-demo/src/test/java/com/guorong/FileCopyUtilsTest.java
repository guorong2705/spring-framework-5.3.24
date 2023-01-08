package com.guorong;

import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

public class FileCopyUtilsTest {

	@Test
	public void copyFile2File() throws IOException {
		File sourceFile = new File("E:/source.txt");
		File targetFile = new File("E:/target.txt");
		FileCopyUtils.copy(sourceFile, targetFile);
	}
}
