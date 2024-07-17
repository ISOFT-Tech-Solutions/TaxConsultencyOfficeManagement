package com.isoft.mtax.service;

public interface RevnueService {
    double calculateRevnueBasedOnMonth(int year, int month);

    double calculateRevnueBasedOnQuarter(int year, int quarter);

    double calculateRevnueBasedOnYear(int year);
}
