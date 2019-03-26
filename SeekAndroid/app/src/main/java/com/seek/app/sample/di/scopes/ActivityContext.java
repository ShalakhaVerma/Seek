package com.seek.app.sample.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by vermas12 on 25/3/19.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
