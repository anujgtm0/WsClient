package utilities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StopResponse {

@SerializedName("idTagInfo")
@Expose
private IdTagInfo idTagInfo;

public IdTagInfo getIdTagInfo() {
return idTagInfo;
}

public void setIdTagInfo(IdTagInfo idTagInfo) {
this.idTagInfo = idTagInfo;
}

}