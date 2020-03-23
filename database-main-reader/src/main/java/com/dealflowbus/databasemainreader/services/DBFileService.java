package com.dealflowbus.databasemainreader.services;

import com.dealflowbus.databasemainreader.exceptions.FileStorageException;
import com.dealflowbus.databasemainreader.exceptions.MyFileNotFoundException;
import com.dealflowbus.databasemainreader.models.DBFile;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.UploadFileResponse;
import com.dealflowbus.databasemainreader.repository.DBFileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
public class DBFileService {


	private final DBFileRepository dbFileRepo;
	private final DBLeadService dBLeadService;


	public DBFileService(DBFileRepository dbFileRepo,
			DBLeadService dBLeadService) {
		this.dbFileRepo = dbFileRepo;
		this.dBLeadService = dBLeadService;
	}

	//uploading new file to lead
	public UploadFileResponse storeFile(MultipartFile file, int id) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		DBFile dbFile;

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
			
			dbFileRepo.save(dbFile);
			
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
		}
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(dbFile.getId())
                .toUriString();
		
		Lead lead = dBLeadService.retrieveLead(id);
		lead.addFile(dbFile);
		dBLeadService.saveLead(lead);
		
		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
		
	}
	
	//retrieving file by fileId
	public ResponseEntity<ByteArrayResource> getFile(String fileId) {
		
		DBFile file = dbFileRepo.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File " + fileId + " does not exist"));
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
	}
	
	//deleting file by fileId
	public String deleteFile(String fileId) {
		
		DBFile file = dbFileRepo.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File " + fileId + " does not exist"));
		
		dbFileRepo.delete(file);
		
		return "file: " + file.getFileName() + " was deleted";

	}
}
