package org.example.init;

import org.example.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTableInit {

    private HashTableInit() {}

    public static Map<String, String> hashTableInit(List<String> contactList) {
        Map<String, String> contactmap = new HashMap<>();
        for (String contactInfo : contactList) {
            String contactName = StringUtil.getContactNameInfo(contactInfo);
            contactmap.put(contactName, contactInfo);
        }
        return contactmap;
    }
}
