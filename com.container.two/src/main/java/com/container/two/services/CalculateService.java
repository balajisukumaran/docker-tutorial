package com.container.two.services;

import com.container.two.dtos.FileInfo;
import com.container.two.dtos.Product;
import com.container.two.dtos.SumInfo;

import java.io.File;
import java.util.Scanner;

public class CalculateService {
    public SumInfo calculateSum(FileInfo fileInfo) {
        int sum = 0;
        try {
            File file = new File("/usr/src/container2/"+fileInfo.file());
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] content = line.split(",");
                Product product = new Product(content[0].trim(), Integer.parseInt(content[1].trim()));

                if (product.name().equals(fileInfo.product())) {
                    sum += product.value();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred.");
        }
        return new SumInfo(fileInfo.file(), sum);
    }
}
