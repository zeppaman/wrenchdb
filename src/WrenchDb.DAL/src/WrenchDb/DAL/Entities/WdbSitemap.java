package WrenchDb.DAL.Entities;
// Generated 27-ott-2013 17.28.51 by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * WdbSitemap generated by hbm2java
 */
@Entity
@Table(name="wdb_sitemap"
    ,schema="public"
    , uniqueConstraints = @UniqueConstraint(columnNames={"sitemap_name", "application_id"}) 
)
public class WdbSitemap  implements java.io.Serializable {


     private long sitemapId;
     private WdbApplication wdbApplication;
     private WdbSitemap wdbSitemap;
     private Serializable sitemapName;
     private long sitemapLabel;
     private String sitemapUrl;
     private String configData;
     private Set<WdbSitemap> wdbSitemaps = new HashSet<WdbSitemap>(0);

    public WdbSitemap() {
    }

	
    public WdbSitemap(long sitemapId, WdbApplication wdbApplication, Serializable sitemapName, long sitemapLabel, String sitemapUrl) {
        this.sitemapId = sitemapId;
        this.wdbApplication = wdbApplication;
        this.sitemapName = sitemapName;
        this.sitemapLabel = sitemapLabel;
        this.sitemapUrl = sitemapUrl;
    }
    public WdbSitemap(long sitemapId, WdbApplication wdbApplication, WdbSitemap wdbSitemap, Serializable sitemapName, long sitemapLabel, String sitemapUrl, String configData, Set<WdbSitemap> wdbSitemaps) {
       this.sitemapId = sitemapId;
       this.wdbApplication = wdbApplication;
       this.wdbSitemap = wdbSitemap;
       this.sitemapName = sitemapName;
       this.sitemapLabel = sitemapLabel;
       this.sitemapUrl = sitemapUrl;
       this.configData = configData;
       this.wdbSitemaps = wdbSitemaps;
    }
   
     @Id 
    
    @Column(name="sitemap_id", unique=true, nullable=false)
    public long getSitemapId() {
        return this.sitemapId;
    }
    
    public void setSitemapId(long sitemapId) {
        this.sitemapId = sitemapId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="application_id", nullable=false)
    public WdbApplication getWdbApplication() {
        return this.wdbApplication;
    }
    
    public void setWdbApplication(WdbApplication wdbApplication) {
        this.wdbApplication = wdbApplication;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_sitemap_id")
    public WdbSitemap getWdbSitemap() {
        return this.wdbSitemap;
    }
    
    public void setWdbSitemap(WdbSitemap wdbSitemap) {
        this.wdbSitemap = wdbSitemap;
    }
    
    @Column(name="sitemap_name", nullable=false)
    public Serializable getSitemapName() {
        return this.sitemapName;
    }
    
    public void setSitemapName(Serializable sitemapName) {
        this.sitemapName = sitemapName;
    }
    
    @Column(name="sitemap_label", nullable=false)
    public long getSitemapLabel() {
        return this.sitemapLabel;
    }
    
    public void setSitemapLabel(long sitemapLabel) {
        this.sitemapLabel = sitemapLabel;
    }
    
    @Column(name="sitemap_url", nullable=false, length=1000)
    public String getSitemapUrl() {
        return this.sitemapUrl;
    }
    
    public void setSitemapUrl(String sitemapUrl) {
        this.sitemapUrl = sitemapUrl;
    }
    
    @Column(name="config_data", length=2000)
    public String getConfigData() {
        return this.configData;
    }
    
    public void setConfigData(String configData) {
        this.configData = configData;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbSitemap")
    public Set<WdbSitemap> getWdbSitemaps() {
        return this.wdbSitemaps;
    }
    
    public void setWdbSitemaps(Set<WdbSitemap> wdbSitemaps) {
        this.wdbSitemaps = wdbSitemaps;
    }




}

