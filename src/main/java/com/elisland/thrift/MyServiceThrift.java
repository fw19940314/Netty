package com.elisland.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.*;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/15
 * @Description:
 */
public class MyServiceThrift {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> personServiceProcessor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());//（协议）压缩
        arg.transportFactory(new TFramedTransport.Factory());//传输
        arg.processorFactory(new TProcessorFactory(personServiceProcessor));
        //启动
        TServer tHsHaServer = new THsHaServer(arg);
        System.out.println("thrift Service started");
        tHsHaServer.serve();
    }
}
