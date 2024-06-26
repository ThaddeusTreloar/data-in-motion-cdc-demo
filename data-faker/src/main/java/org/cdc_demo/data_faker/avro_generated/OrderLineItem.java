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

/** Order Line item table row */
@org.apache.avro.specific.AvroGenerated
public class OrderLineItem extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4684102670029360920L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderLineItem\",\"namespace\":\"org.cdc_demo.data_faker.avro_generated\",\"doc\":\"Order Line item table row\",\"fields\":[{\"name\":\"product_id\",\"type\":\"long\",\"doc\":\"FK to Products table\"},{\"name\":\"order_id\",\"type\":\"long\",\"doc\":\"FK to Address table\"},{\"name\":\"quantity\",\"type\":\"int\",\"doc\":\"Quantity of items\"},{\"name\":\"line_total\",\"type\":\"float\",\"doc\":\"Order line total price\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderLineItem> ENCODER =
      new BinaryMessageEncoder<OrderLineItem>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderLineItem> DECODER =
      new BinaryMessageDecoder<OrderLineItem>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<OrderLineItem> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<OrderLineItem> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<OrderLineItem> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderLineItem>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this OrderLineItem to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a OrderLineItem from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a OrderLineItem instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static OrderLineItem fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** FK to Products table */
   private long product_id;
  /** FK to Address table */
   private long order_id;
  /** Quantity of items */
   private int quantity;
  /** Order line total price */
   private float line_total;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderLineItem() {}

  /**
   * All-args constructor.
   * @param product_id FK to Products table
   * @param order_id FK to Address table
   * @param quantity Quantity of items
   * @param line_total Order line total price
   */
  public OrderLineItem(java.lang.Long product_id, java.lang.Long order_id, java.lang.Integer quantity, java.lang.Float line_total) {
    this.product_id = product_id;
    this.order_id = order_id;
    this.quantity = quantity;
    this.line_total = line_total;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return product_id;
    case 1: return order_id;
    case 2: return quantity;
    case 3: return line_total;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: product_id = (java.lang.Long)value$; break;
    case 1: order_id = (java.lang.Long)value$; break;
    case 2: quantity = (java.lang.Integer)value$; break;
    case 3: line_total = (java.lang.Float)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'product_id' field.
   * @return FK to Products table
   */
  public long getProductId() {
    return product_id;
  }


  /**
   * Sets the value of the 'product_id' field.
   * FK to Products table
   * @param value the value to set.
   */
  public void setProductId(long value) {
    this.product_id = value;
  }

  /**
   * Gets the value of the 'order_id' field.
   * @return FK to Address table
   */
  public long getOrderId() {
    return order_id;
  }


  /**
   * Sets the value of the 'order_id' field.
   * FK to Address table
   * @param value the value to set.
   */
  public void setOrderId(long value) {
    this.order_id = value;
  }

  /**
   * Gets the value of the 'quantity' field.
   * @return Quantity of items
   */
  public int getQuantity() {
    return quantity;
  }


  /**
   * Sets the value of the 'quantity' field.
   * Quantity of items
   * @param value the value to set.
   */
  public void setQuantity(int value) {
    this.quantity = value;
  }

  /**
   * Gets the value of the 'line_total' field.
   * @return Order line total price
   */
  public float getLineTotal() {
    return line_total;
  }


  /**
   * Sets the value of the 'line_total' field.
   * Order line total price
   * @param value the value to set.
   */
  public void setLineTotal(float value) {
    this.line_total = value;
  }

  /**
   * Creates a new OrderLineItem RecordBuilder.
   * @return A new OrderLineItem RecordBuilder
   */
  public static org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder newBuilder() {
    return new org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder();
  }

  /**
   * Creates a new OrderLineItem RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderLineItem RecordBuilder
   */
  public static org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder newBuilder(org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder other) {
    if (other == null) {
      return new org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder();
    } else {
      return new org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder(other);
    }
  }

  /**
   * Creates a new OrderLineItem RecordBuilder by copying an existing OrderLineItem instance.
   * @param other The existing instance to copy.
   * @return A new OrderLineItem RecordBuilder
   */
  public static org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder newBuilder(org.cdc_demo.data_faker.avro_generated.OrderLineItem other) {
    if (other == null) {
      return new org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder();
    } else {
      return new org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder(other);
    }
  }

  /**
   * RecordBuilder for OrderLineItem instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderLineItem>
    implements org.apache.avro.data.RecordBuilder<OrderLineItem> {

    /** FK to Products table */
    private long product_id;
    /** FK to Address table */
    private long order_id;
    /** Quantity of items */
    private int quantity;
    /** Order line total price */
    private float line_total;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.product_id)) {
        this.product_id = data().deepCopy(fields()[0].schema(), other.product_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.order_id)) {
        this.order_id = data().deepCopy(fields()[1].schema(), other.order_id);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.quantity)) {
        this.quantity = data().deepCopy(fields()[2].schema(), other.quantity);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.line_total)) {
        this.line_total = data().deepCopy(fields()[3].schema(), other.line_total);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing OrderLineItem instance
     * @param other The existing instance to copy.
     */
    private Builder(org.cdc_demo.data_faker.avro_generated.OrderLineItem other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.product_id)) {
        this.product_id = data().deepCopy(fields()[0].schema(), other.product_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.order_id)) {
        this.order_id = data().deepCopy(fields()[1].schema(), other.order_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.quantity)) {
        this.quantity = data().deepCopy(fields()[2].schema(), other.quantity);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.line_total)) {
        this.line_total = data().deepCopy(fields()[3].schema(), other.line_total);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'product_id' field.
      * FK to Products table
      * @return The value.
      */
    public long getProductId() {
      return product_id;
    }


    /**
      * Sets the value of the 'product_id' field.
      * FK to Products table
      * @param value The value of 'product_id'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder setProductId(long value) {
      validate(fields()[0], value);
      this.product_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'product_id' field has been set.
      * FK to Products table
      * @return True if the 'product_id' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'product_id' field.
      * FK to Products table
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder clearProductId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'order_id' field.
      * FK to Address table
      * @return The value.
      */
    public long getOrderId() {
      return order_id;
    }


    /**
      * Sets the value of the 'order_id' field.
      * FK to Address table
      * @param value The value of 'order_id'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder setOrderId(long value) {
      validate(fields()[1], value);
      this.order_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'order_id' field has been set.
      * FK to Address table
      * @return True if the 'order_id' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'order_id' field.
      * FK to Address table
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder clearOrderId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'quantity' field.
      * Quantity of items
      * @return The value.
      */
    public int getQuantity() {
      return quantity;
    }


    /**
      * Sets the value of the 'quantity' field.
      * Quantity of items
      * @param value The value of 'quantity'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder setQuantity(int value) {
      validate(fields()[2], value);
      this.quantity = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'quantity' field has been set.
      * Quantity of items
      * @return True if the 'quantity' field has been set, false otherwise.
      */
    public boolean hasQuantity() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'quantity' field.
      * Quantity of items
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder clearQuantity() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'line_total' field.
      * Order line total price
      * @return The value.
      */
    public float getLineTotal() {
      return line_total;
    }


    /**
      * Sets the value of the 'line_total' field.
      * Order line total price
      * @param value The value of 'line_total'.
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder setLineTotal(float value) {
      validate(fields()[3], value);
      this.line_total = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'line_total' field has been set.
      * Order line total price
      * @return True if the 'line_total' field has been set, false otherwise.
      */
    public boolean hasLineTotal() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'line_total' field.
      * Order line total price
      * @return This builder.
      */
    public org.cdc_demo.data_faker.avro_generated.OrderLineItem.Builder clearLineTotal() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderLineItem build() {
      try {
        OrderLineItem record = new OrderLineItem();
        record.product_id = fieldSetFlags()[0] ? this.product_id : (java.lang.Long) defaultValue(fields()[0]);
        record.order_id = fieldSetFlags()[1] ? this.order_id : (java.lang.Long) defaultValue(fields()[1]);
        record.quantity = fieldSetFlags()[2] ? this.quantity : (java.lang.Integer) defaultValue(fields()[2]);
        record.line_total = fieldSetFlags()[3] ? this.line_total : (java.lang.Float) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderLineItem>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderLineItem>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderLineItem>
    READER$ = (org.apache.avro.io.DatumReader<OrderLineItem>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.product_id);

    out.writeLong(this.order_id);

    out.writeInt(this.quantity);

    out.writeFloat(this.line_total);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.product_id = in.readLong();

      this.order_id = in.readLong();

      this.quantity = in.readInt();

      this.line_total = in.readFloat();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.product_id = in.readLong();
          break;

        case 1:
          this.order_id = in.readLong();
          break;

        case 2:
          this.quantity = in.readInt();
          break;

        case 3:
          this.line_total = in.readFloat();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










