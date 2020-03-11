package com.dealflowbus.databasemainreader.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dealflowbus.databasemainreader.exceptions.FileStorageException;
import com.dealflowbus.databasemainreader.exceptions.MyFileNotFoundException;
import com.dealflowbus.databasemainreader.models.DBFile;
import com.dealflowbus.databasemainreader.repository.DBFileRepository;

@Service
public class DBFileService {

	@Autowired
	private DBFileRepository dbFileRepo;
	
	public DBFile storeFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
			
			return dbFileRepo.save(dbFile);
			
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
		}
		
	}
	
	public DBFile getFile(String fileId) {
		
		DBFile file = dbFileRepo.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File " + fileId + " does not exist"));
		
		return file;
	}
	
	public String deleteFile(String fileId) {
		
		DBFile file = getFile(fileId);
		dbFileRepo.delete(file);
		
		return "file: " + file.getFileName() + " was deleted";

	}
}
