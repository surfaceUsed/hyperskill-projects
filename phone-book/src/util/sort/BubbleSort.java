package util.sort;

import util.StringUtil;
import java.util.List;

class BubbleSort {

    private BubbleSort() {}

    static void bubbleSort(List<String> contactList) {
        int length = contactList.size();
        for (int i = 0; i < length - 1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < length - 1 - i; j++) {
                int compareIndex = j + 1;
                String contactName = StringUtil.getContactNameInfo(contactList.get(j));
                String compareName = StringUtil.getContactNameInfo(contactList.get(compareIndex));
                if (contactName.compareTo(compareName) > 0) {
                    swap(contactList, j, compareIndex);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }

    private static void swap(List<String> list, int firstIndex, int lastIndex) {
        String temp = list.get(firstIndex);
        list.set(firstIndex, list.get(lastIndex));
        list.set(lastIndex, temp);
    }
}
