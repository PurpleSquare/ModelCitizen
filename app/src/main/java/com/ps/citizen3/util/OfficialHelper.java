package com.ps.citizen3.util;

import com.ps.citizen3.data.api.GoogleCivicModel.Office;
import com.ps.citizen3.data.api.GoogleCivicModel.Official;
import com.ps.citizen3.data.api.GoogleCivicModel.RepResult;

import java.util.ArrayList;
import java.util.List;

public class OfficialHelper {

    public static List<String> getOfficeInfo(String name) {
        List<String> officeInfo = new ArrayList<>();

        name = name.replace(",","");

        if (name.equals("President of the United States")) {
            officeInfo.add("President");
        } else if (name.equals("Vice-President of the United States")) {
            officeInfo.add("Vice-President");
        } else if (name.equals("United States Senate")) {
            officeInfo.add("Senate");
        } else if (name.contains("United States House of Representatives")) {
            officeInfo.add("House of Representatives");
            officeInfo.add(name.substring(name.indexOf('-') - 2));
        } else if (name.contains("District") && name.indexOf("District") > 0) {
            int i = name.indexOf("District");
            officeInfo.add(name.substring(0,i - 1));
            officeInfo.add(name.substring(i));
        } else {
            officeInfo.add(name);
        }

        return officeInfo;
    }

    public static  void setOfficesForOfficials(RepResult result) {
        int i = 0;
        for (Official official : result.getOfficials()) {
            for (Office office : result.getOffices()) {
                if (office.getOfficialIndices().contains(i)) {
                    official.setOffice(office);
                }
            }
            i++;
        }
    }

    public static  List<Official> orderOfficials(RepResult result) {
        List<Official> newList = new ArrayList<>();

        newList.addAll(getOfficialsInOffice(result, "President of the United States", true));
        newList.addAll(getOfficialsInOffice(result, "Vice-President of the United States", true));
        newList.addAll(getOfficialsInOffice(result, "United States Senate", true));
        newList.addAll(getOfficialsInOffice(result, "United States House of Representatives", false));
        newList.addAll(getOfficialsInOffice(result, "Governor", true));
        newList.addAll(getOfficialsInOffice(result, "Lieutenant Governor", true));
        newList.addAll(getOfficialsInOffice(result, "State Senate", false));
        newList.addAll(getOfficialsInOffice(result, "State Assembly", false));

        for (Official official : result.getOfficials()) {
            if (!newList.contains(official)) {
                newList.add(official);
            }
        }

        return newList;
    }

    private static List<Official> getOfficialsInOffice(RepResult result, String title, boolean exact) {
        List<Official> newList = new ArrayList<>();
        for (Official official : result.getOfficials()) {

            if (exact) {
                if (official.getOffice().getName().equals(title)) {
                    newList.add(official);
                }
            } else {
                if (official.getOffice().getName().contains(title)) {
                    newList.add(official);
                }
            }
        }
        return newList;
    }
}
