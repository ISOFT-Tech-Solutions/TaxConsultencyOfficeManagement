package com.isoft.mtax.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
    List<String> processTdsCustomerCSV(MultipartFile file);
}
