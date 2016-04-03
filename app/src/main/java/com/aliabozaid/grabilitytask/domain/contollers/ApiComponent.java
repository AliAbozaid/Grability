package com.aliabozaid.grabilitytask.domain.contollers;

import com.aliabozaid.grabilitytask.presentation.ui.fragments.ListOfProductsFragment;
import com.aliabozaid.grabilitytask.domain.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class,})
public interface ApiComponent {
    void inject(ListOfProductsFragment activity);
}

