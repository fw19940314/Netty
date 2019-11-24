package com.elisland.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/15
 * @Description:
 */
public class MyClientThrift {
    public static void main(String[] args) {
        TTransport tFramedTransport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol tCompactProtocol = new TCompactProtocol(tFramedTransport);
        PersonService.Client client = new PersonService.Client(tCompactProtocol);
        try {
            System.out.println("888888888");
            tFramedTransport.open();
            Person person = client.getPersonByName("jerry");
            System.out.println(person);
            System.out.println("---------------");

            Person person1 = new Person();
            person1.setAge((short) 12);
            person1.setName("xiaoming");
            person1.setIsStudentIsSet(true);
            client.savePerson(person1);

        }catch (Exception e){

        }finally {
            tFramedTransport.close();
        }
    }
}
