package com.jelmerpijnappel.jelmerqa.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A filter for filtering out harmful XSS code.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class XSSFilter implements Filter {

    /**
     *
     * @param request The current HTTP request.
     * @param response The current HTTP response.
     * @param chain The current filter chain.
     * @throws IOException if thrown by the Filter.
     * @throws ServletException if thrown by the Filters.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        XSSRequestWrapper wrappedRequest = new XSSRequestWrapper((HttpServletRequest) request);
        ServletInputStream inputStream = wrappedRequest.getInputStream();
        String body = "";
        if(inputStream != null) {
            body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
        if (!body.equals("")) {
            String unstrippedBody = body.replace("\n", "");
            log.debug("Unstripped body: " + unstrippedBody);
            String strippedBody = XSSUtils.stripXSS(body);
            log.debug("Stripped body to: " + strippedBody);
            wrappedRequest.setRequestBody(strippedBody.getBytes());
        }
        chain.doFilter(wrappedRequest, response);
    }
}
