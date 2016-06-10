package kab.com.retrofit2vs2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suxx on 30.05.16.
 */
public class Data {
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("transfers")
    @Expose
    private int transfers;
    @SerializedName("airline")
    @Expose
    private String airline;
    @SerializedName("flight_number")
    @Expose
    private int flightNumber;
    @SerializedName("departure_at")
    @Expose
    private String departureAt;
    @SerializedName("return_at")
    @Expose
    private String returnAt;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;

    /**
     *
     * @return
     * The origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     *
     * @param origin
     * The origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     *
     * @return
     * The destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @param destination
     * The destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     *
     * @return
     * The price
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The transfers
     */
    public int getTransfers() {
        return transfers;
    }

    /**
     *
     * @param transfers
     * The transfers
     */
    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    /**
     *
     * @return
     * The airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     *
     * @param airline
     * The airline
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     *
     * @return
     * The flightNumber
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     *
     * @param flightNumber
     * The flight_number
     */
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     *
     * @return
     * The departureAt
     */
    public String getDepartureAt() {
        return departureAt;
    }

    /**
     *
     * @param departureAt
     * The departure_at
     */
    public void setDepartureAt(String departureAt) {
        this.departureAt = departureAt;
    }

    /**
     *
     * @return
     * The returnAt
     */
    public String getReturnAt() {
        return returnAt;
    }

    /**
     *
     * @param returnAt
     * The return_at
     */
    public void setReturnAt(String returnAt) {
        this.returnAt = returnAt;
    }

    /**
     *
     * @return
     * The expiresAt
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     *
     * @param expiresAt
     * The expires_at
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }



}
