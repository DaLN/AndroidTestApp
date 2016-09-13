package com.example.nelson.presentation.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Dagger annotation
 * Custom scope for fragment
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {}
