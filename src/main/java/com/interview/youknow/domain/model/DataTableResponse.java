package com.interview.youknow.domain.model;

public class DataTableResponse<E> {
    private int recordsTotal;
    private int recordsFiltered;
    private E aaData;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public E getData() {
        return aaData;
    }

    public void setData(E aaData) {
        this.aaData = aaData;
    }

    public DataTableResponse(int recordsTotal, int recordsFiltered, E aaData) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.aaData = aaData;
    }

    public DataTableResponse(E aaData) {
        this.recordsTotal = 0;
        this.aaData = aaData; }
}
