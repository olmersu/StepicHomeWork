package com.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by olmer on 12.12.16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatedBy {
    String author();
    String date();
}
