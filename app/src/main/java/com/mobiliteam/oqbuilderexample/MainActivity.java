package com.mobiliteam.oqbuilderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mobiliteam.oqbuilder.OQBuilder;
import com.mobiliteam.oqbuilder.OQCount;
import com.mobiliteam.oqbuilder.OQDateTime;
import com.mobiliteam.oqbuilder.OQExpand;
import com.mobiliteam.oqbuilder.OQFilterEqu;
import com.mobiliteam.oqbuilder.OQFilterExp;
import com.mobiliteam.oqbuilder.OQFilterInfo;
import com.mobiliteam.oqbuilder.OQFilterType;
import com.mobiliteam.oqbuilder.OQFunction;
import com.mobiliteam.oqbuilder.OQOrder;
import com.mobiliteam.oqbuilder.OQOrderType;
import com.mobiliteam.oqbuilder.OQPropertyEx;

public class MainActivity extends AppCompatActivity {

    private final String BASEURL = "https://ideauat.mobiliteam.in/odata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getCount();
//        getContainsUrl();
//        getEndsWithUrl();
//        getStartsWithUrl();
//        getStringLengthUrl();
//        getStringToLowerUrl();
//        getStringToUpperUrl();
//        getStringTrimUrl();
//        getIndexOfUrl();
//        getSubStringUrl();
//        getYearComparisonUrl();
//        getMonthComparisonUrl();
//        getDayComparisonUrl();
//        getHourComparisonUrl();
//        getMinuteComparisonUrl();
//        getSecondComparisonUrl();
//        getFractionalSecondComparisonUrl();
//        getComparisonWithCurrentTimeUrl();
//        getOffsetTimeComparisonUrl();
        getComparisonWithMinDateTimeUrl();
        multipleFilterInExpandCheck();
    }

    private void demo() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("username", OQFilterEqu.eq, "SV0466")))
                .expand(new OQExpand("branchentity"));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("branchentityID", OQFilterEqu.eq, 111104)));

        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("branchentityID", OQFilterEqu.eq, 111104)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("treeleft", OQFilterEqu.ge, 0)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("treeleft", OQFilterEqu.le, 0)));

        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("branchentityID", OQFilterEqu.eq, 111104)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("treeleft", OQFilterEqu.le, 0)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("treeleft", OQFilterEqu.ge, 0)));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("BranchVillage")
                .filter(new OQFilterInfo(new OQFilterExp("branchentityID", OQFilterEqu.eq, 111104)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("treeleft", OQFilterEqu.le, 0)))
                .expand(new OQExpand("villageentity", new OQExpand("subdistrictentity",
                        new OQExpand("districtentity", new OQExpand("stateentity")))));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("VillageAssignment")
                .filter(new OQFilterInfo(new OQFilterExp("assignedto/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("assignedto/treeright", OQFilterEqu.le, 27)));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("VillageSurvey")
                .filter(new OQFilterInfo(new OQFilterExp("assignedto/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("assignedto/treeright", OQFilterEqu.le, 27)))
                .expand(new OQExpand("villageirrigationsources", "villagecrops"));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("Prospect")
                .filter(new OQFilterInfo(new OQFilterExp("centerentity/sfoentity/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("centerentity/sfoentity/treeright", OQFilterEqu.le, 27)))
                .expand(new OQExpand(new String[]{"prospectattachments", "creditchecks"}, new OQExpand("creditcheckdetails")));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("CustomerApplication")
                .select("id", "customername", "documentreferenceno", "idno", "yearsofstay", "address1", "address2", "landmark", "nomineename", "aadharno", "applicationno", "tenure", "omnicustomercode", "interestapplicable")
                .filter(new OQFilterInfo(new OQFilterExp("sfoentity/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("sfoentity/treeright", OQFilterEqu.le, 27)))
                .expand(new OQExpand(new String[]{"familydetails", "bankdetails", "mediclaims"}, new OQExpand("dependantdetails")));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("CustomerApplication")
                .select("id", "customername", "documentreferenceno", "idno", "yearsofstay", "address1", "address2", "landmark", "nomineename", "aadharno", "applicationno", "tenure", "omnicustomercode", "interestapplicable")
                .filter(new OQFilterInfo(new OQFilterExp("sfoentity/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("sfoentity/treeright", OQFilterEqu.le, 27)))
                .expand(new OQExpand(new String[]{"familydetails", "bankdetails", "mediclaims"}, new OQExpand("dependantdetails")))
                .setSkip(0)
                .setTop(100);
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("CustomerApplication")
                .select("id", "customername", "documentreferenceno", "idno", "yearsofstay", "address1", "address2", "landmark", "nomineename", "aadharno", "applicationno", "tenure", "omnicustomercode", "interestapplicable")
                .filter(new OQFilterInfo(new OQFilterExp("sfoentity/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("sfoentity/treeright", OQFilterEqu.le, 27)))
                .expand(new OQExpand(new String[]{"familydetails", "bankdetails", "mediclaims"}, new OQExpand("dependantdetails")))
                .order(new OQOrder("id", OQOrderType.DESC), new OQOrder("prnnumber", OQOrderType.ASC))
                .setSkip(0)
                .setTop(100);
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("CustomerApplication")
                .setId(123)
                .filter(new OQFilterInfo(new OQFilterExp("sfoentity/treeleft", OQFilterEqu.ge, 16)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("sfoentity/treeright", OQFilterEqu.le, 27)))
                .expand(new OQExpand(new String[]{"familydetails", "bankdetails", "mediclaims"}, new OQExpand("dependantdetails")))
                .order(new OQOrder("id", OQOrderType.DESC));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("CustomerApplication")
                .setId(123)
                .filter(new OQFilterInfo(new OQFilterExp("sfoentity/treeleft", OQFilterEqu.ge, 16)))
                .paginate(200);
        display(oqBuilder);

        for (int i = 0; i < 5; i++) {
            oqBuilder.nextPage();
            display(oqBuilder);
        }

    }

    private void getCount() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .getCount(new OQCount())
                .filter(new OQFilterInfo(new OQFilterExp("username", OQFilterEqu.eq, "7")))
                .expand(new OQExpand("branchentity"));
        display(oqBuilder);

        oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .getCount(new OQCount());
        display(oqBuilder);
    }

    private void display(OQBuilder builder) {
        Log.i(this.getClass().getSimpleName(), builder.build());
    }

    private void multipleFilterInExpandCheck() {

        OQExpand attendanceUsersExpand = new OQExpand("AttendanceUsers");
        attendanceUsersExpand.setSelect("CreatedOn", "ModifiedOn");
        attendanceUsersExpand.setFilters(new OQFilterInfo(new OQFilterExp("CreatedOn", OQFilterEqu.ge, "2018-01-01T00:00:00Z", true)),
                new OQFilterInfo(OQFilterType.And, new OQFilterExp("CreatedOn", OQFilterEqu.le, "2018-01-15T23:59:59Z", true)),
                new OQFilterInfo(OQFilterType.And, new OQFilterExp("IsDeleted", OQFilterEqu.eq, false)));

        OQExpand leaveUsersExapnd = new OQExpand("LeaveUsers");
        leaveUsersExapnd.setSelect("LeaveFrom", "LeaveTo");
        leaveUsersExapnd.setFilters(new OQFilterInfo(new OQFilterExp("LeaveFrom", OQFilterEqu.ge, "2018-01-01T00:00:00Z", true)),
                new OQFilterInfo(OQFilterType.And, new OQFilterExp("LeaveTo", OQFilterEqu.le, "2018-01-15T23:59:59Z", true)),
                new OQFilterInfo(OQFilterType.And, new OQFilterExp("IsDeleted", OQFilterEqu.eq, false)));

        OQBuilder builder = new OQBuilder(BASEURL)
                .setEntity("User")
                .setId(1)
                .select("ID")
                .expand(attendanceUsersExpand, leaveUsersExapnd);
        Log.i(this.getClass().getSimpleName(), builder.encode());

    }

    public void getContainsUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("username", OQFilterEqu.contains, "7")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    public void getEndsWithUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("username", OQFilterEqu.endswith, "7")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    public void getStartsWithUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("username", OQFilterEqu.startswith, "7")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    public void getStringLengthUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.length, "username"), OQFilterEqu.eq, 2)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    public void getStringToLowerUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.tolower, "designation"), OQFilterEqu.eq, "sfo")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getStringToUpperUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.toupper, "designation"), OQFilterEqu.eq, "SBM")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getStringTrimUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.trim, "designation"), OQFilterEqu.eq, "SBM")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getIndexOfUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.indexof, "designation", "SF"), OQFilterEqu.eq, 0)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getSubStringUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.substring, "designation", 1), OQFilterEqu.eq, "FO")),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getYearComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.year, "CreatedOn"), OQFilterEqu.eq, 2018)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getMonthComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.month, "CreatedOn"), OQFilterEqu.eq, 6)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getDayComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.day, "CreatedOn"), OQFilterEqu.eq, 15)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getHourComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.hour, "CreatedOn"), OQFilterEqu.eq, 11)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getMinuteComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.minute, "CreatedOn"), OQFilterEqu.eq, 11)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getSecondComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.second, "CreatedOn"), OQFilterEqu.eq, 11)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getFractionalSecondComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.fractionalseconds, "CreatedOn"), OQFilterEqu.eq, 11)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getComparisonWithCurrentTimeUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("CreatedOn", OQFilterEqu.lt, OQDateTime.now)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getOffsetTimeComparisonUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp(new OQPropertyEx(OQFunction.totaloffsetminutes, "CreatedOn"), OQFilterEqu.eq, 60)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }

    private void getComparisonWithMinDateTimeUrl() {
        OQBuilder oqBuilder = new OQBuilder(BASEURL)
                .setEntity("User")
                .filter(new OQFilterInfo(new OQFilterExp("CreatedOn", OQFilterEqu.lt, OQDateTime.mindatetime)),
                        new OQFilterInfo(OQFilterType.And, new OQFilterExp("isadministrator", OQFilterEqu.eq, false)));
        display(oqBuilder);
    }
}
