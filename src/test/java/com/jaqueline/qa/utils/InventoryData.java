package com.jaqueline.qa.utils;

import lombok.Data;

@Data
public class InventoryData {
    private String alias;
    private int expectedProductCount;
    private String productNameToCheck;
}
