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
package org.mybatis.guice;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GuiceTestExtension.class)
final class MyBatisModuleTestCaseTest extends AbstractMyBatisModuleTestCase {

  @Inject
  @Named("contactWithAddress")
  private Contact contactWithAdress;

  @Inject
  private ContactMapperClient contactMapperClient;

  @Inject
  private AddressConverter addressConverter;

  @Test
  void testAddressConverter() throws Exception {
    Address address = new Address();
    address.setNumber(1234);
    address.setStreet("Elm street");
    assert "1234 Elm street".equals(addressConverter.convert(address));
    assert address.equals(addressConverter.convert("1234 Elm street"));
  }

  @Test
  void insertContactWithAddress() throws Exception {
    Address address = new Address();
    address.setNumber(1234);
    address.setStreet("Elm street");
    this.contactWithAdress.setAddress(address);
    this.contactMapperClient.insert(this.contactWithAdress);
  }

  @Test
  void selectContactWithAddress() throws Exception {
    Contact contact = this.contactMapperClient.selectById(this.contactWithAdress.getId());
    assert contact != null : "impossible to retrieve Contact with id '" + this.contactWithAdress.getId() + "'";
    assert this.contactWithAdress.equals(contact) : "Expected " + this.contactWithAdress + " but found " + contact;
  }

  @Test
  void selectAllContactsWithDatabaseId() throws Exception {
    List<Contact> contacts = this.contactMapperClient.getAllWithDatabaseId();
    assert contacts.size() > 0 : "Expected not empty contact table";
  }

}
