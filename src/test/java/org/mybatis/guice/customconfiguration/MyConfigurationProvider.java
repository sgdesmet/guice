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
package org.mybatis.guice.customconfiguration;

import jakarta.inject.Inject;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.mybatis.guice.configuration.ConfigurationProvider;

public class MyConfigurationProvider extends ConfigurationProvider {

  /**
   * @param environment
   *
   * @since 1.0.1
   */
  @Inject
  public MyConfigurationProvider(Environment environment) {
    super(environment);
  }

  @Override
  protected Configuration newConfiguration(Environment environment) {
    return new MyConfiguration(environment);
  }
}
