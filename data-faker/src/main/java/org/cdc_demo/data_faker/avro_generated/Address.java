/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.cdc_demo.data_faker.avro_generated;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** Cutomer address details */
@org.apache.avro.specific.AvroGenerated
public class Address extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4019002666201261916L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Address\",\"namespace\":\"org.cdc_demo.data_faker.avro_generated\",\"doc\":\"Cutomer address details\",\"fields\":[{\"name\":\"customer_id\",\"type\":\"long\",\"doc\":\"FK to Customers table\"},{\"name\":\"address_type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Type of address, can be: SHIPPING | BILLING\"},{\"name\":\"street_number\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Street number\"},{\"name\":\"street_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Street name\"},{\"name\":\"city\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"City\"},{\"name\":\"state\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"State\"},{\"name\":\"country\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Country\"},{\"name\":\"postcode\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Postcode\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Address> ENCODER =
      new BinaryMessageEncoder<Address>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Address> DECODER =
      new BinaryMessageDecoder<Address>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Address> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Address> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Address> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Address>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Address to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Address from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Address instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Address fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** FK to Customers table */
   private long customer_id;
  /** Type of address, can be: SHIPPING | BILLING */
   private java.lang.String address_type;
  /** Street number */
   private java.lang.String street_number;
  /** Street name */
   private java.lang.String street_name;
  /** City */
   private java.lang.String city;
  /** State */
   private java.lang.String state;
  /** Country */
   private java.lang.String country;
  /** Postcode */
   private java.lang.String postcode;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Address() {}

  /**
   * All-args constructor.
   * @param customer_id FK to Customers table
   * @param address_type Type of address, can be: SHIPPING | BILLING
   * @param street_number Street number
   * @param street_name Street name
   * @param city City
   * @param state State
   * @param country Country
   * @param postcode Postcode
   */
  public Address(java.lang.Long customer_id, java.lang.String address_type, java.lang.String street_number, java.lang.String street_name, java.lang.String city, java.lang.String state, java.lang.String country, java.lang.String postcode) {
    this.customer_id = customer_id;
    this.address_type = address_type;
    this.street_number = street_number;
    this.street_name = street_name;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postcode = postcode;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return customer_id;
    case 1: return address_type;
    case 2: return street_number;
    case 3: return street_name;
    case 4: return city;
    case 5: return state;
    case 6: return country;
    case 7: return postcode;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: customer_id = (java.lang.Long)value$; break;
    case 1: address_type = value$ != null ? value$.toString() : null; break;
    case 2: street_number = value$ != null ? value$.toString() : null; break;
    case 3: street_name = value$ != null ? value$.toString() : null; break;
    case 4: city = value$ != null ? value$.toString() : null; break;
    case 5: state = value$ != null ? value$.toString() : null; break;
    case 6: country = value$ != null ? value$.toString() : null; break;
    case 7: postcode = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'customer_id' field.
   * @return FK to Customers table
   */
  public long getCustomerId() {
    return customer_id;
  }


  /**
   * Sets the value of the 'customer_id' field.
   * FK to Customers table
   * @param value the value to set.
   */
  public void setCustomerId(long value) {
    this.customer_id = value;
  }

  /**
   * Gets the value of the 'address_type' field.
   * @return Type of address, can be: SHIPPING | BILLING
   */
  public java.lang.String getAddressType() {
    return address_type;
  }


  /**
   * Sets the value of the 'address_type' field.
   * Type of address, can be: SHIPPING | BILLING
   * @param value the value to set.
   */
  public void setAddressType(java.lang.String value) {
    this.address_type = value;
  }

  /**
   * Gets the value of the 'street_number' field.
   * @return Street number
   */
  public java.lang.String getStreetNumber() {
    return street_number;
  }


  /**
   * Sets the value of the 'street_number' field.
   * Street number
   * @param value the value to set.
   */
  public void setStreetNumber(java.lang.String value) {
    this.street_number = value;
  }

  /**
   * Gets the value of the 'street_name' field.
   * @return Street name
   */
  public java.lang.String getStreetName() {
    return street_name;
  }


  /**
   * Sets the value of the 'street_name' field.
   * Street name
   * @param value the value to set.
   */
  public void setStreetName(java.lang.String value) {
    this.street_name = value;
  }

  /**
   * Gets the value of the 'city' field.
   * @return City
   */
  public java.lang.String getCity() {
    return city;
  }


  /**
   * Sets the value of the 'city' field.
   * City
   * @param value the value to set.
   */
  public void setCity(java.lang.String value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'state' field.
   * @return State
   */
  public java.lang.String getState() {
    return state;
  }


  /**
   * Sets the value of the 'state' field.
   * State
   * @param value the value to set.
   */
  public void setState(java.lang.String value) {
    this.state = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return Country
   */
  public java.lang.String getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * Country
   * @param value the value to set.
   */
  public void setCountry(java.lang.String value) {
    this.country = value;
  }

  /**
   * Gets the value of the 'postcode' field.
   * @return Postcode
   */
  public java.lang.String getPostcode() {
    return postcode;
  }


  /**
   * Sets the value of the 'postcode' field.
   * Postcode
   * @param value the value to set.
   */
  public void setPostcode(java.lang.String value) {
    this.postcode = value;
  }

  /**
   * Creates a new Address RecordBuilder.
   * @return A new Address RecordBuilder
   */
  public static org.cdc_demo.data_faker.avro_generated.Address.Builder newBuilder() {
    return new org.cdc_demo.data_faker.avro_generated.Address.Builder();
  }

  /**
   * Creates a new Address RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Address RecordBuilder
   */
  public static org.cdc_demo.data_faker.avro_generated.Address.Builder newBuilder(org.cdc_demo.data_faker.avro_generated.Address.Builder other) {
    if (other == null) {
      return new org.cdc_demo.data_faker.avro_generated.Address.Builder();
    } else {
      return new org.cdc_demo.data_faker.avro_generated.Address.Builder(other);
    }
  }

  /**
   * Creates a new Address RecordBuilder by copying an existing Address instance.
   * @param other The existing instance to copy.
   * @return A new Address RecordBuilder
   */
  public static org.cdc_demo.data_faker.avro_generated.Address.Builder newBuilder(org.cdc_demo.data_faker.avro_generated.Address other) {
    if (other == null) {
      return new org.cdc_demo.data_faker.avro_generated.Address.Builder();
    } else {
      return new org.cdc_demo.data_faker.avro_generated.Address.Builder(other);
    }
  }

  /**
   * RecordBuilder for Address instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Address>
    implements org.apache.avro.data.RecordBuilder<Address> {

    /** FK to Customers table */
    private long customer_id;
    /** Type of address, can be: SHIPPING | BILLING */
    private java.lang.String address_type;
    /** Street number */
    private java.lang.String street_number;
    /** Street name */
    private java.lang.String street_name;
    /** City */
    private java.lang.String city;
    /** State */
    private java.lang.String state;
    /** Country */
    private java.lang.String country;
    /** Postcode */
    private java.lang.String postcode;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.cdc_demo.data_faker.avro_generated.Address.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.customer_id)) {
        this.customer_id = data().deepCopy(fields()[0].schema(), other.customer_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.address_type)) {
        this.address_type = data().deepCopy(fields()[1].schema(), other.address_type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.street_number)) {
        this.street_number = data().deepCopy(fields()[2].schema(), other.street_number);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.street_name)) {
        this.street_name = data().deepCopy(fields()[3].schema(), other.street_name);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.city)) {
        this.city = data().deepCopy(fields()[4].schema(), other.city);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.state)) {
        this.state = data().deepCopy(fields()[5].schema(), other.state);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.country)) {
        this.country = data().deepCopy(fields()[6].schema(), other.country);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.postcode)) {
        this.postcode = data().deepCopy(fields()[7].schema(), other.postcode);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing Address instance
     * @param other The existing instance to copy.
     */
    private Builder(org.cdc_demo.data_faker.avro_generated.Address other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.customer_id)) {
        this.customer_id = data().deepCopy(fields()[0].schema(), other.customer_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address_type)) {
        this.address_type = data().deepCopy(fields()[1].schema(), other.address_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.street_number)) {
        this.street_number = data().deepCopy(fields()[2].schema(), other.street_number);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.street_name)) {
        this.street_name = data().deepCopy(fields()[3].schema(), other.street_name);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.city)) {
        this.city = data().deepCopy(fields()[4].schema(), other.city);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.state)) {
        this.state = data().deepCopy(fields()[5].schema(), other.state);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.country)) {
        this.country = data().deepCopy(fields()[6].schema(), other.country);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.postcode)) {
        this.postcode = data().deepCopy(fields()[7].schema(), other.postcode);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'customer_id' field.
      * FK to Customers table
      * @return The value.
      */
    public long getCustomerId() {
      return customer_id;
    }


    /**
      * Sets the value of the 'customer_id' field.
      * FK to Customers table
      * @param value The value of 'customer_id'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setCustomerId(long value) {
      validate(fields()[0], value);
      this.customer_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'customer_id' field has been set.
      * FK to Customers table
      * @return True if the 'customer_id' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'customer_id' field.
      * FK to Customers table
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearCustomerId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'address_type' field.
      * Type of address, can be: SHIPPING | BILLING
      * @return The value.
      */
    public java.lang.String getAddressType() {
      return address_type;
    }


    /**
      * Sets the value of the 'address_type' field.
      * Type of address, can be: SHIPPING | BILLING
      * @param value The value of 'address_type'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setAddressType(java.lang.String value) {
      validate(fields()[1], value);
      this.address_type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'address_type' field has been set.
      * Type of address, can be: SHIPPING | BILLING
      * @return True if the 'address_type' field has been set, false otherwise.
      */
    public boolean hasAddressType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'address_type' field.
      * Type of address, can be: SHIPPING | BILLING
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearAddressType() {
      address_type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'street_number' field.
      * Street number
      * @return The value.
      */
    public java.lang.String getStreetNumber() {
      return street_number;
    }


    /**
      * Sets the value of the 'street_number' field.
      * Street number
      * @param value The value of 'street_number'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setStreetNumber(java.lang.String value) {
      validate(fields()[2], value);
      this.street_number = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'street_number' field has been set.
      * Street number
      * @return True if the 'street_number' field has been set, false otherwise.
      */
    public boolean hasStreetNumber() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'street_number' field.
      * Street number
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearStreetNumber() {
      street_number = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'street_name' field.
      * Street name
      * @return The value.
      */
    public java.lang.String getStreetName() {
      return street_name;
    }


    /**
      * Sets the value of the 'street_name' field.
      * Street name
      * @param value The value of 'street_name'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setStreetName(java.lang.String value) {
      validate(fields()[3], value);
      this.street_name = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'street_name' field has been set.
      * Street name
      * @return True if the 'street_name' field has been set, false otherwise.
      */
    public boolean hasStreetName() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'street_name' field.
      * Street name
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearStreetName() {
      street_name = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'city' field.
      * City
      * @return The value.
      */
    public java.lang.String getCity() {
      return city;
    }


    /**
      * Sets the value of the 'city' field.
      * City
      * @param value The value of 'city'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setCity(java.lang.String value) {
      validate(fields()[4], value);
      this.city = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * City
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'city' field.
      * City
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearCity() {
      city = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'state' field.
      * State
      * @return The value.
      */
    public java.lang.String getState() {
      return state;
    }


    /**
      * Sets the value of the 'state' field.
      * State
      * @param value The value of 'state'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setState(java.lang.String value) {
      validate(fields()[5], value);
      this.state = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'state' field has been set.
      * State
      * @return True if the 'state' field has been set, false otherwise.
      */
    public boolean hasState() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'state' field.
      * State
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearState() {
      state = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * Country
      * @return The value.
      */
    public java.lang.String getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * Country
      * @param value The value of 'country'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setCountry(java.lang.String value) {
      validate(fields()[6], value);
      this.country = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * Country
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'country' field.
      * Country
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearCountry() {
      country = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'postcode' field.
      * Postcode
      * @return The value.
      */
    public java.lang.String getPostcode() {
      return postcode;
    }


    /**
      * Sets the value of the 'postcode' field.
      * Postcode
      * @param value The value of 'postcode'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder setPostcode(java.lang.String value) {
      validate(fields()[7], value);
      this.postcode = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'postcode' field has been set.
      * Postcode
      * @return True if the 'postcode' field has been set, false otherwise.
      */
    public boolean hasPostcode() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'postcode' field.
      * Postcode
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.Address.Builder clearPostcode() {
      postcode = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Address build() {
      try {
        Address record = new Address();
        record.customer_id = fieldSetFlags()[0] ? this.customer_id : (java.lang.Long) defaultValue(fields()[0]);
        record.address_type = fieldSetFlags()[1] ? this.address_type : (java.lang.String) defaultValue(fields()[1]);
        record.street_number = fieldSetFlags()[2] ? this.street_number : (java.lang.String) defaultValue(fields()[2]);
        record.street_name = fieldSetFlags()[3] ? this.street_name : (java.lang.String) defaultValue(fields()[3]);
        record.city = fieldSetFlags()[4] ? this.city : (java.lang.String) defaultValue(fields()[4]);
        record.state = fieldSetFlags()[5] ? this.state : (java.lang.String) defaultValue(fields()[5]);
        record.country = fieldSetFlags()[6] ? this.country : (java.lang.String) defaultValue(fields()[6]);
        record.postcode = fieldSetFlags()[7] ? this.postcode : (java.lang.String) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Address>
    WRITER$ = (org.apache.avro.io.DatumWriter<Address>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Address>
    READER$ = (org.apache.avro.io.DatumReader<Address>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.customer_id);

    out.writeString(this.address_type);

    out.writeString(this.street_number);

    out.writeString(this.street_name);

    out.writeString(this.city);

    out.writeString(this.state);

    out.writeString(this.country);

    out.writeString(this.postcode);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.customer_id = in.readLong();

      this.address_type = in.readString();

      this.street_number = in.readString();

      this.street_name = in.readString();

      this.city = in.readString();

      this.state = in.readString();

      this.country = in.readString();

      this.postcode = in.readString();

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.customer_id = in.readLong();
          break;

        case 1:
          this.address_type = in.readString();
          break;

        case 2:
          this.street_number = in.readString();
          break;

        case 3:
          this.street_name = in.readString();
          break;

        case 4:
          this.city = in.readString();
          break;

        case 5:
          this.state = in.readString();
          break;

        case 6:
          this.country = in.readString();
          break;

        case 7:
          this.postcode = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










