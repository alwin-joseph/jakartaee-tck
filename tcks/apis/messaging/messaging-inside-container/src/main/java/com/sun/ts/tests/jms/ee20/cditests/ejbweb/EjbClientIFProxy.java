package com.sun.ts.tests.jms.ee20.cditests.ejbweb;

import com.sun.ts.lib.harness.RemoteStatus;
import com.sun.ts.lib.util.TestUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class EjbClientIFProxy implements InvocationHandler {

    private Properties testProps;

    public static EjbClientIF newInstance(Properties testProps) {
        return (EjbClientIF) Proxy.newProxyInstance(
            EjbClientIF.class.getClassLoader(),
                new Class[] { EjbClientIF.class },
                new EjbClientIFProxy(testProps));
    }

    private  EjbClientIFProxy(Properties testProps) {
        this.testProps = testProps;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

        boolean ok = false;
        if(methodName.equals("init")) {
            //sendPost();
            ok = true;
        } else if(methodName.equals("removeTestData")) {
            RemoteStatus status = sendGet("removeTestData");
            ok = true;
        } else if(methodName.equals("toString")) {
            return Client.class.getName()+"-proxy";
        } else {
            RemoteStatus status = sendGet(methodName);
            ok = status.toStatus().isPassed();
        }

        return ok;
    }

    private RemoteStatus sendGet(String methodName) throws Exception {
        String host = TestUtil.getProperty(testProps, "webServerHost", "localhost");
        String port = TestUtil.getProperty(testProps, "webServerPort", "8080");

        URI uri = URI.create("http://"+host + ":" + port + "/appclient_novehicle/?test=" + methodName);
        System.out.println("sendGet().uri: " + uri);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<byte[]> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofByteArray());
        System.out.println("sendGet() response: " + response.statusCode());
        System.out.println("sendGet() response.headers: " + response.headers());
        if (response.statusCode() != 200) {
            String msg = String.format("Failed to send %s(), status code %d",
                    methodName, response.statusCode());
            throw new IOException(msg);
        }
        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(response.body()))) {
            RemoteStatus status = (RemoteStatus) ois.readObject();
            return status;
        }
    }
}
