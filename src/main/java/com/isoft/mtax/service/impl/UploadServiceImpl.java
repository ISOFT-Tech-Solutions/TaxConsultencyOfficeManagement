package com.isoft.mtax.service.impl;

import com.isoft.mtax.constants.CsvConstants;
import com.isoft.mtax.dto.AddressDto;
import com.isoft.mtax.dto.TdsCustomerDto;
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
        List<String> savedTdsCustomers= validateAndCsvTdsCsvCustomer(csvData);
        return savedTdsCustomers;
    }

    private List<String> validateAndCsvTdsCsvCustomer(List<String[]> csvData) {
        if(csvData.isEmpty()){
            throw new CsvValidationException(messageSource.getMessage("file.empty",null, Locale.getDefault()));
        }
        String[] header=csvData.get(0);
        Map<String, Integer> headerMap = mapHeaders(header);
        if(!isValidTdsCsutomerCsvHeader(header,headerMap)){
            throw new CsvValidationException(messageSource.getMessage("csv.header.invalid",null,Locale.getDefault()));
        }
        List<TDSCustomer> tdsCustomers=new ArrayList<>();
        List<String> savedTdsCustomers =new ArrayList<>();
        for(int i=1;i<csvData.size();i++){
            String[] tdsRows= csvData.get(i);
            if(isValidTdsCsvRow(tdsRows,headerMap)){
                TdsCustomerDto tdsCustomerDto =new TdsCustomerDto();
                TDSCustomer tdsCustomer=new TDSCustomer();
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
