package com.aatif.download.filedownload.rpt;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RptFileDownload {
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));

	@GetMapping(path = "/downloadRpt")
	public ResponseEntity<Resource> download(String param) throws IOException {

		File file = new File("C:\\Users\\aatif\\Downloads\\Testing.csv");

		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
				.contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

}
