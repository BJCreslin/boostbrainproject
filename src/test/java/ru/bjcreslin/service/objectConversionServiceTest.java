package ru.bjcreslin.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;
import ru.bjcreslin.domain.jsonobjects.JSONTrenerObject;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;

import java.util.List;

class objectConversionServiceTest {
    private List<JSONWrapperObject> jsonWrapperObjectList;

    @BeforeEach
    void setUp() throws ErrorParsingTxtJsonToPojo {
        String txt1 = "[\n" +
                "    {\n" +
                "        \"global_id\": 901080390,\n" +
                "        \"Number\": 1,\n" +
                "        \"Cells\": {\n" +
                "            \"global_id\": 901080390,\n" +
                "            \"CoachCommentScore\": null,\n" +
                "            \"Name\": \"Надежда\",\n" +
                "            \"LastName\": \"Абызова\",\n" +
                "            \"MiddleName\": \"Викторовна\",\n" +
                "            \"Gender\": \"женский\",\n" +
                "            \"DateOfBirth\": \"19.01.1973\",\n" +
                "            \"Photo\": null,\n" +
                "            \"Citizenship\": [\n" +
                "                {\n" +
                "                    \"Citizenship\": \"Россия\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"PublicPhone\": [\n" +
                "                {\n" +
                "                    \"PublicPhone\": \"(499) 242-41-61\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Email\": [\n" +
                "                {\n" +
                "                    \"Email\": \"s29@mossport.ru\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"AcademicDegree\": [],\n" +
                "            \"OtherAchievements\": null,\n" +
                "            \"Sport\": [\n" +
                "                {\n" +
                "                    \"SeniorityPeriod\": 27,\n" +
                "                    \"SportName\": \"акробатический рок-н-ролл\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Education\": [],\n" +
                "            \"AdditionalEducation\": [],\n" +
                "            \"Attestation\": [\n" +
                "                {\n" +
                "                    \"AttestationEndDate\": null,\n" +
                "                    \"AvailabilityOfCertification\": \"нет\",\n" +
                "                    \"AttestationBeginDate\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"JobOrganisation\": [\n" +
                "                {\n" +
                "                    \"SportDiscipline\": null,\n" +
                "                    \"JobOrganizationName\": \"ГБУ «СШ № 29 «Хамовники» Москомспорта\",\n" +
                "                    \"JobOrganizationStartDate\": \"01.06.1994\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"CoachCategory\": [\n" +
                "                {\n" +
                "                    \"CoachCategoryEndDate\": null,\n" +
                "                    \"CoachCategoryName\": \"Старший тренер\",\n" +
                "                    \"CoachCategoryStartDate\": \"06.05.2014\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"SportSpecialization\": [\n" +
                "                \"Большой спорт\"\n" +
                "            ],\n" +
                "            \"SportActivityGender\": \"Любой\",\n" +
                "            \"SportActivityMinAge\": 6,\n" +
                "            \"SportActivityMaxAge\": null,\n" +
                "            \"Services\": [],\n" +
                "            \"PersonalAchievement\": null,\n" +
                "            \"Rank\": [],\n" +
                "            \"PupilAchievements\": null,\n" +
                "            \"FederationMembership\": [],\n" +
                "            \"CoachRankPosition\": null,\n" +
                "            \"CoachRankTotal\": null,\n" +
                "            \"CoachRankAverageScore\": null,\n" +
                "            \"CoachComments\": null\n" +
                "        }\n" +
                "    }\n" +
                "]";

        MosRuDataServer mosRuDataServer = new MosRuDataServer();
        TrenerWEBService trenerWEBService = new TrenerWEBService(mosRuDataServer);
        jsonWrapperObjectList = trenerWEBService.textToArrayOfJsonConverter(txt1);
    }

    @Test
    void jSONTrenerObjectToTrener() {
        JSONTrenerObject jsonTrenerObject = objectConversionService.wrapperToTrener(jsonWrapperObjectList.get(0));
        Trener trener = objectConversionService.jSONTrenerObjectToTrener(jsonTrenerObject);
        Assert.assertTrue(trener.getSport().get(0).getSportName().equalsIgnoreCase("акробатический рок-н-ролл"));
    }

    @Test
    void wrapperToTrener() {
        JSONTrenerObject jsonTrenerObject = objectConversionService.wrapperToTrener(jsonWrapperObjectList.get(0));
        Assert.assertTrue(jsonTrenerObject.getName().equalsIgnoreCase("Надежда"));
    }

    @Test
    void listJSONWrapperToTrenerList() {
        List<Trener> list = objectConversionService.listJSONWrapperToTrenerList(jsonWrapperObjectList);
        Assert.assertEquals(1, list.size());
    }
}