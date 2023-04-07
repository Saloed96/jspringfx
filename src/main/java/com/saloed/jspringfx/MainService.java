package com.saloed.jspringfx;

import ch.qos.logback.core.testUtil.RandomUtil;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    public String getText() {
        return "Hello From Service " + RandomUtil.getPositiveInt();
    }
}
