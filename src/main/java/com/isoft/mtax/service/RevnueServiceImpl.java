package com.isoft.mtax.service;

import com.isoft.mtax.entity.GstFiling;
import com.isoft.mtax.entity.TdsFiling;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RevnueServiceImpl implements RevnueService{
    @Autowired
    private FilingService filingService;
    @Override
    public double calculateRevnueBasedOnMonth(int year, int month) {
        List<TdsFiling> tdsFilings=filingService.tdsFilingDetails();
        List<GstFiling> gstFilings=filingService.gstFilingDetails();

        double tdsRevnue=tdsFilings.stream()
                .filter(tdsFiling ->tdsFiling.getFilingDate().getYear()==year && tdsFiling.getFilingDate().getMonth()== Month.of(month))
                .mapToDouble(TdsFiling::getTdsFileCharge).sum();
        double gstRevnue=gstFilings.stream()
               .filter(gstFiling -> gstFiling.getFilingDate().getYear()==year && gstFiling.getFilingDate().getMonth()==Month.of(month))
                .mapToDouble(GstFiling::getGstFileCharge).sum();
        double totalMonthlyRevnue=gstRevnue+tdsRevnue;
        log.info("Revnue Service Imple dsRevnue "+tdsRevnue+"Gst Revnue"+gstRevnue);
        return totalMonthlyRevnue;
    }

    @Override
    public double calculateRevnueBasedOnQuarter(int year, int quarter) {
        List<TdsFiling> tdsFilings=filingService.tdsFilingDetails();
        List<GstFiling> gstFilings=filingService.gstFilingDetails();
        int startMonth = (quarter -1)*3 +1;
        int endMonth =startMonth +2;
        double tdsRevnue=tdsFilings.stream()
                .filter(tdsFiling ->tdsFiling.getFilingDate().getYear()==year && tdsFiling.getFilingDate().getMonthValue() >= startMonth && tdsFiling.getFilingDate().getMonthValue() <= endMonth )
                .mapToDouble(TdsFiling::getTdsFileCharge).sum();
        double gstRevnue=gstFilings.stream()
                .filter(gstFiling -> gstFiling.getFilingDate().getYear()==year && gstFiling.getFilingDate().getMonthValue()>=startMonth && gstFiling.getFilingDate().getMonthValue() <=endMonth)
                .mapToDouble(GstFiling::getGstFileCharge).sum();
        double quartelyRevnue=gstRevnue+tdsRevnue;
        return quartelyRevnue;
    }

    @Override
    public double calculateRevnueBasedOnYear(int year) {
        List<TdsFiling> tdsFilings=filingService.tdsFilingDetails();
        List<GstFiling> gstFilings=filingService.gstFilingDetails();
        double tdsRevnue= tdsFilings.stream().filter(tdsFiling -> tdsFiling.getFilingDate().getYear() ==year)
                .mapToDouble(TdsFiling::getTdsFileCharge)
                .sum();
        double gstRevnue =gstFilings.stream()
                .filter(gstFiling -> gstFiling.getFilingDate().getYear() == year)
                .mapToDouble(GstFiling::getGstFileCharge)
                .sum();
        return tdsRevnue+gstRevnue;
    }
}
