package org.xbib.io.iso23950.v3;

import org.xbib.asn1.ASN1Any;
import org.xbib.asn1.ASN1EncodingException;
import org.xbib.asn1.ASN1Exception;
import org.xbib.asn1.BERConstructed;
import org.xbib.asn1.BEREncoding;


/**
 * Class for representing a <code>PresentRequest_recordComposition</code> from <code>Z39-50-APDU-1995</code>.
 * <pre>
 * PresentRequest_recordComposition ::=
 * CHOICE {
 *   simple [19] EXPLICIT ElementSetNames
 *   complex [209] IMPLICIT CompSpec
 * }
 * </pre>
 */
public final class PresentRequestRecordComposition extends ASN1Any {

    public ElementSetNames c_simple;

    public CompSpec c_complex;

    /**
     * Default constructor for a PresentRequest_recordComposition.
     */

    public PresentRequestRecordComposition() {
    }

    /**
     * Constructor for a PresentRequest_recordComposition from a BER encoding.
     *
     * @param ber       the BER encoding.
     * @param checkTag will check tag if true, use false
     *                  if the BER has been implicitly tagged. You should
     *                  usually be passing true.
     * @throws ASN1Exception if the BER encoding is bad.
     */
    public PresentRequestRecordComposition(BEREncoding ber, boolean checkTag) throws ASN1Exception {
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
        BERConstructed tagwrapper;
        c_simple = null;
        c_complex = null;
        if (ber.tagGet() == 19 &&
                ber.tagTypeGet() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
            try {
                tagwrapper = (BERConstructed) ber;
            } catch (ClassCastException e) {
                throw new ASN1EncodingException("PresentRequest_recordComposition: bad BER form");
            }
            if (tagwrapper.numberComponents() != 1) {
                throw new ASN1EncodingException("PresentRequest_recordComposition: bad BER form");
            }
            c_simple = new ElementSetNames(tagwrapper.elementAt(0), true);
            return;
        }
        if (ber.tagGet() == 209 &&
                ber.tagTypeGet() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
            c_complex = new CompSpec(ber, false);
            return;
        }
        throw new ASN1Exception("PresentRequest_recordComposition: bad BER encoding: choice not matched");
    }

    /**
     * Returns a BER encoding of PresentRequest_recordComposition.
     *
     * @return The BER encoding.
     * @throws ASN1Exception Invalid or cannot be encoded.
     */
    public BEREncoding berEncode() throws ASN1Exception {
        BEREncoding chosen = null;
        BEREncoding[] enc;
        if (c_simple != null) {
            enc = new BEREncoding[1];
            enc[0] = c_simple.berEncode();
            chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 19, enc);
        }
        if (c_complex != null) {
            if (chosen != null) {
                throw new ASN1Exception("CHOICE multiply set");
            }
            chosen = c_complex.berEncode(BEREncoding.CONTEXT_SPECIFIC_TAG, 209);
        }
        if (chosen == null) {
            throw new ASN1Exception("CHOICE not set");
        }
        return chosen;
    }

    public BEREncoding berEncode(int tagType, int tag) throws ASN1Exception {
        throw new ASN1EncodingException("PresentRequest_recordComposition: cannot implicitly tag");
    }

    /**
     * Returns a new String object containing a text representing
     * of the PresentRequest_recordComposition.
     */
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        boolean found = false;
        if (c_simple != null) {
            found = true;
            str.append("simple ");
            str.append(c_simple);
        }
        if (c_complex != null) {
            if (found) {
                str.append("<ERROR: multiple CHOICE: complex> ");
            }
            str.append("complex ");
            str.append(c_complex);
        }
        str.append("}");
        return str.toString();
    }

}
