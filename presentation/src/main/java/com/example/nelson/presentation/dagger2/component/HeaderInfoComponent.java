package com.example.nelson.presentation.dagger2.component;

import com.example.nelson.presentation.dagger2.module.DataModule;
import com.example.nelson.presentation.dagger2.module.DomainModule;
import com.example.nelson.presentation.dagger2.module.PresentationModule;

import dagger.Component;

/**
 * Created by Nelson on 04/09/2016.
 */
@Component(modules = {PresentationModule.class, DomainModule.class, DataModule.class})
public class HeaderInfoComponent {
}
