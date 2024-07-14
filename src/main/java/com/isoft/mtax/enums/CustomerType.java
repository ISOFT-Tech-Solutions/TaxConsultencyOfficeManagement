package com.isoft.mtax.enums;

public enum CustomerType {
    GST_CUSTOMER,
    TDS_CUSTOMER;

    @Override
    public String toString() {
        switch (this){
            case GST_CUSTOMER -> {
                return  "Gst Customer";

            }
            case TDS_CUSTOMER -> {
                return "Tds Customer";
            }
            default -> throw new IllegalArgumentException();
        }

    }
}
