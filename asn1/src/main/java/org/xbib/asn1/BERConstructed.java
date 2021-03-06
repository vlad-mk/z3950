package org.xbib.asn1;

import java.io.IOException;
import java.io.OutputStream;

/**
 * BERConstructed.
 * This class represents a BER encoded ASN.1 object which is
 * constructed from component BER encodings.
 * Generally it is used to store the BER encoding of constructed types
 * (i.e. SEQUENCE, SEQUENCE OF, SET, and SET OF) The end-of-content
 * octets, if required, must be added to the end of the elements by
 * the creator.
 */
public class BERConstructed extends BEREncoding {

    private BEREncoding[] contentElements;

    /**
     * Constructor for a non-primitive BEREncoding.
     *
     * @param asn1Class The tag type.
     * @param tag       The tag number.
     * @param elements  The components making up the constructed BER.
     * @throws ASN1Exception If tag or tag type is invalid
     * @see org.xbib.asn1.BEREncoding#UNIVERSAL_TAG
     * @see org.xbib.asn1.BEREncoding#APPLICATION_TAG
     * @see org.xbib.asn1.BEREncoding#CONTEXT_SPECIFIC_TAG
     * @see org.xbib.asn1.BEREncoding#PRIVATE_TAG
     */
    public BERConstructed(int asn1Class, int tag, BEREncoding[] elements)
            throws ASN1Exception {
        int contentLength = 0;
        for (BEREncoding element : elements) {
            contentLength += element.iTotalLength;
        }
        init(asn1Class, true, tag, contentLength);
        contentElements = elements;
    }

    /**
     * This method outputs the encoded octets for this object
     * to the output stream.
     * Note: the output is not flushed, so you <strong>must</strong>  explicitly
     * flush the output stream after calling this method to ensure that
     * the data has been written out.
     *
     * @param dest OutputStream to write encoding to.
     */
    @Override
    public void output(OutputStream dest) throws IOException {
        outputHead(dest);
        for (BEREncoding contentElement : contentElements) {
            contentElement.output(dest);
        }
    }

    /**
     * This method returns the number of BER encoded elements that this
     * object is made up of to be returned.
     * @return integer
     */
    public int numberComponents() {
        return contentElements.length;
    }

    /**
     * This method allows the elements of the BER encoding to be examined.
     *
     * @param index - the index of the BER object required,
     *              it must be in the range, [0, numberComponents() - 1]
     * @return BER encoding
     */
    public BEREncoding elementAt(int index) {
        return contentElements[index];
    }

    /**
     * Returns a new String object representing this ASN.1 object's value.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        switch (iTagType) {
            case BEREncoding.UNIVERSAL_TAG:
                str.append("UNIVERSAL ");
                break;
            case BEREncoding.APPLICATION_TAG:
                str.append("APPLICATION ");
                break;
            case BEREncoding.CONTEXT_SPECIFIC_TAG:
                str.append("CONTEXT SPECIFIC ");
                break;
            case BEREncoding.PRIVATE_TAG:
                str.append("PRIVATE ");
                break;
            default:
                break;
        }
        str.append(String.valueOf(iTag)).append("]{");
        for (int x = 0; x < contentElements.length; x++) {
            if (x != 0) {
                str.append(',');
            }
            str.append(contentElements[x].toString());
        }
        str.append('}');
        return new String(str);
    }

    /**
     * This protected method is used to implement the "get_encoding" method.
     */
    @Override
    protected int iEncodingGet(int offset, byte[] data) {
        int i = iGetHead(offset, data);
        for (BEREncoding contentElement : contentElements) {
            i = contentElement.iEncodingGet(i, data);
        }
        return i;
    }
}
