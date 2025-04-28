package utils;

import org.openqa.selenium.By;

public class ByUtils {

    private static final String XPATH_SEPARATOR = "//";

    private ByUtils() {
    }

    public static By combineXpathDescendant(By parent, By child) {
        String parentXpath = extractXpath(parent);
        String childXpath = extractXpath(child);

        if (childXpath.startsWith(XPATH_SEPARATOR)) {
            childXpath = childXpath.substring(2);
        }

        String combinedXpath = parentXpath + XPATH_SEPARATOR + childXpath;
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

