package com.csvmodifier;

import com.csvmodifier.model.CSVRecord;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVFileModifier {
    public static void main(String[] args) throws Exception
    {
        List<CSVRecord> list = mapCSVToJava("E:\\ReportData.csv");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<CSVRecord> recs = convertToNewListOfRecords(list);
        convertToNewCSV(recs);
        System.out.println("Done!!!");


    }

    private static List<CSVRecord> convertToNewListOfRecords(List<CSVRecord> list) {
        Map<String, Map<String, List<CSVRecord>>> map1 = list.stream()
                .collect(Collectors.groupingBy(p -> p.getDate(),
                        Collectors.groupingBy(p -> p.getCountry())));

        List<CSVRecord> recs = new ArrayList<CSVRecord>();
        map1.forEach((date, csvCountryMap) -> {

            csvCountryMap.forEach((country,csvsList)->{
                CSVRecord csv = new CSVRecord();
                csv.setDate(date);
                csv.setCountry(country);
                int sumClicks = csvsList.stream()
                        .collect(Collectors.summingInt(CSVRecord::getClicks));
                csv.setclicks(sumClicks);
                int sumImps = csvsList.stream()
                        .collect(Collectors.summingInt(CSVRecord::getImpressions));
                csv.setImpressions(sumImps);
                double sumRevs = csvsList.stream()
                        .collect(Collectors.summingDouble(CSVRecord::getRevenue));
                csv.setRevenue(sumRevs);
                recs.add(csv);
            });

        });
        return recs;
    }

    private static void convertToNewCSV(List<CSVRecord> recs) throws IOException {
        File file = new File("E:\\ReportNewData.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("Date,Country,Impressions,Clicks,Revenue");
        bw.newLine();
        for(int i=0;i<recs.size();i++)
        {
            CSVRecord rec = recs.get(i);
            bw.write(rec.getDate()+","+rec.getCountry()+","+rec.getImpressions()+","+rec.getClicks()+","+rec.getRevenue());
            bw.newLine();
        }
        bw.newLine();

        bw.close();
        fw.close();
    }



    private  static List<CSVRecord> mapCSVToJava(String filePath) {
        List<CSVRecord> empList = new ArrayList<CSVRecord>();

        try {
            File inputF = new File(filePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

// Skip the header since its just coloumn name in table and in CSV file but not the data.
            empList = br.lines().skip(1).map(csv2EmpObj).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return empList;
    }


    private static Function<String, CSVRecord> csv2EmpObj = (line) -> {
        String[] record = line.split(",");// This can be delimiter which
        CSVRecord csvRecord = new CSVRecord();
        if (record[0] != null && record[0].trim().length() > 0) {
            csvRecord.setClient(record[0]);
        }
        if (record[1] != null && record[1].trim().length() > 0) {
         /*   Date date1= null;
            try {
                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(record[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
            csvRecord.setDate(record[1]);
        }
        if (record[2] != null && record[2].trim().length() > 0) {
            csvRecord.setMediaSize(record[2]);
        }
        if (record[3] != null && record[3].trim().length() > 0) {
            csvRecord.setCountry(record[3]);
        }
        if (record[4] != null && record[4].trim().length() > 0) {
            csvRecord.setImpressions(new Integer(record[4]).intValue());
        }
        if (record[5] != null && record[5].trim().length() > 0) {
            csvRecord.setclicks(new Integer(record[5]).intValue());
        }
        if (record[6] != null && record[6].trim().length() > 0) {
            csvRecord.setRevenue(new Double(record[6]).doubleValue());
        }
        return csvRecord;
    };
}
