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
package org.mybatis.guice.jta.simple;

import jakarta.inject.Inject;

import java.util.List;

import org.mybatis.guice.transactional.Transactional;
import org.mybatis.guice.transactional.Transactional.TxType;

public class Schema1Service {
  @Inject
  private Schema1Mapper mapper;

  @Transactional
  public void createSchema1() {
    mapper.createSchema1Step1();
    mapper.createSchema1Step2();
    mapper.createSchema1Step3();
  }

  @Transactional
  public List<Name> getAllNames() {
    return mapper.getAllNames();
  }

  @Transactional
  public int insertName(Name name) {
    return mapper.insertName(name);
  }

  @Transactional(TxType.REQUIRES_NEW)
  public int insertNameWithNewTransaction(Name name) {
    return mapper.insertName(name);
  }
}
