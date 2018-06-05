package com.adtec.daily.bean.demand;

public class DemandTrackKey {
    private Integer id;

    private String demandId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId == null ? null : demandId.trim();
    }
}