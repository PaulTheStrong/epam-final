package com.epam.web.tag;

import org.apache.taglibs.standard.tag.el.fmt.BundleTag;
import org.apache.taglibs.standard.tag.el.fmt.MessageTag;

import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class ErrorTag extends SimpleTagSupport {

    private String errorName;
    private LocalizationContext bundle;

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public LocalizationContext getBundle() {
        return bundle;
    }

    public void setBundle(LocalizationContext bundle) {
        this.bundle = bundle;
    }

    @Override
    public void doTag() throws IOException, JspException {
        JspContext jspContext = getJspContext();
        JspWriter out = jspContext.getOut();
        if (errorName != null && !errorName.equals("")) {
            MessageTag messageTag = new MessageTag();
            messageTag.setBundle(bundle.getResourceBundle().getBaseBundleName());
            messageTag.setKey(errorName);
            out.println("<div class=\"error-box\">");
            messageTag.doStartTag();
            out.println("</div>");
        }
    }
}
//