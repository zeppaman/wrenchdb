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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * WdbServertype generated by hbm2java
 */
@Entity
@Table(name="wdb_servertype"
    ,schema="public"
)
public class WdbServertype  implements java.io.Serializable {


     private int servertypeId;
     private String servertypeName;
     private String servertypeDeployername;
     private Set<WdbApplication> wdbApplications = new HashSet<WdbApplication>(0);

    public WdbServertype() {
    }

	
    public WdbServertype(int servertypeId) {
        this.servertypeId = servertypeId;
    }
    public WdbServertype(int servertypeId, String servertypeName, String servertypeDeployername, Set<WdbApplication> wdbApplications) {
       this.servertypeId = servertypeId;
       this.servertypeName = servertypeName;
       this.servertypeDeployername = servertypeDeployername;
       this.wdbApplications = wdbApplications;
    }
   
     @Id 
          @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="servertype_id", unique=true, nullable=false)
    public int getServertypeId() {
        return this.servertypeId;
    }
    
    public void setServertypeId(int servertypeId) {
        this.servertypeId = servertypeId;
    }
    
    @Column(name="servertype_name", length=300)
    public String getServertypeName() {
        return this.servertypeName;
    }
    
    public void setServertypeName(String servertypeName) {
        this.servertypeName = servertypeName;
    }
    
    @Column(name="servertype_deployername")
    public String getServertypeDeployername() {
        return this.servertypeDeployername;
    }
    
    public void setServertypeDeployername(String servertypeDeployername) {
        this.servertypeDeployername = servertypeDeployername;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="wdbServertype")
    public Set<WdbApplication> getWdbApplications() {
        return this.wdbApplications;
    }
    
    public void setWdbApplications(Set<WdbApplication> wdbApplications) {
        this.wdbApplications = wdbApplications;
    }




}


