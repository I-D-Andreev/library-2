import java.io.*;

/**
 * Class to model the address of a person.
 *
 * @author Ivan Andreev
 */
public class Address implements Serializable {
    /**
     * The first line in the address.
     */
    private String addressLine1;

    /**
     * The second line in the address.
     * It is optional.
     */
    private String addressLine2;

    /**
     * The address' city.
     */
    private String city;

    /**
     * The country of the address.
     */
    private String country;

    /**
     * The address' postcode.
     */
    private String postcode;

    /**
     * Constructs an address object.
     *
     * @param addressLine1 The first line of the address.
     * @param city         The city.
     * @param country      The country.
     * @param postcode     The postcode.
     */
    public Address(String addressLine1, String city, String country, String postcode) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    /**
     * Constructs an address object with 2 address lines.
     *
     * @param addressLine1 The first address line.
     * @param addressLine2 The second address line.
     * @param city         The city.
     * @param country      The country.
     * @param postcode     The postcode.
     */
    public Address(String addressLine1, String addressLine2, String city, String country, String postcode) {
        this(addressLine1, city, country, postcode);
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets the first address line.
     *
     * @return addressLine1 The first line of the address.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the first address line to a new one.
     *
     * @param addressLine1 The new first address line.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets the second address line.
     *
     * @return addressLine2 The second line of the address.
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the second address line to a new one.
     *
     * @param addressLine2 The new second address line.
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets the city.
     *
     * @return city The city of the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the address' city to a new one.
     *
     * @param city The new city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the country.
     *
     * @return country The address' country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the address' country to a new one.
     *
     * @param country The new country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the postcode.
     *
     * @return postcode The address' postcode.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the address' postcode to a new one.
     *
     * @param postcode The new postcode.
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Convert the address to a string.
     *
     * @return answer The address converted to a string.
     */
    @Override
    public String toString() {
        String answer;
        if (this.addressLine2 != null && !this.addressLine2.equals("")) {
            answer = this.addressLine1 + '\n' + this.addressLine2 +
                    '\n' + this.city + '\n' + this.country + '\n' +
                    this.postcode;
        } else {
            answer = this.addressLine1 + '\n' + this.city + '\n' +
                    this.country + '\n' + this.postcode;
        }
        return answer;
    }
}
