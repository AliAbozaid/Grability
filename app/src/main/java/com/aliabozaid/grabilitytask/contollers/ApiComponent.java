package com.aliabozaid.grabilitytask.contollers;

import com.aliabozaid.grabilitytask.fragments.ListOfProductsFragment;
import com.aliabozaid.grabilitytask.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class,})
public interface ApiComponent {
    void inject(ListOfProductsFragment activity);
}

