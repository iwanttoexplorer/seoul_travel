package com.pcwk.tvl.review;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

public class ReviewFormController implements ControllerV {

    @Override
    public JView doWork(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return new JView("/WEB02/project/review-form.jsp");
    }
}
