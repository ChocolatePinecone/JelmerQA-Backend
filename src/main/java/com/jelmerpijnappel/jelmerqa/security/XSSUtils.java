package com.jelmerpijnappel.jelmerqa.security;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;

/**
 * A class filled with utility functions regarding XSS protection.
 */
public class XSSUtils {

    /**
     * Strips a given string of dangerous XSS threats.
     * @param value The string that needs to be stripped of possible XSS threats.
     * @return The previously provided string input, but stripped of any XSS threats.
     */
    public static String stripXSS(String value) {
        if (value == null) {
            return null;
        }
        value = ESAPI.encoder()
                .canonicalize(value)
                .replaceAll("\0", "");
        return Jsoup.clean(value, Whitelist.none());
    }
}
