package com.telecom.aiops.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Forwards all non-API, non-static-file routes back to index.html
 * so Angular's client-side router can handle them (e.g. direct
 * navigation or refresh on /kpi-intelligence, /risk-prediction, etc.).
 */
@Controller
public class SpaRoutingConfig {

    @RequestMapping(value = {
            "/{path:^(?!api|.*\\.).*$}",
            "/{path:^(?!api|.*\\.).*$}/**"
    })
    public String forward() {
        return "forward:/index.html";
    }
}