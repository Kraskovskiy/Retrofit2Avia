package kab.com.retrofit2vs2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Suxx on 31.05.2016.
 */
public class City {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *
     * @return
     * The timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     *
     * @param timeZone
     * The time_zone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }


    /**
     *
     * @return
     * The countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @param countryCode
     * The country_code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
