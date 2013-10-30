package WrenchDb.DAL.Entities;
// Generated 29-ott-2013 7.38.52 by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * WdbSettings generated by hbm2java
 */
@Entity
@Table(name="wdb_settings"
    ,schema="public"
    , uniqueConstraints = @UniqueConstraint(columnNames={"setting_category", "application_id", "setting_key"}) 
)
public class WdbSettings  implements java.io.Serializable {


     private long settingId;
     private WdbApplication wdbApplication;
     private Serializable settingCategory;
     private Serializable settingKey;
     private String settingValue;

    public WdbSettings() {
    }

	
    public WdbSettings(long settingId, WdbApplication wdbApplication, Serializable settingCategory, Serializable settingKey) {
        this.settingId = settingId;
        this.wdbApplication = wdbApplication;
        this.settingCategory = settingCategory;
        this.settingKey = settingKey;
    }
    public WdbSettings(long settingId, WdbApplication wdbApplication, Serializable settingCategory, Serializable settingKey, String settingValue) {
       this.settingId = settingId;
       this.wdbApplication = wdbApplication;
       this.settingCategory = settingCategory;
       this.settingKey = settingKey;
       this.settingValue = settingValue;
    }
   
     @Id 
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="setting_id", unique=true, nullable=false)
    public long getSettingId() {
        return this.settingId;
    }
    
    public void setSettingId(long settingId) {
        this.settingId = settingId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="application_id", nullable=false)
    public WdbApplication getWdbApplication() {
        return this.wdbApplication;
    }
    
    public void setWdbApplication(WdbApplication wdbApplication) {
        this.wdbApplication = wdbApplication;
    }
    
    @Column(name="setting_category", nullable=false)
    public Serializable getSettingCategory() {
        return this.settingCategory;
    }
    
    public void setSettingCategory(Serializable settingCategory) {
        this.settingCategory = settingCategory;
    }
    
    @Column(name="setting_key", nullable=false)
    public Serializable getSettingKey() {
        return this.settingKey;
    }
    
    public void setSettingKey(Serializable settingKey) {
        this.settingKey = settingKey;
    }
    
    @Column(name="setting_value", length=2000)
    public String getSettingValue() {
        return this.settingValue;
    }
    
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }




}


