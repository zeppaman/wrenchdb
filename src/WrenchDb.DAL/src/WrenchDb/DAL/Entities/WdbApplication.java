package WrenchDb.DAL.Entities;
// Generated 8-dic-2013 14.59.34 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * WdbApplication generated by hbm2java
 */
@Entity
@Table(name="wdb_application"
    ,schema="public"
    , uniqueConstraints = @UniqueConstraint(columnNames="application_name") 
)
public class WdbApplication  implements java.io.Serializable {


     private long applicationId;
     private WdbServertype wdbServertype;
     private WdbDatabasetype wdbDatabasetype;
     private String applicationName;
     private String applicationHostname;
     private String applicationServerUri;
     private String serverUser;
     private String serverPassword;
     private String databaseJdbc;
     private String databaseUsername;
     private String databasePassword;
     private Set<WdbSettings> wdbSettingses = new HashSet<WdbSettings>(0);
     private Set<WdbChangescript> wdbChangescripts = new HashSet<WdbChangescript>(0);
     private Set<WdbSitemap> wdbSitemaps = new HashSet<WdbSitemap>(0);
     private Set<WdbEntity> wdbEntities = new HashSet<WdbEntity>(0);
     private Set<WdbRelease> wdbReleases = new HashSet<WdbRelease>(0);

    public WdbApplication() {
    }

	
    public WdbApplication(long applicationId, WdbServertype wdbServertype, WdbDatabasetype wdbDatabasetype, String applicationName) {
        this.applicationId = applicationId;
        this.wdbServertype = wdbServertype;
        this.wdbDatabasetype = wdbDatabasetype;
        this.applicationName = applicationName;
    }
    public WdbApplication(long applicationId, WdbServertype wdbServertype, WdbDatabasetype wdbDatabasetype, String applicationName, String applicationHostname, String applicationServerUri, String serverUser, String serverPassword, String databaseJdbc, String databaseUsername, String databasePassword, Set<WdbSettings> wdbSettingses, Set<WdbChangescript> wdbChangescripts, Set<WdbSitemap> wdbSitemaps, Set<WdbEntity> wdbEntities, Set<WdbRelease> wdbReleases) {
       this.applicationId = applicationId;
       this.wdbServertype = wdbServertype;
       this.wdbDatabasetype = wdbDatabasetype;
       this.applicationName = applicationName;
       this.applicationHostname = applicationHostname;
       this.applicationServerUri = applicationServerUri;
       this.serverUser = serverUser;
       this.serverPassword = serverPassword;
       this.databaseJdbc = databaseJdbc;
       this.databaseUsername = databaseUsername;
       this.databasePassword = databasePassword;
       this.wdbSettingses = wdbSettingses;
       this.wdbChangescripts = wdbChangescripts;
       this.wdbSitemaps = wdbSitemaps;
       this.wdbEntities = wdbEntities;
       this.wdbReleases = wdbReleases;
    }
   
     @Id 
          @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_id", unique=true, nullable=false)
    public long getApplicationId() {
        return this.applicationId;
    }
    
    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="servertype_id", nullable=false)
    public WdbServertype getWdbServertype() {
        return this.wdbServertype;
    }
    
    public void setWdbServertype(WdbServertype wdbServertype) {
        this.wdbServertype = wdbServertype;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="databasetype_id", nullable=false)
    public WdbDatabasetype getWdbDatabasetype() {
        return this.wdbDatabasetype;
    }
    
    public void setWdbDatabasetype(WdbDatabasetype wdbDatabasetype) {
        this.wdbDatabasetype = wdbDatabasetype;
    }
    
    @Column(name="application_name", unique=true, nullable=false, length=200)
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    @Column(name="application_hostname", length=200)
    public String getApplicationHostname() {
        return this.applicationHostname;
    }
    
    public void setApplicationHostname(String applicationHostname) {
        this.applicationHostname = applicationHostname;
    }
    
    @Column(name="application_server_uri", length=1000)
    public String getApplicationServerUri() {
        return this.applicationServerUri;
    }
    
    public void setApplicationServerUri(String applicationServerUri) {
        this.applicationServerUri = applicationServerUri;
    }
    
    @Column(name="server_user", length=200)
    public String getServerUser() {
        return this.serverUser;
    }
    
    public void setServerUser(String serverUser) {
        this.serverUser = serverUser;
    }
    
    @Column(name="server_password", length=200)
    public String getServerPassword() {
        return this.serverPassword;
    }
    
    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }
    
    @Column(name="database_jdbc", length=400)
    public String getDatabaseJdbc() {
        return this.databaseJdbc;
    }
    
    public void setDatabaseJdbc(String databaseJdbc) {
        this.databaseJdbc = databaseJdbc;
    }
    
    @Column(name="database_username", length=300)
    public String getDatabaseUsername() {
        return this.databaseUsername;
    }
    
    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }
    
    @Column(name="database_password", length=300)
    public String getDatabasePassword() {
        return this.databasePassword;
    }
    
    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbApplication")
    public Set<WdbSettings> getWdbSettingses() {
        return this.wdbSettingses;
    }
    
    public void setWdbSettingses(Set<WdbSettings> wdbSettingses) {
        this.wdbSettingses = wdbSettingses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbApplication")
    public Set<WdbChangescript> getWdbChangescripts() {
        return this.wdbChangescripts;
    }
    
    public void setWdbChangescripts(Set<WdbChangescript> wdbChangescripts) {
        this.wdbChangescripts = wdbChangescripts;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbApplication")
    public Set<WdbSitemap> getWdbSitemaps() {
        return this.wdbSitemaps;
    }
    
    public void setWdbSitemaps(Set<WdbSitemap> wdbSitemaps) {
        this.wdbSitemaps = wdbSitemaps;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbApplication")
    public Set<WdbEntity> getWdbEntities() {
        return this.wdbEntities;
    }
    
    public void setWdbEntities(Set<WdbEntity> wdbEntities) {
        this.wdbEntities = wdbEntities;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbApplication")
    public Set<WdbRelease> getWdbReleases() {
        return this.wdbReleases;
    }
    
    public void setWdbReleases(Set<WdbRelease> wdbReleases) {
        this.wdbReleases = wdbReleases;
    }




}


