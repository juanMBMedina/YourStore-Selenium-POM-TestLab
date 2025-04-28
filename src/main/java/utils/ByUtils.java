package utils;

import org.openqa.selenium.By;

public class ByUtils {

    public static By combineXpathDescendant(By parent, By child) {
        String parentXpath = extractXpath(parent);
        String childXpath = extractXpath(child);

        if (childXpath.startsWith("//")) {
            childXpath = childXpath.substring(2);
        }

        String combinedXpath = parentXpath + "//" + childXpath;
        return By.xpath(combinedXpath);
    }

    private static String extractXpath(By by) {
        String locatorAsString = by.toString();
        if (!locatorAsString.startsWith("By.xpath: ")) {
            throw new IllegalArgumentException("Only By.xpath is supported currently");
        }
        return locatorAsString.replace("By.xpath: ", "");
    }
}

