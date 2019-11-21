package utilities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdTagInfo {

@SerializedName("expiryDate")
@Expose
private String expiryDate;
@SerializedName("status")
@Expose
private String status;

public String getExpiryDate() {
return expiryDate;
}

public void setExpiryDate(String expiryDate) {
this.expiryDate = expiryDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}