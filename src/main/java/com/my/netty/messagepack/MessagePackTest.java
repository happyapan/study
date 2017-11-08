package com.my.netty.messagepack;


import org.apache.xalan.templates.TemplateList;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

public class MessagePackTest {

    public static void main(String[] args) throws Exception {
        List<String> names = new ArrayList<String>();
        names.add("Jeff");
        names.add("Kuffy");

        MessagePack messagePack = new MessagePack();

        byte[] vals = messagePack.write(names);

        List<String> results = messagePack.read(vals, Templates.tList(Templates.TString));
        for (String one : results) {
            System.out.println(one);
        }

    }

}
