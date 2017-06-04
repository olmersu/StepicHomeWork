package com.Test.unziper;

/**
 * Created by olmer on 15.04.17.
 */

import com.Test.unziper.system.BaseConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BaseConfig.class);
        Unzipper unzipper = (Unzipper) ctx.getBean(Unzipper.class);
    }

}