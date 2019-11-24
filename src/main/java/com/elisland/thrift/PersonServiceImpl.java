package com.elisland.thrift;

import org.apache.thrift.TException;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/15
 * @Description:
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByName(String name) throws DataException, TException {
        System.out.println("name=>"+name);
        Person person = new Person();
        person.setAge((short)23);
        person.setName("jerry");
        person.setIsStudent(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("person=>"+person);
    }
}
