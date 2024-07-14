package com.isoft.mtax.service.impl;

import com.isoft.mtax.constants.CsvConstants;
import com.isoft.mtax.dto.AddressDto;
import com.isoft.mtax.dto.GstCustomerDto;
import com.isoft.mtax.dto.TdsCustomerDto;
import com.isoft.mtax.entity.Address;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.exception.CsvValidationException;
import com.isoft.mtax.service.CustomerService;
import com.isoft.mtax.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Service class for upload Csv file for
 * TDS Customer registration
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MessageSource messageSource;

    @Override
    public List<String> processTdsCustomerCSV(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException(messageSource.getMessage("csv.empty",null,Locale.getDefault()));
        }
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                csvData.add(fields);
            }
        }
         catch (IOException ex) {
            throw new RuntimeException(messageSource.getMessage("file.read.failure",null,Locale.getDefault())+ ex.getMessage());
        }
        List<String> savedTdsCustomers= validateAndSaveTdsCsvCustomer(csvData);
        return savedTdsCustomers;
    }

    @Override
    public List<String> processGstCustomerCsv(MultipartFile file) {
        if(file.isEmpty()){
            throw new RuntimeException(messageSource.getMessage("csv.empty",null,Locale.getDefault()));
        }
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                csvData.add(fields);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(messageSource.getMessage("file.read.failure",null,Locale.getDefault())+ ex.getMessage());
        }
        List<String> savedTdsCustomers= validateAndSaveGstCustomer(csvData);
        return List.of();
    }

    private List<String> validateAndSaveTdsCsvCustomer(List<String[]> csvData) {
        if(csvData.isEmpty()){
            throw new CsvValidationException(messageSource.getMessage("file.empty",null, Locale.getDefault()));
        }
        String[] header=csvData.get(0);
        Map<String, Integer> headerMap = mapHeaders(header);
        if(!isValidGstCsutomerCsvHeader(header,headerMap)){
            throw new CsvValidationException(messageSource.getMessage("csv.header.invalid",null,Locale.getDefault()));
        }
        List<TDSCustomer> tdsCustomers=new ArrayList<>();
        List<String> savedTdsCustomers =new ArrayList<>();
        for(int i=1;i<csvData.size();i++){
            String[] tdsRows= csvData.get(i);
            if(isValidTdsCsvRow(tdsRows,headerMap)){
                TdsCustomerDto tdsCustomerDto=new TdsCustomerDto();
                tdsCustomerDto.setCustomerName(tdsRows[headerMap.get(CsvConstants.CUSTOMERNAME)]);
                tdsCustomerDto.setPan(tdsRows[headerMap.get(CsvConstants.PAN)]);
                tdsCustomerDto.setEmail(tdsRows[headerMap.get(CsvConstants.EMAIL)]);
                tdsCustomerDto.setPhoneNo(tdsRows[headerMap.get(CsvConstants.PhoneNo)]);
                tdsCustomerDto.setMobile(tdsRows[headerMap.get(CsvConstants.MOBILE)]);
                tdsCustomerDto.setTanNumber(tdsRows[headerMap.get(CsvConstants.TANNUMBER)]);
                tdsCustomerDto.setActive(true);
                AddressDto addressDto=new AddressDto();
                addressDto.setCity(tdsRows[headerMap.get(CsvConstants.CITY)]);
                addressDto.setState(tdsRows[headerMap.get(CsvConstants.STATE)]);
                addressDto.setCountry(tdsRows[headerMap.get(CsvConstants.COUNTRY)]);
                tdsCustomerDto.setAddressDto(addressDto);
                savedTdsCustomers.add("Tds Customer :"+tdsCustomerDto.getCustomerName()+" Added.");
                customerService.saveCsvTdsCustomer(tdsCustomerDto);
            }
            else {
                throw new CsvValidationException(messageSource.getMessage("csv.row.invalid",null,Locale.getDefault()));
            }
        }
        return savedTdsCustomers;
    }

    private boolean isValidGstCsutomerCsvHeader(String[] gstRows, Map<String, Integer> headerMap) {
        if (gstRows.length != headerMap.size()) {
            return false;
        }
       /* if (gstRows[headerMap.get(CsvConstants.CUSTOMERNAME)].isEmpty() || !gstRows[headerMap.get(CsvConstants.EMAIL)].contains("@") ) {
            return false;
        }*/
        return true;
    }

    private List<String> validateAndSaveGstCustomer(List<String[]> csvData) {
        if(csvData.isEmpty()){
            throw new CsvValidationException(messageSource.getMessage("file.empty",null, Locale.getDefault()));
        }
        String[] header=csvData.get(0);
        Map<String, Integer> headerMap = mapHeaders(header);
        if(!isValidGstCsutomerCsvHeader(header,headerMap)){
            throw new CsvValidationException(messageSource.getMessage("csv.header.invalid",null,Locale.getDefault()));
        }
        List<String> savedGstCustomers =new ArrayList<>();
        for(int i=1;i<csvData.size();i++){
            String[] gstRows= csvData.get(i);
            if(isValidGstCsvRow(gstRows,headerMap)){
                GstCustomerDto gstCustomerDto=new GstCustomerDto();
                gstCustomerDto.setCustomerName(gstRows[headerMap.get(CsvConstants.CUSTOMERNAME)]);
                gstCustomerDto.setPan(gstRows[headerMap.get(CsvConstants.PAN)]);
                gstCustomerDto.setEmail(gstRows[headerMap.get(CsvConstants.EMAIL)]);
                gstCustomerDto.setPhoneNo(gstRows[headerMap.get(CsvConstants.PhoneNo)]);
                gstCustomerDto.setMobile(gstRows[headerMap.get(CsvConstants.MOBILE)]);
                gstCustomerDto.setGstinNumber(gstRows[headerMap.get(CsvConstants.GSTIN)]);
                gstCustomerDto.setActive(true);
                 AddressDto addressDto=new AddressDto();
                addressDto.setCity(gstRows[headerMap.get(CsvConstants.CITY)]);
                addressDto.setState(gstRows[headerMap.get(CsvConstants.STATE)]);
                addressDto.setCountry(gstRows[headerMap.get(CsvConstants.COUNTRY)]);
                gstCustomerDto.setAddressDto(addressDto);
                savedGstCustomers.add("GSt Customer :"+gstCustomerDto.getCustomerName()+" Added.");
                customerService.saveCsvGstCustomer(gstCustomerDto);
            }
            else {
                throw new CsvValidationException(messageSource.getMessage("csv.row.invalid",null,Locale.getDefault()));
            }
        }
        return savedGstCustomers;
    }

    private boolean isValidGstCsvRow(String[] gstRows, Map<String, Integer> headerMap) {
        if (gstRows.length != headerMap.size()) {
            return false;
        }
        if (gstRows[headerMap.get(CsvConstants.CUSTOMERNAME)].isEmpty() || !gstRows[headerMap.get(CsvConstants.EMAIL)].contains("@") ) {
            return false;
        }
        return true;
    }

    private boolean isValidTdsCsvRow(String[] tdsRows,Map<String, Integer> headerMap) {
        if (tdsRows.length != headerMap.size()) {
            return false;
        }
        if (tdsRows[headerMap.get(CsvConstants.CUSTOMERNAME)].isEmpty() || !tdsRows[headerMap.get(CsvConstants.EMAIL)].contains("@") ) {
            return false;
        }
        return true;
    }
    private Map<String, Integer> mapHeaders(String[] header) {
        Map<String, Integer> headerMap = new HashMap<>();
        for (int i = 0; i < header.length; i++) {
            headerMap.put(header[i], i);
        }
        return headerMap;
    }

    private boolean isValidTdsCsutomerCsvHeader(String[] header, Map<String, Integer> headerMap) {
        return header.length==Integer.parseInt(CsvConstants.HEADERCOUNT);
    }
}
