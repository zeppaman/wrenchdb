package WrenchDb.DAL.Test;
// Generated 26-nov-2013 23.03.15 by Hibernate Tools 3.2.1.GA



/**
 * WdbPropertyType generated by hbm2java
 */
public class WdbPropertyType  implements java.io.Serializable {


     private long propertyType;
     private String propertyTypeName;
     private String propertyTypeDesc;

    public WdbPropertyType() {
    }

	
    public WdbPropertyType(long propertyType) {
        this.propertyType = propertyType;
    }
    public WdbPropertyType(long propertyType, String propertyTypeName, String propertyTypeDesc) {
       this.propertyType = propertyType;
       this.propertyTypeName = propertyTypeName;
       this.propertyTypeDesc = propertyTypeDesc;
    }
   
    public long getPropertyType() {
        return this.propertyType;
    }
    
    public void setPropertyType(long propertyType) {
        this.propertyType = propertyType;
    }
    public String getPropertyTypeName() {
        return this.propertyTypeName;
    }
    
    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }
    public String getPropertyTypeDesc() {
        return this.propertyTypeDesc;
    }
    
    public void setPropertyTypeDesc(String propertyTypeDesc) {
        this.propertyTypeDesc = propertyTypeDesc;
    }




}

