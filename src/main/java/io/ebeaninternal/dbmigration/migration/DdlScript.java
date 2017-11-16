package io.ebeaninternal.dbmigration.migration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ddlScript complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ddlScript">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ebean-orm.github.io/xml/ns/dbmigration}ddl" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="platforms" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ddlScript", namespace = "http://ebean-orm.github.io/xml/ns/dbmigration", propOrder = {
  "ddl"
})
public class DdlScript {

  @XmlElement(namespace = "http://ebean-orm.github.io/xml/ns/dbmigration")
  protected List<Ddl> ddl;
  @XmlAttribute(name = "platforms")
  protected String platforms;

  /**
   * Gets the value of the ddl property.
   * <p>
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the ddl property.
   * <p>
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getDdl().add(newItem);
   * </pre>
   * <p>
   * <p>
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Ddl }
   */
  public List<Ddl> getDdl() {
    if (ddl == null) {
      ddl = new ArrayList<>();
    }
    return this.ddl;
  }

  /**
   * Gets the value of the platforms property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getPlatforms() {
    return platforms;
  }

  /**
   * Sets the value of the platforms property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setPlatforms(String value) {
    this.platforms = value;
  }

}
