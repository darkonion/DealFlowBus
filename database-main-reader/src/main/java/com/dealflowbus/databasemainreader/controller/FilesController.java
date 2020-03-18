package com.dealflowbus.databasemainreader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dealflowbus.databasemainreader.models.DBFile;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.UploadFileResponse;
import com.dealflowbus.databasemainreader.services.DBFileService;
import com.dealflowbus.databasemainreader.services.LeadRetrieveService;

@RestController
@RequestMapping("/api")
public class FilesController {

	@Autowired
	private LeadRetrieveService leadRetrieveService;
	
	@Autowired
	private DBFileService fileService;
	
	
	@PostMapping("/leads/{id}/files")
	@PreAuthorize("hasAuthority('create_lead')")
	public UploadFileResponse uploadFile(@RequestParam MultipartFile file, @PathVariable int id) {
		
		DBFile dbFile = fileService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();
		
		Lead lead = leadRetrieveService.retrieveLead(id);
		lead.addFile(dbFile);
		leadRetrieveService.saveLead(lead);
		
		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	@GetMapping("/downloadFile/{fileId}")
	@PreAuthorize("hasAuthority('read_lead')")
	public ResponseEntity downloadFile(@PathVariable String fileId) {
		
		DBFile file = fileService.getFile(fileId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));

	}
	
	@DeleteMapping("/deleteFile/{fileId}")
	@PreAuthorize("hasAuthority('delete_lead')")
	public String deleteFile(@PathVariable String fileId) {
		
		return fileService.deleteFile(fileId);

	}
}





