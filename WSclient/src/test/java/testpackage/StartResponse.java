package testpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartResponse {

@SerializedName("authorizedUserId")
@Expose
private Integer authorizedUserId;
@SerializedName("idTagInfo")
@Expose
private IdTagInfo idTagInfo;
@SerializedName("transactionId")
@Expose
private Integer transactionId;

public Integer getAuthorizedUserId() {
return authorizedUserId;
}

public void setAuthorizedUserId(Integer authorizedUserId) {
this.authorizedUserId = authorizedUserId;
}

public IdTagInfo getIdTagInfo() {
return idTagInfo;
}

public void setIdTagInfo(IdTagInfo idTagInfo) {
this.idTagInfo = idTagInfo;
}

public Integer getTransactionId() {
return transactionId;
}

public void setTransactionId(Integer transactionId) {
this.transactionId = transactionId;
}



}