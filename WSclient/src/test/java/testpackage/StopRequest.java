package testpackage;

public class StopRequest { 
    private String idTag; 
    private String meterStop; 
    private String timestamp; 
    private String transactionId;
    private String reason;
    
  
    // Calling getters and setters 
    public String getidTag() 
    { 
        return idTag; 
    } 
  
    public void setidTag(String idTag) 
    { 
        this.idTag = idTag; 
    } 
  
    public String getmeterStop() 
    { 
        return meterStop; 
    } 
  
    public void setmeterStop(String meterStop) 
    { 
        this.meterStop = meterStop; 
    } 
  
    public String gettimestamp() 
    { 
        return timestamp; 
    } 
  
    public void setstimestamp(String timestamp) 
    { 
    	this.timestamp = timestamp; 
    } 
    
    public String gettransactionId() 
    { 
        return transactionId; 
    } 
  
    public void settransactionId(String transactionId) 
    { 
    	this.transactionId = transactionId; 
    } 
    
    public String getreason() 
    { 
        return reason; 
    } 
  
    public void setreason(String reason) 
    { 
    	this.reason = reason; 
    } 
  
    // Creating toString 
    @Override
    public String toString() 
    { 
        return "Organisation [idTag="
            + idTag 
            + ", meterStop="
            + meterStop 
            + ", timestamp="
            + timestamp + ",transactionId="
            		+transactionId+ ",reason="
            		+reason +"]"; 
    } 
} 