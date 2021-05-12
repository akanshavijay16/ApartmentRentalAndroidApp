package com.example.project;

public class ListItem {
    private String name;
    private String description;
    private String imageUrl;
    private String _id;
    private String imageUrl2;
    private String broker_id;
    private String broker_no;
    private String rent;
    private String available;
    private String bhk;
    private String sdate;
    private String brokerage;
    private String securityd;
    private String sqft;
    private String feature;
    private String brokername;

    public ListItem(String name, String description, String imageUrl,String _id, String imageUrl2, String broker_id, String broker_no, String rent, String available, String bhk, String sdate, String brokerage, String securityd, String sqft, String feature, String brokername) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this._id = _id;
        this.imageUrl2 = imageUrl2;
        this.broker_id = broker_id;
        this.broker_no = broker_no;
        this.rent = rent;
        this.available = available;
        this.bhk = bhk;
        this.sdate = sdate;
        this.brokerage = brokerage;
        this.securityd = securityd;
        this.sqft = sqft;
        this.feature = feature;
        this.brokername = brokername;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String get_id() { return _id; }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public String getBroker_id() {
        return broker_id;
    }

    public String getBroker_no() {
        return broker_no;
    }

    public String getRent() {
        return rent;
    }

    public String getAvailable() {
        return available;
    }

    public String getBhk() {
        return bhk;
    }

    public String getSdate() {
        return sdate;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public String getSecurityd() {
        return securityd;
    }

    public String getSqft() {
        return sqft;
    }

    public String getFeature() {
        return feature;
    }

    public String getBrokername() {
        return brokername;
    }
}
