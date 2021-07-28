package com.jelmerpijnappel.jelmerqa.security;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * HTTP request wrapper that removes XSS code threats from headers, parameters and body of the original request.
 */
@Slf4j
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody;

    /**
     * Creates the request wrapper around the given request.
     * @param request The request that needs to be wrapped.
     */
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);

        try {
            requestBody = request.getInputStream().readAllBytes();
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Gets the request headers, stripped of any XSS threats.
     * @param name The name of the headers that must be retrieved.
     * @return A collection of headers with the given name, stripped of XSS threats.
     */
    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> result = new ArrayList<>();
        Enumeration<String> headers = super.getHeaders(name);
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            String[] tokens = header.split(",");
            for (String token : tokens) {
                result.add(XSSUtils.stripXSS(token));
            }
        }
        return Collections.enumeration(result);
    }

    /**
     * Gets the request parameters, stripped of any XSS threats.
     * @param parameter The name of the parameters that must be retrieved.
     * @return A collection of parameters with the given name, stripped of XSS threats.
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = XSSUtils.stripXSS(values[i]);
        }
        return encodedValues;
    }

    /**
     * Gets a request parameter, stripped of any XSS threats.
     * @param parameter The name of the parameter that must be retrieved.
     * @return The parameter with the given name, stripped of XSS threats.
     */
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return XSSUtils.stripXSS(value);
    }

    /**
     * Gets the request body as servlet input stream.
     * @return An input stream of the request body.
     */
    @Override
    public ServletInputStream getInputStream() {
        return new CustomServletInputStream(this.requestBody);
    }

    /**
     * Overwrites the request body with the given one.
     * @param bytes A byte array of the body data that must be set.
     */
    public void setRequestBody(byte[] bytes) {
        this.requestBody = bytes;
    }

    /**
     * A custom ServletInputStream serving as wrapper for a ByteArrayInputStream.
     */
    private static class CustomServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream buffer;

        /**
         * Creates the input stream based on an array of bytes.
         * @param contents An array of bytes containing data for the input stream.
         */
        public CustomServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        /**
         * Reads the next byte of data from the input stream.
         * @return The byte value that was read.
         */
        @Override
        public int read() {
            return buffer.read();
        }

        /**
         * Returns true when all the data from the stream has been read else it returns false.
         * @return Boolean indicating if all the data from the stream has been read.
         */
        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        /**
         * Returns true if data can be read without blocking.
         * @return always true.
         */
        @Override
        public boolean isReady() {
            return true;
        }

        /**
         * Instructs the ServletInputStream to invoke the provided ReadListener when it is possible to read.
         * This is currently not implemented, this function will result in an exception when invoked.
         * @param listener The listener that needs to be invoked.
         */
        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("Not implemented");
        }
    }
}


