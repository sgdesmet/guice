/*
 *    Copyright 2009-2022 the original author or authors.
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

import static org.mockito.Mockito.verify;

import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CacheEnabledConfigurationSettingTest {
  @Mock
  private Configuration configuration;

  @Test
  public void applyConfigurationSetting_True() {
    CacheEnabledConfigurationSetting setting = new CacheEnabledConfigurationSetting(true);
    setting.applyConfigurationSetting(configuration);
    verify(configuration).setCacheEnabled(true);
  }

  @Test
  public void applyConfigurationSetting_False() {
    CacheEnabledConfigurationSetting setting = new CacheEnabledConfigurationSetting(false);
    setting.applyConfigurationSetting(configuration);
    verify(configuration).setCacheEnabled(false);
  }
}
