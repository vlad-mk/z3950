package org.xbib.io.iso23950.v3;

import org.xbib.asn1.ASN1Any;
import org.xbib.asn1.ASN1EncodingException;
import org.xbib.asn1.ASN1Exception;
import org.xbib.asn1.BERConstructed;
import org.xbib.asn1.BEREncoding;

/**
 * Class for representing a <code>AttributesPlusTerm</code> from <code>Z39-50-APDU-1995</code>.
 * <pre>
 * AttributesPlusTerm ::=
 * [102] IMPLICIT SEQUENCE {
 *   attributes AttributeList
 *   term Term
 * }
 * </pre>
 */
public final class AttributesPlusTerm extends ASN1Any {

    public AttributeList sAttributes;

    public Term sTerm;

    /**
     * Default constructor for a AttributesPlusTerm.
     */

    public AttributesPlusTerm() {
    }

    /**
     * Constructor for a AttributesPlusTerm from a BER encoding.
     *
     * @param ber       the BER encoding.
     * @param checkTag will check tag if true, use false
     *                  if the BER has been implicitly tagged. You should
     *                  usually be passing true.
     * @throws ASN1Exception if the BER encoding is bad.
     */

    public AttributesPlusTerm(BEREncoding ber, boolean checkTag)
            throws ASN1Exception {
        super(ber, checkTag);
    }

    /**
     * Initializing object from a BER encoding.
     * This method is for internal use only. You should use
     * the constructor that takes a BEREncoding.
     *
     * @param ber       the BER to decode.
     * @param checkTag if the tag should be checked.
     * @throws ASN1Exception if the BER encoding is bad.
     */
    public void berDecode(BEREncoding ber, boolean checkTag) throws ASN1Exception {
        if (checkTag) {
            if (ber.tagGet() != 102 ||
                    ber.tagTypeGet() != BEREncoding.CONTEXT_SPECIFIC_TAG) {
                throw new ASN1EncodingException
                        ("AttributesPlusTerm: bad BER: tag=" + ber.tagGet() + " expected 102\n");
            }
        }
        BERConstructed berConstructed;
        try {
            berConstructed = (BERConstructed) ber;
        } catch (ClassCastException e) {
            throw new ASN1EncodingException
                    ("AttributesPlusTerm: bad BER form\n");
        }
        int numParts = berConstructed.numberComponents();
        int part = 0;
        BEREncoding p;
        if (numParts <= part) {
            throw new ASN1Exception("AttributesPlusTerm: incomplete");
        }
        p = berConstructed.elementAt(part);
        sAttributes = new AttributeList(p, true);
        part++;
        if (numParts <= part) {
            throw new ASN1Exception("AttributesPlusTerm: incomplete");
        }
        p = berConstructed.elementAt(part);
        sTerm = new Term(p, true);
        part++;
        if (part < numParts) {
            throw new ASN1Exception("AttributesPlusTerm: bad BER: extra data " + part + "/" + numParts + " processed");
        }
    }

    /**
     * Returns a BER encoding of the AttributesPlusTerm.
     *
     * @return The BER encoding.
     * @throws ASN1Exception Invalid or cannot be encoded.
     */
    public BEREncoding berEncode() throws ASN1Exception {
        return berEncode(BEREncoding.CONTEXT_SPECIFIC_TAG, 102);
    }

    /**
     * Returns a BER encoding of AttributesPlusTerm, implicitly tagged.
     *
     * @param tagType The type of the implicit tag.
     * @param tag      The implicit tag.
     * @return The BER encoding of the object.
     * @throws ASN1Exception When invalid or cannot be encoded.
     */
    public BEREncoding berEncode(int tagType, int tag) throws ASN1Exception {
        int numFields = 2;
        BEREncoding fields[] = new BEREncoding[numFields];
        int x = 0;
        fields[x++] = sAttributes.berEncode();
        fields[x] = sTerm.berEncode();
        return new BERConstructed(tagType, tag, fields);
    }

    /**
     * Returns a new String object containing a text representing
     * of the AttributesPlusTerm.
     */
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        int outputted = 0;
        str.append("attributes ");
        str.append(sAttributes);
        outputted++;
        if (0 < outputted) {
            str.append(", ");
        }
        str.append("term ");
        str.append(sTerm);
        str.append("}");
        return str.toString();
    }
}
