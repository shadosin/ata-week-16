package com.kenzie.groupactivity.icecream.dependency;

import com.kenzie.groupactivity.icecream.IceCreamParlorService;

import dagger.Component;

@Component
public interface IceCreamParlorServiceComponent {
    IceCreamParlorService provideIceCreamParlorService();
}
