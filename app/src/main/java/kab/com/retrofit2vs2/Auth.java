package kab.com.retrofit2vs2;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Auth {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private JsonObject data;
    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     *
     * @return
     * The success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The data
     */
    public JsonObject getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(JsonObject data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The error
     */
    public Object getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Object error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

