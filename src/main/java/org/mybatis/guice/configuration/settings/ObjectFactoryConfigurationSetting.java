/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.guice.configuration.settings;

import com.google.inject.Injector;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.Configuration;

public class ObjectFactoryConfigurationSetting implements Provider<ConfigurationSetting> {

  @Inject
  private Injector injector;

  private final Class<? extends ObjectFactory> objectFactoryType;

  public ObjectFactoryConfigurationSetting(Class<? extends ObjectFactory> objectFactoryType) {
    this.objectFactoryType = objectFactoryType;
  }

  public void setInjector(final Injector injector) {
    this.injector = injector;
  }

  @Override
  public ConfigurationSetting get() {
    final ObjectFactory objectFactory = injector.getInstance(objectFactoryType);
    return new ConfigurationSetting() {
      @Override
      public void applyConfigurationSetting(Configuration configuration) {
        configuration.setObjectFactory(objectFactory);
      }
    };
  }

}
